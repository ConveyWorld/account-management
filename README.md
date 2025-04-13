# 🏦 Banking Microservices System

This project is a modern **Banking Microservices Architecture** developed using **Spring Boot**, **gRPC**, **Oracle Database**, and **Docker**. It includes several core services to manage accounts, perform transfers, and generate statements.

## 🚀 Tech Stack

- ☕ Java 21 with Virtual Threads
- 🌱 Spring Boot 3.x
- 🛠 gRPC (Google Remote Procedure Call)
- 🐳 Docker + Docker Compose
- 🛢 Oracle Database
- 🧪 JUnit, Mockito, Jacoco
- 🧵 Virtual Threads for concurrency
- 📘 Postman (for testing gRPC via REST Gateway)

## 🧩 Services Overview

### 1. `account-management`
- Manages account creation, updates, and lookup.
- Uses JPA to persist data to Oracle.
- Exposes gRPC API.
- Connected to `accountRepository`.

### 2. `transfer-service`
- Handles money transfers between accounts.
- Ensures consistency and integrity of transactions.
- Validates account balances before transfer.

### 3. `statement-service`
- Generates account statements.
- Fetches transactional history from the database.
- Potential for report exporting in future versions.

## ⚙️ Architecture Diagram

```text
                  ┌────────────────────┐
                  │    REST Gateway    │ ← REST (Postman)
                  └────────┬───────────┘
                           │
                     ┌─────▼──────┐
                     │   gRPC     │
                     └─────┬──────┘
           ┌───────────────┼────────────────┐
           ▼               ▼                ▼
   Account Service   Transfer Service   Statement Service
         │                 │                  │
       Oracle DB       Oracle DB           Oracle DB
