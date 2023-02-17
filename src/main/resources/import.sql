insert into estado (id, nome) values (1, 'Paraná');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Maringá', 1);
insert into cidade (id, nome, estado_id) values (2, 'Curitiba', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);


insert into pessoa (id, nome, data_nascimento) values (1, 'Karla Pereira', '1972-06-16');
insert into pessoa (id, nome, data_nascimento) values (2, 'Vinícius Pereira', '2003-01-08');
insert into pessoa (id, nome, data_nascimento) values (3, 'Vitória Karina Pereira', '2006-09-22');

insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (1, '87083-675', 'true', 'Rua Pioneiro Olinto Mariani', '1736', 2, 2);
insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (2, '87083-567', 'true', 'Rua Pioneiro Olinto Mariani', '1736', 3, 3);
insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (3, '87083-675', 'true', 'Rua Pioneiro Olinto Mariani', '1736', 1, 1);



insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (4, '87083-675', 'false', 'Rua São Paulo', '1736', 1, 1);
insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (5, '87083-675', 'false', 'Av. Herval', '1736', 2, 2);
insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (6, '87083-675', 'false', 'Av. Duque de Caxias', '1736', 3, 3);
insert into endereco (id, cep, is_principal, logradouro, numero, cidade_id, pessoa_id) values (7, '87083-675', 'false', 'Rua Tiradente ', '1736', 1, 1);




