CREATE TABLE IF NOT EXISTS `drones`
(
    `id`               INT AUTO_INCREMENT PRIMARY KEY,
    `uuid`             VARCHAR(64) UNIQUE                                                        NOT NULL,
    `serial_number`    VARCHAR(100) UNIQUE                                                       NOT NULL,
    `weight_limit`     INT                                                                       NOT NULL,
    `battery_capacity` INT                                                                       NOT NULL DEFAULT 100,
    `medication_uuid`  VARCHAR(64)                                                               NULL,
    `model`            VARCHAR(64) NOT NULL,
    `state`            VARCHAR(64) NOT NULL DEFAULT 'IDLE',
    FOREIGN KEY (`medication_uuid`) REFERENCES `medications` (`uuid`)
);
