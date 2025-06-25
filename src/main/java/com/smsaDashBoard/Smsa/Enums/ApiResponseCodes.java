/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.smsaDashBoard.Smsa.Enums;

/**
 *
 * @author abcom
 */
public enum ApiResponseCodes {
    SUCCESS(1000, "Success", 200),
    UNAUTHORIZED(1401, "Unauthorized", 401),
    TOKEN_EXPIRED(1402, "Token Expired", 401),
    SERVER_ERROR(1500, "Internal Server Error", 500);

    private final int code;
    private final String message;
    private final int httpStatus;

    ApiResponseCodes(int code, String message, int httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
