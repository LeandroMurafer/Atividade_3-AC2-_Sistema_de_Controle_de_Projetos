INSERT INTO setor (nome) VALUES ('Tecnologia');
INSERT INTO setor (nome) VALUES ('Financeiro');
INSERT INTO setor (nome) VALUES ('Recursos Humanos');

INSERT INTO funcionario (nome, email, setor_id) VALUES ('Ana Souza', 'ana.souza@empresa.com', 1);
INSERT INTO funcionario (nome, email, setor_id) VALUES ('Bruno Lima', 'bruno.lima@empresa.com', 1);
INSERT INTO funcionario (nome, email, setor_id) VALUES ('Carla Mendes', 'carla.mendes@empresa.com', 2);
INSERT INTO funcionario (nome, email, setor_id) VALUES ('Diego Alves', 'diego.alves@empresa.com', 3);

INSERT INTO projeto (nome, descricao, data_inicio, data_fim) VALUES ('Portal do Cliente', 'Novo portal de autoatendimento', '2026-02-01', '2026-08-30');
INSERT INTO projeto (nome, descricao, data_inicio, data_fim) VALUES ('Migração Financeira', 'Migração do sistema de contas a pagar', '2026-05-10', NULL);

INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (1, 1);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (1, 2);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (2, 3);
