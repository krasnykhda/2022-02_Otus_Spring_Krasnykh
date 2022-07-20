insert into genres (id,name) values (1,'Роман');
insert into genres (id,name) values (2,'Комедия');
insert into genres (id,name) values (3,'Драма');
insert into authors (id,name) values (1,'Чехов');
insert into authors (id,name) values (2,'Толстой');
insert into authors (id,name) values (3,'Пушкин');
insert into books (id,name,genreid) values (1,'Война и мир',1);
insert into books (id,name,genreid) values (2,'Евгений Онегин',1);
insert into books (id,name,genreid) values (3,'Вишневый сад',2);
insert into books_authors (book_id,author_id) values(1,2);
insert into books_authors (book_id,author_id) values(2,3);
insert into books_authors (book_id,author_id) values(3,1);
insert into comments (id,name,bookid) values (1,'Комментарий к войне и мир',1);
insert into comments (id,name,bookid) values (2,'Комментарий к Евгению Онегину',2);
insert into comments (id,name,bookid) values (3,'Комментарий к вишневому саду',3);



