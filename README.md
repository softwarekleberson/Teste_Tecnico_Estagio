# Arquitetura do Projeto

Este documento descreve a arquitetura do projeto, detalhando as decisões de design e as tecnologias utilizadas em cada camada.

## Entidades

### Cliente
A entidade `Cliente` foi criada para validar os requisitos do projeto, com as seguintes regras de validação:

- **Nome**: Não pode ser vazio.
- **CPF**: Deve ser válido.
- **Data de Nascimento**: A validação garante que a data não seja do futuro (não pode ser amanhã) e que o usuário não seja mais velho que o ser humano mais velho registrado.

Além disso, foi requisitado que o cliente possua um endereço. Embora nenhum campo específico tenha sido pedido, a decisão foi incorporar os dados de endereço diretamente na entidade `Cliente`, o que facilita a expansão do projeto no futuro.

### Contato
A entidade `Contato` contém os seguintes requisitos:

- **Campos obrigatórios**: `Tipo` e `Valor`. 
- Para garantir consistência, a entidade `Contato` só valida os tipos de contato como `Email` ou `Telefone`. 
- Foi utilizado um tipo de dado simplificado, que aceita apenas o `Valor` e a `Observação`.

### Id
Como o projeto é simples e clientes podem ter múltiplos contatos, e considerando que a arquitetura não foi completamente definida para uma solução distribuída, foi adotado um **ID sequencial** (auto-incremento) para facilitar a implementação. Não foi utilizado UUID, pois o projeto provavelmente será executado por um grupo ou uma pessoa.

## Exceções

### Personalização das Exceções
As exceções no projeto são personalizadas e retornam o código **BAD REQUEST (400)** para requisições que não atendem ao padrão esperado. 

Todas as exceções são tratadas através de um `ErrorHandler`, que centraliza o gerenciamento e resposta de erros.

## Repositórios

As ações de persistência de dados são gerenciadas através de interfaces, a saber:

- **RepositorioCliente**: Responsável pelas operações de CRUD para a entidade `Cliente`.
- **RepositorioContato**: Responsável pelas operações de CRUD para a entidade `Contato`.

Essas interfaces seguem o padrão de repositório, garantindo a separação de responsabilidades e mantendo a camada de persistência desacoplada das demais camadas do sistema.

## DTO (Data Transfer Object)

As DTOs são utilizadas para as seguintes finalidades:

- **Transferência de Dados**: Facilitam a transferência de dados entre camadas do sistema.
- **Validação**: Garantem que campos obrigatórios sejam preenchidos e que a estrutura de dados atenda aos requisitos do sistema.
- **Estruturas Complexas**: Permitem a criação de representações mais elaboradas dos dados, como o exemplo de `DadosDetalhamentoCliente`, que apresenta os dados de maneira estruturada e legível.

## Persistência

A persistência de dados é feita através do padrão **JPA** (Java Persistence API), utilizando o framework **Hibernate**. Essa abordagem garante a abstração da camada de persistência e permite um mapeamento objeto-relacional eficiente.

## Service

A camada de **Service** foi implementada para seguir o princípio da **separação de responsabilidades**. Cada classe na camada de serviço é responsável por uma única tarefa, mantendo o código organizado e facilitando a manutenção. 

Além disso, essa separação permite a escalabilidade do sistema, pois facilita a adição de novas funcionalidades sem impactar diretamente as camadas de controle ou persistência.

## DAO (Data Access Object)

A camada **DAO** foi utilizada, pois um requisito não funcional solicitou o uso de um banco de dados relacional. O DAO possibilita o uso do padrão de **Inversão de Dependência**, permitindo que a implementação do repositório seja desacoplada das demais camadas.

## Controllers

A camada **Controller** segue o padrão **RESTful** para exposição da API. Cada endpoint está configurado para responder às requisições HTTP de forma clara e objetiva, respeitando os princípios REST e facilitando a integração com outros sistemas ou interfaces de usuário.
