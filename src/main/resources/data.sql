INSERT INTO `car` (make, model, numberplate)
VALUES
    ('Lada', '2101', '123ASD'),
    ('Kia', 'Sorento', '534TTT'),
    ('Skoda', 'Octavia', '999GLF'),
    ('Kia', 'Sorento', '555TFF'),
    ('Lada', '2101', '445KKK'),
    ('Audi', 'A6', '997HHH'),
    ('BMW', '760', '444RRR'),
    ('Audi', 'A6', '876OUI'),
    ('BMW', '740', '112YUI');

INSERT INTO `user` (name)
VALUES
    ('Teet Järveküle'),
    ('Pille Purk'),
    ('Mati Kaal'),
    ('Külli Kukk'),
    ('Teet Kruus');

INSERT INTO `user_car` (user_id, car_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9);
