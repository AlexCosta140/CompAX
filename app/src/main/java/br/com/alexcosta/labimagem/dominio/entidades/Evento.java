package br.com.alexcosta.labimagem.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by acostas on 13/07/16.
 */
public class Evento implements Serializable {

    //########### Declarando os nomes dos campos do banco de dados para poder colocar o nome de cada coluna da tabela conforme no REPOSITORIOEVENTO em: evento.setId(cursor.getLong(cursor.getColumnIndex(Evento.ID)));
    public static String ID = "_id";
    public static String PACIENTE = "PACIENTE";
    public static String LAUDO = "LAUDO";
    public static String DESCRICAO = "DESCRICAO";
    public static String TP_EXAME = "TP_EXAME";
    public static String ARQUIVO = "ARQUIVO";
    public static String DATA = "DATA";
    //################### FIM ##################

    private long id;
    private String Paciente;
    private String Laudo;
    private String Descricao;
    private String Tp_Exame;
    private String Arquivo;
    private Date Data;

    public Evento(){
        id = 0;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPaciente() {
        return Paciente;
    }
    public void setPaciente(String paciente) {
        Paciente = paciente;
    }

    public String getLaudo() {
        return Laudo;
    }
    public void setLaudo(String laudo) {Laudo = laudo;}

    public String getDescricao() {return Descricao;}
    public void setDescricao(String descricao) {Descricao = descricao;}

    public String getTp_Exame() {return Tp_Exame;}
    public void setTp_Exame(String tp_exame) {Tp_Exame = tp_exame;}

    public String getArquivo() {return Arquivo;}
    public void setArquivo(String arquivo) {Arquivo = arquivo;}

    public Date getData() {
        return Data;
    }
    public void setData(Date data) {
        Data = data;
    }



}
