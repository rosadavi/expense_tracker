---

# 💰 Expense Tracker API

API REST para gerenciamento de despesas pessoais, permitindo que cada usuário tenha controle total sobre seus gastos de forma segura e organizada.

---

## 🚀 Sobre o Projeto

A **Expense Tracker API** foi desenvolvida para permitir que usuários:

* Criem uma conta
* Façam login com autenticação segura (JWT)
* Gerenciem suas próprias despesas (CRUD completo)
* Filtrarem gastos por período

Cada usuário possui seus próprios dados isolados, garantindo segurança e organização.

---

## 🛠️ Tecnologias Utilizadas

* Java + Spring Boot
* Spring Security
* JWT (JSON Web Token)
* JPA / Hibernate
* Banco de dados relacional (ex: PostgreSQL ou MySQL)
* Docker (opcional)

---

## 📦 Funcionalidades

### 👤 Autenticação e Usuário

* Cadastro de novos usuários
* Login com geração de token JWT
* Proteção de rotas com autenticação

---

### 💸 Despesas

* Criar nova despesa
* Listar despesas do usuário
* Atualizar despesa existente
* Deletar despesa

---

### 🔍 Filtros de Despesas

Filtragem por período:

* Última semana
* Último mês
* Últimos 3 meses
* Intervalo personalizado (data inicial e final)

---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação.

### Fluxo:

1. Usuário faz login
2. Recebe um token JWT
3. Envia o token no header das requisições protegidas:

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 📚 Estrutura da API

### 🔑 Auth

| Método | Rota           | Descrição           |
| ------ | -------------- | ------------------- |
| POST   | /auth/register | Cadastro de usuário |
| POST   | /auth/login    | Login e geração JWT |

---

### 💸 Expenses

| Método | Rota             | Descrição          |
| ------ | ---------------- | ------------------ |
| GET    | /expenses        | Listar despesas    |
| GET    | /expenses/filter | Filtrar despesas   |
| POST   | /expenses        | Criar nova despesa |
| PUT    | /expenses/{id}   | Atualizar despesa  |
| DELETE | /expenses/{id}   | Remover despesa    |

---

## 🧾 Modelo de Dados (Resumo)

### User

* id
* name
* email
* password

### Expense

* id
* description
* amount
* category
* date
* user_id

---

## 🏷️ Categorias de Despesas

* Supermercado
* Lazer
* Eletrônicos
* Serviços Públicos
* Vestuário
* Saúde
* Outros

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

* Java 17+
* Maven ou Gradle
* Docker (opcional)
* Banco de dados configurado

---

### Passos

```bash
# Clonar repositório
git clone https://github.com/rosadavi/expense_tracker.git

# Entrar no projeto
cd expense_tracker

# Rodar aplicação
./mvnw spring-boot:run
```

---

## 🧪 Testando a API

Você pode usar:

* Postman
* Insomnia
* curl

---

## 📌 Melhorias Futuras

* Paginação de despesas
* Dashboard com estatísticas
* Exportação de dados (CSV/PDF)
* Integração com frontend
* Refresh token

---

## 📄 Licença

Este projeto está sob a licença MIT.

---
