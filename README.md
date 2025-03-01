# 💱 Currency Converter API

## 📌 Sobre o Projeto

O **Currency Converter API** é uma aplicação que permite a conversão de valores entre diferentes moedas utilizando uma API externa para obter as taxas de câmbio em tempo real. As transações de conversão são registradas no banco de dados para consultas futuras.

---

## 🚀 **Principais Funcionalidades**
✅ Conversão de valores entre múltiplas moedas (**BRL, USD, EUR, JPY**).  
✅ Persistência das transações no banco de dados para histórico.  
✅ Listagem das transações por usuário.  
✅ Tratamento global de erros para respostas mais claras.  
✅ Documentação interativa da API via **Swagger**.  
✅ Testes unitários e de integração garantindo a confiabilidade do sistema.  

---

## 📌 **Tecnologias Utilizadas**
- 🔹 **Kotlin** - Linguagem moderna e segura para desenvolvimento backend.
- 🔹 **Spring Boot** - Framework para desenvolvimento ágil de APIs REST.
- 🔹 **Spring Data JPA** - Para interação eficiente com o banco de dados.
- 🔹 **H2 Database** - Banco de dados em memória para testes.
- 🔹 **MockMvc e Mockito** - Para testes unitários e de integração.
- 🔹 **Swagger/OpenAPI** - Para documentação interativa da API.
- 🔹 **Maven** - Gerenciador de dependências do projeto.

---
## 📂 **Estrutura do Projeto**
O projeto segue a **arquitetura em camadas** para maior organização e escalabilidade:

```bash
src/
│── main/
│   ├── kotlin/com/adailton/currencyconverter/
│   │   ├── controller/    # Controladores (camada de apresentação)
│   │   ├── service/       # Regras de negócio
│   │   ├── repository/    # Acesso ao banco de dados
│   │   ├── model/         # Entidades e DTOs
│   │   ├── exception/     # Tratamento de exceções
│   │   ├── config/        # Configurações do projeto (Swagger, etc)
│── test/                  # Testes unitários e de integração
│── resources/
│   ├── application.properties  # Configurações do banco de dados
│── pom.xml                     # Dependências do projeto (Maven)
│── README.md                    # Documentação do projeto
```
---

## ▶️ **Como Rodar a Aplicação**
### **1️⃣ Pré-requisitos**
- ✅ **Java 17+** instalado (**necessário para rodar o Spring Boot e Maven**).
- ✅ **Maven** instalado (**gerenciador de dependências do projeto**).
- ✅ **IDE** (IntelliJ, VS Code, Eclipse, etc).

### **2️⃣ Clonar o Repositório**
```sh
git clone https://github.com/adailtonsouzza/currency-converter.git
cd currency-converter
```
### **3️⃣  Rodar a Aplicação**
```sh
mvn spring-boot:run
```

### **4️⃣ Criar um Usuário Antes de Fazer Conversões**
Antes de realizar qualquer transação, é **necessário criar um usuário**, pois as transações são registradas no banco associadas a um usuário.


#### **Criar usuário via Swagger**
1. Acesse **[Swagger UI](http://localhost:8080/swagger-ui/index.html)** após iniciar a aplicação.
2. Vá até o endpoint **POST `/api/users`** e crie um usuário.

### **5️⃣ Acessar a Documentação Swagger**
A API está documentada no **Swagger** e pode ser acessada após iniciar a aplicação:

🔗 **[Swagger UI](http://localhost:8080/swagger-ui/index.html)**

---




