/* Database GISA */
CREATE DATABASE IF NOT EXISTS gisa;

USE gisa;

/* Table Exemplo */
CREATE TABLE Exemplo (
    id int(10) NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    idade int(6),
    data DATETIME,
    valor double,
    PRIMARY KEY (id)
);

/* Inserts Exemplo */
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Diego Moraes Leite',99,now(),99.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Karina Bastos',11,now(),11.99);
COMMIT;

/* Database SAF */
CREATE DATABASE IF NOT EXISTS saf;

USE saf;

/* Table Exemplo */
CREATE TABLE Exemplo (
    id int(10) NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    idade int(6),
    data DATETIME,
    valor double,
    PRIMARY KEY (id)
);

/* Inserts Exemplo */
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Leonard Hofstadter',21,now(),22.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Richard White',42,now(),12.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Gina Rockmille',34,now(),19.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Raul Boiler',18,now(),17.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Mike Palliot',33,now(),66.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Nathan Lifty',42,now(),57.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Fernanda River Black',53,now(),36.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Kate Smith',65,now(),51.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Elizabeth Newton',78,now(),64.99);
INSERT INTO `Exemplo` (`nome`,`idade`,`data`,`valor`) VALUES ('Kristin Atkins',87,now(),17.99);

COMMIT;