
CREATE TYPE work_state AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL PRIMARY KEY ,
    email VARCHAR(254) NOT NULL ,
    familyName VARCHAR(200) NOT NULL ,
    user_name VARCHAR(200) NOT NULL ,
    middleName VARCHAR(200) ,
    user_role UUID NOT NULL ,
    user_password VARCHAR(254) NOT NULL,
    status work_state NOT NULL,
    createdAt TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id BIGSERIAL PRIMARY KEY ,
    role_name VARCHAR(254) NOT NULL ,
    description VARCHAR(254) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee
(
    id BIGSERIAL PRIMARY KEY ,
    UserId INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    RoleId INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    email VARCHAR(254) NOT NULL ,
    familyName VARCHAR(200) NOT NULL ,
    name VARCHAR(200) NOT NULL ,
    middleName VARCHAR(200) ,
    role UUID NOT NULL ,
    password VARCHAR(254) NOT NULL
);




