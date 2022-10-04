package sample;

import org.api.excel.annotations.Book;
import org.api.excel.annotations.Box;
import org.api.excel.annotations.Page;

@Book(
        value={
                @Page(name = "Données")
        }
)
public class VuePM7SSAV {

    @Box()
    private String typeActivite;

    @Box(number = 1)
    private String responsable;

    @Box(number = 2)
    private String tache;

    @Box(number = 3)
    private String lot;

    @Box(number = 4)
    private String libelleBudgetaire;

    @Box(number = 5)
    private String codeBudgetaire;

    @Box(number = 6)
    private String ressource;

    @Box(number = 7)
    private Integer idSuitePilotage;

    @Box(number = 8)
    private String statutIntExt;

    @Box(number = 9)
    private String organisation;

    @Box(number = 10)
    private String equipeDosi;

    @Box(number = 11)
    private String statutMoaMoe;

    @Box(number = 12)
    private String typeDePersonne;

    @Box(number = 13)
    private String annee;

    @Box(number = 14)
    private String mois;

    @Box(number = 15)
    private String semaine;

    @Box(number = 16)
    private Double tempsSais;

    @Box(number = 17)
    private Double tempsSoumis;

    @Box(number = 18)
    private Double tempsApprouve;



    public String getTypeActivite() {
        return typeActivite;
    }

    public void setTypeActivite(String typeActivite) {
        this.typeActivite = typeActivite;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getLibelleBudgetaire() {
        return libelleBudgetaire;
    }

    public void setLibelleBudgetaire(String libelleBudgetaire) {
        this.libelleBudgetaire = libelleBudgetaire;
    }

    public String getCodeBudgetaire() {
        return codeBudgetaire;
    }

    public void setCodeBudgetaire(String codeBudgetaire) {
        this.codeBudgetaire = codeBudgetaire;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public Integer getIdSuitePilotage() {
        return idSuitePilotage;
    }

    public void setIdSuitePilotage(Integer idSuitePilotage) {
        this.idSuitePilotage = idSuitePilotage;
    }

    public String getStatutIntExt() {
        return statutIntExt;
    }

    public void setStatutIntExt(String statutIntExt) {
        this.statutIntExt = statutIntExt;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getEquipeDosi() {
        return equipeDosi;
    }

    public void setEquipeDosi(String equipeDosi) {
        this.equipeDosi = equipeDosi;
    }

    public String getStatutMoaMoe() {
        return statutMoaMoe;
    }

    public void setStatutMoaMoe(String statutMoaMoe) {
        this.statutMoaMoe = statutMoaMoe;
    }

    public String getTypeDePersonne() {
        return typeDePersonne;
    }

    public void setTypeDePersonne(String typeDePersonne) {
        this.typeDePersonne = typeDePersonne;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getSemaine() {
        return semaine;
    }

    public void setSemaine(String semaine) {
        this.semaine = semaine;
    }

    public Double getTempsSais() {
        return tempsSais;
    }

    public void setTempsSais(Double tempsSais) {
        this.tempsSais = tempsSais;
    }

    public Double getTempsSoumis() {
        return tempsSoumis;
    }

    public void setTempsSoumis(Double tempsSoumis) {
        this.tempsSoumis = tempsSoumis;
    }

    public Double getTempsApprouve() {
        return tempsApprouve;
    }

    public void setTempsApprouve(Double tempsApprouve) {
        this.tempsApprouve = tempsApprouve;
    }
}
