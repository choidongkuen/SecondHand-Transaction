package com.example.shopproject.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ProductAdd {


    @Getter
    @Setter
    @Builder
    public static class Request{

        private String productName;

        private Long price;

        private boolean onSale;

        private LocalDateTime saleEndDt;

        private Long stock;

    }


    @Getter
    @Setter
    @Builder
    public static class Response{

        private String productName;

    }
}
