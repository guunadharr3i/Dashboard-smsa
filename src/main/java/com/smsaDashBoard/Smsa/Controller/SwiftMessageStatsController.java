package com.smsaDashBoard.Smsa.Controller;

import com.smsaDashBoard.Smsa.DTO.DashBoardApiResponseWrapper;
import com.smsaDashBoard.Smsa.DTO.SwiftMessageDashboardStats;
import com.smsaDashBoard.Smsa.DTO.TokenizedResponse;
import com.smsaDashBoard.Smsa.Enums.ApiResponseCodes;
import com.smsaDashBoard.Smsa.Service.AuthenticateAPis;
import com.smsaDashBoard.Smsa.Service.SwiftMessageService;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class SwiftMessageStatsController {

    private static final Logger logger = LogManager.getLogger(SwiftMessageStatsController.class);

    @Autowired
    private SwiftMessageService service;

    @Autowired
    private AuthenticateAPis authenticateAPi;

    @GetMapping("/counts")
    public ResponseEntity<DashBoardApiResponseWrapper<?>> getCounts(@RequestParam Map<String, String> token) {
        logger.info("Request received for /counts");

        String accessToken = authenticateAPi.validateAndRefreshToken(token);
        if (accessToken == null) {
            logger.warn("Token validation failed.");
            return ResponseEntity
                    .status(ApiResponseCodes.UNAUTHORIZED.getHttpStatus())
                    .body(new DashBoardApiResponseWrapper<>(ApiResponseCodes.UNAUTHORIZED, "Invalid or expired token.", null));
        }

        SwiftMessageDashboardStats counts = service.getMessageCounts();
        logger.info("Message counts fetched successfully.");

        return ResponseEntity.ok(new DashBoardApiResponseWrapper<>(ApiResponseCodes.SUCCESS, "Message counts retrieved successfully.",
                new TokenizedResponse<>(accessToken, counts)));
    }

    @GetMapping("/message-type-counts")
    public ResponseEntity<DashBoardApiResponseWrapper<?>> getMessageTypeCounts(@RequestParam Map<String, String> token) {
        logger.info("Request received for /message-type-counts");

        String accessToken = authenticateAPi.validateAndRefreshToken(token);
        if (accessToken == null) {
            logger.warn("Token validation failed.");
            return ResponseEntity
                    .status(ApiResponseCodes.UNAUTHORIZED.getHttpStatus())
                    .body(new DashBoardApiResponseWrapper<>(ApiResponseCodes.UNAUTHORIZED, "Invalid or expired token.", null));
        }

        Map<String, Long> typeCounts = service.getMessageTypeCounts();
        logger.info("Message type counts fetched successfully.");

        return ResponseEntity.ok(new DashBoardApiResponseWrapper<>(ApiResponseCodes.SUCCESS,
                "Message type counts retrieved successfully.", new TokenizedResponse<>(accessToken, typeCounts)));
    }
}
