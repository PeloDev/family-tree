-- :name create-members-table :!
-- :doc Create members table
CREATE TABLE members (
    id bigserial,
    first_name character varying(100) NOT NULL,
    middle_names character varying(100),
    last_name character varying(100) NOT NULL,
    family_name character varying(100),
    nickname character varying(45),
    preferred_name character varying(100),
    gender character varying(20),
    date_of_birth time without time zone,
    address_of_birth character varying(255),
    city_of_birth character varying(100),
    province_of_birth character varying(100),
    country_of_birth character varying(100),
    image_url character varying(255),
    ethnicity character varying(45),
    culture character varying(45),
    languages character varying(255),
    PRIMARY KEY (id)
);

-- ALTER TABLE
--     IF EXISTS members OWNER to postgres;
-- :name drop-members-table :!
-- :doc Drop members table if exists
DROP TABLE IF EXISTS members CASCADE;

-- A :result value of :n below will return affected row count:
-- :name insert-member :? :1
-- :doc Insert a single member
INSERT INTO
    members (
        first_name,
        middle_names,
        last_name,
        family_name,
        nickname,
        preferred_name,
        gender,
        date_of_birth,
        address_of_birth,
        city_of_birth,
        province_of_birth,
        country_of_birth,
        image_url,
        ethnicity,
        culture,
        languages
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
    ) RETURNING id;

-- A :result value of :n below will return affected row count:
-- :name insert-members :? :*
-- :doc Insert multiple members with :tuple* parameter type
INSERT INTO
    members (
        id,
        first_name,
        middle_names,
        last_name,
        family_name,
        nickname,
        preferred_name,
        gender,
        date_of_birth,
        address_of_birth,
        city_of_birth,
        province_of_birth,
        country_of_birth,
        image_url,
        ethnicity,
        culture,
        languages
    )
VALUES
    :tuple * :members RETURNING id;

-- :name update-member :! :n
UPDATE
    members
SET
    first_name = :first_name,
    middle_names = :middle_names,
    last_name = :last_name,
    family_name = :family_name,
    nickname = :nickname,
    preferred_name = :preferred_name,
    gender = :gender,
    date_of_birth = :date_of_birth,
    address_of_birth = :address_of_birth,
    city_of_birth = :city_of_birth,
    province_of_birth = :province_of_birth,
    country_of_birth = :country_of_birth,
    image_url = :image_url,
    ethnicity = :ethnicity,
    culture = :culture,
    languages = :languages
WHERE
    id = :id;

-- :name delete-member-by-id :! :n
DELETE FROM
    members
where
    id = :id;

-- A ":result" value of ":1" specifies a single record
-- (as a hashmap) will be returned
-- :name member-by-id :? :1
-- :doc Get member by id
SELECT
    *
FROM
    members
WHERE
    id = :id;

-- A ":result" value of ":*" specifies a vector of records
-- (as hashmaps) will be returned
-- :name search-members :? :*
-- :doc Search for members by case-insensitive name
SELECT
    *
FROM
    members
WHERE
    LOWER(first_name) LIKE LOWER('%' || :searchtext || '%'),
    OR LOWER(middle_names) LIKE LOWER('%' || :searchtext || '%'),
    OR LOWER(last_name) LIKE LOWER('%' || :searchtext || '%');

-- :name member-parents :? :*
-- :doc Get all parents of a member
SELECT
    m.*
FROM
    members m
    JOIN parents p ON (p.id = m.id)
WHERE
    p.child_id = :id;

-- :name member-children :? :*
-- :doc Get all children of a member
SELECT
    m.*
FROM
    members m
    JOIN parents p ON (p.child_id = m.id)
WHERE
    p.id = :id;

-- :name member-spouses :? :*
-- :doc Get all spouses of a member
SELECT
    DISTINCT ON (m.id) m.*
FROM
    members m
    JOIN spouses s ON (
        s.id = m.id
        OR s.spouse_id = m.id
    )
WHERE
    m.id != :id (
        s.id = :id
        OR s.spouse_id = :id
    );