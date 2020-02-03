package com.nisum.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    String region;
    String country;
    String itemType;
    String salesChannel;
    String orderPriority;
    String orderDate;
    String orderId;
    String shipDate;
    String unitsSold;
    String unitPrice;
    String unitCost;
    String totalRevenue;
    String totalCost;
    String totalProfit;
}
