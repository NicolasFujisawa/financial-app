# Financial App

Uma aplicação de agendamento de transferência financeira, construída uma REST API com Spring Boot e um client em Vue JS.

## Tecnologias usadas

- Java 11
- Spring Boot
- H2 Database
- Vue
- Docker

## Tecnologias para subir a aplicação

- Docker e docker compose
- JDK 11
- Node LTS

## Como subir a aplicação

Para subir o server:

`cd server`

`./mvnw spring-boot:run`

E acesse em http://localhost:8080

## API's disponíveis

### Criar usuário
POST - http://localhost:8080/users
```json
{
  "name": "nicolas",
  "email": "nicolas@email"
}
```

### Buscar usuário
GET - http://localhost:8080/users/{id}

### Agendar uma transferência
POST - http://localhost:8080/transfers

payerId: id do usuário pagador
payeeId: id do usuário destinatário
amount: quantia enviada
scheduledDate: data do agendamento
transferDate: data para o pagamento

```json
{
  "payerId": 2,
  "payeeId": 1,
  "amount": 10000,
  "scheduledDate": "2023-01-12T11:00:00.000",
  "transferDate": "2023-01-22T11:00:00.000"
}
```

### Buscar transferências de um usuário
GET - http://localhost:8080/users/{id}/bankStatement
