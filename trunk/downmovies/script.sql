SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



CREATE SCHEMA IF NOT EXISTS `downmovies` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

USE `downmovies` ;



-- -----------------------------------------------------

-- Table `downmovies`.`Categoria`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Categoria` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Categoria` (

  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT ,

  `descricao` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`idCategoria`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `downmovies`.`Filme`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Filme` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Filme` (

  `idFilme` INT(11) NOT NULL AUTO_INCREMENT ,

  `titulo` VARCHAR(50) NOT NULL ,

  `descricao` TEXT NOT NULL ,

  `diretor` VARCHAR(50) NOT NULL ,

  `anoLancamento` INT(11) NOT NULL ,

  `idioma` INT(11) NOT NULL ,

  `legenda` INT(11) NOT NULL ,

  `formato` INT(11) NOT NULL ,

  `qualidade` INT(11) NOT NULL ,

  `tamanho` DOUBLE NOT NULL ,

  `tempoDuracao` INT(11) NOT NULL ,

  `idCategoria` INT(11) NOT NULL ,

  `extensaoImg` VARCHAR(4) NOT NULL ,

  PRIMARY KEY (`idFilme`) ,

  INDEX `fk_Filme_Categoria1` (`idCategoria` ASC) ,

  CONSTRAINT `fk_Filme_Categoria1`

    FOREIGN KEY (`idCategoria` )

    REFERENCES `downmovies`.`Categoria` (`idCategoria` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;


-- -----------------------------------------------------

-- Table `downmovies`.`Perfil`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Perfil` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Perfil` (

  `idPerfil` INT(11) NOT NULL AUTO_INCREMENT ,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`idPerfil`, `descricao`) )

ENGINE = InnoDB;

-- -----------------------------------------------------

-- Table `downmovies`.`Usuario`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Usuario` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Usuario` (

  `email` VARCHAR(100) NOT NULL ,

  `nome` VARCHAR(50) NOT NULL ,

  `sobrenome` VARCHAR(50) NOT NULL ,

  `senha` TEXT NOT NULL ,

  PRIMARY KEY (`email`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `downmovies`.`Aquisicao`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Aquisicao` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Aquisicao` (

  `idAquisicao` INT(11) NOT NULL AUTO_INCREMENT ,

  `email` VARCHAR(100) NOT NULL ,

  `idFilme` INT(11) NOT NULL ,

  `data` DATE NOT NULL ,

  PRIMARY KEY (`idAquisicao`, `email`, `idFilme`) ,

  INDEX `fk_Aquisicoes_Filmes1` (`idFilme` ASC) ,

  INDEX `fk_Aquisicao_Usuario1` (`email` ASC) ,

  CONSTRAINT `fk_Aquisicoes_Filmes1`

    FOREIGN KEY (`idFilme` )

    REFERENCES `downmovies`.`Filme` (`idFilme` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_Aquisicao_Usuario1`

    FOREIGN KEY (`email` )

    REFERENCES `downmovies`.`Usuario` (`email` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;


-- -----------------------------------------------------

-- Table `downmovies`.`Autorizacao`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Autorizacao` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Autorizacao` (

  `idPerfil` INT(11) NOT NULL ,

  `email` VARCHAR(100) NOT NULL ,

  PRIMARY KEY (`idPerfil`, `email`) ,

  INDEX `fk_Autorizacao_Perfis1` (`idPerfil` ASC) ,

  INDEX `fk_Autorizacao_Usuario1` (`email` ASC) ,

  CONSTRAINT `fk_Autorizacao_Perfis1`

    FOREIGN KEY (`idPerfil` )

    REFERENCES `downmovies`.`Perfil` (`idPerfil` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_Autorizacao_Usuario1`

    FOREIGN KEY (`email` )

    REFERENCES `downmovies`.`Usuario` (`email` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;



-- -----------------------------------------------------

-- Table `downmovies`.`LOGS`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Logs` ;


CREATE  TABLE IF NOT EXISTS `downmovies`.`Logs` (

  `user_id` VARCHAR(20) NOT NULL,
  
  `data` DATETIME NOT NULL,
  
  `logger` VARCHAR(50) NOT NULL,
  
  `level` VARCHAR(10) NOT NULL,
  
  `mensagem` VARCHAR(1000) NOT NULL
  
 )

ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into categoria(descricao) values('Acao');
insert into categoria(descricao) values('Aventura');
insert into categoria(descricao) values('Classico');
insert into categoria(descricao) values('Comedia');
insert into categoria(descricao) values('Documentario');
insert into categoria(descricao) values('Drama');
insert into categoria(descricao) values('Faroeste');
insert into categoria(descricao) values('Ficcao');
insert into categoria(descricao) values('Infantil');
insert into categoria(descricao) values('Musical');
insert into categoria(descricao) values('Romance');
insert into categoria(descricao) values('Suspense');
insert into categoria(descricao) values('Terror');
insert into categoria(descricao) values('Policial');
insert into categoria(descricao) values('Nao Disponivel');


insert into perfil(descricao) values ('administrador');
insert into perfil(descricao) values ('usuario');

INSERT INTO usuario VALUES('admin@admin.com', 'admin', 'admin', 'aaaa1111');
INSERT INTO usuario VALUES('user@user.com', 'user', 'user', 'aaaa1111');

insert autorizacao values(1, 'admin@admin.com');
insert autorizacao values(2, 'user@user.com');
