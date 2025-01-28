package com.consumer.kafka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.kafka.dominio.Balance;
import com.consumer.kafka.negocio.BalanceRN;

@RestController
@RequestMapping("/")
public class BalanceCtr {

    @Autowired
    private BalanceRN balanceService;

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> getBalances() {
        List<Balance> listBalances = balanceService.retornaTodos();
        return ResponseEntity.status(HttpStatus.OK).body(listBalances);
    }

    @GetMapping("/balances/{accountId}")
    public ResponseEntity<Balance> getBalanceFromAccountId(@PathVariable String accountId) {
        Balance balance = balanceService.getBalanceByAccountId(accountId);
        if (balance == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Balance());
        }
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }
    
}
