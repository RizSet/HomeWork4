SELECT 'YOUNGEST' AS type, name, birthday
FROM worker
WHERE birthday = SELECT birthday
FROM worker
ORDER BY birthday
DESC
LIMIT 1
UNION ALL
SELECT 'ELDEST' AS type, name, birthday
FROM worker
WHERE birthday = SELECT birthday
FROM worker
ORDER BY birthday
LIMIT 1
