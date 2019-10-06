CREATE TABLE account
(
    acc_id serial NOT NULL ,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    birthday date NOT NULL,
    PRIMARY KEY (acc_id)
);

