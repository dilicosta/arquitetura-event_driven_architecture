package com.consumer.kafka.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.consumer.kafka.negocio.BalanceRN;

@Service
public class KafkaConsumer {

    @Autowired
    private BalanceRN balanceService;

    @KafkaListener(topics = "transactions", groupId = "wallet")
    public void consume(String message) {
        System.out.println("Mensagem recebida Transaction: " + message);
    }

    @KafkaListener(topics = "balances", groupId = "wallet")
    public void consumeBalance(String message) {
        System.out.println("Mensagem recebida Balance: " + message);
        balanceService.processarMensagemKafka(message);
    }
}