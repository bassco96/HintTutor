DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
    id INTEGER PRIMARY KEY,
    difficulty TEXT NOT NULL,
    topic TEXT NOT NULL,
    question_text TEXT NOT NULL,
    hint TEXT NOT NULL,
    answer TEXT NOT NULL
);
