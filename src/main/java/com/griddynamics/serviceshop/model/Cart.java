package com.griddynamics.serviceshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Cart {

    private List<CartItem> items;
    private double total = 0D;

    public List<CartItem> getItems() {

        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
