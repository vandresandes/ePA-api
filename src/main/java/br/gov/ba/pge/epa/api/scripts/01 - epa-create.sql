
CREATE TABLE tb_nuc_nucleo (
	nuc_id_nucleo BIGINT IDENTITY(1,1) NOT NULL,
	nuc_no_nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_tpp_tipo_processo (
	tpp_id_tipo_processo BIGINT IDENTITY(1,1) NOT NULL,
	tpp_no_nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_tge_termo_geral (
	tge_id_termo_geral BIGINT IDENTITY(1,1) NOT NULL,
	tge_no_nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_tes_termo_especifico (
	tes_id_termo_especifico BIGINT IDENTITY(1,1) NOT NULL,
	tes_no_nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE tb_doc_documento (
	doc_id_documento BIGINT IDENTITY(1,1) NOT NULL,
	doc_no_nome VARCHAR(100) UNIQUE NOT NULL,
	doc_tp_tipo VARCHAR(20) NOT NULL
);

CREATE TABLE tb_che_checklist (
	che_id_checklist BIGINT IDENTITY(1,1) NOT NULL,
	che_fk_nuc_id_nucleo BIGINT NOT NULL,
	che_fk_tpp_id_tipo_processo BIGINT NOT NULL,
	che_fk_tge_id_termo_geral BIGINT NOT NULL,
	che_fk_tes_id_termo_especifico BIGINT NOT NULL,
	che_fk_doc_id_documento BIGINT NOT NULL,
	che_bo_status BIT NOT NULL
);

-- CONSTRAINTS - PRIMARY KEYS
ALTER TABLE tb_nuc_nucleo
    ADD CONSTRAINT PK__tb_nuc_nucleo PRIMARY KEY (nuc_id_nucleo);
ALTER TABLE tb_tpp_tipo_processo
    ADD CONSTRAINT PK__tb_tpp_tipo_processo PRIMARY KEY (tpp_id_tipo_processo);
ALTER TABLE tb_tge_termo_geral
    ADD CONSTRAINT PK__tb_tge_termo_geral PRIMARY KEY (tge_id_termo_geral);
ALTER TABLE tb_tes_termo_especifico
    ADD CONSTRAINT PK__tb_tes_termo_especifico PRIMARY KEY (tes_id_termo_especifico);
ALTER TABLE tb_doc_documento
    ADD CONSTRAINT PK__tb_doc_documento PRIMARY KEY (doc_id_documento);
ALTER TABLE tb_che_checklist
    ADD CONSTRAINT PK__tb_che_checklist PRIMARY KEY (che_id_checklist);


-- CONSTRAINTS - FOREIGN KEYS
-- tb_che_checklist
ALTER TABLE tb_che_checklist
    ADD CONSTRAINT FK__tb_che_checklist_che_fk_nuc_id_nucleo FOREIGN KEY (che_fk_nuc_id_nucleo) REFERENCES tb_nuc_nucleo(nuc_id_nucleo);
ALTER TABLE tb_che_checklist
    ADD CONSTRAINT FK__tb_che_checklist_che_fk_tpp_id_tipo_processo FOREIGN KEY (che_fk_tpp_id_tipo_processo) REFERENCES tb_tpp_tipo_processo(tpp_id_tipo_processo);
ALTER TABLE tb_che_checklist
    ADD CONSTRAINT FK__tb_che_checklist_che_fk_tge_id_termo_geral FOREIGN KEY (che_fk_tge_id_termo_geral) REFERENCES tb_tge_termo_geral(tge_id_termo_geral);
ALTER TABLE tb_che_checklist
    ADD CONSTRAINT FK__tb_che_checklist_che_fk_tes_id_termo_especifico FOREIGN KEY (che_fk_tes_id_termo_especifico) REFERENCES tb_tes_termo_especifico(tes_id_termo_especifico);
ALTER TABLE tb_che_checklist
    ADD CONSTRAINT FK__tb_che_checklist_che_fk_doc_id_documento FOREIGN KEY (che_fk_doc_id_documento) REFERENCES tb_doc_documento(doc_id_documento);

	
