package com.irfan.smartstudy.model;

public enum Status {
    PENDING("pending"),
    DONE("done");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
