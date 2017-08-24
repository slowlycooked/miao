package com.ifiport.pojo;

public class RecordSum {


    private String type;
    private Double amount;


    public RecordSum(String type, Double amount) {
        this.type = type;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

}
