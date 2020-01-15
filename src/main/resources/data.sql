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
INSERT INTO centre (code,local_unit_id,is_active) VALUES ('01171',1,true);
INSERT INTO centre (code,local_unit_id,is_active) VALUES ('01169',2,true);

-- Table des budgets --
INSERT INTO budget (grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4,local_unit_id)
VALUES ('Redevance', 'IBM', 2020, 100000, 120000, 100000, 103000, 110000,1);

INSERT INTO budget (grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4,local_unit_id)
VALUES ('Redevance', 'Qlik', 2020, 100000, 120000, 100000, 103000, 110000,1);

INSERT INTO budget (grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4,local_unit_id)
VALUES ('Redevance', 'App2', 2020, 50000, 50000, 50000, 50000, 50000,1);

INSERT INTO budget (grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4,local_unit_id)
VALUES ('TMA', 'Appli1', 2020, 80000, 80000, 80000, 80000, 90000,1);

INSERT INTO budget (grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4,local_unit_id)
VALUES ('TMA', 'Appli2', 2020, 80000, 80000, 80000, 80000, 90000,1);

INSERT INTO budget (grande_activite,activite, annee, budget_notifie, estime1, estime2, estime3, estime4,local_unit_id)
VALUES ('Redevance', 'Appli2', 2020, 80000, 80000, 80000, 80000, 90000,2);



-- Table des commandes--
INSERT INTO commande (id_dist_cmd,id_entet_cmd,numero_ols,fournisseur,description_commande,moe_prescripteur,qte_engagee_annee_en_cours,reste_a_receptionner,quantite_commandee,quantite_receptionnee,quantite_facturee,recep_avant_annee_en_cours,date_creation_commande,segment1,segment2,segment3,segment4,segment5,segment6,segment7,catgory,marche,desc_ligne_cmd,local_unit_id,annee,cmd_a_regulariser,cmd_regularisee)
values('3816623','1838666','00733834','ADSERVIO','TMA application 1 T1','Benoit',836,0,836,836,0,836,'04/12/2019',1,2300000,1171,111111,111,11,12,'APPEL','SITXXXXXX','TMA application 1 T1',1,'2020',false,false);

INSERT INTO commande (id_dist_cmd,id_entet_cmd,numero_ols,fournisseur,description_commande,moe_prescripteur,qte_engagee_annee_en_cours,reste_a_receptionner,quantite_commandee,quantite_receptionnee,quantite_facturee,recep_avant_annee_en_cours,date_creation_commande,segment1,segment2,segment3,segment4,segment5,segment6,segment7,catgory,marche,desc_ligne_cmd,local_unit_id,annee,cmd_a_regulariser,cmd_regularisee)
values('3815843','1838346','00733720','ADSERVIO','AMOE appli 1','martin',836,0,836,836,0,836,'04/12/2019',1,2300000,1171,111111,111,11,12,'APPEL','SITXXXXXX','TMA application 1 T1',1,'2020',false,false);

INSERT INTO commande (id_dist_cmd,id_entet_cmd,numero_ols,fournisseur,description_commande,moe_prescripteur,qte_engagee_annee_en_cours,reste_a_receptionner,quantite_commandee,quantite_receptionnee,quantite_facturee,recep_avant_annee_en_cours,date_creation_commande,segment1,segment2,segment3,segment4,segment5,segment6,segment7,catgory,marche,desc_ligne_cmd,local_unit_id,annee,cmd_a_regulariser,cmd_regularisee)
values('3815830','1838337','00733713','FOURNISSEUR 1','Infocentre économique','said',836,0,836,836,0,836,'04/12/2019',1,2300000,1171,111111,111,11,12,'APPEL','SITXXXXXX','TMA application 1 T1',1,'2020',false,false);

INSERT INTO commande (id_dist_cmd,id_entet_cmd,numero_ols,fournisseur,description_commande,moe_prescripteur,qte_engagee_annee_en_cours,reste_a_receptionner,quantite_commandee,quantite_receptionnee,quantite_facturee,recep_avant_annee_en_cours,date_creation_commande,segment1,segment2,segment3,segment4,segment5,segment6,segment7,catgory,marche,desc_ligne_cmd,local_unit_id,annee,cmd_a_regulariser,cmd_regularisee)
values('3815765','1838329','00733705','FOURNISSEUR','Pilotage - Maintenance opérationnelle de type 2 - Industrialisation','said',836,0,836,836,0,836,'04/12/2019',1,2300000,1171,111111,111,11,12,'APPEL','SITXXXXXX','TMA application 1 T1',1,'2020',false,false);

INSERT INTO commande (id_dist_cmd,id_entet_cmd,numero_ols,fournisseur,description_commande,moe_prescripteur,qte_engagee_annee_en_cours,reste_a_receptionner,quantite_commandee,quantite_receptionnee,quantite_facturee,recep_avant_annee_en_cours,date_creation_commande,segment1,segment2,segment3,segment4,segment5,segment6,segment7,catgory,marche,desc_ligne_cmd,local_unit_id,annee,cmd_a_regulariser,cmd_regularisee)
values('3800161','1838329','00733705','FOURNISSEUR','Pilotage - Maintenance opérationnelle de type 2 - Industrialisation','said',836,0,836,836,0,836,'04/12/2019',1,2300000,1171,111111,111,11,12,'APPEL','SITXXXXXX','TMA application 1 T1',2,'2020',false,false);







