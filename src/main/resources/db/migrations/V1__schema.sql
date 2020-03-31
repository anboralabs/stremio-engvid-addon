DROP TABLE IF EXISTS lessons;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    category_id BIGINT PRIMARY KEY NOT NULL,
    name_ VARCHAR(255) NOT NULL
);

CREATE TABLE lessons (
    lesson_id BIGINT PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(4096) NOT NULL,
    publish_date VARCHAR(255) NOT NULL,
    render_link VARCHAR(255) NOT NULL,
    category_ BIGINT NOT NULL,
    slug VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL DEFAULT 'https://www.engvid.com/wp-content/themes/engvid2/images/engvid.png',
    youtube_id VARCHAR(255),
    sync BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT FKM1T3YVW5I7OLWDF32CWUUL7TA FOREIGN KEY (category_) REFERENCES categories (category_id)
);
