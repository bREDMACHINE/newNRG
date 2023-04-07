CREATE TABLE IF NOT EXISTS users (
    user_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(255),
    user_password VARCHAR(255),
    user_role VARCHAR(255),
    status VARCHAR(255),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS factories (
    factory_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    factory_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS documents (
    document_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    document_name VARCHAR(255),
    document_code VARCHAR(255),
    file VARCHAR(55)
);

CREATE TABLE IF NOT EXISTS spares (
    spare_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    spare_name VARCHAR(255),
    spare_description VARCHAR(255),
    spare_code VARCHAR(55)
);

CREATE TABLE IF NOT EXISTS specifications (
    specification_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    specification_name VARCHAR(255),
    specification_description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS types (
    type_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    type_name VARCHAR(255),
    factory_id INT REFERENCES factories(factory_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS values (
    value_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    specification_id INT REFERENCES specifications(specification_id) ON DELETE CASCADE,
    type_id INT REFERENCES types(type_id) ON DELETE CASCADE,
    specification_value INT
);

CREATE TABLE IF NOT EXISTS types_documents (
    type_id INT REFERENCES types(type_id) ON DELETE CASCADE,
    document_id INT REFERENCES documents(document_id) ON DELETE CASCADE,
    PRIMARY KEY (type_id, document_id)
);

CREATE TABLE IF NOT EXISTS types_spares (
    type_id INT REFERENCES types(type_id) ON DELETE CASCADE,
    spare_id INT REFERENCES spares(spare_id) ON DELETE CASCADE,
    PRIMARY KEY (type_id, spare_id)
);

CREATE TABLE IF NOT EXISTS projects (
    project_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    project_name VARCHAR(255),
    project_code VARCHAR(255),
    file VARCHAR(55)
);

CREATE TABLE IF NOT EXISTS equipment (
    equipment_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    operational_name VARCHAR(255),
    installation_year SMALLINT,
    type_id INT REFERENCES types(type_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS equipment_projects (
    equipment_id INT REFERENCES equipment(equipment_id) ON DELETE CASCADE,
    project_id INT REFERENCES projects(project_id) ON DELETE CASCADE,
    PRIMARY KEY (equipment_id, project_id)
);

CREATE TABLE IF NOT EXISTS files (
    file_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    file_name VARCHAR(255),
    file_type VARCHAR(255),
    file_size INT,
    file_content bytea
);

CREATE TABLE IF NOT EXISTS events (
    event_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    date_event TIMESTAMP WITHOUT TIME ZONE,
    name_event VARCHAR(255),
    description_event VARCHAR(255),
    equipment_id INT REFERENCES equipment(equipment_id) ON DELETE CASCADE,
    file_id INT REFERENCES files(file_id) ON DELETE CASCADE
);

