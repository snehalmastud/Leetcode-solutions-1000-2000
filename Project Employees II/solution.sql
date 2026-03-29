# Write your MySQL query statement below

select project_id 
from Project 
group by project_id
having count(distinct employee_id) = (
    select max(count_employee_id) from (
        select project_id, count(employee_id) as count_employee_id
        from Project
        group by project_id
    ) as max_employee
);


-- same as above, but separated into 2 parts
WITH max_employee AS (
    SELECT project_id, COUNT(employee_id) AS count_employee_id
    FROM Project
    GROUP BY project_id
)

SELECT project_id 
FROM Project 
GROUP BY project_id
HAVING COUNT(DISTINCT employee_id) = (
    SELECT MAX(count_employee_id) 
    FROM max_employee
);

--

SELECT project_id
FROM Project
GROUP BY 1
HAVING
    COUNT(1) >= ALL (
        SELECT COUNT(1)
        FROM Project
        GROUP BY project_id
    );
