-- Script d'insertion des exemples de données  --

-- TABLE ROLE --
INSERT INTO role (id,libelle,description,is_active) VALUES (1,'RUL','Responsable d''unité locale',true);
INSERT INTO role (id,libelle,description,is_active) VALUES (2,'RDO','Responsable de domaine',true);
INSERT INTO role (id,libelle,description,is_active) VALUES (3,'RDP','Responsable de Pôle',true);
INSERT INTO role (id,libelle,description,is_active) VALUES (4,'RDU','Responsable d''unité',true);
INSERT INTO role (id,libelle,description,is_active) VALUES (5,'CGU','Contrôle de gestion unité',true);

-- TABLE Unité Local --
INSERT INTO local_unit (id,code,description,is_active) VALUES (1,'SDP','Systèmes Décisionnels et de Pilotage',true);
INSERT INTO local_unit (id,code,description,is_active) VALUES (2,'ECO','Systèmes économiques',true);
INSERT INTO local_unit (id,code,description,is_active) VALUES (3,'CGU','Unité CGU',true);

-- Table Utilisateurs --
INSERT INTO utilisateur (id,matricule,nom,prenom,role_id,is_active,local_unit_id) VALUES (1,'AB123456','Tota','Tata',1,true,1);
INSERT INTO utilisateur (id,matricule,nom,prenom,role_id,is_active,local_unit_id) VALUES (2,'XA231211','Martin','SDP',2,true,2);
INSERT INTO utilisateur (id,matricule,nom,prenom,role_id,is_active,local_unit_id) VALUES (3,'BC123121','Bernard','SDP',2,true,3);
INSERT INTO utilisateur (id,matricule,nom,prenom,role_id,is_active,local_unit_id) VALUES (4,'QS129831','Ali', 'SDP',5,true,2);

-- Table Centre --
INSERT INTO centre (id,code,local_unit_id,is_active) VALUES (1,'01171',1,true);
INSERT INTO centre (id,code,local_unit_id,is_active) VALUES (2,'01169',2,true);

-- Table des budgets --
INSERT INTO budget (id, grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4)
VALUES (1, 'Redevance', 'IBM', 2019, 100000, 120000, 100000, 103000, 110000);

INSERT INTO budget (id, grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4)
VALUES (2, 'Redevance', 'IBM', 2019, 100000, 120000, 100000, 103000, 110000);

INSERT INTO budget (id, grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4)
VALUES (3, 'Redevance', 'Qlik', 2019, 50000, 50000, 50000, 50000, 50000);

INSERT INTO budget (id, grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4)
VALUES (4, 'TMA', 'Appli1', 2019, 80000, 80000, 80000, 80000, 90000);






