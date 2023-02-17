CREATE TABLE estado (
    id BIGINT(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    estado_id BIGINT(20) NOT NULL,
    FOREIGN KEY (estado_id) REFERENCES estado(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estado (id, nome) VALUES (1,'Acre');
INSERT INTO estado (id, nome) VALUES (2,'Bahia');
INSERT INTO estado (id, nome) VALUES (3,'Goiás');
INSERT INTO estado (id, nome) VALUES (4,'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (5,'Santa Catarina');
INSERT INTO estado (id, nome) VALUES (6,'São Paulo');


INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Rio Branco', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Cruzeiro do Sul', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'Salvador', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Porto Seguro', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Santana', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (6, 'Goiânia', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (7, 'Itumbiara', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (8, 'Novo Brasil', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (9, 'Belo Horizonte', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (10, 'Uberlândia', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (11, 'Montes Claros', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (12, 'Florianópolis', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (13, 'Criciúma', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (14, 'Camboriú', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (15, 'Lages', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (16, 'São Paulo', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (17, 'Ribeirão Preto', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (18, 'Campinas', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (19, 'Santos', 6);
