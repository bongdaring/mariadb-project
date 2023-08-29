select version(), current_date, now() from dual;

-- 수학 함수도 사용할 수 있다.(사칙 연산도 된다.)
select pi() from dual;

-- 대소문자 구분 안한다.
select Version(), current_date, Now() FROM dual;

-- table 생성 :DDL
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
show tables;
describe pet;
desc pet;

-- table 삭제: DDL
drop table pet;
 
 
 -- insert: DML(C)
 insert into pet values('스타','최진영','cat','m','2030-08-25', null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='바나나' where name='스타';