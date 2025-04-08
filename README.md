# 📚 Library Management API

Sistema de gerenciamento de biblioteca desenvolvido com Spring Boot, utilizando arquitetura em camadas para lidar com autores, usuários, livros e empréstimos.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- Bean Validation (Jakarta)
- MySQL (conector incluído)
- JUnit & Mockito (para testes)

---

## 🗂️ Estrutura do Projeto

O projeto segue uma arquitetura dividida em pacotes organizados por responsabilidade:

- `Controller`: contém os endpoints REST.
- `Entity`: entidades JPA representando as tabelas do banco de dados.
- `Service`: lógica de negócios.
- `Exception`: tratamento de exceções.
- `Datas`: classes DTO para entrada e saída de dados.

---
## 📌 Endpoints da API

###  Autores (`/autores`)

- `POST /autores`: Adicionar um novo autor.  
 ![Image](https://github.com/user-attachments/assets/1c64e39e-ee6c-4ca6-baaa-55ab3f725278)

- `GET /autores`: Listar todos os autores cadastrados.  
![Image](https://github.com/user-attachments/assets/6671a193-0ee4-4ef3-a971-8ac3869f7b36)

- `GET /autores/{id}`: Visualizar detalhes de um autor.  
![Image](https://github.com/user-attachments/assets/80b99204-44f9-45fc-9074-2f2875ee549f)

- `PUT /autores/{id}`: Editar as informações de um autor.  
![Image](https://github.com/user-attachments/assets/6b4c0a36-6af5-4525-87db-d992a4a03f11)

- `DELETE /autores/{id}`: Remover um autor.  
![Image](https://github.com/user-attachments/assets/1b9651af-e399-4289-b2c4-31df4554149f)

---


## 🧪 Testes

- Testes com JUnit 5 e Mockito.
- Dependências já incluídas no `pom.xml`.

---

## ⚙️ Configuração

Certifique-se de configurar seu `application.properties` com os dados do banco MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/coloque_sua_url
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
