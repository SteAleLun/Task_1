
CREATE TABLE IF NOT EXISTS roles
(
    role_id UUID PRIMARY KEY ,
    role_name VARCHAR(254) NOT NULL ,
    description VARCHAR(254) NOT NULL
);


CREATE TABLE IF NOT EXISTS attachments
(
    attachment_id UUID PRIMARY KEY ,
    title VARCHAR(254) NOT NULL ,
    file_name VARCHAR(254) NOT NULL,
    description VARCHAR(254) NOT NULL,
    version_of UUID,
    file_path VARCHAR(254) NOT NULL,
    uploaded BOOLEAN,
    card_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
    user_id UUID PRIMARY KEY ,
    email VARCHAR(254) NOT NULL ,
    family_name VARCHAR(200) NOT NULL ,
    user_name VARCHAR(200) NOT NULL ,
    middle_name VARCHAR(200) ,
    user_role UUID NOT NULL REFERENCES roles(role_id) ON DELETE CASCADE,
    user_password VARCHAR(254) NOT NULL,
    status VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL
);





