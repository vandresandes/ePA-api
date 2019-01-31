USE [epa]
GO

DROP TABLE [dbo].[tb_checklist]
DROP TABLE [dbo].[tb_nucleo]
DROP TABLE [dbo].[tb_tipo_processo]
DROP TABLE [dbo].[tb_termo_especifico]
DROP TABLE [dbo].[tb_termo_geral]
DROP TABLE [dbo].[tb_documento]
DROP TABLE [dbo].[tb_origem]
DROP TABLE [dbo].[tb_materia]
DROP TABLE [dbo].[tb_motivo_sigilo_segredo_justica]


CREATE TABLE tb_materia (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) NOT NULL,
	CONSTRAINT PK__tb_materia PRIMARY KEY (id)
);

CREATE TABLE tb_nucleo (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) UNIQUE NOT NULL,
	fk_id_materia BIGINT,
	CONSTRAINT PK__tb_nucleo PRIMARY KEY (id),
	CONSTRAINT FK__tb_nucleo_fk_id_materia FOREIGN KEY (fk_id_materia) REFERENCES tb_materia(id)
);

CREATE TABLE tb_tipo_processo (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK__tb_tipo_processo PRIMARY KEY (id)
);

CREATE TABLE tb_termo_geral (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK__tb_termo_geral PRIMARY KEY (id)
);

CREATE TABLE tb_termo_especifico (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(200) UNIQUE NOT NULL,
	CONSTRAINT PK__tb_termo_especifico PRIMARY KEY (id)
);

CREATE TABLE tb_documento (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(2000) NOT NULL,
	tipo VARCHAR(20),
	CONSTRAINT PK__tb_documento PRIMARY KEY (id)
);

CREATE TABLE tb_checklist (
	id BIGINT IDENTITY(1,1) NOT NULL,
	fk_id_nucleo BIGINT NOT NULL,
	fk_id_tipo_processo BIGINT NOT NULL,
	fk_id_termo_geral BIGINT NOT NULL,
	fk_id_termo_especifico BIGINT NOT NULL,
	fk_id_documento BIGINT NOT NULL,
	bo_obrigatorio BIT NOT NULL,
	condicao VARCHAR(300),
	bo_apresentar_justificativa_condicao BIT NOT NULL,
	complexidade VARCHAR(10),
	CONSTRAINT PK__tb_checklist PRIMARY KEY (id),
	CONSTRAINT FK__tb_checklist_fk_id_nucleo FOREIGN KEY (fk_id_nucleo) REFERENCES tb_nucleo(id),
	CONSTRAINT FK__tb_checklist_fk_id_tipo_processo FOREIGN KEY (fk_id_tipo_processo) REFERENCES tb_tipo_processo(id),
	CONSTRAINT FK__tb_checklist_fk_id_termo_geral FOREIGN KEY (fk_id_termo_geral) REFERENCES tb_termo_geral(id),
	CONSTRAINT FK__tb_checklist_fk_id_termo_especifico FOREIGN KEY (fk_id_termo_especifico) REFERENCES tb_termo_especifico(id),
	CONSTRAINT FK__tb_checklist_fk_id_documento FOREIGN KEY (fk_id_documento) REFERENCES tb_documento(id)
);

CREATE TABLE tb_orgao (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR(200),
	CONSTRAINT PK__tb_orgao PRIMARY KEY (id)
);

CREATE TABLE tb_motivo_sigilo_segredo_justica (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(200) NOT NULL,
	CONSTRAINT PK__tb_motivo_sigilo_segredo_justica PRIMARY KEY (id)
);