--https://leetcode.com/problems/second-highest-salary/submissions/
Second Highest Salary

SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE Salary NOT IN (SELECT MAX(Salary) FROM Employee);

------------------------------------------------------------------------------------------------------------------------

--https://leetcode.com/problems/nth-highest-salary/description/
Nth Highest Salary

CREATE OR REPLACE FUNCTION NthHighestSalary(N INT) RETURNS TABLE (Salary INT) AS $$
BEGIN
  RETURN QUERY (
    SELECT DISTINCT e.salary
    FROM Employee e
    ORDER BY salary DESC
    LIMIT 1 OFFSET n-1
  );
END;
$$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------

--https://leetcode.com/problems/rank-scores/submissions/
Rank Scores

SELECT
s.score,
count(unique_scores.score) as rank
FROM scores s, (SELECT DISTINCT score FROM SCORES) unique_scores
WHERE s.score <= unique_scores.score
GROUP BY s.id, s.score
ORDER BY s.score DESC;

------------------------------------------------------------------------------------------------------------------------

--https://leetcode.com/problems/consecutive-numbers/description/
Consecutive Numbers

WITH ConsecutiveCounts AS (
    SELECT
        Num,
        CASE WHEN LAG(Num) OVER (ORDER BY id) = Num THEN 1 ELSE 0 END +
        CASE WHEN LEAD(Num) OVER (ORDER BY id) = Num THEN 1 ELSE 0 END + 1 AS Consecutivetimes
    FROM Logs
)
SELECT DISTINCT Num AS ConsecutiveNums
FROM ConsecutiveCounts
WHERE Consecutivetimes >= 3;

------------------------------------------------------------------------------------------------------------------------

//https://leetcode.com/problems/swap-salary/submissions/
Swap Salary

UPDATE Salary
SET sex = CASE
    WHEN sex = 'm' THEN 'f'
    WHEN sex = 'f' THEN 'm'
    ELSE sex
END;

UPDATE Salary
SET sex = CASE WHEN sex = 'm' THEN 'f' ELSE 'm'
END;

------------------------------------------------------------------------------------------------------------------------

--https://leetcode.com/problems/department-highest-salary/submissions/
Department Highest Salary

SELECT
  d.name AS Department,
  e.name AS Employee,
  e.salary AS Salary
FROM Employee e
JOIN Department d ON e.departmentId = d.id
WHERE (e.departmentId, e.salary) IN
    (SELECT
      departmentId,
      MAX(salary)
     FROM Employee
     GROUP BY departmentId);

------------------------------------------------------------------------------------------------------------------------

--https://leetcode.com/problems/managers-with-at-least-5-direct-reports/submissions/
Managers with at Least 5 Direct Reports

WITH count_reports AS (
  SELECT
    managerId,
    COUNT(*) AS num_reports
  FROM employee
  GROUP BY managerId
)
SELECT
  name
FROM employee e
JOIN count_reports cr ON e.id = cr.managerId
WHERE cr.num_reports >= 5;

________________________________________________________________________________________________________________________

--https://leetcode.com/problems/investments-in-2016/description/
Investments in 2016

SELECT CAST(SUM(tiv_2016) AS NUMERIC(10, 2)) AS tiv_2016
FROM (
  SELECT
    tiv_2016,
    COUNT(*) OVER(PARTITION BY tiv_2015) AS cnt_of_same_tiv2015,
    COUNT(*) OVER(PARTITION BY lat, lon) AS cnt_of_this_city
    FROM insurance
) as subquery
WHERE cnt_of_same_tiv2015 > 1 AND cnt_of_this_city = 1;

------------------------------------------------------------------------------------------------------------------------

--https://leetcode.com/problems/combine-two-tables/description/
Combine Two Tables

SELECT
    p.firstName,
    p.lastName,
    a.city,
    a.state
FROM person p
LEFT JOIN address a ON p.personId = a.personId;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/employees-earning-more-than-their-managers/submissions/
Employees Earning More Than Their Managers

SELECT
    e.name AS employee
FROM employee e
WHERE e.salary > (
    SELECT
        m.salary
    FROM employee m
    WHERE e.managerId = m.id
);

-- SELECT
--     e.name AS employee
-- FROM employee e
-- JOIN employee m ON e.managerId = m.id
-- WHERE e.salary > m.salary;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/duplicate-emails/submissions/
Duplicate Emails

SELECT
  email
FROM person
GROUP BY email
HAVING COUNT(email) > 1;

-- SELECT DISTINCT ON (p1.email) p1.email
-- FROM person p1
-- JOIN person p2 ON p1.id <> p2.id AND p1.email = p2.email;

-- SELECT DISTINCT ON (p1.email) p1.email
-- FROM person p1, person p2
-- WHERE p1.id <> p2.id AND p1.email = p2.email;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/customers-who-never-order/description/
Customers Who Never Order

SELECT
    c.name AS customers
FROM customers c
LEFT JOIN orders o ON c.id = o.customerId
WHERE o.customerId IS NULL;

-- SELECT
--     c.name AS customers
-- FROM customers c
-- WHERE c.id NOT IN (SELECT o.customerId FROM orders o);

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/delete-duplicate-emails/description/
Delete Duplicate Emails

DELETE FROM person p1 USING person p2
WHERE p1.email = p2.email AND p1.id > p2.id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/rising-temperature/description/
Rising Temperature

SELECT
  w1.id
FROM weather w1
JOIN weather w2 ON w1.recordDate-w2.recordDate=1 AND w1.temperature >w2.temperature;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/game-play-analysis-i/description/
Game Play Analysis I

SELECT
    player_id,
    MIN(event_date) AS first_login
FROM activity
GROUP BY player_id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/employee-bonus/submissions/
Employee Bonus

SELECT
    e.name,
    b.bonus
FROM employee e
LEFT JOIN bonus b ON e.empId = b.empId
WHERE b.bonus < 1000 OR b.bonus IS NULL;