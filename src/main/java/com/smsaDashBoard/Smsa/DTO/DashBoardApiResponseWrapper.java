/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smsaDashBoard.Smsa.DTO;

import com.smsaDashBoard.Smsa.Enums.ApiResponseCodes;

/**
 *
 * @author abcom
 */
public class DashBoardApiResponseWrapper<T> {
    private int code;
    private String message;
    private T data;

    public DashBoardApiResponseWrapper(ApiResponseCodes statusCode, String message, T data) {
        this.code = statusCode.getCode();
        this.message = message;
        this.data = data;
    }

    // Getters and setters

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
