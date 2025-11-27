INSERT INTO pgm.usu_usuarios (usu_id, usu_ds_email, usu_ds_password, usu_ds_username) VALUES(1, 'admin@gmail.com', '$2a$10$7D8XnHnlZIPBn1W2Fh90u.1DzmL3/Zi/mlCyT22FNvV5ruUJpKlbG', 'admin');
INSERT INTO pgm.usu_usuarios (usu_id, usu_ds_email, usu_ds_password, usu_ds_username) VALUES(2, 'basic@gmail.com', '$2a$10$7D8XnHnlZIPBn1W2Fh90u.1DzmL3/Zi/mlCyT22FNvV5ruUJpKlbG', 'usuario');

INSERT INTO pgm.rol_roles (rol_id, rol_ds_name) VALUES(1, 'ADMIN');
INSERT INTO pgm.rol_roles (rol_id, rol_ds_name) VALUES(2, 'BASIC');

INSERT INTO pgm.usu_usuarios_roles (usu_id, rol_id) VALUES(1, 1);
INSERT INTO pgm.usu_usuarios_roles (usu_id, rol_id) VALUES(2, 2);


