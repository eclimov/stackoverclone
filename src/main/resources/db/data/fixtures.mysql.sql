--liquibase formatted sql
--changeset eclimov:1590399073355-1

INSERT INTO roles (name, description)
VALUES
    ('ROLE_USER', 'Can update only his own data'),
    ('ROLE_ADMIN', 'Can see and update any data');

INSERT INTO users(username, password)
VALUES
    ('john.doe', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a'),
    ('admin.admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a'),
    ('test.user', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a');

--Implement role hierarchy to avoid setting role tree explicitly for each user
INSERT INTO user_role(user_id, role_id)
VALUES
    (1,1),
    (2,1),
    (2,2),
    (3,1);


INSERT INTO questions(text, user_id)
VALUES
    ('my first question', 1),
    ('my second question', 1),
    ('my third question', 2);

INSERT INTO answers(text, question_id, user_id)
VALUES
    ('answer for question 1 user 2', 1, 2),
    ('answer for question 1 user 2', 1, 2),
    ('answer for question 2 user 2', 2, 2);

INSERT INTO rating_question(value, question_id, user_id)
VALUES
    (1, 1, 1),
    (1, 2, 1),
    (-1, 3, 2);

INSERT INTO rating_answer(value, answer_id, user_id)
VALUES
    (1, 1, 1),
    (-1, 2, 1);
