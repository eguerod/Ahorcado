package ahorcado;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application {

	private static final File PALABRAS_FILE = new File("palabras.txt");
	private static final File PUNTUACIONES_FILE = new File("puntuaciones.csv");

	public static Stage primaryStage;

	private RootController rootController = new RootController();

	@Override
	public void init() throws Exception {
		// cargar las palabras desde fichero
		if (PALABRAS_FILE.exists()) {
			rootController.getPalabras().addAll(Files.readAllLines(PALABRAS_FILE.toPath(), StandardCharsets.UTF_8));
		}

		// cargar las puntuaciones desde fichero
		if (PUNTUACIONES_FILE.exists()) {
			rootController.getPuntuacionesList()
					.addAll(Files.readAllLines(PUNTUACIONES_FILE.toPath(), StandardCharsets.UTF_8));
		}

		rootController.getPartidaController().getPalabraOculta();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		AhorcadoApp.primaryStage = primaryStage;

		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(new Scene(rootController.getView()));
		primaryStage.show();

	}

	@Override
	public void stop() throws Exception {
		// guardar las palabras en un fichero
		final StringBuffer contenido = new StringBuffer();
		rootController.getPalabras().forEach(palabra -> contenido.append(palabra + "\n"));
		Files.writeString(PALABRAS_FILE.toPath(), contenido.toString().trim(), StandardCharsets.UTF_8,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

		// guardar las puntuaciones en un fichero
		final StringBuffer contenido2 = new StringBuffer();
		rootController.getPuntuacionesList().forEach(puntuacion -> contenido2.append(puntuacion + "\n"));
		Files.writeString(PUNTUACIONES_FILE.toPath(), contenido2.toString().trim(), StandardCharsets.UTF_8,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	public static void main(String[] args) {
		launch(args);
	}

}