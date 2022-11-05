package ahorcado.partida;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PartidaController implements Initializable {

	private String palabraOculta;
	private int palabrasAcertadas, letrasAcertadas, fallos;
	private PartidaModel model;

	// view

	@FXML
	private BorderPane view;

	@FXML
	private Button letraButton, resolverButton;

	@FXML
	private TextField intentoText, palabrasAcertadasText, letrasAcertadasText;

	@FXML
	private Label palabraOcultaLabel, letrasJugadasLabel;

	@FXML
	private ImageView image;

	public PartidaController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PartidaView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setModel(new PartidaModel());
		getModel().respuestaProperty().bind(intentoText.textProperty());
		fallos++;
		image.setImage(new Image(getClass().getResourceAsStream("/images/" + fallos + ".png")));
		palabrasAcertadasText.textProperty().set(palabrasAcertadas + "");
		letrasAcertadasText.textProperty().set(letrasAcertadas + "");
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onLetraAction(ActionEvent event) {
		if (letrasJugadasLabel.getText().indexOf(getModel().getRespuesta().toUpperCase().charAt(0)) == -1
				&& palabraOcultaLabel.getText().indexOf(getModel().getRespuesta().toUpperCase().charAt(0)) == -1
				&& !getModel().getRespuesta().isBlank()) {
			if (palabraOculta.indexOf(getModel().getRespuesta().toUpperCase().charAt(0)) == -1) {
				letrasJugadasLabel.textProperty()
						.set(letrasJugadasLabel.getText() + getModel().getRespuesta().toUpperCase().charAt(0) + " ");
				fallos++;
				image.setImage(new Image(getClass().getResourceAsStream("/images/" + fallos + ".png")));
			} else {
				String palabra = "";
				for (Character letra : palabraOculta.toCharArray()) {
					if (letra.equals(getModel().getRespuesta().toUpperCase().charAt(0))) {
						palabra += getModel().getRespuesta().toUpperCase().charAt(0);
						letrasAcertadas++;
						letrasAcertadasText.textProperty().set(letrasAcertadas + "");
					} else if (letra
							.equals(palabraOcultaLabel.textProperty().get().charAt(palabraOculta.indexOf(letra))))
						palabra += palabraOcultaLabel.textProperty().get().charAt(palabraOculta.indexOf(letra));
					else
						palabra += "_";
				}
				palabraOcultaLabel.textProperty().set(palabra);
				if(palabraOcultaLabel.textProperty().get().equals(palabraOculta)) {
					palabrasAcertadas++;
					palabrasAcertadasText.textProperty().set(palabrasAcertadas + "");
					getPalabraOculta();
				}
				
			}
		}
		if (fallos == 9) {
			finDePartida();
		}
		intentoText.textProperty().set("");
	}

	@FXML
	void onResolverAction(ActionEvent event) {
		if (!getModel().getRespuesta().isBlank()) {
			if (getModel().getRespuesta().toUpperCase().equals(palabraOculta)) {
				palabrasAcertadas++;
				palabrasAcertadasText.textProperty().set(palabrasAcertadas + "");
				for (int i = 0; i < palabraOcultaLabel.textProperty().get().length(); i++) {
					if(palabraOcultaLabel.textProperty().get().charAt(i) == '_') {
						letrasAcertadas++;
					}
					letrasAcertadasText.textProperty().set(letrasAcertadas + "");
				}
				getPalabraOculta();
			} else {
				fallos++;
				image.setImage(new Image(getClass().getResourceAsStream("/images/" + fallos + ".png")));
			}
		}
		if (fallos == 9) {
			finDePartida();
		}
		intentoText.textProperty().set("");
	}

	public void getPalabraOculta() {
		int index = (int) Math.round(Math.random() * (getModel().getPalabras().size() - 1));
		palabraOculta = getModel().getPalabras().get(index);
		String palabra = "";
		for (Character letra : palabraOculta.toCharArray()) {
			if (letra.equals(' '))
				palabra += " ";
			else
				palabra += "_";
		}
		palabraOcultaLabel.textProperty().set(palabra);
		letrasJugadasLabel.textProperty().set("");
	}

	private void finDePartida() {
		letraButton.disableProperty().set(true);
		resolverButton.disableProperty().set(true);
		TextInputDialog alerta = new TextInputDialog();
		alerta.setTitle("Fin del Juego");
		alerta.setHeaderText("Has llegado al máximo de errores.\nResultado: " + palabrasAcertadas
				+ " palabras acertadas y " + letrasAcertadas + " letras acertadas.");
		alerta.setContentText("Introduzca su nombre.");
		Optional<String> nombre = alerta.showAndWait();
		if (nombre.isPresent() && !nombre.get().isBlank()) {
			String puntuacion = nombre.get() + "," + palabrasAcertadas + "," + letrasAcertadas;
			model.getPuntuacionesList().add(puntuacion);
		}
	}

	public PartidaModel getModel() {
		return model;
	}

	public void setModel(PartidaModel model) {
		this.model = model;
	}
}
