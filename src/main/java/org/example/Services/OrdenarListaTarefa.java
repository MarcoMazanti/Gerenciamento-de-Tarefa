package org.example.Services;

import org.example.Entities.Tarefa;

import java.util.List;

public class OrdenarListaTarefa {
    public static List<Tarefa> ordenarListaPorNome(List<Tarefa> listaTarefa) {
        listaTarefa.sort((t1, t2) -> t1.getNome().compareToIgnoreCase(t2.getNome()));
        return listaTarefa;
    }

    public static List<Tarefa> ordenarListaPorStatus(List<Tarefa> listaTarefa) {
        listaTarefa.sort((t1, t2) -> t1.getStatus().compareToIgnoreCase(t2.getStatus()));
        return listaTarefa;
    }

    public static List<Tarefa> ordenarListaPorPrioridade(List<Tarefa> listaTarefa) {
        listaTarefa.sort((t1, t2) -> t1.getPrioridade().compareToIgnoreCase(t2.getPrioridade()));
        return listaTarefa;
    }

    public static List<Tarefa> ordenarListaPorDataCriada(List<Tarefa> listaTarefa) {
        listaTarefa.sort((t1, t2) -> t1.getData().compareToIgnoreCase(t2.getData()));
        return listaTarefa;
    }

    public static List<Tarefa> ordenarListaPorDataLimite(List<Tarefa> listaTarefa) {
        listaTarefa.sort((t1, t2) -> t1.getDataLimite().compareToIgnoreCase(t2.getDataLimite()));
        return listaTarefa;
    }
}
