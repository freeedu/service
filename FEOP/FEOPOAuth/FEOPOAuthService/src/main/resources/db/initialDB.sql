INSERT INTO `roles`(id, name, enabled) VALUES ('1', 'ROLE_ADMIN', b'1'), ('2', 'ROLE_USER', b'1'), ('3', 'ROLE_DEV', b'1') ON DUPLICATE KEY UPDATE enabled = b'1';