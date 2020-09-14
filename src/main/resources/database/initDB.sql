
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS users_id_seq CASCADE;

DROP TABLE IF EXISTS documents CASCADE;
DROP SEQUENCE IF EXISTS documents_id_seq CASCADE;
DROP TABLE IF EXISTS type_of_document CASCADE;
DROP TABLE IF EXISTS user_role CASCADE;

CREATE TABLE type_of_document
(
    id   INTEGER PRIMARY KEY,
    name varchar(128) NOT NULL
);

CREATE TABLE documents
(
    id              INTEGER PRIMARY KEY,
    number_of_doc int,
    issued_by_whom  varchar(255),
    date_of_issue   date,
    expiration_date date,
    type_id         integer,
    FOREIGN KEY (type_id) REFERENCES type_of_document (id)
);

CREATE SEQUENCE documents_id_seq START WITH 3 INCREMENT BY 1;




CREATE TABLE users
(
    id          INTEGER PRIMARY KEY,
    name        varchar(255),
    full_name   varchar(255),
    inn         varchar(255),
    birthday    date,
    address     varchar(255),
    email       varchar(255),
    password    varchar(1000),
    document_id integer,
    FOREIGN KEY (document_id) REFERENCES documents (id)
);

CREATE SEQUENCE users_id_seq START WITH 5 INCREMENT BY 1;

CREATE TABLE user_role
(
    id SERIAL NOT NULL,
    roles   varchar(255),
    user_id integer,
    FOREIGN KEY (user_id) REFERENCES users (id)
);





