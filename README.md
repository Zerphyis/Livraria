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

## 📌 Endpoints Disponíveis

### 🔹 Autores (`/autores`)
| Método | Endpoint       | Descrição                     |
|--------|----------------|-------------------------------|
| POST   | `/`            | Cadastrar novo autor          |
| GET    | `/`            | Listar todos os autores       |
| GET    | `/{id}`        | Buscar autor por ID           |
| PUT    | `/{id}`        | Atualizar dados do autor      |
| DELETE | `/{id}`        | Deletar autor                 |

### 🔹 Usuários (`/usuarios`)
| Método | Endpoint       | Descrição                     |
|--------|----------------|-------------------------------|
| POST   | `/`            | Cadastrar novo usuário        |
| GET    | `/`            | Listar todos os usuários      |
| GET    | `/{id}`        | Buscar usuário por ID         |
| PUT    | `/{id}`        | Atualizar dados do usuário    |
| DELETE | `/{id}`        | Deletar usuário               |

### 🔹 Livros (`/livros`)
| Método | Endpoint       | Descrição                     |
|--------|----------------|-------------------------------|
| POST   | `/`            | Cadastrar novo livro          |
| GET    | `/`            | Listar todos os livros        |
| GET    | `/{id}`        | Buscar livro por ID           |
| PUT    | `/{id}`        | Atualizar dados do livro      |
| DELETE | `/{id}`        | Deletar livro                 |

### 🔹 Empréstimos (`/emprestimos`)
| Método | Endpoint                    | Descrição                                |
|--------|-----------------------------|------------------------------------------|
| POST   | `/`                         | Criar um novo empréstimo de livro        |
| PUT    | `/{id}?returnDate=YYYY-MM-DD` | Realizar devolução do livro              |
| GET    | `/`                         | Listar todos os empréstimos              |
| DELETE | `/{id}`                     | Deletar um empréstimo                    |

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
