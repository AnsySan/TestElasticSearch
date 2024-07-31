package ru.ansysan.entity;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductSku {

    private Long productId;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private Boolean productActive;

    private LocalDate productStartDate;

    private String skuCode;

    private Integer skuStock;

}