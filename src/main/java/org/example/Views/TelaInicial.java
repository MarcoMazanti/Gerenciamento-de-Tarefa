package org.example.Views;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TelaInicial {
    public void criarTelaInicial(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/Views/TelaInicial.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Gerenciador de Tarefas");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
