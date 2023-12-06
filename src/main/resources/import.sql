-- Criação do usuário com senha 12345
INSERT INTO TB_USER (id, email, locale, login, name, password, role) VALUES ('814c0188-28b6-42c5-98e9-96c024a6d487', 'lacoste@gmail.com', 'Brasil', 'lacoste', 'Lacoste da Silva', '$2a$12$Jk3JGi2GNs/dU5TfW19bU.BjxOSNaAaGXsgm0eBq1SMo37PH/xiq6', 'ADMIN');

-- Criação do usuário com senha 67890
INSERT INTO TB_USER (id, email, locale, login, name, password, role) VALUES ('8c1bd3a2-7a90-4e50-9b5d-b103a0531033', 'vocenaosabenemeu@gmail.com', 'Brasil', 'irineu', 'Irineu Naosabe', '$2a$12$B0RRXtCYDoqav9lzmaOLmu2MLM7LiyKO79ARxJLrAWxsrnNJVaTbW', 'USER');

-- Criação da comunidade
INSERT INTO TB_COMMUNITIES (id, author_id, name, description, locale, particular, author_role) VALUES ('82b910aa-e64c-48f2-abdf-cb728dfd4b21', '814c0188-28b6-42c5-98e9-96c024a6d487', 'fifinha-dos-cria', 'Melhor comunidade para fifeiros', 'Brasil', 'PUBLIC', 'ADMIN');

-- Adição do membro à comunidade
INSERT INTO COMMUNITY_MEMBERS (community_id, user_id) VALUES ('82b910aa-e64c-48f2-abdf-cb728dfd4b21', '814c0188-28b6-42c5-98e9-96c024a6d487');
