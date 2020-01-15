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
import java.util.List;

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
                    .anyMatch(commande -> commande.getId_entet_cmd().equals(commandeCSV.getId_entet_cmd()));
            if (!b) { //Commande  does not exist
                Commande newCommande = new Commande()
                        .builder()
                        .annee(commandeCSV.getAnnee())
                        .bud_budgget_estime1(commandeCSV.getBud_budgget_estime1())
                        .bud_budgget_estime2(commandeCSV.getBud_budgget_estime2())
                        .bud_budgget_estime3(commandeCSV.getBud_budgget_estime3())
                        .bud_budgget_estime4(commandeCSV.getBud_budgget_estime4())
                        .budget_activite(commandeCSV.getBudget_activite())
                        .budget_grande_activite(commandeCSV.getBudget_grande_activite())
                        .budget_notifie(commandeCSV.getBudget_notifie())
                        .catgory(commandeCSV.getCatgory())
                        .date_creation_commande(commandeCSV.getDate_creation_commande())
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
        return true;
    }
}
