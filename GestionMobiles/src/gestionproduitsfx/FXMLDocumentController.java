package gestionproduitsfx;

import classes.javabeans.Produit;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    @FXML private TextField tfCode;
    @FXML private TextField tfLibelle;
    @FXML private ComboBox<String> cbType;
    @FXML private TextField tfQuantite;
    @FXML private CheckBox cbDispo;

    @FXML private TableView<Produit> tableProduits;
    @FXML private TableColumn<Produit, Integer> colId;
    @FXML private TableColumn<Produit, String> colCode;
    @FXML private TableColumn<Produit, String> colLibelle;
    @FXML private TableColumn<Produit, String> colType;
    @FXML private TableColumn<Produit, Integer> colQuantite;
    @FXML private TableColumn<Produit, Boolean> colDispo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitService.openSession();

        cbType.setItems(FXCollections.observableArrayList(
            "Boisson", "Alimentaire", "Ménager", "Hygiène"
        ));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colDispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        actualiser();

        tableProduits.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    tfCode.setText(newVal.getCode());
                    tfLibelle.setText(newVal.getLibelle());
                    cbType.setValue(newVal.getType());
                    tfQuantite.setText(String.valueOf(newVal.getQuantite()));
                    cbDispo.setSelected(newVal.isDisponibilite());
                }
            }
        );
    }

    @FXML
    public void ajouterProduit() {
        Produit p = new Produit(
            tfCode.getText(),
            tfLibelle.getText(),
            cbType.getValue(),
            Integer.parseInt(tfQuantite.getText()),
            cbDispo.isSelected()
        );
        ProduitService.create(p);
        actualiser();
        viderFormulaire();
    }

    @FXML
    public void modifierProduit() {
        Produit p = tableProduits.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.setCode(tfCode.getText());
            p.setLibelle(tfLibelle.getText());
            p.setType(cbType.getValue());
            p.setQuantite(Integer.parseInt(tfQuantite.getText()));
            p.setDisponibilite(cbDispo.isSelected());
            ProduitService.update(p);
            actualiser();
            viderFormulaire();
        }
    }

    @FXML
    public void supprimerProduit() {
        Produit p = tableProduits.getSelectionModel().getSelectedItem();
        if (p != null) {
            ProduitService.delete(p);
            actualiser();
            viderFormulaire();
        }
    }

    @FXML
    public void actualiser() {
        List<Produit> liste = ProduitService.listProduit();
        ObservableList<Produit> data = FXCollections.observableArrayList(liste);
        tableProduits.setItems(data);
    }

    private void viderFormulaire() {
        tfCode.clear();
        tfLibelle.clear();
        cbType.setValue(null);
        tfQuantite.clear();
        cbDispo.setSelected(false);
    }
}