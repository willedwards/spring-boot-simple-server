package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanRequest {

    @JsonProperty("client.id")
    private String clientId;

    private Long loanAmount;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanRequestAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }
}