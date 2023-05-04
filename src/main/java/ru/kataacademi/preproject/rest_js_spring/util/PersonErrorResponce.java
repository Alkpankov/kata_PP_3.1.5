package ru.kataacademi.preproject.rest_js_spring.util;

public class PersonErrorResponce {
    private String message;
    private long timestampt;

    public PersonErrorResponce(String message, long timestampt) {
        this.message = message;
        this.timestampt = timestampt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestampt() {
        return timestampt;
    }

    public void setTimestampt(long timestampt) {
        this.timestampt = timestampt;
    }
}
