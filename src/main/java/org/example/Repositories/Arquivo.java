package org.example.Repositories;

import org.example.Entities.Tarefa;

import java.io.*;
import java.util.List;

public class Arquivo {
    public static void criarArquivo(List<Tarefa> listaTarefa) {
        try (FileWriter listaTarefas = new FileWriter("listaTarefas.csv")) {
            String texto;

            listaTarefas.write("nome, status, prioridade, descricao, data, dataLimite\n");
            for (Tarefa f : listaTarefa) {
                texto = String.format("\"%s\",%s,%s,\"%s\",\"%s\",\"%s\"\n",
                        f.getNome(), f.getStatus(), f.getPrioridade(), f.getDescricao(), f.getData(), f.getDataLimite());
                listaTarefas.write(texto);
            }

            System.out.println("Arquivo criado com sucesso!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lerArquivo(List<Tarefa> listaTarefa) {
        File arquivo = new File("listaTarefas.csv");

        if(arquivo.exists()) {
            try (BufferedReader lerListaTarefas = new BufferedReader(new FileReader("listaTarefas.csv"))) {
                lerListaTarefas.readLine();
                String linha;
                while ((linha = lerListaTarefas.readLine()) != null) {
                    linha = linha.replace("\"", "");
                    String[] campos = linha.split(",");
                    listaTarefa.add(new Tarefa(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]));
                }

                System.out.println("Lista lida com sucesso!\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Arquivo inexistente no momento!\n");
        }


    }
}
