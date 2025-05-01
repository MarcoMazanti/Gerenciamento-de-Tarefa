package org.example.Controllers.PopUp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Controllers.TelaInicialController;
import org.example.Entities.Tarefa;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import static org.example.Main.listaTarefa;

public class CriarEditarTarefaController implements Initializable {
    @FXML
    public Button botaoCriarEditar;
    @FXML
    private TextField textoNome;
    @FXML
    private DatePicker escolhaData;
    @FXML
    private ComboBox<String> caixaStatus;
    @FXML
    private ComboBox<String> caixaPrioridade;
    @FXML
    private TextArea textoDescricao;

    private TelaInicialController controllerPrincipal;
    private Tarefa tarefaSelecionada;
    private boolean ehEdicao;

    public void setControllerPrincipal(TelaInicialController controllerPrincipal) {
        this.controllerPrincipal = controllerPrincipal;
    }

    public void setControllerPrincipal(TelaInicialController controllerPrincipal, Tarefa tarefaSelecionada) {
        this.controllerPrincipal = controllerPrincipal;
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public void setEhEdicao(boolean ehEdicao) {
        this.ehEdicao = ehEdicao;

        if(ehEdicao && tarefaSelecionada != null) {
            textoNome.setText(tarefaSelecionada.getNome());
            escolhaData.setValue((ZonedDateTime.parse(tarefaSelecionada.getDataLimite()).toLocalDate()));
            caixaStatus.setValue(tarefaSelecionada.getStatus());
            caixaPrioridade.setValue(tarefaSelecionada.getPrioridade());
            textoDescricao.setText(tarefaSelecionada.getDescricao());

            botaoCriarEditar.setText("Editar");
        } else {
            botaoCriarEditar.setText("Criar");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caixaStatus.getItems().addAll("pendente", "concluida");
        caixaPrioridade.getItems().addAll("alta", "media", "baixa");
    }

    public void criarEditarTarefa(ActionEvent actionEvent) throws IOException {
        if(!(escolhaData.getValue() == null || escolhaData.getValue().isBefore(LocalDate.now()) ||
                textoNome.getText().isBlank() || caixaStatus.getValue() == null ||
                caixaPrioridade.getValue() == null || textoDescricao.getText().isBlank())) {
            ZonedDateTime dataLimite = escolhaData.getValue().atStartOfDay(ZonedDateTime.now().getZone());
            Tarefa tarefa = new Tarefa(textoNome.getText(), caixaStatus.getValue().toString(), caixaPrioridade.getValue().toString(), textoDescricao.getText(), dataLimite);

            if (ehEdicao && tarefaSelecionada != null) {
                listaTarefa.set(listaTarefa.indexOf(tarefaSelecionada), tarefa);
                System.out.println("Tarefa editada com sucesso!");
            } else {
                listaTarefa.add(tarefa);
                System.out.println("Tarefa criada com sucesso!");
            }

            controllerPrincipal.recarregarLista();


            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Views/PopUp/ErrorInsert.fxml"));
            Parent root = loader.load();

            ErrorInsertController controller = loader.getController();
            controller.setAviso(aviso());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.showAndWait();
        }
    }

    public void cancelarCriacao(ActionEvent actionEvent) {
        System.out.println("Cancelou a criação da tarefa!");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private String aviso() {
        if (escolhaData.getValue() == null || escolhaData.getValue().isBefore(LocalDate.now()) ||
                textoNome.getText().isBlank() || caixaStatus.getValue() == null ||
                caixaPrioridade.getValue() == null || textoDescricao.getText().isBlank()) {
            return "Insira os dados corretamente!";
        } else {
            return null;
        }
    }
}
