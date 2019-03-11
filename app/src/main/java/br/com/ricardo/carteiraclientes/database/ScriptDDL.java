package br.com.ricardo.carteiraclientes.database;

public class ScriptDDL {

    public static String getCreateTableCliente(){

        String sql = "CREATE TABLE IF NOT EXISTS CLIENTE (\n" +
                "    CODIGO    INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    NOME      VARCHAR(250) NOT NULL DEFAULT (''),\n" +
                "    ENDERECO  VARCHAR(250) NOT NULL DEFAULT (''),\n" +
                "    TELEFONE  VARCHAR(200) NOT NULL DEFAULT (''),\n" +
                "    EMAIL     VARCHAR(30)  NOT NULL DEFAULT('')\n" +
                ");";

        return sql;

    }
}
