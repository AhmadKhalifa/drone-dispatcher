CREATE TABLE IF NOT EXISTS `medications`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `uuid`       VARCHAR(64) UNIQUE  NOT NULL,
    `name`       VARCHAR(255) UNIQUE NOT NULL,
    `code`       VARCHAR(255) UNIQUE NOT NULL,
    `weight`     INT                 NOT NULL,
    `image_uuid` VARCHAR(64)         NULL
);
