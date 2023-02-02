SELECT name, count(client_id) AS project_count
FROM client
JOIN project ON client.id = project.client_id
GROUP BY client_id
HAVING count(client_id) = SELECT count(client_id)
                          FROM project
                          GROUP BY client_id
                          ORDER BY count(client_id)
                          DESC
                          LIMIT 1;
