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

CREATE TABLE `preferencias`(
	uid INT NOT NULL,
	clima VARCHAR(20),
	transporte VARCHAR(20),
	perfil VARCHAR(100),
	servicos VARCHAR(100),
	alimentacao VARCHAR(100),
	pagamento VARCHAR(100),
	PRIMARY KEY (`uid`),
	CONSTRAINT `fk_preferencias_uid`
		FOREIGN KEY (`uid`)
		REFERENCES `usuario` (`id`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

INSERT INTO `usuario`(`nome`, `email`, `telefone`, `data_nascimento`, `cidade_residencia`, `senha`) VALUES ("Exemplo","exemplo@exemplo.com","998989898","2000-12-13","Uberlândia","123");
INSERT INTO `preferencias`(`uid`, `clima`, `transporte`, `perfil`, `servicos`, `alimentacao`, `pagamento`) VALUES (1,"Frio","Avião","Aventureiro,Explorador","","Culinária local","Cartão");

INSERT INTO `destino`(`nome`, `pais`, `gastos`, `clima`) VALUES ("Caldas Novas","Brasil",800.00,"Quente");
INSERT INTO `destino`(`nome`, `pais`, `gastos`, `clima`) VALUES ("Miami","Estados Unidos",5000.00,"Quente");
INSERT INTO `destino`(`nome`, `pais`, `gastos`, `clima`) VALUES ("Santiago","Chile",2000.00,"Frio");
INSERT INTO `destino`(`nome`, `pais`, `gastos`, `clima`) VALUES ("Barcelona","Espanha",6000.00,"Quente");
INSERT INTO `destino`(`nome`, `pais`, `gastos`, `clima`) VALUES ("Sidney","Austrália",8000.00,"Quente");
INSERT INTO `destino`(`nome`, `pais`, `gastos`, `clima`) VALUES ("Toronto","Canadá",5500.00,"Frio");