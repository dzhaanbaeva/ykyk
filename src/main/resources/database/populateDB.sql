INSERT INTO type_of_document VALUES
(1, 'Passport'),
(2, 'Certificate');
--
INSERT INTO documents VALUES
(1, 1, 'MKK', '12.01.2016', '12.01.2026', 1)ON CONFLICT (id) DO NOTHING;
INSERT INTO documents VALUES
(2, 2, 'MKK', '12.01.2020', '12.01.2030', 2) ON CONFLICT (id) DO NOTHING;

--

INSERT INTO users(id, name, full_name, inn, birthday, address, email, password,document_id ) VALUES
(1,  'ainura','dzhaanbaeva', '12002199201450', '20.02.1993', 'bishkek','ainura@gmail.com', '$2a$12$UzIcLA9OToBxsfsNXw/a7.f4Ot1vLjH91UI841dfgXWnno8cMSTVK', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO users(id, name, full_name, inn, birthday, address, email, password,document_id ) VALUES
(2,  'maks','marco', '12002198908750', '15.05.1998', 'bishkek','maks@gmail.com', '$2a$12$UzIcLA9OToBxsfsNXw/a7.f4Ot1vLjH91UI841dfgXWnno8cMSTVK', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO users(id, name, full_name, inn, birthday, address, email, password,document_id ) VALUES
(3,  'lucas','kim', '12145198908956', '15.04.1965', 'bishkek','lucas@gmail.com', '$2a$12$UzIcLA9OToBxsfsNXw/a7.f4Ot1vLjH91UI841dfgXWnno8cMSTVK', 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO users(id, name, full_name, inn, birthday, address, email, password,document_id ) VALUES
(4,  'jet','li', '11405438908682', '15.09.1965', 'bishkek','jet@gmail.com', '$2a$12$UzIcLA9OToBxsfsNXw/a7.f4Ot1vLjH91UI841dfgXWnno8cMSTVK', 2) ON CONFLICT (id) DO NOTHING;

insert into user_role VALUES
(1,'USER', 3),
(2,'USER', 2),
(3,'ADMIN', 1),
(4,'USER', 4);