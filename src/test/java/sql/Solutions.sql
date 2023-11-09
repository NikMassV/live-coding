--https://leetcode.com/problems/second-highest-salary/submissions/

SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE Salary NOT IN (SELECT MAX(Salary) FROM Employee);