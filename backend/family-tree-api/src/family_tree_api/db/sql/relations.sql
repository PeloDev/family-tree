-- :name create-relations-table :!
-- :doc Create relations table
CREATE TYPE direct_relation_type AS ENUM (
    'parent',
    'sibling',
    'child',
    'step-parent',
    'step-sibling',
    'step-child',
    'spouse'
);

CREATE TYPE care_type AS ENUM ('giver', 'receiver');

CREATE TABLE relations (
    member_id bigint NOT NULL,
    relation_id bigint NOT NULL,
    direct_relation direct_relation_type NOT NULL,
    is_blood_relation boolean NOT NULL,
    care care_type,
    PRIMARY KEY (member_id, relation_id),
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (relation_id) REFERENCES members(id) ON DELETE CASCADE
);

-- :name drop-relations-table :!
-- :doc Drop relations table if exists
DROP TABLE IF EXISTS relations CASCADE;

DROP TYPE IF EXISTS direct_relation_type;

DROP TYPE IF EXISTS care_type;

-- A :result value of :n below will return affected row count:
-- :name insert-relation :? :1
-- :doc Insert a single relation
INSERT INTO
    relations (
        member_id,
        relation_id,
        direct_relation,
        is_blood_relation,
        care
    )
VALUES
    (
        :member_id,
        :relation_id,
        :direct_relation :: direct_relation_type,
        :is_blood_relation,
        :care :: care_type
    ) RETURNING member_id,
    relation_id;

-- :name update-relation :! :n
UPDATE
    relations
SET
    direct_relation = :direct_relation,
    is_blood_relation = :is_blood_relation,
    care = :care
WHERE
    member_id = :member_id
    AND relation_id = :relation_id;

-- :name delete-relation-by-id :! :n
DELETE FROM
    relations
WHERE
    member_id = :member_id
    AND relation_id = :relation_id;

-- A ":result" value of ":1" specifies a single record
-- (as a hashmap) will be returned
-- :name relation-between-members :? :1
-- :doc Get relation between two members
SELECT
    *
FROM
    relations
WHERE
    member_id = :member_id
    AND relation_id = :relation_id;