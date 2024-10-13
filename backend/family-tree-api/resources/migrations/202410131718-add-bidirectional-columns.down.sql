ALTER TABLE
    IF EXISTS public.relations RENAME member_to_relation TO direct_relation;
--;;
ALTER TABLE
    IF EXISTS public.relations DROP COLUMN IF EXISTS relation_to_member;