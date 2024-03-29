INSERT INTO users (email, user_password, user_role, status)
VALUES ('admin@admin.ru', '$2a$10$vzsa0LPrAMZb8rg..fssiuaQ2cKjW7BPOTDU3lQ0T8eiu6GAlqxUy', 'ADMIN', 'ACCEPTED');

INSERT INTO documents (document_name, document_code, file_id)
VALUES ('Руководство1', '1РЭ', null),
       ('Руководство2', '2РЭ', null),
       ('Руководство3', '3РЭ', null);

INSERT INTO projects (project_name, project_code, file_id)
VALUES ('ПРОЕКТ1', 'ШИФР1', null),
       ('ПРОЕКТ2', 'ШИФР2', null),
       ('ПРОЕКТ3', 'ШИФР3', null);

INSERT INTO factories (factory_name)
VALUES ('завод1'),
       ('завод2'),
       ('завод3');

INSERT INTO spares (spare_name, spare_description, spare_code)
VALUES ('деталь1', 'оборудование1', 'код11'),
       ('деталь2', 'оборудование1', 'код12'),
       ('деталь3', 'оборудование1', 'код13'),
       ('деталь4', 'оборудование2', 'код24'),
       ('деталь5', 'оборудование2', 'код25');

INSERT INTO specifications (specification_name, specification_description)
VALUES ('Характеристика1', 'Описание1'),
       ('Характеристика2', 'Описание2'),
       ('Характеристика3', 'Описание3'),
       ('Характеристика4', 'Описание4'),
       ('Характеристика5', 'Описание5'),
       ('Характеристика6', 'Описание6'),
       ('Характеристика7', 'Описание7');

INSERT INTO types (type_name, factory_id)
VALUES ('тип1', 1),
       ('тип2', 3);

INSERT INTO values (specification_id, type_id, specification_value)
VALUES (1, 1, 100),
       (2, 1, 200),
       (3, 1, 300),
       (1, 2, 100),
       (2, 2, 200),
       (3, 2, 300);

INSERT INTO types_documents (type_id, document_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2);

INSERT INTO types_spares (type_id, spare_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5);

INSERT INTO equipment (operational_name, installation_year, type_id)
VALUES ('Оборудование1', 1985, 1),
       ('Оборудование2', 2000, 2);

INSERT INTO events (date_event, name_event, description_event, equipment_id, file_id)
VALUES ('2001-02-01', 'TR', 'TR', 1, null),
       ('2001-03-01', 'Test', 'Test', 1, null),
       ('2003-02-01', 'KR', 'KR', 1, null),
       ('2003-03-01', 'Test', 'Test', 1, null),
       ('2006-02-01', 'TR', 'TR', 1, null),
       ('2006-03-01', 'Test', 'Test', 1, null),
       ('2008-02-01', 'KR', 'KR', 1, null),
       ('2008-03-01', 'Test', 'Test', 1, null),
       ('2011-02-01', 'TR', 'TR', 1, null),
       ('2011-03-01', 'Test', 'Test', 1, null),
       ('2013-02-01', 'KR', 'KR', 1, null),
       ('2013-03-01', 'Test', 'Test', 1, null),
       ('2016-02-01', 'TR', 'TR', 1, null),
       ('2016-03-01', 'Test', 'Test', 1, null),
       ('2018-02-01', 'KR', 'KR', 1, null),
       ('2018-03-01', 'Test', 'Test', 1, null),
       ('2021-02-01', 'TR', 'TR', 1, null),
       ('2021-03-01', 'Test', 'Test', 1, null),
       ('2023-02-01', 'KR', 'KR', 1, null),
       ('2023-03-01', 'Test', 'Test', 1, null),
       ('2023-04-01', 'TR', 'TR', 2, null),
       ('2023-05-01', 'Test', 'Test', 2, null);

INSERT INTO equipment_projects (equipment_id, project_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2);
