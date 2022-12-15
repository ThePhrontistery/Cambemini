/*CREATE TYPE USERARRAY AS INTEGER ARRAY[100];

*/
DROP TABLE IF EXISTS ATTACHMENT;
DROP TABLE IF EXISTS NOTE;
DROP TABLE IF EXISTS SWIMLANE;
DROP TABLE IF EXISTS USER_KANBAN_PERMISSION;
DROP TABLE IF EXISTS KANBAN;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS PERMISSION;


CREATE TABLE USERS (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    username VARCHAR(500) DEFAULT NOT NULL
);

CREATE TABLE KANBAN (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(250) DEFAULT NOT NULL,
    description VARCHAR(500),
    code VARCHAR(500)
);

CREATE TABLE NOTE (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    content VARCHAR(MAX) DEFAULT NOT NULL,
    swimlane_id BIGINT DEFAULT NULL
);


CREATE TABLE PERMISSION (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    rol VARCHAR(50) DEFAULT NOT NULL UNIQUE
);

CREATE TABLE ATTACHMENT (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    document_path VARCHAR(250),
    name VARCHAR(250) DEFAULT NULL,
    file VARCHAR(MAX) DEFAULT NULL,
    note_id BIGINT DEFAULT NULL
);

CREATE TABLE USER_KANBAN_PERMISSION(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    user_id BIGINT DEFAULT NULL,
    kanban_id BIGINT DEFAULT NULL,
    permission_id BIGINT DEFAULT NULL
);

CREATE TABLE SWIMLANE(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    kanban_id BIGINT DEFAULT NULL,
    title VARCHAR(250) DEFAULT NULL
);


ALTER TABLE SWIMLANE ADD FOREIGN KEY (kanban_id) REFERENCES KANBAN (id);

ALTER TABLE NOTE ADD FOREIGN KEY (swimlane_id) REFERENCES SWIMLANE (id);

ALTER TABLE ATTACHMENT ADD FOREIGN KEY (note_id) REFERENCES NOTE (id);

ALTER TABLE USER_KANBAN_PERMISSION ADD FOREIGN KEY (user_id) REFERENCES USERS (id);

ALTER TABLE USER_KANBAN_PERMISSION ADD FOREIGN KEY (kanban_id) REFERENCES KANBAN (id);

ALTER TABLE USER_KANBAN_PERMISSION ADD FOREIGN KEY (permission_id) REFERENCES PERMISSION (id);

