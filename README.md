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

### Livros (`/livros`)

- `POST /livros`: Cadastrar um novo livro.  
![Image](https://github.com/user-attachments/assets/6a56469e-d659-40c7-b3da-90511005e0f1)

- `GET /livros`: Listar todos os livros disponíveis.  
  ![Image](https://github.com/user-attachments/assets/287a3677-9cb7-4220-b189-8684fe36ac08)

- `GET /livros/{id}`: Obter detalhes de um livro específico.  
 ![Image](https://github.com/user-attachments/assets/ec98d07b-2af6-4cc4-a23e-79fd150dbf54)

- `PUT /livros/{id}`: Atualizar as informações de um livro.  
 ![Image](https://github.com/user-attachments/assets/cc10f822-782a-46cc-b3b9-99818c3ce1ed)

- `DELETE /livros/{id}`: Deletar um livro do sistema.  
![Image](https://github.com/user-attachments/assets/17f088ab-4ebb-4fb8-8e1f-ed7fc5ee44db)

---
###  Usuários (`/usuarios`)

- `POST /usuarios`: Registrar um novo usuário.  
![Image](https://github.com/user-attachments/assets/5fa7f235-7c83-48b5-a0d8-16967fffe151)

- `GET /usuarios`: Listar todos os usuários.  
![Image](https://github.com/user-attachments/assets/ea614aeb-0f53-4ff5-9edf-51b80194c2e7)

- `GET /usuarios/{id}`: Buscar um usuário pelo ID.  
![Image](https://github.com/user-attachments/assets/2fa1411d-e981-485f-a30b-48b7dfcec40e)

- `PUT /usuarios/{id}`: Atualizar dados de um usuário existente.  
![Image](https://github.com/user-attachments/assets/8253a16d-f05f-4e74-9644-413807bea8a6)

- `DELETE /usuarios/{id}`: Remover um usuário do sistema.  
![Image](https://github.com/user-attachments/assets/67c12fc6-93b8-4bc3-8697-9eaaa1ce0cdf)

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
