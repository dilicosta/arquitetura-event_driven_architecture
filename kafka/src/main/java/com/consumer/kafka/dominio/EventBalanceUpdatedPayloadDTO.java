package com.consumer.kafka.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventBalanceUpdatedPayloadDTO {

    @JsonProperty("account_id_from")
    private String accountIdFrom;
    @JsonProperty("account_id_to")
    private String accountIdTo;
    @JsonProperty("balance_account_id_from")
    private Double balanceAccountIdFrom;
    @JsonProperty("balance_account_id_to")
    private Double balanceAccountIdTo;
    
    public String getAccountIdFrom() {
        return accountIdFrom;
    }
    public void setAccountIdFrom(String accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }
    public String getAccountIdTo() {
        return accountIdTo;
    }
    public void setAccountIdTo(String accountIdTo) {
        this.accountIdTo = accountIdTo;
    }
    public Double getBalanceAccountIdFrom() {
        return balanceAccountIdFrom;
    }
    public void setBalanceAccountIdFrom(Double balanceAccountIdFrom) {
        this.balanceAccountIdFrom = balanceAccountIdFrom;
    }
    public Double getBalanceAccountIdTo() {
        return balanceAccountIdTo;
    }
    public void setBalanceAccountIdTo(Double balanceAccountIdTo) {
        this.balanceAccountIdTo = balanceAccountIdTo;
    }
}
