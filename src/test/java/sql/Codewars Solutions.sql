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
