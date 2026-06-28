CREATE TABLE IF NOT EXISTS employee
(
    id            VARCHAR(255),
    full_name     VARCHAR(255),
    email         VARCHAR(255),
    salary        DECIMAL,
    department_id VARCHAR(255),
    role          VARCHAR(255),
    position      VARCHAR(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    is_active     BOOLEAN,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);