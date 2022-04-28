create table book (
    id        BIGINT not null auto_increment,
    title     VARCHAR(255),
    isbn      VARCHAR(255),
    author_id    BIGINT,
    publisher VARCHAR(255),
              primary key (id)
) engine=InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values (0);