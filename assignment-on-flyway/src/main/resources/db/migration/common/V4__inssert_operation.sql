insert into bookdb2.author(first_name, last_name) values ('Craig', 'Walls');

insert into bookdb2.book(title, isbn, author_id, publisher) values (
 'Spring JPA Remastered 2', '145768', (select id from bookdb2.author where first_name = 'Craig' and last_name = 'Walls'), 'Orielly'
 );

insert into author(first_name, last_name)  values ('Emeka', 'Abella');

insert into book(title, isbn, author_id, publisher) values (
'Spring Hibernate Remastered 2', '145768', (select id from author where first_name = 'Emeka' and last_name = 'Abella'), 'Orielly'
);