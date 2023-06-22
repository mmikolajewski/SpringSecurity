INSERT INTO
    application_user (username, first_name, last_name, password)
VALUES
    --Jan21 / anatomia
    ('Jan21','Jan', 'Bywał', '{bcrypt}$2a$12$IUMhpJ2nQcsD.2FE7oNZNuK.0KNKdPwsPrLWeG2g0n6T4T.vb9Sy2'),
    -- Anna20 / 123
    ('Anna20','Anna', 'Grzebała', '{bcrypt}$2a$12$hqIys8.2XDXNxGvcpUUKJ.ERTYtqsSJtlUafC9DC0f6Y/qio5j9se'),
    -- Borys20 / 123
    ('Borys20','Anna', 'Wanna', '{bcrypt}$2a$12$hqIys8.2XDXNxGvcpUUKJ.ERTYtqsSJtlUafC9DC0f6Y/qio5j9se');

INSERT INTO
    user_role (name)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 2);