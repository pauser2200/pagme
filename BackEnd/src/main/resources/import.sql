INSERT INTO hor.usu_usuarios (usu_id, usu_ds_email, usu_ds_password, usu_ds_username) VALUES(1, 'admin@gmail.com', '$2a$10$behWa4vX2qi0MViTfPobpuLWazUrq8vAUoPsCVHLWSYyDWagHRIeK', 'admin123');
INSERT INTO hor.usu_usuarios (usu_id, usu_ds_email, usu_ds_password, usu_ds_username) VALUES(2, 'basic@gmail.com', '$2a$10$behWa4vX2qi0MViTfPobpuLWazUrq8vAUoPsCVHLWSYyDWagHRIeK', 'user123');

INSERT INTO hor.rol_roles (rol_id, rol_ds_name) VALUES(1, 'ADMIN');
INSERT INTO hor.rol_roles (rol_id, rol_ds_name) VALUES(2, 'BASIC');

INSERT INTO hor.usu_usuarios_roles (usu_id, rol_id) VALUES(1, 1);
INSERT INTO hor.usu_usuarios_roles (usu_id, rol_id) VALUES(2, 2);


