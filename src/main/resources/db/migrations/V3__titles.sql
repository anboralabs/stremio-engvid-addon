DROP TABLE IF EXISTS titles;

CREATE TABLE titles (
    video_slug VARCHAR(255) PRIMARY KEY NOT NULL,
    video_category BIGINT NOT NULL
);