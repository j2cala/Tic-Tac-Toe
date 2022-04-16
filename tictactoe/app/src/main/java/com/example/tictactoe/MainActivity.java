package com.example.tictactoe;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Partida partida;
    private int[]CASILLAS;
    private int jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        CASILLAS = new int[9];
        CASILLAS[0]=R.id.a1;
        CASILLAS[1]=R.id.a2;
        CASILLAS[2]=R.id.a3;
        CASILLAS[3]=R.id.b1;
        CASILLAS[4]=R.id.b2;
        CASILLAS[5]=R.id.b3;
        CASILLAS[6]=R.id.c1;
        CASILLAS[7]=R.id.c2;
        CASILLAS[8]=R.id.c3;
    }

    public void nuevoJuego(View vista){
        ImageView imagen;
        for (int casilla : CASILLAS){
            imagen = (ImageView) findViewById(casilla);
            imagen.setImageResource(R.drawable.casilla);
        }
        jugadores = 1;
        if (vista.getId()==R.id.dosJug) jugadores =2;
        RadioGroup config = (RadioGroup) findViewById(R.id.config);
        int id= config.getCheckedRadioButtonId();
        int dificultad = 0;
        if (id == R.id.normal) dificultad=1;
        else if (id==R.id.imposible) dificultad=2;
        partida = new Partida(dificultad);
        ((Button)findViewById(R.id.unJug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.config)).setAlpha(0);
        ((Button)findViewById(R.id.dosJug)).setEnabled(false);

    }

    public void toque(View vista){
        if (partida == null) return;
        int casilla = 0;
        for (int i=0; i<9; i++){
            if (CASILLAS[i]==vista.getId()){
                casilla=i;
                break;
            }
        }
        if (!partida.marca(casilla)) return;
        marca(casilla);
        int resultado= partida.turno();
        if (resultado>0){
            termina(resultado);
            return;
        }
        if (jugadores==2) return;
        casilla = partida.ai();
        partida.marca(casilla);
        marca(casilla);
        resultado = partida.turno();
        if (resultado>0) termina(resultado);

    }

    private void marca(int casilla){
        ImageView imagen;
        imagen = (ImageView) findViewById(CASILLAS[casilla]);
        if (partida.jugador==1) imagen.setImageResource(R.drawable.circle);
        else imagen.setImageResource(R.drawable.aspa);
    }

    private void termina(int resultado){
        String m;
        if (resultado == 1) m = getString(R.string.circulos);
        else if (resultado==2) m= getString(R.string.aspas);
        else m = getString(R.string.empate);
        Toast toast = Toast.makeText(this, m , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER , 0 , 0);
        toast.show();
        partida=null;
        ((Button)findViewById(R.id.unJug)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.config)).setAlpha(1);
        ((Button)findViewById(R.id.dosJug)).setEnabled(true);


    }


}