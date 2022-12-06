INSERT INTO users (id, avatar, first_name, last_name, last_seen, password, role, username) VALUES ('c04b7e88-0234-4aa0-9c05-aeeaf624fd72', 'https://scontent.fotp3-2.fna.fbcdn.net/v/t39.30808-6/249204848_2467267776737413_8105701682214449482_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=oFIsdfE6y0YAX_xPnvT&tn=SwMMsW2MNoU8cga-&_nc_ht=scontent.fotp3-2.fna&oh=00_AfCjEkb9RWjxHkMX2A_pDlzaiA1gWAMcJk5Xi8P8qlA4MQ&oe=638FA487', 'Alexandru', 'Pop', '2022-12-03T12:40:21.207321', '$2a$10$WzVFb2zGTKuxKS8Q6WkmVeTWf.3A68EZ3WM7/Fml7aJy0ek6s/l6W', 'ROLE_USER', 'alexandrupop');
INSERT INTO users (id, avatar, first_name, last_name, last_seen, password, role, username) VALUES ('42f26fe9-481c-4c00-852a-d9b674fc162c', 'https://scontent.fotp3-2.fna.fbcdn.net/v/t1.18169-9/10178025_591959094313641_9182246845800778263_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=EKc4Artp_tIAX-Mfunv&_nc_ht=scontent.fotp3-2.fna&oh=00_AfAAVggB13hN8QvbqbAbCWvrL1PasrlRRs71zcqtXGhrrA&oe=63B2826D', 'Teodora', 'Orzan', '2022-12-03T12:41:07.253143', '$2a$10$OZUEttIjH.MbtZSpv9RsXuNsvHShfIkpvIrGLqISHGast.tylz1Zy', 'ROLE_USER', 'teodoraorzan');
INSERT INTO users (id, avatar, first_name, last_name, last_seen, password, role, username) VALUES ('6c9db9b8-f5e2-40df-ab80-38854cd8be87', 'https://scontent.fotp3-2.fna.fbcdn.net/v/t1.18169-9/13466539_969196253201350_1813197929337959475_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=787_P84G_T4AX-uF61V&tn=SwMMsW2MNoU8cga-&_nc_ht=scontent.fotp3-2.fna&oh=00_AfBKNvu0xaFuPalVc9CwU5jAC6fCKaMF22aFmgU-kbNtLA&oe=63B28249', 'Andy-Constantin', 'Lupu', '2022-12-03T12:41:26.365738', '$2a$10$GfpHag1KcDZcEFyf5a8ZnuOkBDFP.66dpbtFMwRn46m/SNQiBPIAq', 'ROLE_USER', 'andylupu');
INSERT INTO users (id, avatar, first_name, last_name, last_seen, password, role, username) VALUES ('d5d3589a-fa31-4133-afc9-1e2f876803f4', 'https://scontent.fotp3-1.fna.fbcdn.net/v/t39.30808-6/316086364_3383741135195061_2828847930780420414_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=mzp-Lu_lbegAX-sp5is&_nc_ht=scontent.fotp3-1.fna&oh=00_AfBQQcdDFMTpo3gHtVyNBSTj4VFsooOZl9poPs5TVeAyoQ&oe=6390FDD6', 'Andrei-Valentin', 'Ilie', '2022-12-03T12:41:58.934548', '$2a$10$5n5CG.rgmMkyHcNgf5vuLecdTtsMowduLRXcc5iZBLk0cn2KNCj3m', 'ROLE_USER', 'andreiilie');

INSERT INTO users_contacts(user_id, contact_id) VALUES('c04b7e88-0234-4aa0-9c05-aeeaf624fd72','6c9db9b8-f5e2-40df-ab80-38854cd8be87');
INSERT INTO users_contacts(user_id, contact_id) VALUES('c04b7e88-0234-4aa0-9c05-aeeaf624fd72','42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO users_contacts(user_id, contact_id) VALUES('c04b7e88-0234-4aa0-9c05-aeeaf624fd72','d5d3589a-fa31-4133-afc9-1e2f876803f4');
INSERT INTO users_contacts(user_id, contact_id) VALUES('42f26fe9-481c-4c00-852a-d9b674fc162c','c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO users_contacts(user_id, contact_id) VALUES('42f26fe9-481c-4c00-852a-d9b674fc162c','6c9db9b8-f5e2-40df-ab80-38854cd8be87');
INSERT INTO users_contacts(user_id, contact_id) VALUES('42f26fe9-481c-4c00-852a-d9b674fc162c','d5d3589a-fa31-4133-afc9-1e2f876803f4');
INSERT INTO users_contacts(user_id, contact_id) VALUES('6c9db9b8-f5e2-40df-ab80-38854cd8be87','c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO users_contacts(user_id, contact_id) VALUES('6c9db9b8-f5e2-40df-ab80-38854cd8be87','42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO users_contacts(user_id, contact_id) VALUES('6c9db9b8-f5e2-40df-ab80-38854cd8be87','d5d3589a-fa31-4133-afc9-1e2f876803f4');
INSERT INTO users_contacts(user_id, contact_id) VALUES('d5d3589a-fa31-4133-afc9-1e2f876803f4','c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO users_contacts(user_id, contact_id) VALUES('d5d3589a-fa31-4133-afc9-1e2f876803f4','42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO users_contacts(user_id, contact_id) VALUES('d5d3589a-fa31-4133-afc9-1e2f876803f4','6c9db9b8-f5e2-40df-ab80-38854cd8be87');

INSERT INTO chats (chat_type, id, name, started_on, admin_id) VALUES ('simple', '8066b571-f121-40c2-b74b-30c4318fd2c7', null, null, null);
INSERT INTO chats (chat_type, id, name, started_on, admin_id) VALUES ('simple', 'b37a9a22-f9dc-434b-8340-89c7b6a57200', null, null, null);
INSERT INTO chats (chat_type, id, name, started_on, admin_id) VALUES ('group', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', 'Grup MPAI', null, 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');

INSERT INTO users_chats (chat_id, user_id) VALUES ('8066b571-f121-40c2-b74b-30c4318fd2c7', 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO users_chats (chat_id, user_id) VALUES ('8066b571-f121-40c2-b74b-30c4318fd2c7', '42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO users_chats (chat_id, user_id) VALUES ('b37a9a22-f9dc-434b-8340-89c7b6a57200', 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO users_chats (chat_id, user_id) VALUES ('b37a9a22-f9dc-434b-8340-89c7b6a57200', '6c9db9b8-f5e2-40df-ab80-38854cd8be87');
INSERT INTO users_chats (chat_id, user_id) VALUES ('3a96ec7f-4ff6-4926-a17f-e1b092601aa6', '6c9db9b8-f5e2-40df-ab80-38854cd8be87');
INSERT INTO users_chats (chat_id, user_id) VALUES ('3a96ec7f-4ff6-4926-a17f-e1b092601aa6', '42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO users_chats (chat_id, user_id) VALUES ('3a96ec7f-4ff6-4926-a17f-e1b092601aa6', 'd5d3589a-fa31-4133-afc9-1e2f876803f4');

INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('1b0b3f99-a74e-415c-a309-662b80b8de21', 'Buna Teodora', '2022-12-03 12:43:37.823174', '8066b571-f121-40c2-b74b-30c4318fd2c7', 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('361ecd5d-02f3-4051-a113-7bb472a8c058', 'Buna Alex', '2022-12-03 13:43:37.823174', '8066b571-f121-40c2-b74b-30c4318fd2c7', '42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('13786080-7539-11ed-a1eb-0242ac120002', 'Cf?', '2022-12-03 13:58:37.823174', '8066b571-f121-40c2-b74b-30c4318fd2c7', 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('6bf10771-cf80-4d23-9f68-681e6edf228f', 'Oo, salutari domnul Pop', '2022-12-05 13:12:18.074102', 'b37a9a22-f9dc-434b-8340-89c7b6a57200', '6c9db9b8-f5e2-40df-ab80-38854cd8be87');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('554e1ba4-4736-460f-ad60-437924228509', 'Salutare tuturor', '2022-11-01 15:30:18.074102', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('dd3a9f02-2cde-4240-8f95-0bc3351449b9', 'Ce proiect facem?', '2022-11-01 15:32:18.074102', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', '42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('6ca94168-4b58-4e68-a83c-b8e8e5380b57', 'Eu zic aplicatie de mesagerie instanta', '2022-11-01 15:35:18.074102', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', 'c04b7e88-0234-4aa0-9c05-aeeaf624fd72');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('1cbbd01d-9467-4e31-90d0-5da9dcd4f724', 'Sunt de acord!', '2022-11-01 15:36:18.074102', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', '42f26fe9-481c-4c00-852a-d9b674fc162c');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('5c1fd4b8-d9ee-4777-83bb-d3a4d0375dc1', 'Si eu', '2022-11-01 15:38:18.074102', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', '6c9db9b8-f5e2-40df-ab80-38854cd8be87');
INSERT INTO messages (id, content, date, chat_id, sender_id) VALUES ('2a5a1332-eeae-49db-963c-ec3b5f6f599c', 'Si mie imi place', '2022-11-01 15:40:18.074102', '3a96ec7f-4ff6-4926-a17f-e1b092601aa6', 'd5d3589a-fa31-4133-afc9-1e2f876803f4');

