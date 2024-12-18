CREATE TABLE CarModel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL
);

CREATE TABLE Dealer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model_id BIGINT NOT NULL,
    dealer_id BIGINT NOT NULL,
    country_origin VARCHAR(255),
    country_code VARCHAR(10),
    status VARCHAR(50),
    configuration VARCHAR(255),
    color VARCHAR(50),
    price DECIMAL(10, 2),
    FOREIGN KEY (model_id) REFERENCES CarModel(id),
    FOREIGN KEY (dealer_id) REFERENCES Dealer(id)
);