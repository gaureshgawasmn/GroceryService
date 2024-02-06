CREATE TABLE IF NOT EXISTS grocery (
    id INT NOT NULL AUTO_INCREMENT,
    price FLOAT NOT NULL,
    quantity INT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_details (
    mobile_number VARCHAR(255),
    name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    user_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS order_details (
    grocery_id INT,
    id INT NOT NULL AUTO_INCREMENT,
    quantity INT NOT NULL,
    total_price FLOAT NOT NULL,
    user_user_id VARCHAR(255),
    PRIMARY KEY (id)
);

-- Insert your initialization queries here
INSERT INTO user_details (user_id, mobile_number, name, password, role) VALUES
('admin', '940058896', 'Admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN,USER'),
('user', '940058896', 'User', 'ee11cbb19052e40b07aac0ca060c23ee', 'USER'),
('ram@xyz.com', '940058896', 'Ram', 'b04d6c7efa125fc28ece9ebc04967a8c', 'ADMIN,USER'),
('sundar@xyz.com', '943558897', 'Sundar', '57317fcc2b718d0ad99ae07ffdfacbc8', 'USER'),
('anil@xyz.com', '945058898', 'Anil', 'dae25370b4b2cd9c9d8483059950cdf4', 'USER'),
('gopal@xyz.com', '940158899', 'Gopal', '0c675f5c3546d0f6f99d90b5ab8dfe7e', 'USER');

INSERT INTO grocery (id, price, quantity, name) VALUES
(10001, 10, 10, 'Rice'),
(10002, 20, 20, 'Dal'),
(10003, 30, 30, 'Sugar'),
(10004, 40, 40, 'Salt'),
(10005, 50, 50, 'Oil'),
(10006, 60, 60, 'Wheat'),
(10007, 70, 70, 'Rava');

INSERT INTO order_details (id, user_user_id, grocery_id, quantity, total_price) VALUES
(20001, 'ram@xyz.com', 10001, 5, 50),
(20002, 'ram@xyz.com', 10002, 5, 100),
(20003, 'sundar@xyz.com', 10004, 5, 200),
(20004, 'sundar@xyz.com', 10005, 2, 100);