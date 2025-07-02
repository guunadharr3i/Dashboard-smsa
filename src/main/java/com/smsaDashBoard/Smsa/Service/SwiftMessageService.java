/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author abcom
 */
package com.smsaDashBoard.Smsa.Service;

import com.SMSAAPis.SMSAAPI.DTO.SwiftRequestPojo;
import com.smsaDashBoard.Smsa.DTO.SwiftMessageDashboardStats;
import com.smsaDashBoard.Smsa.Entity.SwiftMessageHeader;
import java.util.List;
import java.util.Map;

public interface SwiftMessageService {

    public List<SwiftMessageHeader> getFilteredMessages(SwiftRequestPojo filters);

    public SwiftMessageDashboardStats getDashboardStats();

    public List<SwiftMessageHeader> getFullData();

    public SwiftMessageDashboardStats getMessageCounts();

    public Map<String, Long> getMessageTypeCounts();
}
