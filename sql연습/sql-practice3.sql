-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select e.emp_no, e.first_name, s.salary
	from employees e join salaries s on e.emp_no = s.salary
where s.to_date = '9999-01-01'
order by s.salary desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select e.emp_no, e.first_name, t.title
	from employees e join titles t on e.emp_no = t.emp_no
where t.to_date = '9999-01-01'
order by e.first_name asc;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select e.emp_no, e.first_name, d.dept_name
	from employees e join dept_emp de on e.emp_no = de.emp_no
    join departments d on de.dept_no = d.dept_no
where de.to_date = '9999-01-01'
order by e.first_name asc;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.
-- '9999-01-01' 아닌 사원들
select e.emp_no, concat(e.first_name,' ',e.last_name) as 'full name'
	from employees e join titles t on e.emp_no = t.emp_no
where t.title = 'Technique Leader'
	and t.to_date not in ('9999-01-01');

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select concat(e.first_name,' ',e.last_name) as 'full name', t.title
	from employees e join titles t on e.emp_no = t.emp_no
    join dept_emp de on e.emp_no = de.emp_no
    join departments d on de.dept_no = d.dept_no
    where e.last_name like 'S%';
    
-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 
-- 이름, 급여 출력
-- 급여가 큰 순서대로 출력하세요.
select e.first_name, s.salary
	from employees e join titles t on e.emp_no = t.emp_no
    join salaries s on e.emp_no = s.emp_no
where t.title = 'Engineer'
	and 40000 <= s.salary
order by s.salary desc;


-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select d.dept_name , avg(s.salary)
	from departments d join dept_emp de on d.dept_no = de.dept_no
    join salaries s on de.emp_no = s.emp_no
where de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
group by d.dept_name
order by avg(s.salary) desc;
    
-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select t.title, avg(s.salary)
	from titles t join employees e on t.emp_no = e.emp_no
	join salaries s on e.emp_no = s.emp_no
where t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
group by t.title
order by avg(s.salary) desc;
