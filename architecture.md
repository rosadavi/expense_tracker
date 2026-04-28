```md
# 💰 Expense Tracker API - Arquitetura

Este documento descreve a arquitetura da API de controle de despesas, explicando os módulos principais, fluxo de autenticação, persistência e regras de negócio.

---

# 🧠 Visão Geral

A aplicação é uma API REST construída com Spring Boot que permite:

- Cadastro e autenticação de usuários
- Geração e validação de JWT
- Gerenciamento de despesas por usuário
- Segurança via Spring Security + filtros personalizados

A arquitetura segue um padrão em camadas:

Controller → Service → Repository → Database

---

# 🔐 Módulo de Autenticação (Auth)

## AuthController

Responsável por:
- Registro de usuários (/auth/register)
- Login de usuários (/auth/login)
- Geração de JWT

### Fluxo de login:
1. Busca usuário por email
2. Valida senha com BCrypt
3. Gera token JWT com userId
4. Retorna token ao cliente

---

## JwtService

Responsável por:
- Gerar tokens JWT
- Validar tokens recebidos
- Extrair subject (userId)

Características:
- Usa chave HMAC SHA-256
- Token expira em 1 hora
- Usa jjwt com API moderna (Keys.hmacShaKeyFor)

---

## JwtFilter (Middleware de Segurança)

Intercepta todas as requisições HTTP.

Função:
- Lê header Authorization
- Valida token JWT
- Extrai userId
- Autentica usuário no contexto do Spring Security

Resultado:
- Se válido → usuário autenticado no SecurityContext
- Se inválido → retorna 401 Unauthorized

---

# ⚙️ Módulo de Segurança

## SecurityConfig

Configura o Spring Security:

- Define rotas públicas (/auth/**)
- Protege todas as outras rotas
- Desabilita CSRF (API stateless)
- Registra JwtFilter no fluxo de segurança

Fluxo:
Request → JwtFilter → Security Rules → Controller

---

## SecurityBeanConfig

Define beans globais de segurança:

- PasswordEncoder (BCrypt)

Responsável por:
- Criptografar senhas
- Validar senhas no login

---

# 👤 Módulo de Usuário

## User (Entity)

Representa o usuário no banco.

Campos:
- id (UUID)
- name
- email (único)
- password (criptografada)

---

## UserService

Responsável por regras de negócio:

- Criar usuário (com senha criptografada)
- Buscar por ID
- Buscar por email

---

## UserRepository

Interface JPA que fornece:
- CRUD automático
- findByEmail

---

# 💸 Módulo de Despesas

## Expense (Entity)

Representa uma despesa.

Campos:
- id
- description
- amount
- category
- date (gerada automaticamente)
- user (ManyToOne)

Regra:
- date é preenchida automaticamente com @PrePersist

---

## ExpenseService

Responsável pela lógica:

- Criar despesa vinculada a usuário
- Listar despesas por usuário
- Deletar despesas

Regra importante:
- valida se usuário existe antes de criar despesa
- associa despesa ao usuário

---

## ExpenseRepository

Interface JPA:
- CRUD padrão
- findByUserId(UUID id)

---

# 🌐 Controllers

## AuthController

Endpoints:
- POST /auth/register → cria usuário
- POST /auth/login → autentica e retorna JWT

---

## ExpenseController

Endpoints:
- POST /expense/{userId} → cria despesa
- GET /expense/{userId} → lista despesas
- DELETE /expense/{expenseId} → remove despesa

---

## UserController

Atualmente vazio, reservado para futuras features:
- perfil do usuário
- atualização de dados
- consulta autenticada

---

# 🔄 Fluxo Completo

## Registro
POST /auth/register → UserService → UserRepository → DB

## Login
POST /auth/login → valida senha → gera JWT → retorna token

## Request protegida
Request com JWT → JwtFilter valida → SecurityConfig autoriza → Controller executa

---

# 🔐 Segurança

- JWT para autenticação stateless
- Spring Security para autorização
- BCrypt para senhas
- Rotas públicas: /auth/**
- Rotas protegidas: todas as outras

---

# 🧠 Arquitetura geral

Client → Controller → Service → Repository → Database  
+ Security Layer (JwtFilter + SecurityConfig)

---

# 🚀 Conclusão

Projeto implementa:
- Autenticação JWT completa
- Segurança com Spring Security
- Separação de responsabilidades (clean architecture)
- Relacionamento entre entidades (User ↔ Expense)
- Estrutura escalável e pronta para produção
```
