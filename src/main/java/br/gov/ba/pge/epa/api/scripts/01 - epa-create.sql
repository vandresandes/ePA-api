DROP TABLE [dbo].[tb_checklist]
DROP TABLE [dbo].[tb_nucleo]
DROP TABLE [dbo].[tb_tipo_processo]
DROP TABLE [dbo].[tb_termo_especifico]
DROP TABLE [dbo].[tb_termo_geral]
DROP TABLE [dbo].[tb_documento]


CREATE TABLE tb_nucleo (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_tipo_processo (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_termo_geral (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_termo_especifico (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(200) UNIQUE NOT NULL
);

CREATE TABLE tb_documento (
	id BIGINT IDENTITY(1,1) NOT NULL,
	nome VARCHAR(2000) NOT NULL,
	tipo VARCHAR(20)
);

CREATE TABLE tb_checklist (
	id BIGINT IDENTITY(1,1) NOT NULL,
	fk_id_nucleo BIGINT NOT NULL,
	fk_id_tipo_processo BIGINT NOT NULL,
	fk_id_termo_geral BIGINT NOT NULL,
	fk_id_termo_especifico BIGINT NOT NULL,
	fk_id_documento BIGINT NOT NULL,
	bo_status BIT NOT NULL
);

-- CONSTRAINTS - PRIMARY KEYS
ALTER TABLE tb_nuc_nucleo
    ADD CONSTRAINT PK__tb_nucleo PRIMARY KEY (id);
ALTER TABLE tb_tipo_processo
    ADD CONSTRAINT PK__tb_tipo_processo PRIMARY KEY (id);
ALTER TABLE tb_termo_geral
    ADD CONSTRAINT PK__tb_termo_geral PRIMARY KEY (id);
ALTER TABLE tb_termo_especifico
    ADD CONSTRAINT PK__tb_termo_especifico PRIMARY KEY (id);
ALTER TABLE tb_documento
    ADD CONSTRAINT PK__tb_documento PRIMARY KEY (id);
ALTER TABLE tb_checklist
    ADD CONSTRAINT PK__tb_checklist PRIMARY KEY (id);

-- CONSTRAINTS - FOREIGN KEYS
-- tb_che_checklist
ALTER TABLE tb_checklist
    ADD CONSTRAINT FK__tb_checklist_fk_id_nucleo FOREIGN KEY (fk_id_nucleo) REFERENCES tb_nuc_nucleo(id);
ALTER TABLE tb_checklist
    ADD CONSTRAINT FK__tb_checklist_fk_id_tipo_processo FOREIGN KEY (fk_id_tipo_processo) REFERENCES tb_tpp_tipo_processo(id);
ALTER TABLE tb_checklist
    ADD CONSTRAINT FK__tb_checklist_fk_id_termo_geral FOREIGN KEY (fk_id_termo_geral) REFERENCES tb_tge_termo_geral(id);
ALTER TABLE tb_checklist
    ADD CONSTRAINT FK__tb_checklist_fk_id_termo_especifico FOREIGN KEY (fk_id_termo_especifico) REFERENCES tb_tes_termo_especifico(id);
ALTER TABLE tb_checklist
    ADD CONSTRAINT FK__tb_checklist_fk_id_documento FOREIGN KEY (fk_id_documento) REFERENCES tb_doc_documento(id);
