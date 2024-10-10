-- :name create-members-table :!
-- :doc Create members table
CREATE TABLE `members` (
    `id` int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) NOT NULL,
    `middle_names` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) NOT NULL,
    `family_name` varchar(45) DEFAULT NULL,
    `nickname` varchar(45) DEFAULT NULL,
    `preferred_name` varchar(45) DEFAULT NULL,
    `gender` varchar(45) DEFAULT NULL,
    `date_of_birth` datetime DEFAULT NULL,
    `address_of_birth` varchar(45) DEFAULT NULL,
    `city_of_birth` varchar(45) DEFAULT NULL,
    `province_of_birth` varchar(45) DEFAULT NULL,
    `country_of_birth` varchar(45) DEFAULT NULL,
    `image_url` varchar(45) DEFAULT NULL,
    `ethnicity` varchar(45) DEFAULT NULL,
    `culture` varchar(45) DEFAULT NULL,
    `languages` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- :name drop-members-table :!
-- :doc Drop members table if exists
DROP TABLE IF EXISTS `members`;

-- A :result value of :n below will return affected row count:
-- :name insert-member :! :n
-- :doc Insert a single member
INSERT INTO
    `members` (
        `first_name`,
        `middle_names`,
        `last_name`,
        `family_name`,
        `nickname`,
        `preferred_name`,
        `gender`,
        `date_of_birth`,
        `address_of_birth`,
        `city_of_birth`,
        `province_of_birth`,
        `country_of_birth`,
        `image_url`,
        `ethnicity`,
        `culture`,
        `languages`
    )
VALUES
    (
        :first_name,
        :middle_names,
        :last_name,
        :family_name,
        :nickname,
        :preferred_name,
        :gender,
        :date_of_birth,
        :address_of_birth,
        :city_of_birth,
        :province_of_birth,
        :country_of_birth,
        :image_url,
        :ethnicity,
        :culture,
        :languages
    );

-- A :result value of :n below will return affected row count:
-- :name insert-members :! :n
-- :doc Insert multiple members with :tuple* parameter type
INSERT INTO
    `members` (
        `id`,
        `first_name`,
        `middle_names`,
        `last_name`,
        `family_name`,
        `nickname`,
        `preferred_name`,
        `gender`,
        `date_of_birth`,
        `address_of_birth`,
        `city_of_birth`,
        `province_of_birth`,
        `country_of_birth`,
        `image_url`,
        `ethnicity`,
        `culture`,
        `languages`
    )
VALUES
    :tuple * :members;

-- :name update-member :! :n
UPDATE
    `members`
SET
    `first_name` = :first_name,
    `middle_names` = :middle_names,
    `last_name` = :last_name,
    `family_name` = :family_name,
    `nickname` = :nickname,
    `preferred_name` = :preferred_name,
    `gender` = :gender,
    `date_of_birth` = :date_of_birth,
    `address_of_birth` = :address_of_birth,
    `city_of_birth` = :city_of_birth,
    `province_of_birth` = :province_of_birth,
    `country_of_birth` = :country_of_birth,
    `image_url` = :image_url,
    `ethnicity` = :ethnicity,
    `culture` = :culture,
    `languages` = :languages
WHERE
    `id` = :id;

-- :name delete-members-by-id :! :n
DELETE FROM
    `members`
where
    `id` = :id;

-- A ":result" value of ":1" specifies a single record
-- (as a hashmap) will be returned
-- :name member-by-id :? :1
-- :doc Get member by id
SELECT
    *
FROM
    `members`
WHERE
    `id` = :id;

-- A ":result" value of ":*" specifies a vector of records
-- (as hashmaps) will be returned
-- :name search-members :? :*
-- :doc Search for members by case-insensitive name
SELECT
    *
FROM
    `members`
WHERE
    LOWER(`first_name`) LIKE LOWER('%:searchtext%'),
    OR LOWER(`middle_names`) LIKE LOWER('%:searchtext%'),
    OR LOWER(`last_name`) LIKE LOWER('%:searchtext%');