package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Commentaire;
import model.MAJBase;
import model.MongoDBConnexion;
import model.Oeuvre;
import model.Utilisateur;

public class AppController implements Initializable {

    private MongoDBConnexion mdb	  = new MongoDBConnexion();
    private Utilisateur	     selectUser	  = null;
    private Oeuvre	     selectOeuvre = null;
    private List<Oeuvre>     oeuvres	  = null;
    private Text	     text	  = new Text();

    @FXML
    private StackPane stackPane;

    @FXML
    private BorderPane connexionPane;

    @FXML
    private Label connexionLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button loginValidation;

    @FXML
    private BorderPane accueilPane;

    @FXML
    private TableView<Oeuvre> tableNote;

    @FXML
    private TableColumn<Oeuvre, String> tableNoteOeuvre;

    @FXML
    private TableColumn<Oeuvre, String> tableNoteNote;

    @FXML
    private TableView<Oeuvre> tableCommentaire;

    @FXML
    private TableColumn<Oeuvre, String> tableCommentaireOeuvre;

    @FXML
    private TableColumn<Oeuvre, String> tableCommentaireDate;

    @FXML
    private Button consultation;

    @FXML
    private Button deconnexion1;

    @FXML
    private Button lire1;

    @FXML
    private BorderPane consultationPane;

    @FXML
    private TableView<Oeuvre> tableConsultation;

    @FXML
    private TableColumn<Oeuvre, String> tableConsultationTitre;

    @FXML
    private TableColumn<Oeuvre, String> tableConsultationTheme;

    @FXML
    private TableColumn<Oeuvre, String> tableConsultationPages;

    @FXML
    private TableColumn<Oeuvre, String> tableConsultationDate;

    @FXML
    private TableColumn<Oeuvre, String> tableConsultationNote;

    @FXML
    private Button accueil;

    @FXML
    private Button deconnexion2;

    @FXML
    private Button lire2;

    @FXML
    private BorderPane oeuvrePane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contenu;

    @FXML
    private Label titre;

    @FXML
    private TextFlow contenuTextFlow;

    @FXML
    private Label commentaires;

    @FXML
    private VBox commentairesBox;

    @FXML
    private VBox auteursBox;

    @FXML
    private Button commenter;

    @FXML
    private Button accueil2;

    @FXML
    private Button consultation2;

    @FXML
    private Button deconnexion3;

    @FXML
    private BorderPane commentairePane;

    @FXML
    private Button ecriture;

    @FXML
    private Button accueil3;

    @FXML
    private Button consultation3;

    @FXML
    private Button deconnexion4;

    @FXML
    private RadioButton star1;

    @FXML
    private ToggleGroup stars;

    @FXML
    private RadioButton star2;

    @FXML
    private RadioButton star3;

    @FXML
    private RadioButton star4;

    @FXML
    private RadioButton star5;

    @FXML
    private TextArea commentaireArea;

    /**
     * Initialiser la vue
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	changePane(connexionPane);
	tableNoteOeuvre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	tableNoteNote.setCellValueFactory(new PropertyValueFactory<>("note"));
	tableCommentaireOeuvre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	tableCommentaireDate.setCellValueFactory(new PropertyValueFactory<>("dateDerCommentaire"));
	tableConsultationTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	tableConsultationTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
	tableConsultationPages.setCellValueFactory(new PropertyValueFactory<>("nbPage"));
	tableConsultationDate.setCellValueFactory(new PropertyValueFactory<>("datePubli"));
	tableConsultationNote.setCellValueFactory(new PropertyValueFactory<>("note"));
	tableNote.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableCommentaire.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableConsultation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	contenuTextFlow.setPrefWidth(scrollPane.getWidth());
	InputStream is;
	try {
	    is = new FileInputStream("./src/main/resources/images/etoile.png");
	    Image image = new Image(is);
	    ImageView imageView1 = new ImageView(image);
	    imageView1.setFitHeight(16);
	    imageView1.setFitWidth(16);
	    ImageView imageView2 = new ImageView(image);
	    imageView2.setFitHeight(16);
	    imageView2.setFitWidth(16);
	    ImageView imageView3 = new ImageView(image);
	    imageView3.setFitHeight(16);
	    imageView3.setFitWidth(16);
	    ImageView imageView4 = new ImageView(image);
	    imageView4.setFitHeight(16);
	    imageView4.setFitWidth(16);
	    ImageView imageView5 = new ImageView(image);
	    imageView5.setFitHeight(16);
	    imageView5.setFitWidth(16);
	    star1.setGraphic(imageView1);
	    star2.setGraphic(imageView2);
	    star3.setGraphic(imageView3);
	    star4.setGraphic(imageView4);
	    star5.setGraphic(imageView5);
	}
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @FXML
    void connexion() {
	if (!loginTextField.getText().isEmpty() && loginExiste(loginTextField.getText())) {
	    selectUser = mdb.getUtilisateur(loginTextField.getText());
	    oeuvres = mdb.getOeuvres(selectUser.getRole());
	    connexionLabel.setVisible(false);
	    consultation();
	}
	else {
	    connexionLabel.setVisible(true);
	}
	loginTextField.clear();
    }

    private boolean loginExiste(String login) {
	MongoCollection<Document> collection = new MongoDBConnexion().getDatabase().getCollection("utilisateur");
	return collection.countDocuments(new Document("login", login)) > 0;
    }

    @FXML
    public void accueil() {
	selectOeuvre = null;
	tableNote.getItems().clear();
	tableCommentaire.getItems().clear();
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    oeuvres.stream().filter(oeuvre -> !oeuvre.getNote().equals("Non notée"))
		    .sorted(Comparator.comparing(Oeuvre::getNote).reversed()).collect(Collectors.toList())
		    .forEach(oeuvre -> tableNote.getItems().add(oeuvre));
	    oeuvres.stream().filter(oeuvre -> oeuvre.getDateDerCommentaire() != null)
		    .sorted(Comparator.comparing(Oeuvre::getDateDerCommentaire).reversed()).collect(Collectors.toList())
		    .forEach(oeuvre -> tableCommentaire.getItems().add(oeuvre));
	}
	changePane(accueilPane);
    }

    @FXML
    public void consultation() {
	selectOeuvre = null;
	tableConsultation.getItems().clear();
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    oeuvres.stream().sorted(Comparator.comparing(Oeuvre::getTitre)).collect(Collectors.toList())
		    .forEach(oeuvre -> tableConsultation.getItems().add(oeuvre));
	}
	changePane(consultationPane);
    }

    @FXML
    public void deconnexion() {
	selectUser = null;
	oeuvres = null;
	selectOeuvre = null;
	changePane(connexionPane);
    }

    @FXML
    public void lire() {
	if (selectOeuvre != null) {
	    titre.setText(selectOeuvre.getTitre());
	    contenuTextFlow.getChildren().clear();
	    text.setText(selectOeuvre.getContenu());
	    contenuTextFlow.getChildren().add(text);
	    auteursBox.getChildren().clear();
	    selectOeuvre.getAuteurs().forEach(auteur -> auteursBox.getChildren()
		    .add(new Label(auteur.getPrenom() + " " + auteur.getNom().toUpperCase())));
	    commentairesBox.getChildren().clear();
	    mdb.getCommentaires(selectOeuvre).stream()
		    .sorted(Comparator.comparing(Commentaire::getDatePublication).reversed()).forEach(commentaire -> {
			commentairesBox.getChildren().add(new Label(commentaire.getNote() + " - "
				+ commentaire.getLogin() + " le "
				+ commentaire.getDatePublication().format(DateTimeFormatter.ofPattern("dd LLLL yyyy"))
				+ " :"));
			TextFlow commentaireFlow = new TextFlow();
			Text contenuCommentaire = new Text(commentaire.getTexte());
			commentaireFlow.getChildren().add(contenuCommentaire);
			commentairesBox.getChildren().add(commentaireFlow);
			commentairesBox.getChildren().add(new Separator());
		    });
	    commentaireArea.clear();
	    stars.selectToggle(null);
	    changePane(oeuvrePane);
	}
    }

    @FXML
    public void commenter() {
	changePane(commentairePane);
    }

    @FXML
    public void ecrire() {
	if (stars.getSelectedToggle() != null && !commentaireArea.getText().isEmpty()) {
	    MAJBase.insererCommentaireEnBase(new Commentaire(selectOeuvre.getTitre(), selectUser.getLogin(),
		    LocalDate.now(), Double.parseDouble(((RadioButton) (stars.getSelectedToggle())).getText()),
		    commentaireArea.getText()));
	    selectOeuvre.calculerNote();
	    selectOeuvre.calculerDateDerCommentaire();
	    lire();
	}
    }

    @FXML
    public void selectionnerOeuvreTC() {
	if (tableCommentaire.getSelectionModel().getSelectedItem() != null) {
	    selectOeuvre = tableCommentaire.getSelectionModel().getSelectedItem();
	}
    }

    @FXML
    public void selectionnerOeuvreTCons() {
	if (tableConsultation.getSelectionModel().getSelectedItem() != null) {
	    selectOeuvre = tableConsultation.getSelectionModel().getSelectedItem();
	}
    }

    @FXML
    public void selectionnerOeuvreTN() {
	if (tableNote.getSelectionModel().getSelectedItem() != null) {
	    selectOeuvre = tableNote.getSelectionModel().getSelectedItem();
	}
    }

    private void changePane(Pane pane) {
	ObservableList<Node> childs = stackPane.getChildren();
	while (!childs.get(childs.size() - 1).equals(pane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
    }
}