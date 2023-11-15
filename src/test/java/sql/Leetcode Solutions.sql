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