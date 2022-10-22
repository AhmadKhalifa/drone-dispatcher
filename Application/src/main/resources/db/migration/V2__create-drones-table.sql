CREATE TABLE IF NOT EXISTS `drones`
(
    `uuid`             VARCHAR(64) PRIMARY KEY                                                   NOT NULL,
    `serial_number`    VARCHAR(100) UNIQUE                                                       NOT NULL,
    `weight_limit`     INT(11)                                                                   NOT NULL,
    `battery_capacity` INT(11)                                                                   NOT NULL DEFAULT 100,
    `medication_uuid`  VARCHAR(64)                                                               NULL,
    `state`            ENUM ('IDLE','LOADING', 'LOADED', 'DELIVERING', 'DELIVERED', 'RETURNING') NOT NULL DEFAULT 'IDLE',
    FOREIGN KEY (`medication_uuid`) REFERENCES `medications` (`uuid`)
);
