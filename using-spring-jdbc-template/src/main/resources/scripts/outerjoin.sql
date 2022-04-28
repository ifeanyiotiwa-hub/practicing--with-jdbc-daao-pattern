use bookdb2;

SELECT author.id as id, first_name, last_name, book.id as id, title, isbn, publisher from author
left outer join book on author.id = book.author_id where author_id = 1;