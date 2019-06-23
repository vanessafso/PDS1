CREATE TABLE `usuario`(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(100),
	email VARCHAR(50),
	telefone VARCHAR(20),
	data_nascimento DATE,
	cidade_residencia VARCHAR(50),
	senha VARCHAR(20),
	PRIMARY KEY (`id`)
);

CREATE TABLE `destino`(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(100),
	pais VARCHAR(30),
	gastos FLOAT,
	clima VARCHAR(10),
	PRIMARY KEY (`id`)
);

CREATE TABLE `busca`(
	id INT NOT NULL AUTO_INCREMENT,
	uid INT NOT NULL,
	data_ida DATE,
	data_volta DATE,
	gastos FLOAT,
	PRIMARY KEY (`id`, `uid`),
	CONSTRAINT `fk_busca_uid`
		FOREIGN KEY (`uid`)
		REFERENCES `usuario` (`id`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);