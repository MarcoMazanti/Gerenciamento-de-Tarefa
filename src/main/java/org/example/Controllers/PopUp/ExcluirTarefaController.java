package org.example.Controllers.PopUp;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.example.Controllers.TelaInicialController;
import org.example.Entities.Tarefa;
import static org.example.Main.listaTarefa;

public class ExcluirTarefaController {
    private TelaInicialController controllerPrincipal;
    private Tarefa tarefaSelecionada;

    public void setControllerPrincipal(TelaInicialController controllerPrincipal, Tarefa tarefaSelecionada) {
        this.controllerPrincipal = controllerPrincipal;
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public void cancelarExclusao(ActionEvent actionEvent) {
        System.out.println("Cancelou a exclusão da tarefa!");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void excluirTarefa(ActionEvent actionEvent) {
        listaTarefa.remove(tarefaSelecionada);
        controllerPrincipal.recarregarLista();

        System.out.println("Exclusão feita com sucesso!");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
