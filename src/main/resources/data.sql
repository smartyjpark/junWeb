INSERT INTO USER (id, user_id, password, name, email) VALUES (1, 'smartyjpark', '1234', 'yejun', 'smartyjpark@gmail.com');
INSERT INTO USER (id, user_id, password, name, email) VALUES (2, 'smartycpark', '1234', 'yechan', 'smartycpark@gmail.com');
INSERT INTO USER (id, user_id, password, name, email) VALUES (3, 'javajigi', '1234', '자바지기', 'javajigi@gmail.com');



INSERT INTO Question (question_id, writer_id, title, contents, date, deleted) VALUES ('1', '1', '초보입니다. 언어 추천해주세요.', '자바가 편하세요 자바스크립트가 편하세요? ㅎㅎ	', CURRENT_TIMESTAMP , 'false');
INSERT INTO Question (question_id, writer_id, title, contents, date, deleted) VALUES ('2', '2', '자바 어떡합니까 하하', '자바 힘들어요 ㅠㅠㅠ', CURRENT_TIMESTAMP, 'false');

INSERT INTO Answer (answer_id, question_question_id, writer_id, contents, date, deleted, my_answer) VALUES ('1', '1', '3', '자바가 짱입니다', CURRENT_TIMESTAMP, 'false', 'false');
INSERT INTO Answer (answer_id, question_question_id, writer_id, contents, date, deleted, my_answer) VALUES ('2', '2', '3', '힘들어도 해야지 인마', CURRENT_TIMESTAMP, 'false', 'false');