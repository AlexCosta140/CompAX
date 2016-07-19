package br.com.alexcosta.labimagem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Pegando imagem da logomarca da empresa
        ImageView imgLogo = (ImageView) findViewById(R.id.imageLogo);
        imgLogo.setImageResource(R.drawable.unimed_logo);

        Button btUnidade = (Button) findViewById(R.id.btUnidade);
        btUnidade.setBottom(R.id.btUnidade);

        Button btConvenio = (Button) findViewById(R.id.btConvenio);
        btConvenio.setBottom(R.id.btConvenio);

        Button btTelefone = (Button) findViewById(R.id.btTelefone);
        btTelefone.setBottom(R.id.btTelefone);

        Button btPreA = (Button) findViewById(R.id.btPreA);
        btPreA.setBottom(R.id.btPreA);

        Button btAgenda = (Button) findViewById(R.id.btAgenda);
        btAgenda.setBottom(R.id.btAgenda);

        Button btResult = (Button) findViewById(R.id.btResult);
        btResult.setBottom(R.id.btResult);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            //Montando e exibindo caixa de dialogo em condição
            final AlertDialog alert = new AlertDialog.Builder(
                    new ContextThemeWrapper(this,android.R.style.Theme_Dialog))
                    .create();
            alert.setTitle("Sair");
            alert.setMessage("Deseja sair do " + TelaInicial.nomeApp + " ? ");
            alert.setIcon(R.drawable.interrogacao);
            alert.setCancelable(false);
            alert.setCanceledOnTouchOutside(false);
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Não",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Apenas não faz nada
                            alert.dismiss();
                        }
                    });
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "Sim",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//                        if (conn != null) {
//                            conn.close();
//                        }
                            finish();
                            //System.exit(-1);

                        }
                    });
            alert.show();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu__home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.empresa) {
            startActivity(new Intent(this, Empresa.class));
        } else if (id == R.id.certificacao) {
            startActivity(new Intent(this, Certificacao.class));

        } else if (id == R.id.corpo_clinico) {
            startActivity(new Intent(this, CorpoClinico.class));

        } else if (id == R.id.compartilhar) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void startLaudos(View view) {
        startActivity(new Intent(this, Laudos.class));
    }

    public void startPAgenda(View view) {
        startActivity(new Intent(this, PAgenda.class));
    }

    public void startAgenda(View view) {
        startActivity(new Intent(this, Agenda.class));
    }

    public void faleconosco(View view) {
        startActivity(new Intent(this, FaleConosco.class));
    }

    public void Convenios(View view) {
        startActivity(new Intent(this, Convenios.class));
    }

    public void Unidades(View view) {
        startActivity(new Intent(this, Unidades.class));
    }
}
