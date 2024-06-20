package com.jefiro.email;

import com.jefiro.conect.SQL;
import com.jefiro.modelos.Usuario;
import com.jefiro.validacao.ValidaTempo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidarEmail extends Usuario {
    private final SQL SQL = new SQL();
    private final ValidaTempo TEMPO = new ValidaTempo();

    public ValidarEmail() {
        SQL.conectar();
    }

    public void inserirDados(String codigo) {
        try {
            var comando = "INSERT INTO validaEmail (idUsuario, codigo, dataAtual, dataExpira)";
            var values = String.format("VALUES ('%s', '%s', '%s', '%s')", getIdUsuario(), codigo, TEMPO.pegarData(), TEMPO.expiraData());
            SQL.insert(comando + values);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean validarCodigo(String codigo){
        try {
            var querry = String.format("USE cadadstroUsuario; SELECT * FROM validaEmail WHERE idUsuario = '%s';", getIdUsuario());
            ResultSet resultSet = SQL.insertGetter(querry);

            if (resultSet.next()) {
                var id = resultSet.getString("idUsuario");
                var codigodb = resultSet.getString("codigo");
                var dataExpira = resultSet.getString("dataExpira");

                if (id != null || codigo != null) {
                    if (getIdUsuario().equalsIgnoreCase(id)) {
                        var hora1 = TEMPO.pegarData();
                        if (TEMPO.comparaHoras(hora1, dataExpira)) {
                            return codigo.equalsIgnoreCase(codigodb);
                        }
                    }
                }
            }
            resultSet.close();
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }finally {
            SQL.close();
        }
        return false;
    }
}