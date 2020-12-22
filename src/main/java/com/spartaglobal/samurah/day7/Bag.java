package com.spartaglobal.samurah.day7;

public class Bag {
    private Bag[] bagContent;
    private final int quantity;
    private final String color;

    public Bag(String color, Bag[] bagContent){
        this.color = color;
        this.bagContent = bagContent;
        quantity = 1;
    }

    public Bag(int quantity, String color){
        this.quantity = quantity;
        this.color = color;
    }

    public Bag[] getBagContent() {
        return bagContent;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getColor() {
        return color;
    }
}
