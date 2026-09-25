# Spring Microservices — AT

**Aluno:** Jean Michael Estevez Alvarez

Microsserviço acrescentado nesta atividade: **fornecedores-service**, na porta **8084**.

Ele foi criado a partir do `clientes-service` e integrado à arquitetura que já
existia: registro no Eureka, configuração vinda do Config Server, acesso pelo
Spring Cloud Gateway sem rota manual, chamada ao `produtos-service` por
OpenFeign, execução em container e pipeline de CI no GitHub Actions.

---

## Portas

| Serviço | Porta | Papel |
|---|---|---|
| eureka-server | 8761 | Service discovery |
| config-server | 8888 | Serve os `.properties` da pasta `config-repo` |
| produtos-service | 8081 | Catálogo de produtos |
| vendas-service | 8082 | Vendas |
| clientes-service | 8083 | Clientes (usado como molde) |
| **fornecedores-service** | **8084** | **Criado nesta atividade** |
| gateway | 8085 | Porta de entrada única |
| auth-service | 8086 | Login/JWT do trabalho anterior |

O `auth-service` ocupava a 8084 e foi movido para a 8086, porque o enunciado
reserva a 8084 para o `fornecedores-service`.

---

## Endpoints do fornecedores-service

| Método | Rota | Resposta |
|---|---|---|
| GET | `/fornecedores` | lista todos |
| GET | `/fornecedores/{id}` | o fornecedor, ou **404** se o id não existir |
| POST | `/fornecedores` | **201 Created** com o objeto salvo e o id gerado |
| GET | `/fornecedores/produtos` | produtos do `produtos-service`, via OpenFeign |

Pelo gateway, as mesmas rotas ficam em `http://localhost:8085/fornecedores-service/...`.

---

## Como rodar

### Com Docker (um comando só)

```bash
docker compose up --build
```

```bash
curl http://localhost:8085/fornecedores-service/fornecedores
```

### Sem Docker

A ordem importa: o `fornecedores-service` busca a porta no Config Server, então
ele precisa subir depois dele. Cada linha em um terminal:

```bash
java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
java -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar
java -jar produtos-service/target/produtos-service-0.0.1-SNAPSHOT.jar
java -jar fornecedores-service/target/fornecedores-service-0.0.1-SNAPSHOT.jar
java -jar gateway/target/gateway-0.0.1-SNAPSHOT.jar
```

O `config-server` precisa ser iniciado a partir da raiz do projeto, que é onde
está a pasta `config-repo`.

Console do H2: `http://localhost:8084/h2-console`, com a URL JDBC
`jdbc:h2:mem:fornecedoresdb`, usuário `sa` e senha em branco.

---

## Onde está cada exercício

| Exercício | Onde |
|---|---|
| 1 — projeto rodando | `eureka-server/`, `produtos-service/` |
| 2 — branch e PR | branch `atividade-jeanalvarez`, este arquivo |
| 3 — o microsserviço | `fornecedores-service/pom.xml`, `FornecedoresServiceApplication.java` |
| 4 — entidade e H2 | `model/Fornecedor.java`, `repository/`, `config/DataInitializer.java` |
| 5 — consultas e 404 | `service/FornecedorService.java`, `controller/FornecedorController.java` |
| 6 — Eureka | dependência no `pom.xml` e `@EnableDiscoveryClient` |
| 7 — Config Server | `config-repo/fornecedores-service.properties` |
| 8 — Gateway | `gateway/src/main/resources/application.properties` |
| 9 — cadastro | método `cadastrar` no controller |
| 10 — OpenFeign | `client/ProdutoClient.java`, `dto/ProdutoDTO.java` |
| 11 — Docker | `fornecedores-service/Dockerfile`, `docker-compose.yml`, `config-repo/fornecedores-service-docker.properties` |
| 12 — CI | `.github/workflows/ci.yml` |

As evidências da execução estão em [docs/prints](docs/prints), com as saídas de
terminal originais em `docs/prints/raw`.

---

## Material de apoio

Anotações usadas durante o desenvolvimento:

- [Documentação dos microsserviços](https://claude.ai/code/artifact/2c541174-0b49-486d-9ee2-1682e91b8daf)
- [Guia de implementação com Docker](https://claude.ai/code/artifact/a5f3c8e5-81de-4791-a16c-a71d4aa45f0f)
- [Guia de implementação com Kubernetes](https://claude.ai/code/artifact/68cd2944-af3d-430f-8e6f-ea27c4186982)
