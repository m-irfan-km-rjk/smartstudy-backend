package com.irfan.smartstudy.model;

public enum Status {
    PENDING("pending"),
    DONE("done");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public static Status fromString(String value) {
        for (Status s : Status.values()) {
            if (s.status.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }
}
