# Desafio PicPay - Backend

Este é um projeto backend para o **Desafio PicPay**, onde foram implementados os serviços de criação de usuários e transações, incluindo a validação de saldo e tipo de usuário (comum ou lojista). O projeto também envia notificações de transações realizadas com sucesso, utilizando um serviço de notificação simulada.

## Tecnologias Utilizadas
- **Java** (versão 17 ou superior)
- **Spring Boot** (framework principal)
- **JPA/Hibernate** (para persistência de dados)
- **RestTemplate** (para consumir serviços externos)
- **H2 Database** (banco de dados em memória para testes)

## Endpoints

### 1. Usuários

#### Criar Usuário
- **POST** `/users`
- **Body (JSON)**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "document": "123456789",
    "balance": 100.00,
    "email": "john.doe@example.com",
    "password": "password123",
    "userType": "COMMON"
  }
  ```
- **Resposta**:
  - **Status 201**: Usuário criado com sucesso.
  - **Body**: Dados do usuário criado.

#### Listar Usuários
- **GET** `/users`
- **Resposta**:
  - **Status 200**: Lista de todos os usuários.
  - **Body**: Lista de usuários.

### 2. Transações

#### Criar Transação
- **POST** `/transactions`
- **Body (JSON)**:
  ```json
  {
    "value": 50.00,
    "senderId": 1,
    "receiverId": 2
  }
  ```
- **Resposta**:
  - **Status 201**: Transação criada com sucesso.
  - **Body**: Dados da transação.

## Estrutura do Projeto

- **controllers**: Contém as classes responsáveis por gerenciar as requisições HTTP.
  - `TransactionController`: Gerencia as transações entre usuários.
  - `UserController`: Gerencia a criação e listagem de usuários.
  
- **domain**: Contém as entidades que representam os dados no banco de dados.
  - `User`: Entidade de usuário com informações como nome, email, tipo de usuário e saldo.
  - `Transaction`: Entidade de transação contendo informações sobre o valor, o remetente e o destinatário.

- **dtos**: Contém objetos de transferência de dados, utilizados para comunicação entre as camadas.
  - `TransactionDTO`: Contém os dados necessários para criar uma transação.
  - `UserDTO`: Contém os dados necessários para criar um usuário.
  - `NotificationDTO`: Usado para enviar notificações por email.

- **services**: Contém as lógicas de negócios e a comunicação com o banco de dados.
  - `TransactionService`: Gerencia a criação de transações e validações de saldo.
  - `UserService`: Gerencia a criação e validação de usuários.
  - `NotificationService`: Simula o envio de notificações por email.

- **repositories**: Contém as interfaces para acesso ao banco de dados.
  - `UserRepository`: Interface para interação com a tabela de usuários.
  - `TransactionRepository`: Interface para interação com a tabela de transações.

- **infra**: Contém configurações adicionais e tratamento de exceções.
  - `ControllerExceptionHandler`: Captura e trata exceções comuns, como entradas duplicadas ou usuários não encontrados.

## Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd desafio-picpay
   ```

3. Compile o projeto:
   ```bash
   ./mvnw clean install
   ```

4. Execute o servidor:
   ```bash
   ./mvnw spring-boot:run
   ```

5. O servidor estará rodando na porta `8080` por padrão.

## Testes

Para rodar os testes do projeto, use o seguinte comando:

```bash
./mvnw test
```

## Contribuições

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork deste repositório.
2. Crie uma branch para sua modificação (`git checkout -b minha-modificacao`).
3. Faça as alterações necessárias e adicione testes, se necessário.
4. Envie um pull request.
