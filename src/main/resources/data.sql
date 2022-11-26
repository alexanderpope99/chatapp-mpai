INSERT INTO users (id, nickname, password, role, username) VALUES ('6d74a125-8bbf-4f93-ada8-97ffaff7b815', 'Gigi', '$2a$10$WW7D1FOv62F1mAU5.TIg8.otTrnvcZZsiucexMVX9XHJGNcRYPi4S', 'ROLE_USER', 'gigi');
INSERT INTO users (id, nickname, password, role, username) VALUES ('4801c99e-ead6-45a4-b5b7-cb9b759c6d39', 'Gigi2', '$2a$10$Fn.LVXvfhz4GsSPRQ1nGO.eU7bGclvSaLifRdK4Bj0hPmcla61CWa', 'ROLE_USER', 'gigi2');
INSERT INTO users (id, nickname, password, role, username) VALUES ('fd1e4dfb-94c0-456f-8224-e654505664bc', 'Gigi3', '$2a$10$rSf4UrBE81g6AFK6/eO.Y.rOE2wQP5TPCviOnrH28SRs/hnt7GG26', 'ROLE_USER', 'gigi3');
INSERT INTO users (id, nickname, password, role, username) VALUES ('8cb78597-4814-4543-8c54-94ec43f302f9', 'Gigi4', '$2a$10$klbQ2m.muSkgzuHjj90e9uY/qDu5flm2XX0MXb9pk6c4/eHtkDc1q', 'ROLE_USER', 'gigi4');
INSERT INTO users (id, nickname, password, role, username) VALUES ('d38a3cb9-01c6-47cb-bf6f-9bd96461b17b', 'Gigi5', '$2a$10$1qvWs/M1XR.D5lNxmXIWEuIjgOutmkdoBLgnK9HB86QR3dUATo3WS', 'ROLE_USER', 'gigi5');

INSERT INTO chats (chat_type, id, name, nickname, admin_id) VALUES ('simple', '2eb506ad-dcae-43b4-80bf-b18404dcdd56', null, null, null);
INSERT INTO chats (chat_type, id, name, nickname, admin_id) VALUES ('group', 'a1a4b470-0661-4dd7-be04-61fd3d544f1a', 'Group 1', null, '6d74a125-8bbf-4f93-ada8-97ffaff7b815');

INSERT INTO users_chats (chat_id, user_id) VALUES ('2eb506ad-dcae-43b4-80bf-b18404dcdd56', '6d74a125-8bbf-4f93-ada8-97ffaff7b815');
INSERT INTO users_chats (chat_id, user_id) VALUES ('2eb506ad-dcae-43b4-80bf-b18404dcdd56', '4801c99e-ead6-45a4-b5b7-cb9b759c6d39');
INSERT INTO users_chats (chat_id, user_id) VALUES ('a1a4b470-0661-4dd7-be04-61fd3d544f1a', '6d74a125-8bbf-4f93-ada8-97ffaff7b815');
INSERT INTO users_chats (chat_id, user_id) VALUES ('a1a4b470-0661-4dd7-be04-61fd3d544f1a', '8cb78597-4814-4543-8c54-94ec43f302f9');
INSERT INTO users_chats (chat_id, user_id) VALUES ('a1a4b470-0661-4dd7-be04-61fd3d544f1a', 'fd1e4dfb-94c0-456f-8224-e654505664bc');
INSERT INTO users_chats (chat_id, user_id) VALUES ('a1a4b470-0661-4dd7-be04-61fd3d544f1a', 'd38a3cb9-01c6-47cb-bf6f-9bd96461b17b');

INSERT INTO messages (id, content, date_time, chat_id, sender_id) VALUES ('72e79d2f-a419-418c-bd30-01ff07ac1e64', 'Buna', '2022-11-26 21:19:57.122712', '2eb506ad-dcae-43b4-80bf-b18404dcdd56', '6d74a125-8bbf-4f93-ada8-97ffaff7b815');
INSERT INTO messages (id, content, date_time, chat_id, sender_id) VALUES ('674ed905-8eb2-46c1-a708-b68ed1047404', 'Buna', '2022-11-26 21:20:16.031282', 'a1a4b470-0661-4dd7-be04-61fd3d544f1a', '6d74a125-8bbf-4f93-ada8-97ffaff7b815');
INSERT INTO messages (id, content, date_time, chat_id, sender_id) VALUES ('b5c795ac-cb01-435a-a6e8-24606cd12df1', 'Salut', '2022-11-26 21:20:26.173262', 'a1a4b470-0661-4dd7-be04-61fd3d544f1a', '6d74a125-8bbf-4f93-ada8-97ffaff7b815');
