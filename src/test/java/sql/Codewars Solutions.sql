--https://www.codewars.com/kata/580918e24a85b05ad000010c

SELECT
p.id,
p.name,
COUNT(t.id) as toy_count
FROM people p
JOIN toys t on p.id = t.people_id
GROUP BY p.id;