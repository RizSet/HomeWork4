SELECT concat('project', ' ',  project_id) AS name,
        sum(salary) * datediff(month, start_date, finish_date) AS price,
FROM PROJECT_WORKER
         JOIN worker ON project_worker.worker_id = worker.id
         JOIN PROJECT ON project_worker.PROJECT_ID  = PROJECT.id
GROUP BY PROJECT_ID
ORDER BY price
DESC
