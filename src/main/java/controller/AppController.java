package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.MongoDBConnexion;
import model.Oeuvre;

public class AppController implements Initializable {

    private List<Oeuvre> oeuvres;

    @FXML
    private StackPane stackPane;

    @FXML
    private BorderPane connexionPane;

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
	MongoDBConnexion mdb = new MongoDBConnexion();
	oeuvres = mdb.getOeuvres();
	ObservableList<Node> childs = stackPane.getChildren();
	childs.forEach(node -> node.setVisible(false));
	while (!childs.get(childs.size() - 1).equals(connexionPane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
	tableNote.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableCommentaire.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableConsultation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	contenuTextFlow.setPrefWidth(scrollPane.getWidth());
	Text textRemplissage = new Text();
	textRemplissage.setText(
		"Lorem Ipsum is simply dummy teximply dummy text of the printing and typesetting industry.  dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lo");
	contenuTextFlow.getChildren().add(textRemplissage);
    }

    @FXML
    void connexion() {
	if (verifierLogin()) {
	    accueil();
	}
	loginTextField.clear();
    }

    private boolean verifierLogin() {
	// TODO Auto-generated method stub
	return true;
    }

    @FXML
    public void accueil() {
	tableNote.getItems().clear();
	tableCommentaire.getItems().clear();
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    oeuvres.stream().sorted(Comparator.comparing(Oeuvre::getNote).reversed()).collect(Collectors.toList())
		    .forEach(oeuvre -> {
			tableNote.getItems().add(oeuvre);
		    });
	    oeuvres.stream().sorted(Comparator.comparing(Oeuvre::getDateDerCommentaire).reversed())
		    .collect(Collectors.toList()).forEach(oeuvre -> {
			tableCommentaire.getItems().add(oeuvre);
		    });
	}
	ObservableList<Node> childs = stackPane.getChildren();
	while (!childs.get(childs.size() - 1).equals(accueilPane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
    }

    @FXML
    public void consultation() {
	tableConsultation.getItems().clear();
	if (oeuvres != null && !oeuvres.isEmpty()) {
	    oeuvres.stream().sorted(Comparator.comparing(Oeuvre::getTitre)).collect(Collectors.toList())
		    .forEach(oeuvre -> tableConsultation.getItems().add(oeuvre));
	}
	ObservableList<Node> childs = stackPane.getChildren();
	while (!childs.get(childs.size() - 1).equals(consultationPane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
    }

    @FXML
    public void deconnexion() {
	ObservableList<Node> childs = stackPane.getChildren();
	while (!childs.get(childs.size() - 1).equals(connexionPane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
    }

    @FXML
    public void lire() {
	ObservableList<Node> childs = stackPane.getChildren();
	while (!childs.get(childs.size() - 1).equals(oeuvrePane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
    }

    @FXML
    public void commenter() {
	ObservableList<Node> childs = stackPane.getChildren();
	while (!childs.get(childs.size() - 1).equals(commentairePane)) {
	    Node topNode = childs.get(childs.size() - 1);
	    Node newTopNode = childs.get(childs.size() - 2);
	    topNode.setVisible(false);
	    topNode.toBack();
	    newTopNode.setVisible(true);
	}
    }

    @FXML
    public void ecrire() {
	lire();
    }

}