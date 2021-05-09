package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AppController implements Initializable {

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
    private TableView<String> tableNote;

    @FXML
    private TableColumn<String, String> tableNoteOeuvre;

    @FXML
    private TableColumn<String, String> tableNoteNote;

    @FXML
    private TableView<String> tableCommentaire;

    @FXML
    private TableColumn<String, String> tableCommentaireOeuvre;

    @FXML
    private TableColumn<String, String> tableCommentaireDate;

    @FXML
    private Button consultation;

    @FXML
    private Button deconnexion1;

    @FXML
    private Button lire1;

    @FXML
    private BorderPane consultationPane;

    @FXML
    private TableView<String> tableConsultation;

    @FXML
    private TableColumn<String, String> tableConsultationTitre;

    @FXML
    private TableColumn<String, String> tableConsultationTheme;

    @FXML
    private TableColumn<String, String> tableConsultationPages;

    @FXML
    private TableColumn<String, String> tableConsultationDate;

    @FXML
    private Button accueil;

    @FXML
    private Button deconnexion2;

    @FXML
    private Button lire2;

    @FXML
    private BorderPane oeuvrePane;

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

    /**
     * Initialiser la vue
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
	Text textRemplissage = new Text();
	textRemplissage.setText(
		"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
	contenuTextFlow.getChildren().add(textRemplissage);
    }

    @FXML
    void connexion() {
	accueil();
    }

    @FXML
    public void accueil() {
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

    }

}