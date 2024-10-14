-- :name create-spouses-table :!
-- :doc Create spouses table
CREATE TABLE spouses (
    id bigint NOT NULL,
    spouse_id bigint NOT NULL,
    PRIMARY KEY (id, spouse_id),
    FOREIGN KEY (id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (spouse_id) REFERENCES members(id) ON DELETE CASCADE
);

-- :name drop-spouses-table :!
-- :doc Drop spouses table if exists
DROP TABLE IF EXISTS spouses CASCADE;

-- A :result value of :n below will return affected row count:
-- :name insert-spouses :? :1
-- :doc Insert a spouses relation
INSERT INTO
    spouses (id, spouse_id)
VALUES
    (:id, :spouse_id) RETURNING id,
    spouse_id;

-- :name delete-spouse-relation-by-ids :! :n
DELETE FROM
    spouses
WHERE
    id = :id
    AND spouse_id = :spouse_id;