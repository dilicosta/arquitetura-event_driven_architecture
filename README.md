# arquitetura-event_driven_architecture

Base: https://github.com/devfullcycle/fc-eda

- Desafio realizado na linguagem java: pasta kafka. Serviço que lê os eventos do Kafka gerados pelo Wallet GO e gravar os balances.

- Comando para rodar o projeto:

```
docker compose up -d
Acessar o container go 
Executar cd cmd/walletcore 
go run main.go
```

Toda chamada a funcionalidade de transactions da wallet core, disparada 2 eventos para o kafka (transactions e balances). 
O Sistema lê os eventos de balance e mantêm um banco com os balances recedidos via kafka. 

- arquivo teste servico de balances: kafka/api/client.http