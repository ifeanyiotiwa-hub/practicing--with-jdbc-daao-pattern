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

create table author (
    id         BIGINT not null auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    primary key (id)
) engine=InnoDB;

alter table book
    add constraint book_author_fk foreign key (author_id) references author(id);

insert into bookdb2.author(first_name, last_name) values ('Craig', 'Walls') on duplicate key update id=id+100;

insert into bookdb2.book(title, isbn, author_id, publisher) values (
   'Spring JPA Remastered 2', '145768', (select id from bookdb2.author where first_name = 'Craig' and last_name = 'Walls'), 'Orielly'
);

insert into author(first_name, last_name)  values ('Emeka', 'Abella') on duplicate key update id=id+1000;

insert into book(title, isbn, author_id, publisher) values (
   'Spring Hibernate Remastered 2', '145768', (select id from author where first_name = 'Emeka' and last_name = 'Abella'), 'Orielly'
);