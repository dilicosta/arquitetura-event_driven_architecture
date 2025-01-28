package com.consumer.kafka.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventBalanceUpdatedDTO {
    
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Payload")
    private EventBalanceUpdatedPayloadDTO payload;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EventBalanceUpdatedPayloadDTO getPayload() {
        return payload;
    }
    public void setPayload(EventBalanceUpdatedPayloadDTO payload) {
        this.payload = payload;
    }

    
    
}
