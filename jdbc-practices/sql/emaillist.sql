-- emaillist

-- insert

insert into emaillist values(null, '마','이에스큐엘','mysql@mysql.com');

-- findAll
select no, first_name, last_name from emaillist order by no asc;

-- delete
delete from emaillist where email='mysql@mysql.com';
