INSERT INTO users (email, user_password, user_role, status)
VALUES ('admin@admin.ru', '$2a$10$vzsa0LPrAMZb8rg..fssiuaQ2cKjW7BPOTDU3lQ0T8eiu6GAlqxUy', 'ADMIN', 'ACCEPTED');

INSERT INTO documents (document_name, document_code, file)
VALUES ('Руководство по эксплуатации1', '1РЭ', 'file1'),
       ('Руководство по эксплуатации2', '2РЭ', 'file2'),
       ('Руководство по эксплуатации3', '3РЭ', 'file3');

INSERT INTO projects (project_name, project_code, file)
VALUES ('ПРОЕКТ1', 'ШИФР ПРОЕКТА1', 'file1'),
       ('ПРОЕКТ2', 'ШИФР ПРОЕКТА2', 'file2'),
       ('ПРОЕКТ3', 'ШИФР ПРОЕКТА3', 'file3');

INSERT INTO factories (factory_name)
VALUES ('завод1'),
       ('завод2'),
       ('завод3');

INSERT INTO spares (spare_name, spare_description, spare_code)
VALUES ('деталь1', 'для установки в оборудование1', 'код11'),
       ('деталь2', 'для установки в оборудование1', 'код12'),
       ('деталь3', 'для установки в оборудование1', 'код13'),
       ('деталь4', 'для установки в оборудование2', 'код24'),
       ('деталь5', 'для установки в оборудование2', 'код25');

INSERT INTO specifications (specification_name, specification_description)
VALUES ('Характеристика1, ед.', 'Описание Характеристики1'),
       ('Характеристика2, ед.', 'Описание Характеристики2'),
       ('Характеристика3, ед.', 'Описание Характеристики3'),
       ('Характеристика4, ед.', 'Описание Характеристики4'),
       ('Характеристика5, ед.', 'Описание Характеристики5'),
       ('Характеристика6, ед.', 'Описание Характеристики6'),
       ('Характеристика7, ед.', 'Описание Характеристики7');

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
VALUES ('2001-02-01', 'TR', 'TR', 1, 1),
       ('2001-03-01', 'Test', 'Test', 1, 2),
       ('2003-02-01', 'KR', 'KR', 1, 3),
       ('2003-03-01', 'Test', 'Test', 1, 4),
       ('2006-02-01', 'TR', 'TR', 1, 5),
       ('2006-03-01', 'Test', 'Test', 1, 6),
       ('2008-02-01', 'KR', 'KR', 1, 7),
       ('2008-03-01', 'Test', 'Test', 1, 7),
       ('2011-02-01', 'TR', 'TR', 1, 8),
       ('2011-03-01', 'Test', 'Test', 1, 9),
       ('2013-02-01', 'KR', 'KR', 1, 10),
       ('2013-03-01', 'Test', 'Test', 1, 11),
       ('2016-02-01', 'TR', 'TR', 1, 12),
       ('2016-03-01', 'Test', 'Test', 1, 13),
       ('2018-02-01', 'KR', 'KR', 1, 14),
       ('2018-03-01', 'Test', 'Test', 1, 15),
       ('2021-02-01', 'TR', 'TR', 1, 16),
       ('2021-03-01', 'Test', 'Test', 1, 17),
       ('2023-02-01', 'KR', 'KR', 1, 18),
       ('2023-03-01', 'Test', 'Test', 1, 19),
       ('2023-04-01', 'TR', 'TR', 2, 20),
       ('2023-05-01', 'Test', 'Test', 2, 21);

INSERT INTO equipment_projects (equipment_id, project_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2);
