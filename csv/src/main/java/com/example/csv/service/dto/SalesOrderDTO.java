package com.example.csv.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "sales_order")
public class SalesOrder {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "sales_channel")
    private String salesChannel;

    @Column(name = "order_priority")
    private String orderPriority;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "ship_date")
    private Date shipDate;

    @Column(name = "units_sold")
    private Long unitsSold;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "unit_cost")
    private Double unitCost;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "total_profit")
    private Double totalProfit;

    @Column(name = "creator")
    private String creator;

    @Override
    public String toString() {
        //TODO
        return null;
    }



}
