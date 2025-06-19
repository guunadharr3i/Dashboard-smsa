package com.smsaDashBoard.Smsa.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class SwiftMessage {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMSA_MESSAGE_ID")
    private Long id;
   
    
}
