# The Cats API

API REST para gerenciamento de raças e imagens de gatos, integrando com [TheCatAPI](https://thecatapi.com/).

## Funcionalidades

- Importação de raças de gatos e suas imagens a partir da TheCatAPI.
- Importação de imagens por categoria (ex: gatos de chapéu, óculos).
- Consulta de raças por temperamento, origem ou ID.
- Exposição de endpoints REST para manipulação e consulta dos dados.
- Banco de dados em memória (H2) para desenvolvimento e testes.
- Pronto para monitoramento com Prometheus e Grafana.

## Endpoints

### Raças

- `POST /racas/importar`  
  Importa todas as raças e imagens da TheCatAPI.

- `GET /racas`  
  Lista todas as raças cadastradas.

- `GET /racas/{id}`  
  Busca raça pelo ID.

- `GET /racas/temperamento/{valor}`  
  Busca raças por temperamento.

- `GET /racas/origem/{valor}`  
  Busca raças por origem.

### Imagens

- `POST /imagens/importar/chapeu`  
  Importa imagens de gatos com chapéu.

- `POST /imagens/importar/oculos`  
  Importa imagens de gatos com óculos.

## Como rodar

### Pré-requisitos

- Java 21+
- Maven

### Executando localmente

```sh
./mvnw spring-boot:run
```

Acesse o H2 Console em:  
`http://localhost:8080/h2-console`  
(JDBC URL: `jdbc:h2:mem:cats`, usuário: `sa`, senha: em branco)

### Docker

Para rodar com Docker Compose (app, H2, Prometheus, Grafana):

Imagem disponibilizada no DockerHub.

```sh
docker pull ppedde/catsapi:0.0.3
```

```sh
docker-compose up
```

A aplicação estará em `http://localhost:8080`. 
Precisamos popular o BD H2. Use a collection do Insomnia para isso. Método POST (importar racas).
Após poderá rodar no insomnia o método GET para testar as API.

## Configuração

Veja [src/main/resources/application.yml](src/main/resources/application.yml) para configurações de banco, JPA e endpoints de monitoramento.

## Monitoramento

- Prometheus: `http://localhost:9090`
- Grafana: `http://localhost:3000`
- Usuário padrão do Grafana Admin e senha Admin. Primeiro acesso irá solicitar a troca da senha.

## Estrutura do Projeto

- [`com.priscila.catsapi.controller`](src/main/java/com/priscila/catsapi/controller) — Controllers REST
- [`com.priscila.catsapi.service`](src/main/java/com/priscila/catsapi/service) — Serviços de negócio
- [`com.priscila.catsapi.model`](src/main/java/com/priscila/catsapi/model) — Entidades JPA
- [`com.priscila.catsapi.repository`](src/main/java/com/priscila/catsapi/repository) — Repositórios Spring Data

## Licença

MIT

---
Inspirado por [TheCatAPI](https://thecatapi.com/).
