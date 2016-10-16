/**
 * This script has been written for use with an Oracle database.
 * 
 * To use, first create a schema in your database to load these objects into.
 * 
 * Next, grant the schema the necessary privileges to create tables, procedures,
 * and sequences.
 *
 * Last, connect as the schema that you have created and execute this script.
 */

create table recipes(
    id          number not null,
    recipe_num  varchar2(10) not null,
    name        varchar2(100) not null,
    description varchar2(500),
    text        clob,
    constraint recipes_pk primary key (id) enable
);

create sequence recipes_seq
start with 1
increment by 1;

insert into recipes values(
recipes_seq.nextval,
'11-1',
'Connecting to a Database',
'DriverManager and DataSource Implementations',
'Recipe Text');

insert into recipes values(
recipes_seq.nextval,
'11-2',
'Querying a Database and Retrieving Results',
'Obtaining and using data from a DBMS',
'Recipe Text');

insert into recipes values(
recipes_seq.nextval,
'11-3',
'Handling SQL Exceptions',
'Using SQLException',
'Recipe Text');

create table book_author(
id          number primary key,
last        varchar2(30),
first       varchar2(30));

create sequence book_author_seq
start with 1
increment by 1;

create table author_work(
id              number primary key,
author_id       number not null,
chapter_number  number not null,
chapter_title   varchar2(100) not null,
constraint author_work_fk
foreign key(author_id) references book_author(id));

create sequence author_work_seq
start with 1
increment by 1;

insert into book_author values(
book_author_seq.nextval,
'JUNEAU',
'JOSH');

insert into book_author values(
book_author_seq.nextval,
'DEA',
'CARL');

insert into book_author values(
book_author_seq.nextval,
'BEATY',
'MARK');

insert into book_author values(
book_author_seq.nextval,
'GUIME',
'FREDDY');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
1,
'Getting Started With Java 7');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
2,
'Strings');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
3,
'Numbers and Dates');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'BEATY'),
4,
'Data Structures, Conditionals, and Iteration');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
5,
'Input and Output');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
6,
'Exceptions, Logging, Debugging');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
7,
'Object Oriented Java');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
8,
'Concurrency');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
9,
'Debugging and Unit Testing');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
10,
'Unicode, Internationalization, Currency');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
11,
'Working with Databases');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'DEA'),
12,
'Java 2D Graphics and Media');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
13,
'Java 3D');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'DEA'),
14,
'Swing API');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'DEA'),
15,
'JavaFX Fundamentals');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'DEA'),
16,
'Graphics with JavaFX');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'DEA'),
17,
'Media with JavaFX');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
18,
'Working with Servlets and Applets');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
19,
'Intro to Android');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'DEA'),
20,
'JavaFX and the Web');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'GUIME'),
21,
'Email');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
22,
'XML and Web Services');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
23,
'Networking');

insert into author_work values(
author_work_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
24,
'Security and Cryptography');

create table recipe_text (
id              number primary key,
recipe_id       number not null,
text            clob,
constraint recipe_text_fk
foreign key (recipe_id)
references recipes(id));

create type chap_list_type as varray(10) of number;

create table author_recipes (
id              number primary key,
author_id       number,
chapter_list    chap_list_type);

create sequence author_recipes_seq
start with 1
increment by 1;

insert into author_recipes values(
author_recipes_seq.nextval,
(select id from book_author where last = 'JUNEAU'),
chap_list_type(1,2,3,7,11,18,19,23,24));

create or replace procedure dummy_proc (text IN VARCHAR2,
                                        msg OUT VARCHAR2) as
begin
    -- Do something
    msg :=text;
end;

commit;
/