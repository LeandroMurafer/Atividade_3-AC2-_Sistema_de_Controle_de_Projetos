# Gestão de Projetos

API REST em Spring Boot 3 (Java 17) com Spring Web, Spring Data JPA, H2 e Lombok, mais um protótipo de tela de Projetos.

## Como executar

```bash
mvn spring-boot:run
```

- Tela de projetos: http://localhost:8080/
- Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:gestaodb`, usuário `sa`, senha vazia)

Ao subir, `data.sql` carrega alguns setores, funcionários e projetos de exemplo.

## Modelo

- **Setor** 1—N **Funcionario**
- **Projeto** N—N **Funcionario** (tabela `projeto_funcionario`)

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | `/projetos` | Cadastra projeto (`ProjetoDTO`) |
| GET | `/projetos/{id}` | Projeto com funcionários vinculados |
| POST | `/projetos/{idProjeto}/funcionarios/{idFuncionario}` | Vincula funcionário ao projeto |
| GET | `/projetos` | Lista todos (usado pela tela) |
| GET | `/projetos/periodo?inicio=yyyy-MM-dd&fim=yyyy-MM-dd` | Projetos com início no intervalo |
| POST | `/funcionarios` | Cadastra funcionário (`FuncionarioDTO`) |
| GET | `/funcionarios/{id}/projetos` | Projetos do funcionário |
| GET | `/funcionarios` | Lista funcionários |
| POST | `/setores` | Cadastra setor (`SetorDTO`) |
| GET | `/setores/{id}` | Setor com funcionários |
| GET | `/setores` | Lista setores com funcionários |

## Exemplos

```bash
curl -X POST localhost:8080/projetos -H 'Content-Type: application/json' \
  -d '{"nome":"Novo App","descricao":"MVP","dataInicio":"2026-11-01","dataFim":null}'

curl -X POST localhost:8080/projetos/1/funcionarios/3

curl 'localhost:8080/projetos/periodo?inicio=2026-01-01&fim=2026-06-30'
```

## Regras de negócio e erros

- Data de fim não pode ser anterior à de início (422).
- Setor com nome único; funcionário com e-mail único (422).
- Funcionário não pode ser vinculado duas vezes ao mesmo projeto (422).
- Recurso inexistente retorna 404; dados inválidos retornam 400 com a mensagem em `mensagem`.

<img width="1437" height="809" alt="image" src="https://github.com/user-attachments/assets/7e1a6da4-af1a-45d8-9235-8ccc4be292f4" />

<img width="1439" height="763" alt="image" src="https://github.com/user-attachments/assets/8714f32f-7d81-4812-a408-8c5cf9a557e8" />


