CREATE TABLE IF NOT EXISTS `medications`
(
    `uuid`       VARCHAR(64) PRIMARY KEY NOT NULL,
    `name`       VARCHAR(255) UNIQUE     NOT NULL,
    `code`       VARCHAR(255) UNIQUE     NOT NULL,
    `weight`     INT(11)                 NOT NULL,
    `image_uuid` VARCHAR(64)             NULL
);
