
INSERT INTO `oauth_client_details` VALUES ('mobile','{bcrypt}$2a$10$7Xksxr9USYfHFpXaAqI4ieBIMg.Bz4WQkkIJ3auGLeQR/m5hQVSIu','http://localhost:8080/code','READ,WRITE',30,60,'inventory,payment','authorization_code,password,refresh_token,implicit',NULL,'{}',NULL);
INSERT INTO `permission` VALUES (1,'create_profile'),(4,'delete_profile'),(2,'read_profile'),(3,'update_profile');
INSERT INTO `permission_role` VALUES (1,1),(2,1),(3,1),(4,1),(2,2),(3,2);
INSERT INTO `role` VALUES (1,'ROLE_admin'),(2,'ROLE_operator'),(3,'ROLE_ACTUATOR_SUPERVISOR');
INSERT INTO `role_user` VALUES (2,2),(1,1),(3,1);
INSERT INTO `user` VALUES (1,'krish','{bcrypt}$2a$10$KyxnFX.uwFnxSja2Tfr7lOykWC5I/vyNPcgXNrEDW9qq8vt46/vba','k@krishantha.com',1,1,1,1),(2,'suranga','{bcrypt}$2a$10$KyxnFX.uwFnxSja2Tfr7lOykWC5I/vyNPcgXNrEDW9qq8vt46/vba','k@krishantha.com',1,1,1,1);
