-- :name create-parents-table :!
-- :doc Create parents table
CREATE TABLE parents (
    id bigint NOT NULL,
    child_id bigint NOT NULL,
    is_blood_relation boolean NOT NULL,
    is_caregiver boolean NOT NULL,
    PRIMARY KEY (id, child_id),
    FOREIGN KEY (id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (child_id) REFERENCES members(id) ON DELETE CASCADE
);

-- :name drop-parents-table :!
-- :doc Drop parents table if exists
DROP TABLE IF EXISTS parents CASCADE;

-- A :result value of :n below will return affected row count:
-- :name insert-parent-child :? :1
-- :doc Insert a single parent-child relation
INSERT INTO
    parents (
        id,
        child_id,
        is_blood_relation,
        is_caregiver
    )
VALUES
    (
        :id,
        :child_id,
        :is_blood_relation,
        :is_caregiver
    ) RETURNING id,
    child_id;

-- :name update-parent-child :! :n
UPDATE
    parents
SET
    is_blood_relation = :is_blood_relation,
    is_caregiver = :is_caregiver
WHERE
    id = :id
    AND child_id = :child_id;

-- :name delete-parent-child-by-id :! :n
DELETE FROM
    parents
WHERE
    id = :id
    AND child_id = :child_id;