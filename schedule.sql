CREATE TABLE Event(
    eventId BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo VARCHAR(300) NOT NULL ,
    manId VARCHAR(50) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    createDay DATE NOT NULL,
    updateDay DATE NOT NULL,
    FOREIGN KEY (manId) REFERENCES Manager(manId)
);

CREATE TABLE Manager(
    manId VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) ,
    email  VARCHAR(50),
    createDay DATE NOT NULL,
    updateDay DATE NOT NULL
);