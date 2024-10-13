ALTER TABLE
    IF EXISTS public.relations RENAME direct_relation TO member_to_relation;
--;;
ALTER TABLE
    IF EXISTS public.relations
ADD
    COLUMN relation_to_member direct_relation_type NOT NULL;