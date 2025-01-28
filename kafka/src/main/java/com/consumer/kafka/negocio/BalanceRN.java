package com.consumer.kafka.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumer.kafka.dominio.Balance;
import com.consumer.kafka.dominio.EventBalanceUpdatedDTO;
import com.consumer.kafka.infraestructure.BalanceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BalanceRN {
    
    @Autowired
    private BalanceRepository balanceRepository;

    public List<Balance> retornaTodos(){
        return balanceRepository.findAll();
    }

    public Balance getBalanceByAccountId(String accountId){
        if (accountId.isEmpty()){
            return null;
        }

        Optional<Balance> opt = balanceRepository.findByAccountId(accountId);
        if (opt.isEmpty()){
            return null;
        }

        return opt.get();

    }

    /*
     * {
   "Name":"BalanceUpdated",
   "Payload":{
      "account_id_from":"87495b95-1c7f-4038-ae55-ab36ed6a9411",
      "account_id_to":"9e3c6bb1-bf75-11e9-9ea7-2a2ae2dbcce4",
      "balance_account_id_from":98,
      "balance_account_id_to":27
   }
     */


    public void processarMensagemKafka(String message){
        System.out.println("dentro da RN processar mensagem");
        ObjectMapper mapper = new ObjectMapper();
        try {
            EventBalanceUpdatedDTO event = mapper.readValue(message, EventBalanceUpdatedDTO.class);

            Optional<Balance> optCli1 = balanceRepository.findByAccountId(event.getPayload().getAccountIdFrom());

            Balance balance1 = new Balance();
            if (optCli1.isEmpty()){
                balance1.setAccountId(event.getPayload().getAccountIdFrom());
                balance1.setBalance(event.getPayload().getBalanceAccountIdFrom());
            } else {
                balance1 = optCli1.get();
                balance1.setBalance(event.getPayload().getBalanceAccountIdFrom());
            }
            balanceRepository.save(balance1);

            Optional<Balance> optCli2 = balanceRepository.findByAccountId(event.getPayload().getAccountIdTo());
            Balance balance2 = new Balance();
            if (optCli2.isEmpty()){
                balance2.setAccountId(event.getPayload().getAccountIdTo());
                balance2.setBalance(event.getPayload().getBalanceAccountIdTo());
            } else {
                balance2 = optCli2.get();
                balance2.setBalance(event.getPayload().getBalanceAccountIdTo());
            }
            balanceRepository.save(balance2);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
