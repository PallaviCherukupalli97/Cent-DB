create database test4;
create database prit;
select * from prit;
use prit;
create table p1 (name varchar, age int);
insert into p1 (name,age) values (Prit,23);
update p1 set name=Jay where age=23;
create database test;
drop database prot;
drop database prit;
use test;
create table test1 (name varchar, age int, subject varchar);
select * from test1;
insert into test1 (name,age,subject) values (jay,23,data);
select * from test;
select * from test1;
insert into test1 (name,age,subject) values (prit,23,adv);
select * from test1;
select * from test1 where name=prit;
begin transaction
use test;
select * from test1;
commit;
use test;
begin transaction
select * from test1;
select * from test1 where name=prit;
commit;
