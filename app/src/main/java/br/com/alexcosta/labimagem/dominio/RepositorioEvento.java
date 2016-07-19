package br.com.alexcosta.labimagem.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.alexcosta.labimagem.EventoArrayAdapter;
import br.com.alexcosta.labimagem.R;
import br.com.alexcosta.labimagem.SpinnerArrayAdapter;
import br.com.alexcosta.labimagem.TelaInicial;
import br.com.alexcosta.labimagem.database.DataBase;
import br.com.alexcosta.labimagem.dominio.entidades.Evento;

/**
 * Created by acostas on 13/07/16.
 */
public class RepositorioEvento extends _Default {
    private EditText edtData;
    private EditText edtVezes;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    public RepositorioEvento(SQLiteDatabase conn) {
        this.conn = conn;
    }



    public SpinnerArrayAdapter buscaPacientes(Context context) {
        //EventoArrayAdapter adpPacientes = new EventoArrayAdapter(context, R.layout.item_eventos);
        SpinnerArrayAdapter adpPacientes = new SpinnerArrayAdapter(context, R.layout.item_spinner);

        String whereClause = "USERNAME = ?";
        String[] whereArgs = {TelaInicial.login};
        Cursor cursor = conn.query(TelaInicial.NOME_BD, null, whereClause, whereArgs, null, null, "PACIENTE");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Evento evento = new Evento();
                evento.setPaciente(cursor.getString(cursor.getColumnIndex(Evento.PACIENTE)));
//                evento.setLaudo(cursor.getString(cursor.getColumnIndex(Evento.LAUDO)));
//                evento.setDescricao(cursor.getString(cursor.getColumnIndex(Evento.DESCRICAO)));
//                evento.setTp_Exame(cursor.getString(cursor.getColumnIndex(Evento.TP_EXAME)));
//                evento.setArquivo(cursor.getString(cursor.getColumnIndex(Evento.ARQUIVO)));
//                evento.setData(new Date(cursor.getLong(cursor.getColumnIndex(Evento.DATA))));
                adpPacientes.add(evento);
            } while (cursor.moveToNext());
        }
        return adpPacientes;
    }


    public EventoArrayAdapter buscaLaudos(Context context, String PacienteSelecionado) {
        EventoArrayAdapter adpEventos = new EventoArrayAdapter(context, R.layout.item_eventos);
        String whereClause;
        String[] whereArgs;
        if (!PacienteSelecionado.equals("Todos")) {
            whereClause = "PACIENTE = ? AND USERNAME = ?";
            whereArgs = new String[]{PacienteSelecionado, TelaInicial.login};
        } else {
            whereClause = "USERNAME = ?";
            whereArgs = new String[]{TelaInicial.login};
        }
        Cursor cursor = conn.query(TelaInicial.NOME_BD, null, whereClause, whereArgs, null, null, "DATA DESC, LAUDO");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Evento evento = new Evento();
                evento.setPaciente(cursor.getString(cursor.getColumnIndex(Evento.PACIENTE)));
                evento.setLaudo(cursor.getString(cursor.getColumnIndex(Evento.LAUDO)));
                evento.setDescricao(cursor.getString(cursor.getColumnIndex(Evento.DESCRICAO)));
                evento.setTp_Exame(cursor.getString(cursor.getColumnIndex(Evento.TP_EXAME)));
                evento.setArquivo(cursor.getString(cursor.getColumnIndex(Evento.ARQUIVO)));
                evento.setData(new Date(cursor.getLong(cursor.getColumnIndex(Evento.DATA))));
                adpEventos.add(evento);
            } while (cursor.moveToNext());
        }
        return adpEventos;
    }



    public void buscaLaudosNuvemGravaOff(Evento evento)  {
        if (TelaInicial.onLine == "Sim") {
            DB db = new DB();
            try {
                ResultSet resultSet = db.select("SELECT * FROM tb_exames WHERE username = '" + TelaInicial.login + "'");
                if (resultSet != null) {
                    while (resultSet.next()) {

                        //Fazer Select p ver se ja tem em OFF
                        String whereClause = "LAUDO = ? AND USERNAME = ? ";
                        String[] whereArgs = {resultSet.getString("laudo"), TelaInicial.login};
                        Cursor cursor = conn.query(TelaInicial.NOME_BD, null, whereClause, whereArgs, null, null, null);

                        if (cursor.getCount() > 0) { //Se for >0 é que já tem em OFF
                            cursor.moveToFirst();
                            do {

                            } while (cursor.moveToNext());
                        } else {
                            ContentValues values = new ContentValues();
                            values.put("PACIENTE", resultSet.getString("paciente"));
                            values.put("LAUDO", resultSet.getString("laudo"));
                            values.put("DESCRICAO", resultSet.getString("descricao"));
                            values.put("TP_EXAME", resultSet.getString("tp_exame"));
                            values.put("ARQUIVO", resultSet.getString("arquivo"));
                            values.put("USERNAME", resultSet.getString("username"));
                            String str = resultSet.getString("data");
                            SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
                            Date data = formatador.parse(str);
                            Long time = new Long(data.getTime());
                            //values.put("DATA", evento.getData().getTime());
                            values.put("DATA", time);
//                            String str = resultSet.getString("data");
//                            SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
//                            Date data = formatador.parse(str);
//                            Long time = new Long(data.getTime());
//                            values.put("DATA", time);
                            conn.insertOrThrow(TelaInicial.NOME_BD, null, values);
                        }


                    }

                }
            } catch (Exception ex) {
                this._mensagem = ex.getMessage();
                this._status = false;
            }
        }
    }



    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            TelaInicial.onLine = "Sim";
            return true;
        } else {
            Toast.makeText(context, "Sem internet no momento.", Toast.LENGTH_LONG).show();
            TelaInicial.onLine = "Não";
            return false;
        }
    }




}
