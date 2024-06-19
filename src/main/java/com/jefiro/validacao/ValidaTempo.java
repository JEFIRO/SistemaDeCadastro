package com.jefiro.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ValidaTempo {
    private String data;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public String pegarData(){
        this.data = LocalDateTime.now().format(formatter);
        return data;
    }
    public String expiraData() {
        LocalDateTime dataAtual = LocalDateTime.parse(data, formatter);
        LocalDateTime dataExpira = dataAtual.plusMinutes(15);
        return dataExpira.format(formatter);
    }
    public Boolean comparaHoras(String horaAtual, String horaExpirar){

        LocalTime hora1 = LocalTime.parse(horaAtual, formatter);
        LocalTime hora2 = LocalTime.parse(horaExpirar, formatter);

        if (hora1.isBefore(hora2)){
            return true;
        } else if (hora1.isAfter(hora2) || hora1.equals(hora2)) {
            return false;
        }else {
            return false;
        }
    }

}
