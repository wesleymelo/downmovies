SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



DROP SCHEMA IF EXISTS `downmovies` ;

CREATE SCHEMA IF NOT EXISTS `downmovies` ;

USE `downmovies` ;



-- -----------------------------------------------------

-- Table `downmovies`.`downmovies`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`filme` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`filme` (

  `idFilme` INT(11) NOT NULL AUTO_INCREMENT ,

  `titulo` VARCHAR(50) NOT NULL ,

  `descricao` TEXT NOT NULL ,

  `diretor` VARCHAR(50) NOT NULL ,

  `categoria` VARCHAR(50) NOT NULL ,
  
  `anoLancamento` INT(11) NOT NULL ,

  `idioma` VARCHAR(30) NOT NULL ,

  `legenda` VARCHAR(30) NOT NULL ,

  `formato` VARCHAR(30)  NOT NULL ,

  `qualidade` VARCHAR(30)  NOT NULL ,

  `tamanho` DOUBLE NOT NULL ,

  `tempoDuracao` INT(11) NOT NULL ,
  
  `nomeArquivoFilme` VARCHAR(100) NULL ,
  
  `nomeArquivoImagem` VARCHAR(100) NULL ,

  PRIMARY KEY (`idFilme`) )

ENGINE = InnoDB;


-- -----------------------------------------------------

-- Table `downmovies`.`Perfis`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Perfil` ;


CREATE  TABLE IF NOT EXISTS `downmovies`.`Perfil` (

  `idPerfil` INT(11) NOT NULL AUTO_INCREMENT ,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`idPerfil`, `descricao`) )

ENGINE = InnoDB;


-- -----------------------------------------------------

-- Table `downmovies`.`Usuarios`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Usuario` ;


CREATE  TABLE IF NOT EXISTS `downmovies`.`Usuario` (

  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT ,

  `nome` VARCHAR(50) NOT NULL ,

  `sobrenome` VARCHAR(50) NOT NULL ,

  `email` VARCHAR(200) NOT NULL ,

  `senha` TEXT NOT NULL ,

  PRIMARY KEY (`idUsuario`) )

ENGINE = InnoDB;


-- -----------------------------------------------------

-- Table `downmovies`.`Autorizacao`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Autorizacao` ;


CREATE  TABLE IF NOT EXISTS `downmovies`.`Autorizacao` (

  `idPerfil` INT(11) NOT NULL ,

  `idUsuario` INT(11) NOT NULL ,

  PRIMARY KEY (`idPerfil`, `idUsuario`) ,

  INDEX `fk_Autorizacao_Perfis1` (`idPerfil` ASC) ,

  INDEX `fk_Autorizacao_Usuarios1` (`idUsuario` ASC) ,

  CONSTRAINT `fk_Autorizacao_Perfis1`

    FOREIGN KEY (`idPerfil` )

    REFERENCES `downmovies`.`Perfil` (`idPerfil` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_Autorizacao_Usuarios1`

    FOREIGN KEY (`idUsuario` )

    REFERENCES `downmovies`.`Usuario` (`idUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;

-- -----------------------------------------------------

-- Table `downmovies`.`Aquisicoes`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Aquisicao` ;


CREATE  TABLE IF NOT EXISTS `downmovies`.`Aquisicao` (

  `idAquisicao` INT(11) NOT NULL AUTO_INCREMENT ,

  `idUsuario` INT(11) NOT NULL ,

  `idFilme` INT(11) NOT NULL ,

  `data` DATE NOT NULL ,

  PRIMARY KEY (`idAquisicao`, `idUsuario`, `idFilme`) ,

  INDEX `fk_Aquisicoes_Filmes1` (`idFilme` ASC) ,

  INDEX `fk_Aquisicoes_Usuarios1` (`idUsuario` ASC) ,

  CONSTRAINT `fk_Aquisicoes_Filmes1`

    FOREIGN KEY (`idFilme` )

    REFERENCES `downmovies`.`Filme` (`idFilme` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_Aquisicoes_Usuarios1`

    FOREIGN KEY (`idUsuario` )

    REFERENCES `downmovies`.`Usuario` (`idUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

