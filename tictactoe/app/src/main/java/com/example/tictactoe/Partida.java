package com.example.tictactoe;
import java.util.Random;

public class Partida {
    private final int[][] SECUENCIAS = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public final int dificultad;
    private int[] casillas;
    public int jugador;

    Partida(int dificultad){
        this.dificultad=dificultad;
        casillas = new int[9];
        for (int i =0; i<9; i++){
            casillas[i] = 0;
        }
        jugador=1;
    }

    //comprobar si la casilla esta vacia

    public boolean marca(int casilla){
        if (casillas[casilla]!=0) return false;
        casillas[casilla]= jugador;
        return true;
    }

    public int turno(){
        boolean iguales = true;
        boolean empate = true;
        for (int i = 0; i<SECUENCIAS.length; i++){
            for (int pos: SECUENCIAS[i]){
                if(casillas[pos] != jugador) iguales=false;
                if (casillas[pos]==0) empate=false;
            }
            if (iguales)return jugador;
            iguales=true;
        }
        if (empate) return 3;
        jugador++;
        if (jugador>2) jugador=1;
        return 0;
    }

    public int dosEnRaya(int esteJugador){
        int casilla = -1;
        int cuentaJugador = 0;
        for (int i =0; i < SECUENCIAS.length;i++){
            for (int pos: SECUENCIAS[i]){
                if (casillas[pos] == esteJugador) cuentaJugador++;
                if (casillas[pos]==0) casilla=pos;

            }
            if (cuentaJugador==2&& casilla!=-1) return casilla;
            casilla=-1;
            cuentaJugador=0;
        }
        return -1;
    }


    public int ai(){
        int casilla;
        casilla= dosEnRaya(jugador);
        if (casilla!=-1) return casilla;
        if (dificultad>0){
            int otroJugador = jugador +1;
            if (otroJugador>0)  otroJugador=1;
            casilla = dosEnRaya(otroJugador);
            if (casilla !=-1) return casilla;
        }

        if (dificultad==2){
            if (casillas[0]==0)return 0;
            if (casillas[2]==0)return 2;
            if (casillas[6]==0)return 6;
            if (casillas[8]==0)return 8;
        }
        Random azar =new Random();
        while (true){
            casilla= azar.nextInt(9);
            if(casillas[casilla]==0) return casilla;
        }

    }












}
