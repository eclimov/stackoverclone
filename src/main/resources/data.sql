INSERT INTO users(name)
VALUES('u1');
INSERT INTO users(name)
VALUES('u2');

INSERT INTO questions(text, user_id)
VALUES('my first question', 1);
INSERT INTO questions(text, user_id)
VALUES('my second question', 1);

INSERT INTO answers(text, question_id, user_id)
VALUES('my first answer', 1, 2);