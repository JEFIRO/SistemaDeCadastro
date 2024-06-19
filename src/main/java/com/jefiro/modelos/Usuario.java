package com.jefiro.modelos;

import com.jefiro.generete.IdGenerete;

public class Usuario {
    private IdGenerete id = new IdGenerete();
    private String nome;
    private String senha;
    private String gmail;
    private String telefone;
    private String cpf;
    private String aniversario;
    private final String idUsuario = id.id();

    public Usuario() {
    }

    public Usuario(String nome, String senha, String gmail, String telefone, String celular, String cpf, String aniversario) {
        this.nome = nome;
        this.senha = senha;
        this.gmail = gmail;
        this.telefone = telefone;
        this.cpf = cpf;
        this.aniversario = aniversario;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGmail() {
        return "jefefrs@gmail.com";
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
