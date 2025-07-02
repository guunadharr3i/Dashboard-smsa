package com.smsaDashBoard.Smsa.Service;

import com.SMSAAPis.SMSAAPI.DTO.SwiftRequestPojo;
import com.smsaDashBoard.Smsa.CustomExceptions.SwiftMessageException;
import com.smsaDashBoard.Smsa.DTO.SwiftMessageDashboardStats;
import com.smsaDashBoard.Smsa.Entity.SwiftMessageHeader;
import com.smsaDashBoard.Smsa.Repo.SwiftMessageHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;

@Service
public class SwiftMessageServiceImpl implements SwiftMessageService {
    
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SwiftMessageServiceImpl.class);
    
    @Autowired
    private SwiftMessageHeaderRepository repository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Cacheable(value = "swiftMessages", key = "'filter_'+#filter.transactionRef")
    public List<SwiftMessageHeader> getFilteredMessages(SwiftRequestPojo filter) {
        logger.info("Fetching filtered Swift messages for filter: {}", filter);
        
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<SwiftMessageHeader> query = cb.createQuery(SwiftMessageHeader.class);
            Root<SwiftMessageHeader> root = query.from(SwiftMessageHeader.class);
            
            List<Predicate> predicates = new ArrayList<>();
            
            if (filter.getTransactionRef() != null && !filter.getTransactionRef().isEmpty()) {
                logger.debug("Filtering by transactionRef: {}", filter.getTransactionRef());
                predicates.add(cb.like(root.get("transactionRef"), "%" + escapeLike(filter.getTransactionRef()) + "%"));
            }
            
            if (filter.getSenderBic() != null && !filter.getSenderBic().isEmpty()) {
                logger.debug("Filtering by senderBic: {}", filter.getSenderBic());
                predicates.add(cb.like(root.get("senderBic"), "%" + escapeLike(filter.getSenderBic()) + "%"));
            }
            
            if (filter.getFileDate() != null) {
                logger.debug("Filtering by fileDate: {}", filter.getFileDate());
                predicates.add(cb.equal(root.get("fileDate"), filter.getFileDate()));
            }
            
            if (filter.getMtCode() != null) {
                logger.debug("Filtering by mtCode: {}", filter.getMtCode());
                predicates.add(cb.like(root.get("mtCode"), "%" + escapeLike(filter.getMtCode().toString()) + "%"));
            }
            
            if (filter.getMsgType() != null && !filter.getMsgType().isEmpty()) {
                logger.debug("Filtering by msgType: {}", filter.getMsgType());
                predicates.add(cb.like(root.get("msgType"), "%" + escapeLike(filter.getMsgType()) + "%"));
            }
            
            query.where(cb.and(predicates.toArray(new Predicate[0])));
            query.select(root).distinct(true);
            
            TypedQuery<SwiftMessageHeader> typedQuery = entityManager.createQuery(query);
            List<SwiftMessageHeader> result = typedQuery.getResultList();
            
            logger.info("Filtered message count: {}", result.size());
            return result;
            
        } catch (Exception e) {
            logger.error("Exception occurred while filtering Swift messages", e);
            return new ArrayList<>();
        }
    }
    
    private String escapeLike(String param) {
        return param.replace("\\", "\\\\")
                .replace("_", "\\_")
                .replace("%", "\\%");
    }
    
    public void printStatistics() {
        try {
            logger.info("Printing statistics...");
            
            long total = repository.countAllRecords();
            long inputCount = repository.countInputRefs();
            long outputCount = repository.countOutputRefs();
            List<Object[]> messageTypeStats = repository.countByMessageType();
            
            logger.info("Total Records: {}", total);
            logger.info("Input Refs: {}", inputCount);
            logger.info("Output Refs: {}", outputCount);
            
            for (Object[] row : messageTypeStats) {
                String type = (String) row[0];
                Long count = (Long) row[1];
                logger.info("Message Type [{}] Count: {}", type, count);
            }
        } catch (Exception e) {
            logger.error("Error occurred while printing statistics", e);
        }
    }
    
    @Override
    public SwiftMessageDashboardStats getDashboardStats() {
        logger.info("Fetching dashboard statistics...");
        try {
            SwiftMessageDashboardStats dto = new SwiftMessageDashboardStats();
            
            dto.setTotalSwiftPaymentsCount(repository.countAllRecords());
            dto.setRecivedPayments(repository.countInputRefs());
            dto.setSentPayments(repository.countOutputRefs());
            
            List<Object[]> results = repository.countByMessageType();
            Map<String, Long> typeCounts = new HashMap<>();
            for (Object[] row : results) {
                String type = (String) row[0];
                Long count = (Long) row[1];
                typeCounts.put(type, count);
                logger.debug("Message type: {}, Count: {}", type, count);
            }

            logger.info("Dashboard stats prepared successfully.");
            return dto;
            
        } catch (Exception e) {
            logger.error("Error fetching Swift Message Stats", e);
            throw new SwiftMessageException("Error fetching Swift Message Stats", e);
        }
    }
    
    @Override
    public List<SwiftMessageHeader> getFullData() {
        logger.info("Fetching full Swift message data from database...");
        try {
            List<SwiftMessageHeader> allRecords = repository.findAll();
            logger.info("Total records fetched: {}", allRecords.size());
            return allRecords;
        } catch (Exception e) {
            logger.error("Error fetching full data", e);
            return new ArrayList<>();
        }
    }
    
    public SwiftMessageDashboardStats getMessageCounts() {
        logger.info("Fetching total, received, and sent payment counts...");
        try {
            SwiftMessageDashboardStats dto = new SwiftMessageDashboardStats();
            dto.setRecivedPayments(repository.countInputRefs());
            dto.setSentPayments(repository.countOutputRefs());
            dto.setTotalSwiftPaymentsCount(dto.getRecivedPayments() + dto.getSentPayments());
            return dto;
        } catch (Exception e) {
            logger.error("Error fetching message counts", e);
            throw new SwiftMessageException("Error fetching message counts", e);
        }
    }
    
    @Override
    public Map<String, Long> getMessageTypeCounts() {
        logger.info("Fetching message type counts...");
        Map<String, Long> typeCounts = new HashMap<>();
        try {
            List<Object[]> results = repository.countByMessageType();
            logger.debug("messageTypes size" + results.size());
            for (Object[] row : results) {
                String type = (String) row[0];
                Long count = (Long) row[1];
                
                if (type != null) {
                    typeCounts.put(type, count);
                    logger.debug("Type: {}, Count: {}", type, count);
                } else {
                    logger.warn("Skipping null message type with count: {}", count);
                }
            }
            return typeCounts;
        } catch (Exception e) {
            logger.error("Error fetching message type counts", e);
            throw new SwiftMessageException("Error fetching message type counts", e);
        }
    }
}
