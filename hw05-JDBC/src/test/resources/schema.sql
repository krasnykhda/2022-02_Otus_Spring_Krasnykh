drop table if exists book;
drop table if exists Author;
drop table if exists Genre;
CREATE TABLE Book(ID bigserial PRIMARY KEY, NAME VARCHAR(255),AuthorID BIGINT,GenreID BIGINT);
CREATE TABLE Author(ID bigserial PRIMARY KEY, NAME VARCHAR(255));
CREATE TABLE Genre(ID bigserial PRIMARY KEY, NAME VARCHAR(255));