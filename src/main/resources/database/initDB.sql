

CREATE TABLE IF NOT EXISTS users
(
    user_id UUID PRIMARY KEY ,
    email VARCHAR(254) NOT NULL ,
    familyName VARCHAR(200) NOT NULL ,
    user_name VARCHAR(200) NOT NULL ,
    middleName VARCHAR(200) ,
    user_role UUID NOT NULL ,
    user_password VARCHAR(254) NOT NULL,
    status VARCHAR(8) NOT NULL,
    createdAt TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id UUID PRIMARY KEY ,
    role_name VARCHAR(254) NOT NULL ,
    description VARCHAR(254) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees
(
    id BIGSERIAL PRIMARY KEY ,
    UserId UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    RoleId UUID NOT NULL REFERENCES roles(role_id) ON DELETE CASCADE,
    email VARCHAR(254) NOT NULL ,
    familyName VARCHAR(200) NOT NULL ,
    name VARCHAR(200) NOT NULL ,
    middleName VARCHAR(200) ,
    roleEntity UUID NOT NULL ,
    password VARCHAR(254) NOT NULL
);




