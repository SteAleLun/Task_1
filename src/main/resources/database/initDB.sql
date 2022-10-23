
CREATE TYPE work_state AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TABLE IF NOT EXISTS users
(
    id UUID ,
    email VARCHAR(254) NOT NULL ,
    familyName VARCHAR(200) NOT NULL ,
    name VARCHAR(200) NOT NULL ,
    middleName VARCHAR(200) ,
    role UUID NOT NULL ,
    password VARCHAR(254) NOT NULL ,
    status work_state ,
    createdAt TIMESTAMP
);

