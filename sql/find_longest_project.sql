SELECT CONCAT('project', ' ',  id) AS name,
       datediff(month, start_date, finish_date) AS month_count
FROM project
WHERE datediff(month, start_date, finish_date) = SELECT datediff(month, start_date, finish_date) AS month_count
                                                 FROM project
                                                 ORDER BY MONTH_COUNT
                                                 DESC
                                                 LIMIT 1;
