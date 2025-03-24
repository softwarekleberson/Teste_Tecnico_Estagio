CREATE TABLE contatos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    valor VARCHAR(255) NOT NULL,
    observacao VARCHAR(255),
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE -- ← Exclusão em cascata!
);

CREATE INDEX idx_contatos_cliente_id ON contatos(cliente_id);