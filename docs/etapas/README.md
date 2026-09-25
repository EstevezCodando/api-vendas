# Versões intermediárias (andaimes)

Estes arquivos **não fazem parte do projeto entregue**. Eles existem só para
permitir subir a atividade ao GitHub em etapas, uma branch por exercício, com
cada etapa compilando e rodando de verdade.

O projeto final está pronto na raiz do `AT/`. Cada arquivo aqui é o estado
daquele mesmo arquivo em um ponto anterior da construção:

| Arquivo | Vira | Usado na etapa |
|---|---|---|
| `application.properties.ex3` | `fornecedores-service/src/main/resources/application.properties` | Exercício 3 — antes da externalização |
| `FornecedorService.ex5.java` | `.../service/FornecedorService.java` | Exercício 5 — só consultas |
| `FornecedorController.ex5.java` | `.../controller/FornecedorController.java` | Exercício 5 — GET e 404 |
| `FornecedorService.ex9.java` | `.../service/FornecedorService.java` | Exercício 9 — consultas + salvar |
| `FornecedorController.ex9.java` | `.../controller/FornecedorController.java` | Exercício 9 — GET, 404 e POST |
| `docker-compose.ex11.yml` | `docker-compose.yml` | Exercício 11 — antes da correção de ordem de subida |

As duas últimas etapas (`docker-compose.ex11.yml` e o `TokenFilter` original,
que já vem no projeto da aula) servem para reproduzir dois problemas reais
antes de corrigi-los nas branches `fix/`:

- com o `docker-compose.ex11.yml`, os microsserviços sobem antes do Config
  Server e ficam todos na porta 8080;
- com o `TokenFilter` original, o gateway devolve 401 na consulta de
  fornecedores.

O passo a passo completo está em `../PASSO-A-PASSO-GITHUB.md`.
