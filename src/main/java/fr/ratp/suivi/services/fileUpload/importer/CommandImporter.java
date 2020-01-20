package fr.ratp.suivi.services.fileUpload.importer;

import fr.ratp.suivi.domain.Commande;
import fr.ratp.suivi.repositories.CommandeRepository;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.services.fileUpload.beans.CommandeBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommandImporter extends BaseImporter {

    @Autowired
    private final CommandeRepository commandeRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Override
    public Boolean importData(List beans) {
        List<Commande> allCommandeFromDB = commandeRepository.findAll();
        List<CommandeBean> commandeFromCSVFile = (List<CommandeBean>) beans;
        List<Commande> commandeToCreateOrUpdate = new ArrayList<>();

        commandeFromCSVFile.stream().forEach(commandeCSV -> {
            boolean b = allCommandeFromDB
                    .stream()
                    .anyMatch(commande -> commande.getId_dist_cmd().equals(commandeCSV.getId_dist_cmd()));
            if (!b) { //Commande  does not exist
                Commande newCommande = new Commande()
                        .builder()
                        .annee(commandeCSV.getAnnee())
                        .bud_budgget_estime1(commandeCSV.getBud_budgget_estime1())
                        .bud_budgget_estime2(commandeCSV.getBud_budgget_estime2())
                        .bud_budgget_estime3(commandeCSV.getBud_budgget_estime3())
                        .bud_budgget_estime4(commandeCSV.getBud_budgget_estime4())
                        .activite(commandeCSV.getBudget_activite())
                        .grande_activite(commandeCSV.getBudget_grande_activite())
                        .notifie(commandeCSV.getBudget_notifie())
                        .catgory(commandeCSV.getCatgory())
                        .date_creation_commande(new Date(commandeCSV.getDate_creation_commande()))
                        .desc_ligne_cmd(commandeCSV.getDesc_ligne_cmd())
                        .description_commande(commandeCSV.getDescription_commande())
                        .fournisseur(commandeCSV.getFournisseur())
                        .id_dist_cmd(commandeCSV.getId_dist_cmd())
                        .id_entet_cmd(commandeCSV.getId_entet_cmd())
                        .localUnit(localUnitRepository.findByCode(commandeCSV.getLocalUnit()).get())
                        .marche(commandeCSV.getMarche())
                        .moe_prescripteur(commandeCSV.getMoe_prescripteur())
                        .nouveau_segment7(commandeCSV.getSegment7())
                        .numero_ols(commandeCSV.getNumero_ols())
                        .qte_engagee_annee_en_cours(commandeCSV.getQte_engagee_annee_en_cours())
                        .quantite_commandee(commandeCSV.getQuantite_commandee())
                        .segment1(commandeCSV.getSegment1())
                        .segment2(commandeCSV.getSegment2())
                        .segment3(commandeCSV.getSegment3())
                        .segment4(commandeCSV.getSegment4())
                        .segment5(commandeCSV.getSegment5())
                        .segment6(commandeCSV.getSegment6())
                        .segment7(commandeCSV.getSegment7())
                        .build();
                commandeToCreateOrUpdate.add(newCommande);

            }
        });

        //Verify if what exist in database is still
        allCommandeFromDB.stream().forEach(commande -> {
                    Optional<CommandeBean> commandFromCSVFile = commandeFromCSVFile
                            .stream()
                            .filter(commandCSV -> commande.getId_dist_cmd().equals(commandCSV.getId_dist_cmd()))
                            .findFirst();

                    if (commandFromCSVFile.isPresent()) { //L'object exist dans la base et le fichier
                        //On met à jour l'object avec le contenu du CSV
                        CommandeBean commandeBean = commandFromCSVFile.get();
                        commande.setId_entet_cmd(commandeBean.getId_entet_cmd());
                        commande.setNumero_ols(commandeBean.getNumero_ols());
                        commande.setFournisseur(commandeBean.getFournisseur());
                        commande.setDescription_commande(commandeBean.getDescription_commande());
                        commande.setMoe_prescripteur(commandeBean.getMoe_prescripteur());
                        commande.setQte_engagee_annee_en_cours(commandeBean.getQte_engagee_annee_en_cours());
                        commande.setReste_a_receptionner(commandeBean.getReste_a_receptionner());
                        commande.setQte_engagee_annee_en_cours(commandeBean.getQte_engagee_annee_en_cours());
                        commande.setQuantite_commandee(commandeBean.getQuantite_commandee());
                        commande.setQuantite_receptionnee(commandeBean.getQuantite_receptionnee());
                        commande.setRecep_avant_annee_en_cours(commandeBean.getRecep_avant_annee_en_cours());
                        commande.setDate_creation_commande(new Date(commandeBean.getDate_creation_commande()));
                        commande.setSegment1(commandeBean.getSegment1());
                        commande.setSegment2(commandeBean.getSegment2());
                        commande.setSegment3(commandeBean.getSegment3());
                        commande.setSegment4(commandeBean.getSegment4());
                        commande.setSegment5(commandeBean.getSegment5());
                        commande.setSegment6(commandeBean.getSegment6());
                        commande.setSegment7(commandeBean.getSegment7());
                        commande.setCatgory(commandeBean.getCatgory());
                        commande.setMarche(commandeBean.getMarche());
                        commande.setDesc_ligne_cmd(commandeBean.getDesc_ligne_cmd());
                        commandeToCreateOrUpdate.add(commande);
                    }
                }
        );


        if (!commandeToCreateOrUpdate.isEmpty())
            commandeRepository.saveAll(commandeToCreateOrUpdate);
        return true;
    }
}
