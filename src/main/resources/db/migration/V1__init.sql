CREATE TABLE portfolio_ap
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    experience   VARCHAR(21844),
    image_path   VARCHAR(255),
    name         VARCHAR(255),
    twitter_user VARCHAR(255),
    email        VARCHAR(255),
    address      VARCHAR(255),
    phone        VARCHAR(255),
    zip_code     VARCHAR(255),
    PRIMARY KEY (`id`)
);