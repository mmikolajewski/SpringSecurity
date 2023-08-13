INSERT INTO
    application_user (id, username, first_name, last_name, password)
VALUES
    (1, 'Jan21','Jan', 'Bywał', '{noop}anatomia'),
    (2, 'Anna20','Anna', 'Grzebała', '{noop}123'),
    (3,'Borys20','Anna', 'Wanna', '{noop}123');

INSERT INTO
    user_role (id, name)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 2);