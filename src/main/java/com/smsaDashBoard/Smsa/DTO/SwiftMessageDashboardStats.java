/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smsaDashBoard.Smsa.DTO;

/**
 *
 * @author abcom
 */

public class SwiftMessageDashboardStats {

    private long totalSwiftPaymentsCount;
    private long recivedPayments;
    private long sentPayments;

    // Getters and setters
    


    /**
     * @return the totalSwiftPaymentsCount
     */
    public long getTotalSwiftPaymentsCount() {
        return totalSwiftPaymentsCount;
    }

    /**
     * @param totalSwiftPaymentsCount the totalSwiftPaymentsCount to set
     */
    public void setTotalSwiftPaymentsCount(long totalSwiftPaymentsCount) {
        this.totalSwiftPaymentsCount = totalSwiftPaymentsCount;
    }

    /**
     * @return the recivedPayments
     */
    public long getRecivedPayments() {
        return recivedPayments;
    }

    /**
     * @param recivedPayments the recivedPayments to set
     */
    public void setRecivedPayments(long recivedPayments) {
        this.recivedPayments = recivedPayments;
    }

    /**
     * @return the sentPayments
     */
    public long getSentPayments() {
        return sentPayments;
    }

    /**
     * @param sentPayments the sentPayments to set
     */
    public void setSentPayments(long sentPayments) {
        this.sentPayments = sentPayments;
    }
}
