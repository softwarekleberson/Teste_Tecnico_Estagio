# Arquitetura do Projeto

Este documento descreve a arquitetura e as decisões técnicas adotadas no projeto.

## Entidades

### Cliente
- **Validações**:
  - Nome não pode ser vazio.
  - CPF deve ser válido.
  - Data de nascimento não pode ser no futuro nem exceder a idade do ser humano mais velho registrado.
- **Endereço**: Incluído diretamente na entidade `Cliente` para facilidade de expansão futura.

### Contato
- **Campos obrigatórios**: `Tipo` e `Valor`.
- **Validação de tipo**: Apenas `Email` ou `Telefone` são aceitos como tipos válidos.
- **Simplificação**: A entidade armazena apenas `Valor` e `Observação`.

### ID
- **ID sequencial** foi adotado, visto que o projeto é simples e clientes podem ter muitos contatos. Optou-se por um ID sequencial devido à natureza do projeto, que será executado por uma pessoa ou grupo.

## Exceções

- **Exceções personalizadas**: Todas as exceções retornam código **BAD REQUEST (400)** quando há falha de validação.
- **ErrorHandler**: Centraliza o tratamento e resposta de erros.

## Repositórios

- **RepositorioCliente**: Interface responsável pelas operações CRUD da entidade `Cliente`.
- **RepositorioContato**: Interface responsável pelas operações CRUD da entidade `Contato`.

## DTO (Data Transfer Object)

- Facilitam a **transferência de dados** entre camadas.
- **Validação** de campos obrigatórios e criação de **estruturas complexas** de apresentação de dados.

## Persistência

- **JPA com Hibernate** para persistência de dados, utilizando o mapeamento objeto-relacional.

## Service

- **Separação de responsabilidades**: Cada classe de serviço realiza uma única tarefa, promovendo escalabilidade e fácil manutenção.

## DAO (Data Access Object)

- A camada **DAO** foi utilizada devido a um requisito não funcional de banco de dados relacional, permitindo a **inversão de dependência** e implementação do repositório.

## Controllers

- **Padrão RESTful**: A controller segue o padrão REST para expor endpoints de forma clara e estruturada.

## Como executar esse projeto, no backend

- No Backend
- Navegue até a classe principal.
- Execute o projeto clicando no botão verde, chamado Run.

- No Frontend
- Navegue até a pasta frontend.

- Abra o arquivo index.html em seu navegador.

## Dependências

- **spring-boot-starter-data-jpa**
- **spring-boot-starter-web**
- **flyway-core**
- **flyway-mysql**
- **mysql-connector-j**
- **spring-boot-devtools**
- **spring-boot-starter-test**
- **spring-boot-starter-validation**

## Configuração

No arquivo `application.properties`, defina as seguintes configurações:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/desafio
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.error.include-stacktrace=never
