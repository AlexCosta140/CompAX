package br.com.alexcosta.labimagem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TelaInicial extends AppCompatActivity {
    public static final String NOME_BD = "TB_LAUDOS";
    public static final String LABIMAGEM_LOGADO = "INFORMACOES_LOGIN_AUTOMATICO";
    public static String nome;
    public static String login;
    public static String onLine;
    public static String nomeApp;
    public static String nomeEmpresa;
    public static String empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //LÊ no Cookie do Logado anteriormente
        SharedPreferences prefs = getSharedPreferences(LABIMAGEM_LOGADO, MODE_PRIVATE);
        nome= prefs.getString("nome", null);
        login= prefs.getString("login", null);
        //FIM LÊ no Cookie

        nomeApp = "LabImagem";
        nomeEmpresa = "Unimed";



        login="Alex";

        Thread thread = new Thread(new Timer());
        thread.start();
    }

    private class Timer implements Runnable {
        @Override
        public void run() {
            for (int i=0; i>=0; i--) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
                if (i==0) {
                    chamaOutraClasse();
                }
            }
        }
    }

    public void chamaOutraClasse() {
        //Validando os dados do Cookie
        if (login!= null) {
            startActivity(new Intent(this, Home.class));
            this.finish();
        } else {
            Intent outraActivity = new Intent(this, Login.class);
            startActivity(outraActivity);
            this.finish();
        }
    }

}