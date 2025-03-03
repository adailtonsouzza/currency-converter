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

### **Opção 1**
### **Rodando via Docker 🐳**
Caso você não queira instalar Java/Maven, pode rodar a aplicação diretamente via Docker.

**Baixar e rodar a aplicação via Docker**
```sh
docker pull adailtonsouzza/currency-converter:1.1
docker run -p 8080:8080 adailtonsouzza/currency-converter:1.1
```
Após rodar, a API estará disponível em http://localhost:8080.

**OBS: Seguir os passos da opção 2, a partir do passo 4**

### **Opção 2**
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

### **4️⃣ Criar um Usuário e gerar a Access Key Antes de Fazer Conversões**
Antes de realizar qualquer transação, é **necessário criar um usuário**, pois as transações são registradas no banco associadas a um usuário.


#### **Criar usuário via Swagger**
1. Acesse **[Swagger UI](http://localhost:8080/swagger-ui/index.html)** após iniciar a aplicação.
2. Vá até o endpoint **POST `/api/users`** e crie um usuário.

#### **Gerar Access Key**
1. Acesse  **[exchangeratesapi](https://manage.exchangeratesapi.io/)**
2. Criar conta ou entrar, e só copiar a key. 

### **5️⃣ Acessar a Documentação Swagger**
A API está documentada no **Swagger** e pode ser acessada após iniciar a aplicação:

🔗 **[Swagger UI](http://localhost:8080/swagger-ui/index.html)**

---
## 📌 **Endpoints Disponíveis**

### **Usuários**
| Método | Endpoint          | Descrição                     |
|--------|------------------|------------------------------|
| `POST` | `/api/users`      | Cria um novo usuário         |
| `PUT` | `/api/users`      | Altera um  usuário         |
| `GET`  | `/api/users/{id}` | Obtém um usuário pelo ID     |
| `GET`  | `/api/users`      | Lista todos os usuários      |

### **Transações**
| Método | Endpoint                 | Descrição                              |
|--------|--------------------------|----------------------------------------|
| `POST` | `/api/transactions`       | Realiza uma conversão de moeda        |
| `GET`  | `/api/transactions/{userId}` | Lista transações de um usuário |




