package com.smsaDashBoard.Smsa.Controller;

import com.smsaDashBoard.Smsa.DTO.SwiftMessageDashboardStats;
import com.smsaDashBoard.Smsa.DTO.TokenizedResponse;
import com.smsaDashBoard.Smsa.Service.AuthenticateAPis;
import com.smsaDashBoard.Smsa.Service.SwiftMessageService;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SwiftMessageStatsController {

    private static final Logger logger = LogManager.getLogger(SwiftMessageStatsController.class);

    @Autowired
    private SwiftMessageService service;

    @Autowired
    private AuthenticateAPis authenticateAPi;

    @GetMapping("/dashBoard")
    public ResponseEntity<?> getSwiftMessageStats(@RequestParam Map<String, String> token) {
        String accessToken = authenticateAPi.validateAndRefreshToken(token);
        if (accessToken == null) {
            logger.warn("Token validation failed.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }

        logger.info("Fetching dashboard stats");
        SwiftMessageDashboardStats stats = service.getDashboardStats();
        TokenizedResponse<SwiftMessageDashboardStats> response = new TokenizedResponse<>(accessToken, stats);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/counts")
    public ResponseEntity<?> getCounts(@RequestParam Map<String, String> token) {
        String accessToken = authenticateAPi.validateAndRefreshToken(token);
        if (accessToken == null) {
            logger.warn("Token validation failed.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }

        logger.info("Fetching message counts");
        SwiftMessageDashboardStats counts = service.getMessageCounts();
        TokenizedResponse<SwiftMessageDashboardStats> response = new TokenizedResponse<>(accessToken, counts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/message-type-counts")
    public ResponseEntity<?> getMessageTypeCounts(@RequestParam Map<String, String> token) {
        String accessToken = authenticateAPi.validateAndRefreshToken(token);
        if (accessToken == null) {
            logger.warn("Token validation failed.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }

        logger.info("Fetching message type counts");
        Map<String, Long> typeCounts = service.getMessageTypeCounts();
        TokenizedResponse<Map<String, Long>> response = new TokenizedResponse<>(accessToken, typeCounts);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String hello() {
        logger.info("Health check endpoint hit");
        return "Dashboard Application Deployed in Server";
    }
}
