package br.com.alexcosta.labimagem.database;

/**
 * Created by acostas on 13/07/16.
 */
public class ScriptSQL {

    public static String criaTabelaLogin() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS TB_LOGIN ( ");
        sqlBuilder.append("_id      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NAME     VARCHAR (255), ");
        sqlBuilder.append("EMAIL    VARCHAR (255), ");
        sqlBuilder.append("USERNAME VARCHAR (30), ");
        sqlBuilder.append("PASSWORD VARCHAR (30) ");
        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }

    public static String criaTabelaPacientes() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS TB_PACIENTES ( ");
        sqlBuilder.append("_id      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NAME     VARCHAR (255), ");
        sqlBuilder.append("USERNAME VARCHAR (255) ");
        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }

    public static String criaTabelaLaudos() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS TB_LAUDOS ( ");
        sqlBuilder.append("_id         INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("PACIENTE    VARCHAR (255), ");
        sqlBuilder.append("LAUDO       VARCHAR (255), ");
        sqlBuilder.append("DESCRICAO   VARCHAR (255), ");
        sqlBuilder.append("TP_EXAME    VARCHAR (2), ");
        sqlBuilder.append("ARQUIVO     VARCHAR (255), ");
        sqlBuilder.append("DATA        DATE, ");
        sqlBuilder.append("USERNAME    VARCHAR (255) ");
        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }

    public static String criaTabelaTpExames() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS TB_TP_EXAMES ( ");
        sqlBuilder.append("_id   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME          VARCHAR (255) ");
        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }

}

