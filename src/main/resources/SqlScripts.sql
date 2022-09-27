CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE answers (
	answer_id uuid NOT NULL default uuid_generate_v4(),
	question_id uuid NULL,
	is_right bool NULL,
	answer_text varchar NULL,
	CONSTRAINT answers_pkey PRIMARY KEY (answer_id),
	CONSTRAINT fk_questions FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

CREATE TABLE groups (
	group_id uuid NOT NULL default uuid_generate_v4(),
	subject_id uuid NULL,

	grade int4 NULL,
	CONSTRAINT groups_pkey PRIMARY KEY (group_id),
	CONSTRAINT fk_subjects FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

CREATE TABLE questions (
	question_id uuid NOT NULL default uuid_generate_v4(),
	theme_id uuid NULL,
	points int4 NULL,
	question_text varchar NULL,
	"type" varchar NULL,
	CONSTRAINT questions_pkey PRIMARY KEY (question_id),
	CONSTRAINT fk_themes FOREIGN KEY (theme_id) REFERENCES themes(theme_id)
);

CREATE TABLE subjects (
	subject_id uuid NOT NULL default uuid_generate_v4(),
	title varchar NULL,
	CONSTRAINT subjects_pkey PRIMARY KEY (subject_id)
);

CREATE TABLE test_histories (
	test_histories_id uuid NOT NULL default uuid_generate_v4(),
	user_id uuid NULL,
	test_id uuid NULL,
	passing_date date NULL,
	CONSTRAINT test_histories_pkey PRIMARY KEY (test_histories_id),
	CONSTRAINT fk_tests FOREIGN KEY (test_id) REFERENCES tests(test_id),
	CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE test_history_answers (
	test_histories_id uuid NULL,
	question_id uuid NULL,
	answer_id uuid NULL,
	CONSTRAINT pk_test_history_answers PRIMARY KEY (test_histories_id, question_id, answer_id),
	CONSTRAINT fk_test_histories FOREIGN KEY (test_histories_id) REFERENCES test_histories(test_histories_id),
	CONSTRAINT fk_answers FOREIGN KEY (answer_id) REFERENCES answers(answer_id),
	CONSTRAINT fk_questions FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

CREATE TABLE tests (
	test_id uuid NOT NULL default uuid_generate_v4(),
	title varchar NULL,
	subject_id uuid,
	CONSTRAINT tests_pkey PRIMARY KEY (test_id)
	CONSTRAINT fk_subjects FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

CREATE TABLE themes (
	theme_id uuid NOT NULL default uuid_generate_v4(),
	subject_id uuid NULL,
	title varchar NULL,
	CONSTRAINT themes_pkey PRIMARY KEY (theme_id),
	CONSTRAINT fk_subjects FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

CREATE TABLE users (
	user_id uuid NOT NULL default uuid_generate_v4(),
	name varchar NULL,
	sname varchar NULL,
	role varchar NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

CREATE TABLE test_i_questions (
    test_id uuid,
    question_id uuid,
    CONSTRAINT fk_tests FOREIGN KEY (test_id) REFERENCES tests(test_id)
    CONSTRAINT fk_questions FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

CREATE TABLE user_i_group (
    group_id uuid,
    user_id uuid,
    CONSTRAINT fk_groups FOREIGN KEY (group_id) REFERENCES groups(group_id)
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);