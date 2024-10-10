-- :name create-relations-table :!
-- :doc Create relations table
CREATE TABLE `relations` (
    `member_id` int NOT NULL,
    `relation_id` int NOT NULL,
    `direct_relation` enum(
        'parent',
        'sibling',
        'child',
        'step-parent',
        'step-sibling',
        'step-child',
        'spouse'
    ) NOT NULL,
    `is_blood_relation` tinyint NOT NULL,
    `care` enum('giver', 'receiver') DEFAULT NULL,
    PRIMARY KEY (`member_id`, `relation_id`) FOREIGN KEY (`member_id`) REFERENCES members(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`relation_id`) REFERENCES members(`id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- :name drop-relations-table :!
-- :doc Drop relations table if exists
DROP TABLE IF EXISTS `relations`;

-- A :result value of :n below will return affected row count:
-- :name insert-relation :! :n
-- :doc Insert a single relation
INSERT INTO
    `family_tree`.`relations` (
        `member_id`,
        `relation_id`,
        `direct_relation`,
        `is_blood_relation`,
        `care`
    )
VALUES
    (
        :member_id,
        :relation_id,
        :direct_relation,
        :is_blood_relation,
        :care
    );

-- :name update-relation :! :n
UPDATE
    `relations`
SET
    `direct_relation` = :direct_relation,
    `is_blood_relation` = :is_blood_relation,
    `care` = :care
WHERE
    `member_id` = :member_id
    AND `relation_id` = :relation_id;

-- :name delete-relation-by-id :! :n
DELETE FROM
    `relations`
WHERE
    `member_id` = :member_id
    AND `relation_id` = :relation_id;

-- A ":result" value of ":1" specifies a single record
-- (as a hashmap) will be returned
-- :name relation-between-members :? :1
-- :doc Get relation between two members
SELECT
    *
FROM
    `relations`
WHERE
    `member_id` = :member_id
    AND `relation_id` = :relation_id;