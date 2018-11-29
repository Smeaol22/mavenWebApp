USE TEST;

/* Create Table (Should be used after base creation see userCreation.sql)*/
CREATE TABLE IF NOT EXISTS ANSWER (answer_id INT AUTO_INCREMENT PRIMARY KEY,
answer_label VARCHAR(500) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS QUESTION ( question_id INT AUTO_INCREMENT PRIMARY KEY,
question_label VARCHAR(500) UNIQUE NOT NULL,
answer_id INT NOT NULL,
FOREIGN KEY fk_answer(answer_id) REFERENCES ANSWER(answer_id));

CREATE TABLE IF NOT EXISTS TAGS (tags_id INT AUTO_INCREMENT PRIMARY KEY, tags_label varchar(50) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS QUESTIONTAGSRELATION (question_id INT NOT NULL , tags_id INT NOT NULL, FOREIGN KEY fk_on_question_id(question_id) REFERENCES QUESTION(question_id), FOREIGN KEY fk_on_tags_id(tags_id) REFERENCES TAGS(tags_id));



/* Initialize value*/
INSERT INTO ANSWER(answer_label) VALUES('rouge');
INSERT INTO ANSWER(answer_label) VALUES('bleue');

INSERT INTO TAGS(tags_label) VALUES('CHEVAL');
INSERT INTO TAGS(tags_label) VALUES('HENRY IV');

SELECT tags_label FROM TAGS WHERE tags_id IN (SELECT tags_id FROM QUESTIONTAGSRELATION WHERE question_id = 1);

INSERT INTO QUESTION(question_label, answer_id) VALUES ('Quel est la couleur du cheval rouge d henry IV', 1);

INSERT INTO QUESTION(question_label, answer_id) VALUES ('Quel est la couleur du cheval bleue d henry IV', 2);

INSERT INTO QUESTIONTAGSRELATION(question_id, tags_id) VALUES(1,2);
INSERT INTO QUESTIONTAGSRELATION(question_id, tags_id) VALUES(1,1);


