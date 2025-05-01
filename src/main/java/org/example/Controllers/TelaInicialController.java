package org.example.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.Controllers.PopUp.CriarEditarTarefaController;
import org.example.Controllers.PopUp.ExcluirTarefaController;
import org.example.Controllers.PopUp.ListarTarefaController;
import org.example.Entities.Tarefa;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static org.example.Main.listaTarefa;

public class TelaInicialController implements Initializable {
    @FXML
    public ListView<Tarefa> listaDeTarefas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (listaDeTarefas != null) {
            ObservableList<Tarefa> tarefas = FXCollections.observableArrayList(listaTarefa);

            listaDeTarefas.setItems(tarefas);

            listaDeTarefas.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Tarefa tarefa, boolean empty) {
                    super.updateItem(tarefa, empty);

                    if (empty || tarefa == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/ItemTarefa.fxml"));
                            Parent root = loader.load();

                            ItemTarefaController controller = loader.getController();
                            controller.setDados(tarefa);

                            setGraphic(root);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void adicionarTarefa(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/PopUp/CriarEditarTarefa.fxml"));
        Parent root = loader.load();
        CriarEditarTarefaController controller = loader.getController();
        controller.setControllerPrincipal(this);
        controller.setEhEdicao(false);

        Stage stage = new Stage();
        stage.setTitle("Criar Tarefa");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void listarTarefa(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/PopUp/ListarTarefa.fxml"));
        Parent root = loader.load();

        ListarTarefaController controller = loader.getController();
        controller.setControllerPrincipal(this);

        Stage stage = new Stage();
        stage.setTitle("Listar Tarefa");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void editarTarefa(ActionEvent actionEvent) throws IOException {
        Tarefa tarefaSelecionada = listaDeTarefas.getSelectionModel().getSelectedItem();

        if (tarefaSelecionada != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/PopUp/CriarEditarTarefa.fxml"));
            Parent root = loader.load();

            CriarEditarTarefaController controller = loader.getController();
            controller.setControllerPrincipal(this, tarefaSelecionada);
            controller.setEhEdicao(true);

            Stage stage = new Stage();
            stage.setTitle("Editar Tarefa");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } else {
            System.out.println("Nenhuma tarefa foi selecionada!");
        }
    }

    public void excluirTarefa(ActionEvent actionEvent) throws IOException {
        Tarefa tarefaSelecionada = listaDeTarefas.getSelectionModel().getSelectedItem();

        if (tarefaSelecionada != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/PopUp/ExcluirTarefa.fxml"));
            Parent root = loader.load();
            ExcluirTarefaController controller = loader.getController();
            controller.setControllerPrincipal(this, tarefaSelecionada);

            Stage stage = new Stage();
            stage.setTitle("Excuir Tarefa");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } else {
            System.out.println("Nenhuma tarefa foi selecionada!");
        }

    }

    public void recarregarLista() {
        listaDeTarefas.setItems(FXCollections.observableArrayList(listaTarefa));
    }

    public void recarregarListaFiltrada(List<Tarefa> listaTarefaFiltro) {
        listaDeTarefas.setItems(FXCollections.observableArrayList(listaTarefaFiltro));
    }
}
