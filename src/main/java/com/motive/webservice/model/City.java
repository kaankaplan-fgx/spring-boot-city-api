package com.motive.webservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    
    private Date createDate = new Date();
    @Id
    private String id;
    private String name;

    
    
}
