CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(16) NOT NULL,
    bouquet_name VARCHAR(255),
    gift_wrap BOOLEAN,
    set_items VARCHAR(255),
    status VARCHAR(32) NOT NULL,
    price DOUBLE PRECISION
);
