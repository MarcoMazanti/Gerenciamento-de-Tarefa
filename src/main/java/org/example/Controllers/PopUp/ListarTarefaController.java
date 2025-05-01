package org.example.Controllers.PopUp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Controllers.TelaInicialController;
import org.example.Services.OrdenarListaTarefa;
import org.example.Entities.Tarefa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.example.Main.listaTarefa;

public class ListarTarefaController implements Initializable {
    @FXML
    public ComboBox textoFiltrar;
    @FXML
    public ComboBox filtroExpecifico;
    @FXML
    public VBox BlocoFiltroExpecifico;
    @FXML
    private RadioButton rbNome, rbStatus, rbPrioridade, rbDataCriada, rbDataLimite;

    public ToggleGroup grupo;

    private TelaInicialController controllerPrincipal;

    public void setControllerPrincipal(TelaInicialController controllerPrincipal) {
        this.controllerPrincipal = controllerPrincipal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textoFiltrar.getItems().addAll("geral", "status", "prioridade");

        grupo = new ToggleGroup();

        rbNome.setToggleGroup(grupo);
        rbStatus.setToggleGroup(grupo);
        rbPrioridade.setToggleGroup(grupo);
        rbDataCriada.setToggleGroup(grupo);
        rbDataLimite.setToggleGroup(grupo);
    }

    public void mostrarFuncaoExpecifica(ActionEvent inputMethodEvent) {
        if (textoFiltrar.getValue().equals("status")) {
            BlocoFiltroExpecifico.setVisible(true);
            filtroExpecifico.getItems().addAll("pendente", "concluida");
        } else if (textoFiltrar.getValue().equals("prioridade")) {
            BlocoFiltroExpecifico.setVisible(true);
            filtroExpecifico.getItems().addAll("alta", "media", "baixa");
        } else {
            BlocoFiltroExpecifico.setVisible(false);
        }
    }

    public void cancelarTarefa(ActionEvent actionEvent) {
        System.out.println("Cancelou a listagem das tarefas!");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void filtrarOrdenarTarefa(ActionEvent actionEvent) throws IOException {
        String filtro = (textoFiltrar.getValue() != null) ? textoFiltrar.getValue().toString() : null;
        String valorFiltro = (filtroExpecifico.getValue() != null) ? filtroExpecifico.getValue().toString() : null;

        if ((filtro != null && valorFiltro != null) || filtro.equals("geral")) {
            List<Tarefa> listaFiltrada = new ArrayList<>(listaTarefa);

            switch (textoFiltrar.getValue().toString()) {
                case "status":
                    listaFiltrada = listaFiltrada.stream()
                            .filter(tarefa -> tarefa.getStatus().equals(filtroExpecifico.getValue().toString()))
                            .collect(Collectors.toList());
                    break;
                case "prioridade":
                    listaFiltrada = listaFiltrada.stream()
                            .filter(tarefa -> tarefa.getPrioridade().equals(filtroExpecifico.getValue().toString()))
                            .collect(Collectors.toList());
                    break;
            }

            if (rbNome.isSelected()) {
                OrdenarListaTarefa.ordenarListaPorNome(listaFiltrada);
            } else if (rbStatus.isSelected()) {
                OrdenarListaTarefa.ordenarListaPorStatus(listaFiltrada);
            } else if (rbPrioridade.isSelected()) {
                OrdenarListaTarefa.ordenarListaPorPrioridade(listaFiltrada);
            } else if (rbDataCriada.isSelected()) {
                OrdenarListaTarefa.ordenarListaPorDataCriada(listaFiltrada);
            } else if (rbDataLimite.isSelected()) {
                OrdenarListaTarefa.ordenarListaPorDataLimite(listaFiltrada);
            }

            controllerPrincipal.recarregarListaFiltrada(listaFiltrada);

            System.out.println("Lista filtrada e ordenada com sucesso!");

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/PopUp/ErrorInsert.fxml"));
            Parent root = loader.load();

            ErrorInsertController controller = loader.getController();
            controller.setAviso("Insira os dados corretamente!");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.showAndWait();
        }
    }
}
