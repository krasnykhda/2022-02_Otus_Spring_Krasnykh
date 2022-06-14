DROP TABLE IF EXISTS Book;
CREATE TABLE Book(ID BIGINT PRIMARY KEY, NAME VARCHAR(255),AuthorID BIGINT,GenreID BIGINT);
CREATE TABLE Author(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));
CREATE TABLE Genre(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));