-- inner join

-- 예제1: 현재, 근무하고 있는 직원 이름과 직책을 모두 출력
select *
	from employees a, titles b 
where a.emp_no = b.emp_no     		-- join 조건(n-1)
	and b.to_date='9999-01-01'; 	-- row 선택 조건