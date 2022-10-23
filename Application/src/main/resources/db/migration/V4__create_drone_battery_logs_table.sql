CREATE TABLE IF NOT EXISTS `drone_battery_logs`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `uuid`       VARCHAR(64) UNIQUE NOT NULL,
    `drone_uuid` VARCHAR(64)        NOT NULL,
    `percentage` INT                NOT NULL,
    `checked_at` DATETIME           NOT NULL,
    FOREIGN KEY (`drone_uuid`) REFERENCES `drones` (`uuid`)
);
