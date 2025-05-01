package org.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.Entities.Tarefa;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ItemTarefaController {
    @FXML
    public Label nome;
    @FXML
    public Label status;
    @FXML
    public Label prioridade;
    @FXML
    public Label dataCriada;
    @FXML
    public Label dataLimite;
    @FXML
    public Label comentario;

    public void setDados(Tarefa tarefa) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        nome.setText(tarefa.getNome());
        status.setText(tarefa.getStatus());
        prioridade.setText(tarefa.getPrioridade());
        dataCriada.setText(formatter.format(ZonedDateTime.parse(tarefa.getData())));
        dataLimite.setText(formatter.format(ZonedDateTime.parse(tarefa.getDataLimite())));
        comentario.setText(tarefa.getDescricao());

        if(status.getText().equals("pendente")) {
            status.setStyle("-fx-text-fill: #ff3f3f");
        } else {
            status.setStyle("-fx-text-fill: #10b810");
        }

        if(prioridade.getText().equals("alta")) {
            prioridade.setStyle("-fx-text-fill: #ff3f3f");
        } else if(prioridade.getText().equals("media")) {
            prioridade.setStyle("-fx-text-fill: #ff9900");
        } else {
            prioridade.setStyle("-fx-text-fill: #10b810");
        }

        dataCriada.setStyle("-fx-text-fill: #10b810");
        dataLimite.setStyle("-fx-text-fill: #ff3f3f");
    }
}
