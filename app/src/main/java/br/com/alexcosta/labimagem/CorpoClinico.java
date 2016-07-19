package br.com.alexcosta.labimagem;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class CorpoClinico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corpo_clinico);

        TextView seminternet = (TextView) findViewById(R.id.seminternet);
        WebView browser = (WebView) findViewById(R.id.webViewCorpoClinico);

        //Valida conexao com a internet
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            browser.loadUrl("http://www.alexcosta.com.br/apps/" + TelaInicial.nomeEmpresa + "/corpoclinico.html");
        } else {
            seminternet.setVisibility(View.VISIBLE);
        }
    }

}
