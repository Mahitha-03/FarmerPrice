-- AgriPrice schema reference. The application can also create/update tables via Hibernate.
CREATE DATABASE IF NOT EXISTS agriprice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE agriprice;

CREATE TABLE IF NOT EXISTS users (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, full_name VARCHAR(80) NOT NULL,
 email VARCHAR(120) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL,
 role VARCHAR(20) NOT NULL DEFAULT 'FARMER', phone VARCHAR(20),
 created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL,
 INDEX idx_users_role(role)
);
CREATE TABLE IF NOT EXISTS crops (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL UNIQUE,
 category VARCHAR(60), unit VARCHAR(20), description VARCHAR(500), active BOOLEAN NOT NULL DEFAULT TRUE,
 created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL, INDEX idx_crops_name(name)
);
CREATE TABLE IF NOT EXISTS markets (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(120) NOT NULL UNIQUE,
 district VARCHAR(80) NOT NULL, state VARCHAR(80) NOT NULL, address VARCHAR(255), active BOOLEAN NOT NULL DEFAULT TRUE,
 created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL, INDEX idx_market_district(district)
);
CREATE TABLE IF NOT EXISTS crop_prices (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, crop_id BIGINT NOT NULL, market_id BIGINT NOT NULL,
 price_per_kg DECIMAL(12,2) NOT NULL, price_date DATE NOT NULL, source VARCHAR(120),
 created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL,
 CONSTRAINT fk_price_crop FOREIGN KEY(crop_id) REFERENCES crops(id),
 CONSTRAINT fk_price_market FOREIGN KEY(market_id) REFERENCES markets(id),
 CONSTRAINT uk_price_crop_market_date UNIQUE(crop_id,market_id,price_date),
 INDEX idx_price_crop_date(crop_id,price_date), INDEX idx_price_market(market_id)
);
CREATE TABLE IF NOT EXISTS price_alerts (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, user_id BIGINT NOT NULL, crop_id BIGINT NOT NULL,
 target_price DECIMAL(12,2) NOT NULL, active BOOLEAN NOT NULL DEFAULT TRUE,
 created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL,
 FOREIGN KEY(user_id) REFERENCES users(id), FOREIGN KEY(crop_id) REFERENCES crops(id)
);
CREATE TABLE IF NOT EXISTS search_history (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, user_id BIGINT NOT NULL, crop_id BIGINT NOT NULL,
 searched_at DATETIME NOT NULL, created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL,
 FOREIGN KEY(user_id) REFERENCES users(id), FOREIGN KEY(crop_id) REFERENCES crops(id),
 INDEX idx_search_user_date(user_id,searched_at)
);
