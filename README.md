# HintTutor

HintTutor is a console-based Java tutoring application that presents programming questions stored in a SQLite database. Users can answer each question directly or request a single hint before submitting an answer. At the end of the session, HintTutor reviews every response and displays a final score.

## Features

- Loads programming questions from SQLite using JDBC
- Displays question difficulty and topic
- Supports one optional hint per question
- Compares answers without treating capitalization or whitespace differences as incorrect
- Stores session results for an end-of-quiz review
- Reports the user's final score
- Includes SQL files for recreating and inspecting the database

## Technologies

- Java 26
- Maven
- SQLite
- JDBC

## Project Structure

```text
HintTutor/
├── src/main/java/org/example/Main.java
├── tutor.db
├── schema.sql
├── seed.sql
├── pom.xml
└── .gitignore
```

## Running the Project

1. Clone the repository.
2. Make sure Java 26 and Maven are installed.
3. Keep `tutor.db` in the project root.
4. Open the project in IntelliJ IDEA and run `Main.java`.

Maven will download the SQLite JDBC dependency defined in `pom.xml`.

## Database

The `questions` table contains:

- `id`
- `difficulty`
- `topic`
- `question_text`
- `hint`
- `answer`

`tutor.db` is included so the application can run immediately. `schema.sql` and `seed.sql` are also included so the database structure and sample data are visible and can be recreated.

## Current Scope

HintTutor is currently a console application focused on guided programming practice. Possible future improvements include randomized question selection, difficulty filtering, more flexible answer grading, expanded question sets, and a graphical user interface.
