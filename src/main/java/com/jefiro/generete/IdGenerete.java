package com.jefiro.generete;

import java.util.Random;
public class IdGenerete {
    private int aleatorio;

    public IdGenerete() {
    }

    public String id(){
        Random randon = new Random();
        aleatorio = randon.nextInt(999999);
        while (6 > Integer.toString(aleatorio).length()) {
            aleatorio = randon.nextInt(999999);
        }
        return String.valueOf(aleatorio);
    }

    @Override
    public String toString() {
        return String.valueOf(aleatorio);
    }
}
