------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/66acd927c487bb5f867a38c5/train/sql
Categorize and Count Job Applications Based on Status

WITH status_groups AS (
    SELECT
        CASE
            WHEN application_status <= 5 THEN 'Rejected'
            ELSE 'Approved'
        END AS status_group
    FROM applications
)
SELECT
    sg.status_group,
    COUNT(a.status_group) AS application_num
FROM (VALUES ('Rejected'), ('Approved')) AS sg(status_group)
LEFT JOIN status_groups a ON sg.status_group = a.status_group
GROUP BY sg.status_group
ORDER BY CASE sg.status_group WHEN 'Rejected' THEN 1 WHEN 'Approved' THEN 2 END

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/66c71c893759d440748154f8/train/sql
Number of countries visited

SELECT
  p.name AS name,
  COUNT(DISTINCT v.country_id) AS countries_visited
FROM people p
LEFT JOIN visits v ON p.id = v.person_id
GROUP BY p.name
ORDER BY countries_visited DESC, p.name ASC

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/67741444c77444b19e8b5223/train/sql
Finding Products Matching All Selected Tags

SELECT product_id
FROM (
    SELECT DISTINCT product_id, tag
    FROM product_tags
    WHERE tag IN ('Electronics', 'Gadgets')
) t
GROUP BY product_id
HAVING COUNT(DISTINCT tag) = 2
ORDER BY product_id DESC;

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/677c44eb274bbf4664cbaf58/train/sql
The Great Data Entry Blame Game

WITH typo_errors AS (
  SELECT
    c.entered_by AS specialist_id,
    COUNT(*) AS error_count
  FROM certifications c
  JOIN employees e ON c.employee_id = e.id
  WHERE levenshtein(e.name, c.cert_name) BETWEEN 1 AND 3
  GROUP BY c.entered_by
),
total_errors AS (
  SELECT SUM(error_count) AS total FROM typo_errors
)
SELECT
  t.specialist_id,
  t.error_count,
  ROUND((t.error_count::numeric / te.total) * 100, 2)::varchar AS error_percentage
FROM typo_errors t, total_errors te
ORDER BY t.error_count DESC, t.specialist_id;

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/677854ee17c7b76184c1471c/train/sql
Identifying Mutual Likes in a Dating App

SELECT DISTINCT
    LEAST(ul1.liker_id, ul1.liked_id) AS user1_id,
    GREATEST(ul1.liker_id, ul1.liked_id) AS user2_id
FROM
    user_likes ul1
WHERE EXISTS (
    SELECT 1
    FROM user_likes ul2
    WHERE ul2.liker_id = ul1.liked_id
    AND ul2.liked_id = ul1.liker_id
)
ORDER BY user1_id, user2_id;

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/5ab828bcedbcfc65ea000099/train/sql
SQL with Pokemon: Damage Multipliers

SELECT
    p.pokemon_name,
    p.str * m.multiplier AS modifiedStrength,
    m.element
FROM pokemon p
JOIN multipliers m ON p.element_id = m.id
WHERE p.str * m.multiplier >= 40
ORDER BY modifiedStrength DESC, p.pokemon_name;

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/677c44eb274bbf4664cbaf58/train/sql
The Great Data Entry Blame Game

WITH error_records AS (
    SELECT
        c.entered_by AS specialist_id
    FROM certifications c
    JOIN employees e ON c.employee_id = e.id
    WHERE levenshtein(e.name, c.cert_name) BETWEEN 1 AND 3
)
SELECT
    specialist_id,
    COUNT(*) AS error_count,
    TO_CHAR(
        ROUND(
            (COUNT(*) * 100.0 / SUM(COUNT(*)) OVER ()),
            2
        ),
        'FM999.00'
    ) AS error_percentage
FROM error_records
GROUP BY specialist_id
ORDER BY error_count DESC, specialist_id;

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/6492b17a7c08e4005790053e/train/sql
Youngest Team Members

SELECT
  employee_id,
  full_name,
  team,
  birth_date
FROM (
    SELECT
        *,
        ROW_NUMBER() OVER (
            PARTITION BY team
            ORDER BY birth_date DESC
        ) AS age_rank
    FROM employees
) ranked_employees
WHERE age_rank = 1
ORDER BY team ASC;

------------------------------------------------------------------------------------------------------------------------
--https://www.codewars.com/kata/580918e24a85b05ad000010c

SELECT
    p.id,
    p.name,
COUNT(t.id) as toy_count
FROM people p
JOIN toys t on p.id = t.people_id
GROUP BY p.id;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/64c0fd3bb57ecf0058e101dd
Books That Have Been Out for the Longest Duration

--Create VIEW is based borrowing_info and used CASE calculate days, skip the first book
CREATE VIEW book_info AS

--Using LAG window from loans, ordered by return_date, get table with prevoius and current return_dates (not exist if  have no date)
  WITH borrowing_info AS (
      SELECT
          loan_id,
          book_id,
          borrower_name,
          return_date,
          LAG(return_date) OVER (PARTITION BY book_id ORDER BY return_date) AS prev_return_date
      FROM
          loans
)
  SELECT
      b.book_id,
      b.title,
      b.author,
      info.loan_id,
      info.borrower_name,
      CASE
        WHEN info.return_date IS NOT NULL THEN info.return_date - info.prev_return_date
        WHEN info.return_date IS NULL THEN CURRENT_DATE - info.prev_return_date
      END AS longest_borrow_interval
  FROM books b JOIN borrowing_info info ON b.book_id = info.book_id
      WHERE prev_return_date IS NOT NULL
;

--Create table is contained longest_borrow_interval and based on inner crossing get result
WITH intervals AS (
    SELECT
        MAX(longest_borrow_interval) AS longest_borrow_interval
        FROM book_info
) SELECT book_id, title, author, loan_id, borrower_name, (i.longest_borrow_interval || ' Days') AS longest_borrow_interval
  FROM intervals i INNER JOIN book_info b ON i.longest_borrow_interval = b.longest_borrow_interval;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/653f7207da59f62d2ee55035/train/sql
Drug Dosages with Dual Unit Measurements

SELECT
    dr.record_id,
    d.drug_name,
    dr.drug_amount,
CASE WHEN u2.unit_name IS NULL THEN u1.unit_name ELSE CONCAT(u1.unit_name, '/', u2.unit_name) END AS dose_units
FROM dose_records dr
JOIN drugs d ON dr.drug_id = d.drug_id
JOIN units u1 ON dr.drug_unit_id = u1.unit_id
LEFT JOIN units u2 ON dr.check_unit_id = u2.unit_id
ORDER BY d.drug_name ASC, dr.record_id ASC;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/6532433d49d3ef6435de1928/train/sql
Find Messages with Multiple Occurrences of a Word "Apple"

--The POSITION function is used to find the position of the first occurrence of "apple" in the message.
--The SUBSTRING function is then used to extract the substring starting from the position after the first occurrence of "apple".
--Another POSITION function is applied to find the position of the second occurrence of "apple" within the extracted substring.
--The result of the second POSITION function is then adjusted by adding the position of the first occurrence to obtain the correct second_occurrence_position.

SELECT
    id,
    message,
POSITION('apple' IN SUBSTRING(LOWER(message) FROM POSITION('apple' IN LOWER(message)) + 1)) + POSITION('apple' IN LOWER(message)) AS second_occurrence_position
FROM messages
WHERE LOWER(message) LIKE '%apple%apple%'
ORDER BY id DESC;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/650c5aa70b7009a1640c9596/train/sql
Sibling Count based on Common Parent

SELECT
    cp1.client_id,
    COUNT(DISTINCT cp2.client_id) AS num_siblings
FROM client_parents cp1
LEFT JOIN client_parents cp2 ON cp1.parent_id = cp2.parent_id AND cp1.client_id != cp2.client_id
GROUP BY cp1.client_id;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/62b0da0e58e471000f28ce99/train/sql
First Normal Form

CREATE TABLE dishes (
    restaurant_id INT,
    dish VARCHAR(255),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

INSERT INTO dishes (restaurant_id, dish) VALUES
    (1, 'Chicken Wings'),
    (1, 'Chicken Strips'),
    (2, 'Hamburger'),
    (2, 'Cheeseburger'),
    (2, 'Big Burger'),
    (2, 'Chickenburger'),
    (3, 'Coffee');

INSERT INTO dishes (restaurant_id, dish)
SELECT id, menu FROM restaurants;

ALTER TABLE restaurants
DROP COLUMN menu;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/6509e370597b85753f09b01f/train/sql
Parameterized SQL: Section Access Control

PREPARE find_sections(int) AS
SELECT id, section_name
FROM section_access
WHERE $1 = ANY (string_to_array(user_access, ',')::int[])
ORDER BY id;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/594633020a561e329a0000a2/train/sql
Easy SQL: Counting and Grouping

SELECT
  race,
  COUNT(race) AS count
FROM
demographics
GROUP BY race
ORDER BY count DESC;

------------------------------------------------------------------------------------------------------------------------

--https://www.codewars.com/kata/64de2c3a529cadf9db8c5f33/train/sql
Top-Scoring Students for Each Subject

SELECT
    array_agg(s.student_name ORDER BY s.student_id ASC) AS student_names,
    sub.subject_name,
    MAX(m.mark_rate) AS mark_rate
FROM marks m
JOIN students s ON m.student_id = s.student_id
JOIN subjects sub ON m.subject_id = sub.subject_id
WHERE m.mark_rate = (SELECT MAX(mark_rate) FROM marks WHERE subject_id = m.subject_id)
GROUP BY sub.subject_name;

