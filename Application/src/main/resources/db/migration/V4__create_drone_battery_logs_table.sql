CREATE TABLE IF NOT EXISTS `drone_battery_logs`
(
    `uuid`       VARCHAR(64) PRIMARY KEY NOT NULL,
    `drone_uuid` VARCHAR(64)             NOT NULL,
    `percentage` INT                     NOT NULL,
    `checked_at` DATETIME                NOT NULL,
    FOREIGN KEY (`drone_uuid`) REFERENCES `drones` (`uuid`)
);
