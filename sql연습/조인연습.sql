-- inner join

-- 예제1: 현재, 근무하고 있는 직원 이름과 직책을 모두 출력
select *
	from employees a, titles b 
where a.emp_no = b.emp_no     		-- join 조건(n-1)
	and b.to_date='9999-01-01'; 	-- row 선택 조건

-- 예제2: 현재, 근무하고 있는 직원 사번, 이름과 직책을 모두 출력하되 여성 엔지니어(Engineer)만 출력
select *
	from employees a, titles b 
where a.emp_no = b.emp_no     		-- join 조건(n-1)
	and b.to_date='9999-01-01' 	-- row 선택 조건
    and a.gender = 'f'				-- row 선택 조건2
    and b.title = 'Engineer';		-- row 선택 조건3
    
--
-- ANSI / ISO SQL1999 JOIN 표준 문법
--

-- 1) join ~ on *
-- 예제: 현재, 직책별 평균 연봉을 큰 순서대로 출력
select a.title, max(b.salary)
	from titles a, salaries b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
group by a.title
order by avg(b.salary) desc;
    
select a.title, max(b.salary)
	from titles a
    join salaries b on a.emp_no = b.emp_no
where  a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
group by a.title
order by avg(b.salary) desc;

-- 2) Natural Join
-- 조인 대상이 되는 테이블들에 이름이 같은 공통 컬럼이 있는 경우
-- 조인 조건을 명시적으로 암묵적으로 조인이 된다.
-- 예제: 현재 근무하고 있는 직원의 이름과 직책을 출력
select a.first_name, b.title
	from employees a join titles b on a.emp_no = b.emp_no
where b.to_date = '9999-01-01'
order by a.first_name asc;

select a.first_name, b.title
	from employees a natural join titles b
where b.to_date = '9999-01-01'
order by a.first_name asc;

-- 3) join ~ using
--  natural join의 문제점
-- 예제: 현재, 근무하고 있는 직원의 직책과 연봉을 출력
select count(*)
	from titles a natural join salaries b
where a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01';

-- 해결1: join ~ using 
select count(*)
	from titles a join salaries b using(emp_no)
where a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01';
    
-- 해결2: join ~ on    
select count(*)
	from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01';
    
    
-- 실습문제1
-- 현재, 직원별 근무 부서를 출력해보세요.
-- 사번, 직원이름(first_name), 부서명 순으로 출력
select e.emp_no, e.first_name, d.dept_name
	from employees e, departments d, dept_emp de
where e.emp_no = de.emp_no
	and d.dept_no = de.dept_no
    and de.to_date = '9999-01-01';

-- 실습문제2
-- 현재, 직책별 평균 연봉과 직원 수를 구하되 직책별 직원 수가 100명이상인 직책만 출력하세요.
-- 직책, 평균 연봉, 직원 수 순으로 출력
select title, avg(salary), count(*)
	from titles t, salaries s
where t.emp_no = s.emp_no
	and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
group by t.title
	having count(*) >= 100;
    
-- 실습문제3
-- 현재, 부서별로 직책이 Engineer인 지원들에 대해서만 평균연봉을 구하세요.
-- 부서이름, 평균급여로 출력하고 평균 연봉이 높은 순으로 정렬하세요.
select b.emp_no, t.title, d.dept_name, avg(s.salary)
	from departments d, dept_emp b, titles t, salaries s
where d.dept_no = b.dept_no
	and b.emp_no = t.emp_no
    and t.emp_no = s.emp_no
    and b.to_date = '9999-01-01'
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and t.title = 'Engineer'
group by d.dept_name
order by avg(s.salary) desc;

select d.dept_name, avg(s.salary)
	from departments d 
    join dept_emp de on d.dept_no = de.dept_no
    join titles t on de.emp_no = t.emp_no
	join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
	and t.title like 'Engineer'
group by d.dept_name
order by avg(s.salary) desc;

    
