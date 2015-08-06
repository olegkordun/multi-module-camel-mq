create table Company (
    id BIGINT not null,
    category varchar(16) not null,
    details varchar(4000) not null,
    email varchar(255) not null,
    info varchar(4000) not null,
    name varchar(400) not null,
    phone varchar(255),
    status varchar(16) not null,
    primary key (id)
);

create table user_role (
    user_id BIGINT not null,
    role_name varchar(16) not null,
    primary key (user_id, role_name)
);

create table users (
    id BIGINT not null,
    comment varchar(255),
    email varchar(255) not null,
    firstName varchar(255),
    username varchar(255) not null,
    lastName varchar(255),
    password varchar(255) not null,
    company_id BIGINT,
    primary key (id)
);

ALTER TABLE users ADD CONSTRAINT users_username_uk UNIQUE(username);

alter table user_role
add constraint user_role_fk
foreign key (user_id)
references users;

alter table users
add constraint user_company_fk
foreign key (company_id)
references Company;

create sequence user_seq;
create sequence company_seq;
