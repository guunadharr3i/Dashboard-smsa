package com.smsaDashBoard.Smsa.Controller;

import com.smsaDashBoard.Smsa.DTO.SwiftMessageDashboardStats;
import com.smsaDashBoard.Smsa.Service.SwiftMessageService;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SwiftMessageStatsController {

    private static final Logger logger = LogManager.getLogger(SwiftMessageStatsController.class);

    @Autowired
    private SwiftMessageService service;

    @GetMapping("/dashBoard")
    public SwiftMessageDashboardStats getSwiftMessageStats() {
        logger.info("Fetching dashboard stats");
        return service.getDashboardStats();
    }

    @GetMapping("/dashboard/counts")
    public ResponseEntity<SwiftMessageDashboardStats> getCounts() {
        return ResponseEntity.ok(service.getMessageCounts());
    }
    @GetMapping("/dashboard/message-type-counts")
public ResponseEntity<Map<String, Long>> getMessageTypeCounts() {
    return ResponseEntity.ok(service.getMessageTypeCounts());
}


    @GetMapping
    public String hello() {
        logger.info("Health check endpoint hit");
        return "Dashboard Application Deployed in Server";
    }
}
