CREATE TABLE IF NOT EXISTS currencies (
    `id` BIGINT NOT NULL,
    `symbol` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
--    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
    );

CREATE TABLE IF NOT EXISTS prices (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `currency_id` BIGINT NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `updated` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
--    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
--    INDEX `currency_id_idx` (`currency_id` ASC) VISIBLE,
    CONSTRAINT `currency_id`
      FOREIGN KEY (`currency_id`)
      REFERENCES currencies (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS users (
  `id` BIGINT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `price_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`)
  CONSTRAINT `price_id`
        FOREIGN KEY (`price_id`)
        REFERENCES prices (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);