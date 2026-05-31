package classes.javabeans;

import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "type")
    private String type;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "disponibilite")
    private boolean disponibilite;

    // Constructeur vide
    public Produit() {}

    // Constructeur avec paramètres
    public Produit(String code, String libelle, String type, int quantite, boolean disponibilite) {
        this.code = code;
        this.libelle = libelle;
        this.type = type;
        this.quantite = quantite;
        this.disponibilite = disponibilite;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public boolean isDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }

    @Override
    public String toString() {
        return "Produit{id=" + id + ", code=" + code + ", libelle=" + libelle + "}";
    }
}