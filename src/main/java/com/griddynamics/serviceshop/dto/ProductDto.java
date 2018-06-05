package com.griddynamics.serviceshop.dto;

public class ProductDto {
    private long id;
    private long quantity;


    public ProductDto(long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
