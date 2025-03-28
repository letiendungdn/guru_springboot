use restdb;

create table customer (
                          id varchar(36) not null,
                          created_date datetime(6),
                          name varchar(255),
                          update_date datetime(6),
                          version integer,
                          primary key (id)
) engine=InnoDB
