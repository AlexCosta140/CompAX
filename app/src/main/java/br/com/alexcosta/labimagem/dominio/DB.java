package br.com.alexcosta.labimagem.dominio;

/**
 * Created by acostas on 13/07/16.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

//######################
//Classe para BD online
public class DB extends _Default implements Runnable{

    private Connection connNuven;
    //CONEXOES COM POSTGRESQL
//    private String host = "pgsql.alexcosta.com.br";//"192.168.1.10";
//    private String db = "alexcosta"; //Nome da base de dados no KingHost=alexcosta
//    private int port = 5432;
//    private String user = "alexcosta";//"postgres"; //Mesmo nome da base de dados no KingHost=alexcosta
//    private String pass = "PostgrePW";
//    private String url = "jdbc:postgresql://%s:%d/%s";

    private String host = "pgsql.alexcosta.com.br";
    private String db = "alexcosta";
    private int port = 5432;
    private String user = "alexcosta";
    private String pass = "PostgrePW";
    private String url = "jdbc:postgresql://%s:%d/%s";

    public DB() {
        super();
        this.url = String.format(this.url, this.host, this.port, this.db);
        this.conecta();
        this.disconecta();
    }

    @Override
    public void run() {
        //conectar
        try {
            Class.forName("org.postgresql.Driver");
            this.connNuven = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    private void conecta() {
        Thread thread = new Thread(this);
        thread.start();  //Aqui executa o RUN acima
        try {
            thread.join(); //Aqui aguarda a conclusao do methodo RUN
        } catch (Exception e) {
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    private void disconecta() {
        if (this.connNuven != null) {
            try {
                this.connNuven.close();
            } catch (Exception e) {

            } finally {
                this.connNuven = null;
            }
        }
    }

    public ResultSet select(String query) {
        this.conecta();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.connNuven, query).execute().get();
        } catch (Exception e) {
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return  resultSet;
    }

    public ResultSet execute(String query) {
        this.conecta();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.connNuven, query).execute().get();
        } catch (Exception e) {
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return  resultSet;
    }
}
