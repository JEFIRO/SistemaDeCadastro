package com.jefiro.email;

import com.jefiro.conect.SQL;
import com.jefiro.modelos.Usuario;
import com.jefiro.validacao.ValidaTempo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidarEmail extends Usuario {
    private final SQL SQL = new SQL("cadadstroUsuario", "jefiro", "admin");
    private final ValidaTempo TEMPO = new ValidaTempo();
    private ResultSet resultSet;
    private String codigo;
    private String dataAtual;

    public void inserirDados() {
        SQL.conectar();
        var comando = "INSERT INTO validaEmail (idUsuario, codigo, dataAtual, dataExpira)";
        var values = String.format("VALUES ('%s', '%s', '%s', '%s')", getIdUsuario(), codigo, TEMPO.pegarData(), TEMPO.expiraData());
        SQL.insert(comando + values);
        SQL.close();
    }

    public ResultSet buscarDados() {
        SQL.conectar();
        var querry = String.format("USE cadadstroUsuario; SELECT * FROM validaEmail WHERE idUsuario = '%s';", getIdUsuario());
        resultSet = SQL.insertGetter(querry);
        return resultSet;
    }
    public boolean validarCodigo(){
        try {
            if (resultSet.next()) {
                var id = resultSet.getString("idUsuario");
                var codigo = resultSet.getString("codigo");
                var dataExpira = resultSet.getString("dataExpira");

                if (id != null || codigo != null || dataAtual != null) {
                    if (getIdUsuario().equalsIgnoreCase(id)) {
                        var hora1 = TEMPO.pegarData();
                        if (TEMPO.comparaHoras(hora1, dataExpira)) {
                            return this.codigo.equalsIgnoreCase(codigo);
                        }
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ValidarEmail(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }

    @Override
    public String toString() {
        return codigo;
    }
}