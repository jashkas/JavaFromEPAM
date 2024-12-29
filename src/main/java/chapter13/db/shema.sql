CREATE TABLE ProductGroup (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    product_group_id INT,
    FOREIGN KEY (product_group_id) REFERENCES ProductGroup(group_id)
);

CREATE TABLE Parameter (
    param_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    param_group_id INT
);

CREATE TABLE ProductParameter (
    product_id INT,
    param_id INT,
    value_param VARCHAR(255),
    PRIMARY KEY (product_id, param_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (param_id) REFERENCES Parameter(param_id)
);

CREATE TABLE ParameterGroup (
    param_group_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    product_group_id INT,
    FOREIGN KEY (product_group_id) REFERENCES ProductGroup(group_id),
    FOREIGN KEY (param_id) REFERENCES Parameter(param_id)
);