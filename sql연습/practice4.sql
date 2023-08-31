-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
	from salaries s
where s.salary > (select avg(s.salary)
						from salaries s
					where s.to_date = '9999-01-01')
	and s.to_date = '9999-01-01';

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서, 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select e.emp_no, e.first_name, max_salary.dept_name, max_salary.maxi
	from employees e, (	select de.emp_no, d.dept_name, max(s.salary) as maxi
							from departments d join dept_emp de on d.dept_no = de.dept_no
							join salaries s on de.emp_no = s.emp_no
						where de.to_date = '9999-01-01'
							and s.to_date = '9999-01-01'
						group by d.dept_name) max_salary
where e.emp_no = max_salary.emp_no
order by maxi desc;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select e.emp_no, e.first_name, s.salary
	from employees e join dept_emp de on e.emp_no = de.emp_no
    join salaries s on e.emp_no = s.emp_no 
    join (	select de.dept_no, avg(s.salary) as avg_salary
				from dept_emp de join salaries s on de.emp_no = s.salary
			where de.to_date = '9999-01-01'
				and s.to_date = '9999-01-01'
			group by de.dept_no) avg_de on de.dept_no = avg_de.dept_no
where s.salary > avg_de.avg_salary
	and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01';

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no as '사번', e.first_name as '이름', dep_manage.first_name as '매니저 이름', dep_manage.dept_name as '부서'
	from employees e join dept_emp de on e.emp_no = de.emp_no
    join (	select d.dept_no, d.dept_name, e.emp_no, e.first_name
				from dept_manager dm join departments d on dm.dept_no = d.dept_no
				join employees e on e.emp_no = dm.emp_no
			where dm.to_date = '9999-01-01') dep_manage on de.dept_no = dep_manage.dept_no;

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select e.emp_no, e.first_name, t.title, s.salary
	from employees e join dept_emp de on e.emp_no = de.emp_no
    join titles t on e.emp_no = t.emp_no
    join salaries s on e.emp_no = s.emp_no
    join (	select de.dept_no
				from dept_emp de join salaries s on de.emp_no = s.emp_no
			group by de.dept_no
				having avg(s.salary)
			order by avg(s.salary) desc limit 1
) max_dept on de.dept_no = max_dept.dept_no
where de.to_date = '9999-01-01';


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 총무 20000
select d.dept_name, avg(s.salary)
	from departments d join dept_emp de on d.dept_no = de.dept_no
    join salaries s on de.emp_no = s.emp_no
group by d.dept_no
	having avg(s.salary)
order by avg(s.salary) desc limit 1;


-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000
select t.title, avg(s.salary)
	from titles t join salaries s on t.emp_no = s.emp_no
group by t.title
	having avg(s.salary)
order by avg(s.salary) desc limit 1;

-- 문제8. (순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select d.dept_name as '부서이름', e.first_name as '사원이름', s.salary as '연봉', manager.first_name as '매니저 이름', manager.salary as '매니저 연봉'
	from employees e join dept_emp de on e.emp_no = de.emp_no
    join departments d on d.dept_no = de.dept_no
    join salaries s on e.emp_no = s.emp_no
    join (	select dm.dept_no, s.salary, e.first_name
				from dept_manager dm join salaries s on dm.emp_no = s.emp_no
				join employees e on e.emp_no = dm.emp_no
			where dm.to_date = '9999-01-01'
				and s.to_date = '9999-01-01') manager on manager.dept_no = de.dept_no
where s.salary > manager.salary;

    
