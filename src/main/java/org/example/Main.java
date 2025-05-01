package org.example;
import javafx.stage.Stage;
import javafx.application.Application;
import org.example.Views.TelaInicial;
import org.example.Entities.Tarefa;

import java.util.ArrayList;
import java.util.List;

import static org.example.Repositories.Arquivo.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        lerArquivo(listaTarefa);
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.criarTelaInicial(primaryStage);
    }

    @Override
    public void stop() {
        criarArquivo(listaTarefa);
    }

    public static List<Tarefa> listaTarefa = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }
}