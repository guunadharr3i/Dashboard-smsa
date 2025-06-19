/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smsaDashBoard.Smsa.CustomExceptions;

/**
 *
 * @author abcom
 */
public class SwiftMessageException extends RuntimeException {

    public SwiftMessageException(String message) {
        super(message);
    }

    public SwiftMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
