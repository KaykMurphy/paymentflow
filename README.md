# 💳 Payment Flow API

> Sistema completo de fluxo de pagamentos com controle de estoque, saldo de usuários e histórico de pedidos.

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4+-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)

---

## 📋 Sobre o Projeto

API RESTful para gerenciamento de fluxo de pagamentos, permitindo:

- ✅ Cadastro de usuários e produtos
- ✅ Controle de saldo de usuários
- ✅ Gestão de estoque de produtos
- ✅ Criação e consulta de pedidos
- ✅ Validações de negócio (saldo insuficiente, estoque esgotado)
- ✅ Tratamento global de exceções
- ✅ HATEOAS para navegabilidade da API

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17+** - Linguagem de programação
- **Spring Boot 3.4+** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM
- **Spring HATEOAS** - Hypermedia para API REST
- **Spring Validation** - Validação de dados

### Banco de Dados
- **PostgreSQL** - Banco de dados relacional
- **H2 Database** - Banco em memória para testes

### DevOps
- **Docker** - Containerização
- **Docker Compose** - Orquestração de containers

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de código boilerplate
- **Jakarta Validation** - Validações declarativas

---

## 🏗️ Arquitetura

```
src/main/java/com/example/paymentflow/
│
├── controller/          # Controladores REST
│   ├── OrderController
│   ├── ProductController
│   └── UserController
│
├── service/            # Lógica de negócio
│   ├── OrderService
│   ├── ProductService
│   └── UserService
│
├── repository/         # Camada de dados (JPA)
│   ├── OrderRepository
│   ├── OrderItemRepository
│   ├── ProductRepository
│   └── UserRepository
│
├── entity/            # Entidades JPA
│   ├── Order
│   ├── OrderItem
│   ├── Product
│   └── User
│
├── dtos/              # Data Transfer Objects
│   ├── OrderReceiptDTO
│   ├── ProductCreateDTO
│   ├── ProductResponseDTO
│   ├── PurchaseRequestDTO
│   ├── UserCreateDTO
│   ├── UserDepositDTO
│   └── UserResponseDTO
│
├── exceptions/        # Exceções customizadas
│   ├── GlobalExceptionHandler
│   ├── ResourceNotFoundException
│   ├── InsufficientBalanceException
│   └── InsufficientStockException
│
├── hateoas/          # Assemblers HATEOAS
│   └── OrderAssembler
│
└── enums/            # Enumerações
    └── Status
```

---

## 🚀 Como Executar

### Pré-requisitos

#### Opção 1: Executar com Docker (Recomendado) 🐳
- Docker
- Docker Compose

#### Opção 2: Executar Localmente
- Java 17 ou superior
- Maven 3.8+
- PostgreSQL 12+
- Postman ou Insomnia (para testes)

---

### 🐳 Executar com Docker (Recomendado)

A forma mais simples e rápida de executar o projeto é usando Docker Compose, que configura automaticamente o banco de dados PostgreSQL e a aplicação.

#### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/payment-flow-api.git
cd payment-flow-api
```

#### 2. Execute com Docker Compose

```bash
docker-compose up -d
```

Isso irá:
- ✅ Criar um container PostgreSQL configurado
- ✅ Construir e executar a aplicação Spring Boot
- ✅ Configurar automaticamente a rede entre os containers
- ✅ Expor a API na porta 8080

#### 3. Acesse a aplicação

A API estará disponível em: `http://localhost:8080`

#### Comandos úteis do Docker

```bash
# Ver logs da aplicação
docker-compose logs -f app

# Ver logs do banco de dados
docker-compose logs -f db

# Parar os containers
docker-compose down

# Parar e remover volumes (limpa o banco de dados)
docker-compose down -v

# Reconstruir as imagens
docker-compose up --build

# Verificar status dos containers
docker-compose ps
```

---

### 💻 Executar Localmente (Sem Docker)

#### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/payment-flow-api.git
cd payment-flow-api
```

#### 2. Configure o banco de dados

Crie um banco no PostgreSQL:

```sql
CREATE DATABASE todo_api;
```

Edite o `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_api
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

#### 3. Execute a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 📚 Endpoints da API

### 👤 Usuários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/users` | Criar novo usuário |
| `GET` | `/users/{id}` | Buscar usuário por ID |
| `POST` | `/users/{id}/deposit` | Depositar saldo |

### 📦 Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/products` | Criar novo produto |
| `GET` | `/products` | Listar todos os produtos |
| `GET` | `/products/{id}` | Buscar produto por ID |

### 🛒 Pedidos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/orders` | Criar novo pedido |
| `GET` | `/orders/{id}` | Buscar pedido por ID |

---

## 📝 Exemplos de Uso

### Criar Usuário

**POST** `/users`

```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123",
  "balance": 1000.00
}
```

**Resposta (201 Created):**

```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "balance": 1000.00
}
```

---

### Criar Produto

**POST** `/products`

```json
{
  "name": "Notebook Dell",
  "description": "i7 16GB RAM",
  "price": 4500.00,
  "stock": 10
}
```

**Resposta (201 Created):**

```json
{
  "id": 1,
  "name": "Notebook Dell",
  "price": 4500.00,
  "stock": 10
}
```

---

### Depositar Saldo

**POST** `/users/1/deposit`

```json
{
  "amount": 5000.00
}
```

**Resposta:** `204 No Content`

---

### Criar Pedido

**POST** `/orders`

```json
{
  "userId": 1,
  "productId": 1,
  "quantity": 2
}
```

**Resposta (201 Created):**

```json
{
  "orderId": 1,
  "moment": "2025-11-24T23:45:00.000Z",
  "total": 9000.00,
  "newBalance": 1000.00,
  "_links": {
    "self": {
      "href": "http://localhost:8080/orders/1"
    }
  }
}
```

---

## ⚠️ Tratamento de Erros

### Saldo Insuficiente

**Resposta (400 Bad Request):**

```json
{
  "timestamp": "2025-11-24T23:45:00.000Z",
  "status": 400,
  "error": "Insufficient balance",
  "message": "Insufficient balance"
}
```

### Estoque Insuficiente

**Resposta (400 Bad Request):**

```json
{
  "timestamp": "2025-11-24T23:45:00.000Z",
  "status": 400,
  "error": "Insufficient stock",
  "message": "Out of stock"
}
```

### Recurso Não Encontrado

**Resposta (404 Not Found):**

```json
{
  "timestamp": "2025-11-24T23:45:00.000Z",
  "status": 404,
  "error": "Resource not found",
  "message": "User not found"
}
```

### Erro de Validação

**Resposta (400 Bad Request):**

```json
{
  "timestamp": "2025-11-24T23:45:00.000Z",
  "status": 400,
  "error": "Validation failed",
  "errors": {
    "name": "Name is required",
    "email": "Invalid email format"
  }
}
```

---

## 🗄️ Modelo de Dados

### Diagrama ER

```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│    User     │       │    Order     │       │   Product   │
├─────────────┤       ├──────────────┤       ├─────────────┤
│ id (PK)     │───┐   │ id (PK)      │   ┌───│ id (PK)     │
│ name        │   └──→│ user_id (FK) │   │   │ name        │
│ email       │       │ moment       │   │   │ description │
│ password    │       │ total        │   │   │ price       │
│ balance     │       │ status       │   │   │ stock       │
└─────────────┘       └──────────────┘   │   │ version     │
                             │            │   └─────────────┘
                             │            │
                      ┌──────▼────────┐   │
                      │  OrderItem    │   │
                      ├───────────────┤   │
                      │ id (PK)       │   │
                      │ order_id (FK) │───┘
                      │ product_id(FK)│───┘
                      │ quantity      │
                      │ price         │
                      └───────────────┘
```

---

## 🔒 Validações Implementadas

- ✅ Email válido
- ✅ Campos obrigatórios (nome, email, preço, etc)
- ✅ Quantidade mínima de 1 item
- ✅ Valores positivos (preço, estoque, saldo)
- ✅ Saldo suficiente antes de criar pedido
- ✅ Estoque disponível antes de criar pedido
- ✅ Controle de concorrência com `@Version` no Product

---

## 🧪 Testes

### Usando H2 Console (Desenvolvimento)

Acesse: `http://localhost:8080/h2-console`

**JDBC URL:** `jdbc:h2:mem:testdb`  
**Username:** `sa`  
**Password:** *(deixe em branco)*

### Collection do Postman

Importe a collection disponível em: `docs/postman_collection.json`

---

## 📦 Build e Deploy

### Build Local

Gerar o JAR do projeto:

```bash
mvn clean package
```

O arquivo será gerado em `target/payment-flow-api.jar`

### Build com Docker

Construir a imagem Docker:

```bash
docker build -t payment-flow-api:latest .
```

### Deploy

#### Deploy Local

Execute o JAR gerado:

```bash
java -jar target/payment-flow-api.jar
```

#### Deploy com Docker

Execute o container:

```bash
docker run -p 8080:8080 payment-flow-api:latest
```

#### Deploy Completo com Docker Compose

```bash
docker-compose up -d
```

---

## 📦 Dependências Principais

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring HATEOAS -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-hateoas</artifactId>
    </dependency>
    
    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

---

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Kayk Murphy**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/KaykMurphy)

---

<div align="center">
  
⭐ Se este projeto te ajudou, considere dar uma estrela!

Made with ❤️ and ☕

</div>
