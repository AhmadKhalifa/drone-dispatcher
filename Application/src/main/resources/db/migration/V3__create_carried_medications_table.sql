CREATE TABLE IF NOT EXISTS `carried_medications`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `uuid`            VARCHAR(64) UNIQUE NOT NULL,
    `drone_uuid`      VARCHAR(64)        NOT NULL,
    `medication_uuid` VARCHAR(64)        NOT NULL,
    `quantity`        INT                NOT NULL,
    FOREIGN KEY (`drone_uuid`) REFERENCES `drones` (`uuid`),
    FOREIGN KEY (`medication_uuid`) REFERENCES `medications` (`uuid`)
);
