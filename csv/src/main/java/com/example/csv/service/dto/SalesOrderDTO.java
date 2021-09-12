package com.example.csv.service.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class SalesOrderDTO {

    private Long id;

    private String region;

    private String country;

    private String itemType;

    private String salesChannel;

    private String orderPriority;

    private Date orderDate;

    private Long orderId;

    private Date shipDate;

    private Long unitsSold;

    private Double unitPrice;

    private Double unitCost;

    private Double totalRevenue;

    private Double totalCost;

    private Double totalProfit;

    private String creator;

    @Override
    public String toString() {
        //TODO
        return null;
    }



}
