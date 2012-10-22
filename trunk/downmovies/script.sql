SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



DROP SCHEMA IF EXISTS `downmovies` ;

CREATE SCHEMA IF NOT EXISTS `downmovies` ;

USE `downmovies` ;



-- -----------------------------------------------------

-- Table `downmovies`.`Categoria`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Categoria` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Categoria` (

  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`id_categoria`) )

ENGINE = InnoDB;


-- -----------------------------------------------------

-- Table `downmovies`.`Qualidade`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Qualidade` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Qualidade` (

  `id_qualidade` INT(11) NOT NULL AUTO_INCREMENT,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`id_qualidade`) )

ENGINE = InnoDB;




-- -----------------------------------------------------

-- Table `downmovies`.`Formato`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Formato` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Formato` (

  `id_formato` INT(11) NOT NULL AUTO_INCREMENT,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`id_formato`) )

ENGINE = InnoDB;






-- -----------------------------------------------------

-- Table `downmovies`.`downmovies`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`filme` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`filme` (

  `id_filme` INT(11) NOT NULL AUTO_INCREMENT ,

  `titulo` VARCHAR(50) NOT NULL ,

  `descricao` TEXT NOT NULL ,

  `diretor` VARCHAR(50) NOT NULL ,

  `ano_lancamento` INT(11) NOT NULL ,

  `idioma` VARCHAR(30) NOT NULL ,

  `legenda` VARCHAR(30) NOT NULL ,

  `id_formato` INT(11)  NOT NULL ,

  `id_qualidade` INT(11)  NOT NULL ,

  `tamanho` DOUBLE NOT NULL ,

  `tempo_duracao` INT(11) NOT NULL ,

  `id_categoria` INT(11) NOT NULL ,

  PRIMARY KEY (`id_filme`) ,

  INDEX `fk_filme_Categoria` (`id_categoria` ASC)
  INDEX `fk_filme_Formato` (`id_formato` ASC)
  INDEX `fk_filme_Qualidade` (`id_qualidade` ASC)
  )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `downmovies`.`Perfis`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Perfil` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Perfil` (

  `id_perfil` INT(11) NOT NULL AUTO_INCREMENT ,

  `descricao` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`id_perfil`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `downmovies`.`Usuarios`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Usuario` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Usuario` (

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

-- Table `downmovies`.`Aquisicoes`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `downmovies`.`Aquisicao` ;



CREATE  TABLE IF NOT EXISTS `downmovies`.`Aquisicao` (

  `id_aquisicao` INT(11) NOT NULL AUTO_INCREMENT ,

  `id_usuario` INT(11) NOT NULL ,

  `id_filme` INT(11) NOT NULL ,

  `data` DATE NOT NULL ,

  PRIMARY KEY (`id_aquisicao`, `id_usuario`, `id_filme`) ,

  INDEX `fk_Aquisicoes_downmovies1` (`id_filme` ASC) ,

  INDEX `fk_Aquisicoes_Usuarios1` (`id_usuario` ASC) )

ENGINE = InnoDB;







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
