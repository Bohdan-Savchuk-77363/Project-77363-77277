SELECT id, user_id, length(photo_url), photo_url IS NULL, photo_url = '' FROM
    user_profiles;

UPDATE user_profiles SET photo_url = NULL;