
-- 문제1.
-- 사번이 10944인 사원의 이름은(이름만)
select first_name from employees where emp_no='10944';

-- 문제3
-- 
-- 남
select count(*) from employees where gender='남';
-- 여
select count(*) from employees where gender='여';

-- 문제4
-- 현재(to_date='9999-01-01')

-- 문제5

-- 문제6

-- 문제7(x)
select first_name from employees order by length(first_name) desc;

-- 문제11
select from_date, to_date from dept_manager order by from_date desc; 


