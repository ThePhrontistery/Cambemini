
INSERT INTO USERS (id, permission_id) VALUES (1, 1);
INSERT INTO USERS (id, permission_id) VALUES (1, 2);
INSERT INTO USERS (id, permission_id) VALUES (2, 3);
INSERT INTO USERS (id, permission_id) VALUES (2, 4);
INSERT INTO USERS (id, permission_id) VALUES (3, 5);


INSERT INTO KANBAN (id, title) VALUES (1, 'kanban1');
INSERT INTO KANBAN (id, title) VALUES (1, 'kanban1');
INSERT INTO KANBAN (id, title) VALUES (2, 'kanban2');
INSERT INTO KANBAN (id, title) VALUES (2, 'kanban2');
INSERT INTO KANBAN (id, title) VALUES (2, 'kanban2');

INSERT INTO USER_KANBAN (user_id, kanban_id) VALUES (1, 1);
INSERT INTO USER_KANBAN (user_id, kanban_id) VALUES (2, 1);
INSERT INTO USER_KANBAN (user_id, kanban_id) VALUES (1, 2);
INSERT INTO USER_KANBAN (user_id, kanban_id) VALUES (2, 2);
INSERT INTO USER_KANBAN (user_id, kanban_id) VALUES (3, 2);

INSERT INTO PERMISSION (id, permission_type, user_id, kanban_id) VALUES (1, 1, 1, 1);
INSERT INTO PERMISSION (id, permission_type, user_id, kanban_id) VALUES (2, 2, 1, 2);
INSERT INTO PERMISSION (id, permission_type, user_id, kanban_id) VALUES (3, 3, 2, 1);
INSERT INTO PERMISSION (id, permission_type, user_id, kanban_id) VALUES (4, 2, 2, 2);
INSERT INTO PERMISSION (id, permission_type, user_id, kanban_id) VALUES (5, 2, 3, 2);

INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (1, 1,"TO DO");
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (2, 1,"DOING");
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (3, 1,"DONE");
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (4, 2, "TO DO");
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (5, 2,"DOING");
INSERT INTO SWIMLANE (id, kanban_id, title) VALUES (6, 2, "DONE");

INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (1, "contenido1", 1, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (2, "contenido2", 1, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (3, "contenido3", 2, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (4, "contenido4", 3, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (5, "contenido5", 3, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (6, "contenido6", 4, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (7, "contenido7", 4, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (8, "contenido8", 4, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (9, "contenido9", 5, null);
INSERT INTO NOTE (id, content, swimlane_id, attachment_id) VALUES (10, "contenido10", 6, null);
