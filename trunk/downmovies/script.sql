SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



DROP SCHEMA IF EXISTS `filmes` ;

CREATE SCHEMA IF NOT EXISTS `filmes` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

USE `filmes` ;



-- -----------------------------------------------------

-- Table `filmes`.`Categorias`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `filmes`.`Categorias` ;



CREATE  TABLE IF NOT EXISTS `filmes`.`Categorias` (

  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT ,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`id_categoria`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `filmes`.`Filmes`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `filmes`.`Filmes` ;



CREATE  TABLE IF NOT EXISTS `filmes`.`Filmes` (

  `id_filme` INT(11) NOT NULL AUTO_INCREMENT ,

  `titulo` VARCHAR(50) NOT NULL ,

  `descricao` TEXT NOT NULL ,

  `diretor` VARCHAR(50) NOT NULL ,

  `ano_lancamento` INT(11) NOT NULL ,

  `idioma` VARCHAR(30) NOT NULL ,

  `legenda` VARCHAR(30) NOT NULL ,

  `formato` VARCHAR(30) NOT NULL ,

  `qualidade` VARCHAR(30) NOT NULL ,

  `tamanho` DOUBLE NOT NULL ,

  `tempo_duracao` INT(11) NOT NULL ,

  `id_categoria` INT(11) NOT NULL ,

  PRIMARY KEY (`id_filme`) ,

  INDEX `fk_Filmes_Categorias` (`id_categoria` ASC) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `filmes`.`Perfis`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `filmes`.`Perfis` ;



CREATE  TABLE IF NOT EXISTS `filmes`.`Perfis` (

  `id_perfil` INT(11) NOT NULL AUTO_INCREMENT ,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`id_perfil`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `filmes`.`Usuarios`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `filmes`.`Usuarios` ;



CREATE  TABLE IF NOT EXISTS `filmes`.`Usuarios` (

  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT ,

  `nome` VARCHAR(50) NOT NULL ,

  `sobrenome` VARCHAR(50) NOT NULL ,

  `email` VARCHAR(200) NOT NULL ,

  `senha` TEXT NOT NULL ,

  `id_perfil` INT(11) NOT NULL ,

  PRIMARY KEY (`id_usuario`) ,

  INDEX `fk_Usuarios_Perfis1` (`id_perfil` ASC) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `filmes`.`Aquisicoes`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `filmes`.`Aquisicoes` ;



CREATE  TABLE IF NOT EXISTS `filmes`.`Aquisicoes` (

  `id_aquisicao` INT(11) NOT NULL AUTO_INCREMENT ,

  `id_usuario` INT(11) NOT NULL ,

  `id_filme` INT(11) NOT NULL ,

  `data` DATE NOT NULL ,

  PRIMARY KEY (`id_aquisicao`, `id_usuario`, `id_filme`) ,

  INDEX `fk_Aquisicoes_Filmes1` (`id_filme` ASC) ,

  INDEX `fk_Aquisicoes_Usuarios1` (`id_usuario` ASC) )

ENGINE = InnoDB;







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

