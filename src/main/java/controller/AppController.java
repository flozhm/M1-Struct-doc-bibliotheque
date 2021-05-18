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
import javafx.scene.control.ComboBox;
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

    // Connexion à la base de donnée
    private MongoDBConnexion mdb = new MongoDBConnexion();

    // Utilisateur de l'application
    private Utilisateur selectUser = null;

    // Oeuvre sélectionnée dans l'application
    private Oeuvre selectOeuvre = null;

    // Liste des oeuvres à afficher
    private List<Oeuvre> oeuvres = null;

    @FXML
    private StackPane stackPane;

    @FXML
    private BorderPane connexionPane;
    @FXML
    private Label      connexionLabel;
    @FXML
    private TextField  loginTextField;
    @FXML
    private Button     loginValidation;

    @FXML
    private BorderPane			accueilPane;
    @FXML
    private TableView<Oeuvre>		tableNote;
    @FXML
    private TableColumn<Oeuvre, String>	tableNoteOeuvre;
    @FXML
    private TableColumn<Oeuvre, String>	tableNoteNote;
    @FXML
    private TableView<Oeuvre>		tableCommentaire;
    @FXML
    private TableColumn<Oeuvre, String>	tableCommentaireOeuvre;
    @FXML
    private TableColumn<Oeuvre, String>	tableCommentaireDate;
    @FXML
    private Button			consultation;
    @FXML
    private Button			deconnexion1;
    @FXML
    private Button			lire1;

    @FXML
    private BorderPane			consultationPane;
    @FXML
    private TextField			rechercheTitre;
    @FXML
    private TextField			rechercheContenu;
    @FXML
    private ComboBox<String>		rechercheTheme;
    @FXML
    private TableView<Oeuvre>		tableConsultation;
    @FXML
    private TableColumn<Oeuvre, String>	tableConsultationTitre;
    @FXML
    private TableColumn<Oeuvre, String>	tableConsultationTheme;
    @FXML
    private TableColumn<Oeuvre, String>	tableConsultationPages;
    @FXML
    private TableColumn<Oeuvre, String>	tableConsultationDate;
    @FXML
    private TableColumn<Oeuvre, String>	tableConsultationNote;

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
    private VBox       contenu;
    @FXML
    private Label      titre;
    @FXML
    private TextFlow   contenuTextFlow;
    @FXML
    private Label      commentaires;
    @FXML
    private VBox       commentairesBox;
    @FXML
    private VBox       auteursBox;
    @FXML
    private Button     commenter;
    @FXML
    private Button     accueil2;
    @FXML
    private Button     consultation2;
    @FXML
    private Button     deconnexion3;

    @FXML
    private BorderPane	commentairePane;
    @FXML
    private Button	ecriture;
    @FXML
    private Button	accueil3;
    @FXML
    private Button	consultation3;
    @FXML
    private Button	deconnexion4;
    @FXML
    private RadioButton	star1;
    @FXML
    private ToggleGroup	stars;
    @FXML
    private RadioButton	star2;
    @FXML
    private RadioButton	star3;
    @FXML
    private RadioButton	star4;
    @FXML
    private RadioButton	star5;
    @FXML
    private TextArea	commentaireArea;

    /**
     * Initialiser la vue
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	// Afficher l'écran de connexion
	changePane(connexionPane);

	// Paramètrage des éléments à afficher dans les tableaux
	tableNoteOeuvre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	tableNoteNote.setCellValueFactory(new PropertyValueFactory<>("note"));
	tableCommentaireOeuvre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	tableCommentaireDate.setCellValueFactory(new PropertyValueFactory<>("dateDerCommentaire"));
	tableConsultationTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	tableConsultationTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
	tableConsultationPages.setCellValueFactory(new PropertyValueFactory<>("nbPage"));
	tableConsultationDate.setCellValueFactory(new PropertyValueFactory<>("datePubli"));
	tableConsultationNote.setCellValueFactory(new PropertyValueFactory<>("note"));

	// Redimensionnement des colonnes des tableaux
	tableNote.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableCommentaire.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableConsultation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	// Redimensionnement du TextFlow possèdant le contenu des oeuvres
	contenuTextFlow.setPrefWidth(scrollPane.getWidth());

	// Ajout d'images d'étoiles sur les Radiobuttons de notes
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

    /**
     * Connecte l'utilisateur et génère les données adéquates
     */
    @FXML
    void connexion() {
	if (!loginTextField.getText().isEmpty() && loginExiste(loginTextField.getText())) {
	    // Récupération de l'utilisateur en base
	    selectUser = mdb.getUtilisateur(loginTextField.getText());

	    // Récupération des oeuvres liées au rôle de l'utilisateur
	    oeuvres = mdb.getOeuvres(selectUser.getRole());

	    // Remplissage de la ComboBox des Thèmes
	    rechercheTheme.getItems().clear();
	    oeuvres.forEach(oeuvre -> {
		if (!rechercheTheme.getItems().contains(oeuvre.getTheme())) {
		    rechercheTheme.getItems().add(oeuvre.getTheme());
		}
	    });
	    rechercheTheme.getItems().add("");
	    rechercheTheme.getSelectionModel().select("");

	    // Affichage de l'écran d'accueil
	    consultation();
	    connexionLabel.setVisible(false);
	}
	else {
	    // Affichage d'une erreur si le login n'existe pas
	    connexionLabel.setVisible(true);
	}
	loginTextField.clear();
    }

    /**
     * Déconnecte l'utilisateur
     */
    @FXML
    public void deconnexion() {
	// Réinitialise les données
	selectUser = null;
	oeuvres = null;
	selectOeuvre = null;

	// Affichage de l'écran de Connexion
	changePane(connexionPane);
    }

    /**
     * Prépare les données de la page d'Accueil
     */
    @FXML
    public void consultation() {
	// Réinitialise les données
	selectOeuvre = null;
	rechercheTitre.setText("");
	rechercheContenu.setText("");
	rechercheTheme.getSelectionModel().select("");
	tableConsultation.getItems().clear();

	// Remplit le tableau en triant les oeuvres
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    oeuvres.stream().sorted(Comparator.comparing(Oeuvre::getTitre)).collect(Collectors.toList())
		    .forEach(oeuvre -> tableConsultation.getItems().add(oeuvre));
	}

	// Affichage de l'écran d'accueil
	changePane(consultationPane);
    }

    /**
     * Actualise le tableau de consultation en prenant en compte les paramètres
     * utilisateurs
     */
    @FXML
    public void rechercher() {
	// Réinisialise les données
	tableConsultation.getItems().clear();

	// Remplit le tableau en filtrant par titre, contenu et thème s'il est
	// sélectionné
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    if (rechercheTheme.getSelectionModel().getSelectedItem().isEmpty()) {
		oeuvres.stream().filter(oeuvre -> oeuvre.getTitre().contains(rechercheTitre.getText()))
			.filter(oeuvre -> oeuvre.getContenu().contains(rechercheContenu.getText()))
			.sorted(Comparator.comparing(Oeuvre::getTitre)).collect(Collectors.toList())
			.forEach(oeuvre -> tableConsultation.getItems().add(oeuvre));
	    }
	    else {
		oeuvres.stream().filter(oeuvre -> oeuvre.getTitre().contains(rechercheTitre.getText()))
			.filter(oeuvre -> oeuvre.getContenu().contains(rechercheContenu.getText()))
			.filter(oeuvre -> oeuvre.getTheme()
				.equals(rechercheTheme.getSelectionModel().getSelectedItem()))
			.sorted(Comparator.comparing(Oeuvre::getTitre)).collect(Collectors.toList())
			.forEach(oeuvre -> tableConsultation.getItems().add(oeuvre));
	    }
	}
    }

    /**
     * Prépare les données de la page d'Informations
     */
    @FXML
    public void accueil() {
	// Réinitialise les données
	selectOeuvre = null;
	tableNote.getItems().clear();
	tableCommentaire.getItems().clear();

	// Remplit les tableaux en triant les oeuvres notées
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    oeuvres.stream().filter(oeuvre -> !oeuvre.getNote().equals("Non notée"))
		    .sorted(Comparator.comparing(Oeuvre::getNote).reversed()).collect(Collectors.toList())
		    .forEach(oeuvre -> tableNote.getItems().add(oeuvre));
	    oeuvres.stream().filter(oeuvre -> oeuvre.getDateDerCommentaire() != null)
		    .sorted(Comparator.comparing(Oeuvre::getDateDerCommentaire).reversed()).collect(Collectors.toList())
		    .forEach(oeuvre -> tableCommentaire.getItems().add(oeuvre));
	}

	// Affichage de l'écran d'Informations
	changePane(accueilPane);
    }

    /**
     * Prépare les données pour la lecture d'une oeuvre
     */
    @FXML
    public void lire() {
	if (selectOeuvre != null) {
	    // Modification du titre et du contenu de l'oeuvre
	    titre.setText(selectOeuvre.getTitre());
	    contenuTextFlow.getChildren().clear();
	    Text text = new Text();
	    text.setText(selectOeuvre.getContenu());
	    contenuTextFlow.getChildren().add(text);

	    // Ajout des auteurs
	    auteursBox.getChildren().clear();
	    selectOeuvre.getAuteurs().forEach(auteur -> auteursBox.getChildren()
		    .add(new Label(auteur.getPrenom() + " " + auteur.getNom().toUpperCase())));

	    // Ajout des commentaires
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

	    // Affichage de l'écran de Lecture
	    changePane(oeuvrePane);
	}
    }

    /**
     * Prépare les données pour l'écriture d'un commentaire
     */
    @FXML
    public void commenter() {
	// Réinitialise les données
	commentaireArea.clear();
	stars.selectToggle(null);

	// Affichage de l'écran d'écriture d'un Commentaire
	changePane(commentairePane);
    }

    /**
     * Insert le commentaire en base de données
     */
    @FXML
    public void ecrire() {
	if (stars.getSelectedToggle() != null && !commentaireArea.getText().isEmpty()) {
	    // Insertion du commentaire en base
	    MAJBase.insererCommentaire(new Commentaire(selectOeuvre.getTitre(), selectUser.getLogin(), LocalDate.now(),
		    Double.parseDouble(((RadioButton) (stars.getSelectedToggle())).getText()),
		    commentaireArea.getText()));

	    // Calculs des nouvelles données de l'oeuvre concernant les notes
	    selectOeuvre.calculerNote();
	    selectOeuvre.calculerDateDerCommentaire();

	    // Affichage de l'écran de Lecture
	    lire();
	}
    }

    /**
     * Vérifie si le login de l'utilisateur existe en base
     * 
     * @param login login de l'utilisateur
     * @return true si le login existe
     */
    private boolean loginExiste(String login) {
	MongoCollection<Document> utilisateurs = mdb.getDatabase().getCollection("utilisateur");
	return utilisateurs.countDocuments(new Document("login", login)) == 1;
    }

    /**
     * Change l'oeuvre sélectionnée pour le tableau des derniers commentaires
     */
    @FXML
    public void selectionnerOeuvreTC() {
	if (tableCommentaire.getSelectionModel().getSelectedItem() != null) {
	    selectOeuvre = tableCommentaire.getSelectionModel().getSelectedItem();
	}
    }

    /**
     * Change l'oeuvre sélectionnée pour le tableau de consultation
     */
    @FXML
    public void selectionnerOeuvreTCons() {
	if (tableConsultation.getSelectionModel().getSelectedItem() != null) {
	    selectOeuvre = tableConsultation.getSelectionModel().getSelectedItem();
	}
    }

    /**
     * Change l'oeuvre sélectionnée pour le tableau des meilleures notes
     */
    @FXML
    public void selectionnerOeuvreTN() {
	if (tableNote.getSelectionModel().getSelectedItem() != null) {
	    selectOeuvre = tableNote.getSelectionModel().getSelectedItem();
	}
    }

    /**
     * Change l'écran affiché sur l'interface
     * 
     * @param pane panneau à afficher
     */
    private void changePane(Pane pane) {
	// Change l'ordre des panneaux jusqu'à ce que le bon soit affiché
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