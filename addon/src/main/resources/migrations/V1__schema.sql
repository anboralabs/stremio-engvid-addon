DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    category_id BIGINT        PRIMARY KEY NOT NULL,
    name_ VARCHAR(255)        NOT NULL
);

CREATE TABLE lessons (
  lesson_id BIGINT               PRIMARY KEY NOT NULL,
  title VARCHAR(255)             NOT NULL,
  description VARCHAR(4096)      NOT NULL,
  publish_date VARCHAR(255)      NOT NULL,
  render_link VARCHAR(255)       NOT NULL,
  category_ BIGINT               NOT NULL,
  slug VARCHAR(255)              NOT NULL,
  image_url VARCHAR(255)         ,
  youtube_id VARCHAR(255)        ,
  sync BOOLEAN                   NOT NULL DEFAULT false,
  constraint FKM1T3YVW5I7OLWDF32CWUUL7TA foreign key (category_) references categories(category_id)
);
