------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/capital-gainloss/description/
Capital Gain/Loss

SELECT
    stock_name,
    SUM(CASE WHEN operation = 'Sell' THEN price ELSE 0 END) - SUM(CASE WHEN operation = 'Buy' THEN price ELSE 0 END) AS capital_gain_loss
FROM Stocks
GROUP BY 1

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/restaurant-growth/description/
Restaurant Growth

WITH day_sum AS (
    SELECT
        visited_on,
        SUM(amount) AS sum_amount
    FROM Customer
    GROUP BY visited_on
    ORDER BY visited_on
)
SELECT
    visited_on,
    SUM(sum_amount) OVER (ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS amount,
    ROUND(AVG(sum_amount) OVER (ROWS BETWEEN 6 PRECEDING AND CURRENT ROW), 2) AS average_amount
FROM day_sum
OFFSET 6

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/monthly-transactions-i/
Monthly Transactions I

SELECT
    to_char(trans_date, 'yyyy-mm') AS month,
    country,
    COUNT(amount) AS trans_count,
    COUNT(CASE WHEN state = 'approved' THEN amount ELSE NULL END) AS approved_count,
    SUM(amount) AS trans_total_amount,
    SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END) AS approved_total_amount
FROM Transactions t
GROUP BY 1,2

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/product-price-at-a-given-date/
Product Price at a Given Date

SELECT DISTINCT ON (p1.product_id)
    p1.product_id,
    coalesce(p2.new_price, 10) as price
FROM products p1
LEFT JOIN products p2 ON p1.product_id = p2.product_id and p2.change_date <= '2019-08-16'
ORDER BY p1.product_id, p2.change_date DESC

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/last-person-to-fit-in-the-bus/description/
Last Person to Fit in the Bus

SELECT person_name
FROM (
    SELECT person_id,
           person_name,
           SUM(weight) OVER (ORDER BY turn) AS cum_sum
     FROM Queue
     ORDER BY turn DESC
)
WHERE cum_sum <= 1000
LIMIT 1

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/exchange-seats/description/
Exchange Seats

SELECT (
    CASE WHEN id % 2 = 1 AND id = (SELECT max(id) FROM Seat) THEN id
    WHEN id % 2 = 1 THEN id + 1
    WHEN id % 2 = 0 THEN id - 1
    END
) AS
    id,
    student
FROM Seat
ORDER BY id

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/group-sold-products-by-the-date/description/
Group Sold Products By The Date

SELECT
    sell_date,
    COUNT(DISTINCT product) AS num_sold,
    STRING_AGG(DISTINCT product, ',' ORDER BY product) AS products
FROM Activities
GROUP BY sell_date
ORDER BY sell_date

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/list-the-products-ordered-in-a-period/description/
List the Products Ordered in a Period

SELECT
    p.product_name,
    SUM(o.unit) AS unit
FROM Products p
JOIN Orders o ON p.product_id = o.product_id
WHERE o.order_date BETWEEN '2020-02-01' AND '2020-02-29'
GROUP BY 1
HAVING unit >= 100;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/students-and-examinations/
Students and Examinations

SELECT
    s.student_id,
    student_name,
    s.subject_name,
    count (e.subject_name) AS attended_exams
FROM
    (
        SELECT student_id, student_name, subject_name
        FROM Students, Subjects
    ) AS s
    LEFT JOIN Examinations e
        ON s.subject_name = e.subject_name
            AND s.student_id = e.student_id
GROUP BY s.student_id, student_name, s.subject_name
ORDER BY s.student_id, s.subject_name

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/immediate-food-delivery-ii/description/
Immediate Food Delivery II

SELECT
    ROUND(AVG(CASE WHEN order_date = customer_pref_delivery_date THEN 1 ELSE 0 END) * 100, 2) as immediate_percentage
FROM delivery
WHERE (customer_id, order_date) in (
    SELECT customer_id, min(order_date)
    FROM delivery
    GROUP BY customer_id
)

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/market-analysis-i/
Market Analysis I

SELECT
    u.user_id AS buyer_id,
    u.join_date AS join_date,
    COUNT(o.order_date) AS orders_in_2019
FROM users u
LEFT JOIN orders o ON u.user_id = o.buyer_id AND EXTRACT('YEAR' FROM o.order_date) = '2019'
GROUP BY u.user_id, u.join_date;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/product-sales-analysis-iii/description/
Product Sales Analysis III

SELECT
    product_id,
    year AS first_year,
    quantity,
    price
FROM sales
WHERE (product_id, year) IN (
    SELECT
        product_id,
        MIN(year)
    FROM sales
    GROUP BY product_id
);

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/customers-who-bought-all-products/description/
Customers Who Bought All Products

SELECT
    customer_id
FROM customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (
    SELECT
        COUNT(DISTINCT product_key)
    FROM product
)

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/tree-node/description/
Tree Node

SELECT
    id,
    CASE
        WHEN p_id IS NULL THEN 'Root'
        WHEN id IN (SELECT DISTINCT p_id FROM tree) THEN 'Inner'
        ELSE 'Leaf'
    END AS type
FROM tree;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/submissions/
Friend Requests II: Who Has the Most Friends

WITH ids AS (
    SELECT
        requester_id AS id
    FROM requestAccepted
    UNION ALL
    SELECT
        accepter_id AS id
    FROM requestAccepted
)
SELECT
    id,
    COUNT(id) AS num
FROM ids
GROUP BY id
ORDER BY num DESC
LIMIT 1;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/game-play-analysis-iv/submissions/
Game Play Analysis IV

SELECT
    ROUND(COUNT(a1.player_id) / ROUND(COUNT(a.player_id), 2), 2) AS fraction
FROM activity a
LEFT JOIN activity a1 ON a1.player_id = a.player_id AND a1.event_date = a.event_date + interval '1' day
WHERE (a.player_id, a.event_date) IN (
    SELECT
        player_id,
        MIN(event_date)
    FROM activity
    GROUP BY player_id
)

------------------------------------------------------------------------------------------------------------------------
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

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/find-customer-referee/description/
Find Customer Referee

SELECT
    name
FROM customer
WHERE referee_id != 2 OR referee_id IS NULL;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/customer-placing-the-largest-number-of-orders/submissions/
Customer Placing the Largest Number of Orders

SELECT
    customer_number
FROM orders
GROUP BY customer_number
ORDER BY COUNT(order_number) DESC
LIMIT 1;

WITH count_orders AS (
    SELECT
        customer_number,
        COUNT(*) counts
    FROM orders
    GROUP BY customer_number
)
SELECT
    customer_number
FROM count_orders
ORDER BY counts DESC
LIMIT 1;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/big-countries/submissions/
Big Countries

SELECT
    name,
    population,
    area
FROM world
WHERE area >= 3000000 OR population >= 25000000;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/classes-more-than-5-students/submissions/
Classes More Than 5 Students

SELECT
    class
FROM courses
GROUP BY class
HAVING COUNT(student)>=5;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/sales-person/submissions/
Sales Person

SELECT
    sp.name AS name
FROM salesperson sp
WHERE sp.sales_id NOT IN (
    SELECT
        o.sales_id
    FROM orders o
    JOIN company c ON o.com_id = c.com_id
    WHERE c.name = 'RED'
)

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/triangle-judgement/description/
Triangle Judgement

SELECT
    *,
    CASE WHEN x + y > z AND x + z > y AND y + z > x THEN 'Yes' ELSE 'No' END AS triangle
FROM triangle;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/biggest-single-number/
Biggest Single Number

SELECT
    MAX(num) AS num
FROM (
    SELECT
        num
    FROM mynumbers
    GROUP BY num
    HAVING COUNT(num) = 1
);

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/not-boring-movies/description/
Not Boring Movies

SELECT
    *
FROM cinema
WHERE id % 2 = 1 AND description != 'boring'
ORDER BY rating DESC

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/actors-and-directors-who-cooperated-at-least-three-times/description/
Actors and Directors Who Cooperated At Least Three Times

SELECT
    actor_id,
    director_id
FROM actordirector
GROUP BY actor_id, director_id
HAVING COUNT(director_id) >= 3;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/product-sales-analysis-i/description/
Product Sales Analysis I

SELECT
    p.product_name,
    s.year,
    s.price
FROM sales s
JOIN product p ON s.product_id = p.product_id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/project-employees-i/description/
Project Employees I

SELECT
    p.project_id,
    ROUND(AVG(e.experience_years), 2) AS average_years
FROM project p
JOIN employee e ON p.employee_id = e.employee_id
GROUP BY p.project_id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/sales-analysis-iii/description/
Sales Analysis III

SELECT
    p.product_id,
    p.product_name
FROM product p
RIGHT JOIN sales s ON p.product_id = s.product_id
GROUP BY p.product_id, p.product_name
HAVING MIN(s.sale_date) >= '2019-01-01' AND
       MAX(s.sale_date) <= '2019-03-31'

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/user-activity-for-the-past-30-days-i/description/
User Activity for the Past 30 Days I

SELECT
    activity_date as day,
    count(distinct user_id) as active_users
FROM activity
WHERE activity_date > Date '2019-07-27' - 30 AND activity_date <= '2019-07-27'
GROUP BY day;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/article-views-i/description/
Article Views I


SELECT
    DISTINCT ON (author_id) author_id as id
FROM views
WHERE author_id = viewer_id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/reformat-department-table/description/
Reformat Department Table

SELECT id,
	sum(CASE WHEN month = 'Jan' THEN revenue ELSE NULL END) AS Jan_Revenue,
	sum(CASE WHEN month = 'Feb' THEN revenue ELSE NULL END) AS Feb_Revenue,
	sum(CASE WHEN month = 'Mar' THEN revenue ELSE NULL END) AS Mar_Revenue,
	sum(CASE WHEN month = 'Apr' THEN revenue ELSE NULL END) AS Apr_Revenue,
	sum(CASE WHEN month = 'May' THEN revenue ELSE NULL END) AS May_Revenue,
	sum(CASE WHEN month = 'Jun' THEN revenue ELSE NULL END) AS Jun_Revenue,
	sum(CASE WHEN month = 'Jul' THEN revenue ELSE NULL END) AS Jul_Revenue,
	sum(CASE WHEN month = 'Aug' THEN revenue ELSE NULL END) AS Aug_Revenue,
	sum(CASE WHEN month = 'Sep' THEN revenue ELSE NULL END) AS Sep_Revenue,
	sum(CASE WHEN month = 'Oct' THEN revenue ELSE NULL END) AS Oct_Revenue,
	sum(CASE WHEN month = 'Nov' THEN revenue ELSE NULL END) AS Nov_Revenue,
	sum(CASE WHEN month = 'Dec' THEN revenue ELSE NULL END) AS Dec_Revenue
FROM department
GROUP BY id

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/queries-quality-and-percentage/description/
Queries Quality and Percentage

SELECT
    query_name,
    ROUND((AVG(rating/position)), 2) as quality,
    ROUND(SUM(CASE WHEN rating < 3 THEN 1 else 0 end) * 100 / COUNT(*), 2) as poor_query_percentage
FROM queries
WHERE query_name IS NOT NULL
GROUP BY query_name;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/average-selling-price/
Average Selling Price

SELECT
    p.product_id,
    CASE WHEN SUM(us.units) IS NULL THEN 0 WHEN SUM(us.units) = 0 THEN 0 ELSE ROUND(SUM(p.price * us.units) / ROUND(SUM(us.units), 2), 2) END AS average_price
FROM prices p
LEFT JOIN unitsSold us ON p.product_id = us.product_id AND us.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/submissions/
Number of Unique Subjects Taught by Each Teacher

SELECT
    teacher_id,
    COUNT(DISTINCT subject_id) AS cnt
FROM teacher
GROUP BY teacher_id;

------------------------------------------------------------------------------------------------------------------------
--https://leetcode.com/problems/replace-employee-id-with-the-unique-identifier/description/
Replace Employee ID With The Unique Identifier

SELECT
    eu.unique_id,
    e.name
FROM EmployeeUNI eu
RIGHT JOIN Employees e ON eu.id = e.id;

------------------------------------------------------------------------------------------------------------------------