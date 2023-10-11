SET @ADMIN_ROLE_ID = (SELECT id FROM tbl_roles WHERE name = 'ADMIN');
SET @USER_ROLE_ID = (SELECT id FROM tbl_roles WHERE name = 'USER');

INSERT INTO tbl_users (id, role_id, email, password, first_name, last_name, birth_date)
VALUES
    (UUID(), @ADMIN_ROLE_ID, 'mrlotzo@gmail.com', '', 'Prince', 'Oncada', '2000-11-29'),
    (UUID(), @USER_ROLE_ID, 'prince.oncada@gmail.com', '', 'Grant', 'Stalen', '2000-11-29');
