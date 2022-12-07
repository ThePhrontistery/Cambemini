
INSERT INTO USERS (id, username) VALUES (1, 'Cesar');
INSERT INTO USERS (id, username) VALUES (2, 'Mercedes');
INSERT INTO USERS (id, username) VALUES (3, 'Jacques');
INSERT INTO USERS (id, username) VALUES (4, 'Raul');
INSERT INTO USERS (id, username) VALUES (5, 'Fredy');

INSERT INTO KANBAN (id, title) VALUES (1, 'kanban1');
INSERT INTO KANBAN (id, title) VALUES (2, 'kanban1');
INSERT INTO KANBAN (id, title) VALUES (3, 'kanban2');
INSERT INTO KANBAN (id, title) VALUES (4, 'kanban2');
INSERT INTO KANBAN (id, title) VALUES (5, 'kanban2');

INSERT INTO PERMISSION (id, rol) VALUES (1, 'Owner');
INSERT INTO PERMISSION (id, rol) VALUES (2, 'Editor');
INSERT INTO PERMISSION (id, rol) VALUES (3, 'Collaborator');

INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (1, 1,'TO DO');
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (2, 1,'DOING');
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (3, 1,'DONE');
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (4, 2, 'TO DO');
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (5, 2,'DOING');
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (6, 2, 'DONE');

INSERT INTO NOTE (id, content, swimlane_id) VALUES (1, 'contenido1', 1);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (2, 'contenido2', 1);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (3, 'contenido3', 2);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (4, 'contenido4', 3);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (5, 'contenido5', 3);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (6, 'contenido6', 4);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (7, 'contenido7', 4);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (8, 'contenido8', 4);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (9, 'contenido9', 5);
INSERT INTO NOTE (id, content, swimlane_id) VALUES (10, 'contenido10', 5);

INSERT INTO ATTACHMENT (id, document_path, note_id) VALUES (1,'url1', 1);
INSERT INTO ATTACHMENT (id, document_path, note_id) VALUES (2,'url2', 2);
INSERT INTO ATTACHMENT (id, document_path, note_id) VALUES (3,'url3', 3);
INSERT INTO ATTACHMENT (id, document_path, note_id) VALUES (4,'url4', 4);

INSERT INTO USER_KANBAN_PERMISSION (id, user_id, kanban_id, permission_id) VALUES (1, 1, 1, 1);
INSERT INTO USER_KANBAN_PERMISSION (id, user_id, kanban_id, permission_id) VALUES (2, 2, 1, 2);
INSERT INTO USER_KANBAN_PERMISSION (id, user_id, kanban_id, permission_id) VALUES (3, 3, 1, 3);
INSERT INTO USER_KANBAN_PERMISSION (id, user_id, kanban_id, permission_id) VALUES (4, 4, 2, 1);
INSERT INTO USER_KANBAN_PERMISSION (id, user_id, kanban_id, permission_id) VALUES (5, 5, 2, 2);
INSERT INTO USER_KANBAN_PERMISSION (id, user_id, kanban_id, permission_id) VALUES (6, 2, 2, 3);