--liquibase formatted sql

--changeset eclimov:1590399073355-1
CREATE TABLE answers (id BIGINT AUTO_INCREMENT NOT NULL, text VARCHAR(255) NULL, user_id BIGINT NOT NULL, question_id BIGINT NOT NULL, CONSTRAINT PK_ANSWERS PRIMARY KEY (id));

--changeset eclimov:1590399073355-2
CREATE TABLE questions (id BIGINT AUTO_INCREMENT NOT NULL, text VARCHAR(255) NULL, user_id BIGINT NOT NULL, test VARCHAR(255) NULL, CONSTRAINT PK_QUESTIONS PRIMARY KEY (id));

--changeset eclimov:1590399073355-3
CREATE TABLE rating_answer (id BIGINT AUTO_INCREMENT NOT NULL, value INT NULL, answer_id BIGINT NOT NULL, user_id BIGINT NOT NULL, CONSTRAINT PK_RATING_ANSWER PRIMARY KEY (id));

--changeset eclimov:1590399073355-4
CREATE TABLE rating_question (id BIGINT AUTO_INCREMENT NOT NULL, value INT NULL, question_id BIGINT NOT NULL, user_id BIGINT NOT NULL, CONSTRAINT PK_RATING_QUESTION PRIMARY KEY (id));

--changeset eclimov:1590399073355-5
CREATE TABLE roles (id BIGINT AUTO_INCREMENT NOT NULL, description VARCHAR(255) NULL, name VARCHAR(255) NULL, CONSTRAINT PK_ROLES PRIMARY KEY (id));

--changeset eclimov:1590399073355-6
CREATE TABLE user_role (user_id BIGINT NOT NULL, role_id BIGINT NOT NULL);

--changeset eclimov:1590399073355-7
CREATE TABLE users (id BIGINT AUTO_INCREMENT NOT NULL, password VARCHAR(255) NULL, username VARCHAR(255) NULL, CONSTRAINT PK_USERS PRIMARY KEY (id));

--changeset eclimov:1590399073355-8
CREATE INDEX FK_2nuepan7w6vgupoegetvyg2fq ON answers(user_id);

--changeset eclimov:1590399073355-9
CREATE INDEX FK_3c8cktk5w0pwc2fuoh0wutdvs ON rating_question(question_id);

--changeset eclimov:1590399073355-10
CREATE INDEX FK_a8hukb7yjph7wnrlnn8x0fcja ON answers(question_id);

--changeset eclimov:1590399073355-11
CREATE INDEX FK_apcc8lxk2xnug8377fatvbn04 ON user_role(user_id);

--changeset eclimov:1590399073355-12
CREATE INDEX FK_it77eq964jhfqtu54081ebtio ON user_role(role_id);

--changeset eclimov:1590399073355-13
CREATE INDEX FK_jpc1rgnivey7xj382qw157l4k ON questions(user_id);

--changeset eclimov:1590399073355-14
CREATE INDEX FK_ofag9xnq9sdf7rai29ofkqlmt ON rating_answer(user_id);

--changeset eclimov:1590399073355-15
CREATE INDEX FK_s4774cp6sslqbcax2ekg4d9st ON rating_answer(answer_id);

--changeset eclimov:1590399073355-16
CREATE INDEX FK_targ6jmrk69le3dr6t23boejt ON rating_question(user_id);

--changeset eclimov:1590399073355-17
ALTER TABLE answers ADD CONSTRAINT FK_2nuepan7w6vgupoegetvyg2fq FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-18
ALTER TABLE rating_question ADD CONSTRAINT FK_3c8cktk5w0pwc2fuoh0wutdvs FOREIGN KEY (question_id) REFERENCES questions (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-19
ALTER TABLE answers ADD CONSTRAINT FK_a8hukb7yjph7wnrlnn8x0fcja FOREIGN KEY (question_id) REFERENCES questions (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-20
ALTER TABLE user_role ADD CONSTRAINT FK_apcc8lxk2xnug8377fatvbn04 FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-21
ALTER TABLE user_role ADD CONSTRAINT FK_it77eq964jhfqtu54081ebtio FOREIGN KEY (role_id) REFERENCES roles (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-22
ALTER TABLE questions ADD CONSTRAINT FK_jpc1rgnivey7xj382qw157l4k FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-23
ALTER TABLE rating_answer ADD CONSTRAINT FK_ofag9xnq9sdf7rai29ofkqlmt FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-24
ALTER TABLE rating_answer ADD CONSTRAINT FK_s4774cp6sslqbcax2ekg4d9st FOREIGN KEY (answer_id) REFERENCES answers (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--changeset eclimov:1590399073355-25
ALTER TABLE rating_question ADD CONSTRAINT FK_targ6jmrk69le3dr6t23boejt FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

