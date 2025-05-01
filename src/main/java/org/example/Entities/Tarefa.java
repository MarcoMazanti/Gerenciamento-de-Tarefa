package org.example.Entities;
import java.time.*;

public class Tarefa {
    private String Nome;
    private String Status;
    private String Prioridade;
    private String Descricao;
    private final ZonedDateTime Data;
    private ZonedDateTime DataLimite;

    public Tarefa(String nome, String status, String prioridade, String descricao, ZonedDateTime dataLimite) {
        this.Nome = nome;
        this.Status = status;
        this.Prioridade = prioridade;
        this.Descricao = descricao;
        Data = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.DataLimite = dataLimite;
    }

    public Tarefa(String nome, String status, String prioridade, String descricao, String data, String dataLimite) {
        this.Nome = nome;
        this.Status = status;
        this.Prioridade = prioridade;
        this.Descricao = descricao;
        this.Data = ZonedDateTime.parse(data);
        this.DataLimite = ZonedDateTime.parse(dataLimite);
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPrioridade() {
        return Prioridade;
    }

    public void setPrioridade(String prioridade) {
        Prioridade = prioridade;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getData() {
        return Data.toString();
    }

    public String getDataLimite() {
        return DataLimite.toString();
    }

    public void setDataLimite(String diasRestantes) {
        this.DataLimite = Data.plusDays(Integer.parseInt(diasRestantes));
    }
}
