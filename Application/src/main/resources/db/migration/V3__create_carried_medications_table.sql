CREATE TABLE IF NOT EXISTS `carried_medications`
(
    `drone_uuid`      VARCHAR(64) PRIMARY KEY NOT NULL,
    `medication_uuid` VARCHAR(64)             NOT NULL,
    `quantity`        INT(11)                 NOT NULL,
    FOREIGN KEY (`drone_uuid`) REFERENCES `drones` (`uuid`),
    FOREIGN KEY (`medication_uuid`) REFERENCES `medications` (`uuid`)
);
