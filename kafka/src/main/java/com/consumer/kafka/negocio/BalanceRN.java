package com.consumer.kafka.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumer.kafka.dao.BalanceRepository;
import com.consumer.kafka.dominio.Balance;
import com.consumer.kafka.dominio.EventBalanceUpdatedDTO;
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

    public void processarMensagemKafka(String message){
        ObjectMapper mapper = new ObjectMapper();
        try {
            EventBalanceUpdatedDTO event = mapper.readValue(message, EventBalanceUpdatedDTO.class);
            saveFrom(event);
            saveTo(event);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

	private void saveTo(EventBalanceUpdatedDTO event) {
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
	}

	private void saveFrom(EventBalanceUpdatedDTO event) {
		Optional<Balance> optCli = balanceRepository.findByAccountId(event.getPayload().getAccountIdFrom());

		Balance balance = new Balance();
		if (optCli.isEmpty()){
		    balance.setAccountId(event.getPayload().getAccountIdFrom());
		    balance.setBalance(event.getPayload().getBalanceAccountIdFrom());
		} else {
		    balance = optCli.get();
		    balance.setBalance(event.getPayload().getBalanceAccountIdFrom());
		}
		balanceRepository.save(balance);
	}
}
