INSERT INTO employee (name, email) VALUES ('Michalina Zareba', 'Michalina.Zareba@gmail');
INSERT INTO employee (name, email) VALUES ('Jakub Opielka', 'Jakub.Opielka@gmail');

INSERT INTO job_cycle (start_time, end_time, job_name) VALUES ('10:20','17:45', 'Knippen');
INSERT INTO job_cycle (start_time, end_time, job_name) VALUES ('6:30', '19:29', 'Overige');

INSERT INTO job_cycle_employees (employees_id, job_cycles_id) VALUES (1, 2);
INSERT INTO job_cycle_employees (employees_id, job_cycles_id) VALUES (2, 1);

