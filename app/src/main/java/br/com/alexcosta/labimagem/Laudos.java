package br.com.alexcosta.labimagem;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.com.alexcosta.labimagem.database.DataBase;
import br.com.alexcosta.labimagem.dominio.RepositorioEvento;
import br.com.alexcosta.labimagem.dominio.entidades.Evento;

public class Laudos extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lstLaudos;
    private ArrayAdapter<Evento> adpPacientes;
    private ArrayAdapter<Evento> adpEventos;
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioEvento repositorioEvento;
    //private FiltraDados filtraDados;
    private Evento evento;
    private Text txtLaudox;
    private Spinner spnPaciente;

    public static String PacienteSelecionado = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudos);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Atualizando dados", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                repositorioEvento.buscaLaudosNuvemGravaOff(evento);
                adpEventos = repositorioEvento.buscaLaudos(Laudos.this, PacienteSelecionado);
                lstLaudos.setAdapter(adpEventos);

            }
        });

        //Adicionando novo Titulo do formulario
        this.setTitle("Meus Laudos");
        //FIM Adicionando novo Titulo do formulario


        //LÊ no Cookie
//        SharedPreferences prefs = getSharedPreferences(TelaInicial.LABIMAGEM_LOGADO, MODE_PRIVATE);
//        TelaInicial.nome= prefs.getString("nome", null);
//        TelaInicial.login =  prefs.getString("login", null);
        //FIM LÊ no Cookie


        spnPaciente = (Spinner) findViewById(R.id.spinnerPaciente);
        lstLaudos = (ListView) findViewById(R.id.lstLaudos);
        lstLaudos.setOnItemClickListener(this);



        try {
            //Toast.makeText(this, "Atualizando os dados...", Toast.LENGTH_LONG).show();
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            repositorioEvento = new RepositorioEvento(conn);

            //Valida conexao com a internet
            repositorioEvento.isOnline(this);
            if (TelaInicial.onLine == "Sim") {
                repositorioEvento.buscaLaudosNuvemGravaOff(evento);
            } else {
                Toast.makeText(this, "Sem internet no momento.", Toast.LENGTH_LONG).show();
            }

            //lstLaudos.setAdapter(adpPacientes);

//            ArrayList<String> lstPaciente = new ArrayList<String>();
//            String whereClause = "USERNAME = ?";
//            String[] whereArgs = {TelaInicial.login};
//            Cursor cursor = conn.query(TelaInicial.NOME_BD, null, whereClause, whereArgs, null, null, "PACIENTE");
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                do {
//                    try {
//                        //evento.setPaciente(cursor.getString(cursor.getColumnIndex(Evento.PACIENTE)));
//                        lstPaciente.add(cursor.getString(cursor.getColumnIndex(Evento.PACIENTE)));
//                    } catch (Exception e) {
//                        Toast.makeText(this, "Erro ao carregar a lista de pacientes", Toast.LENGTH_LONG).show();
//                    }
//                } while (cursor.moveToNext());
//            } else {
//                Toast.makeText(this, "Não há laudos existente", Toast.LENGTH_LONG).show();
//            }

            String[] lista = new  String[]{"Todos", "Lara", "Pitucha"};
            ArrayAdapter<String> adpPacientes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lista);
            adpPacientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPaciente.setAdapter(adpPacientes);


//            adpPacientes = repositorioEvento.buscaPacientes(this);
//            adpPacientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spnPaciente.setAdapter(adpPacientes);



            //Busca e Lista os Laudos no ListView com Style
//            adpEventos = repositorioEvento.buscaLaudos(this, "");
//            lstLaudos.setAdapter(adpEventos);
            //filtraDados = new FiltraDados(adpEventos);

            adpEventos = repositorioEvento.buscaLaudos(Laudos.this, "Todos");
            lstLaudos.setAdapter(adpEventos);
            //filtraDados = new FiltraDados(adpEventos);


            spnPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    PacienteSelecionado = parent.getSelectedItem().toString();
                        adpEventos = repositorioEvento.buscaLaudos(Laudos.this, parent.getSelectedItem().toString());
                        lstLaudos.setAdapter(adpEventos);
                        //filtraDados = new FiltraDados(adpEventos);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });




        } catch (SQLException ex) {
            Toast.makeText(this, "Erro ao criar o banco off-line: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laudos, menu);
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



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            conn.close();     //banco off line
        }
        if (dataBase != null) {
            dataBase.close(); //banco on line
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpEventos = repositorioEvento.buscaLaudos(Laudos.this, "");
        lstLaudos.setAdapter(adpEventos);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Evento evento = adpEventos.getItem(position);
        Intent it = new Intent(this, AbreLaudo.class);
        it.putExtra(TelaInicial.NOME_BD, evento);  //Passando os parametros para abrir o conteudo na outra pagina mas como é Serializable teve que ir na classe Evento e digitar o IMPLEMENTS SERIALIZABLE = public class Evento implements Serializable {
        startActivityForResult(it, 0); //Para dar Refresh quando voltar na primeira pagina
        //this.finish();

    }
}
