package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DadosOpenHelper extends SQLiteOpenHelper {


    public DadosOpenHelper(Context context) {
        super(context, "DADOS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

         db.execSQL("CREATE TABLE IF NOT EXISTS CLIENTE (\n" +
                 "    CODIGO    INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                 "    NOME      VARCHAR(250) NOT NULL DEFAULT (''),\n" +
                 "    ENDERECO  VARCHAR(250) NOT NULL DEFAULT (''),\n" +
                 "    TELEFONE  VARCHAR(200) NOT NULL DEFAULT (''),\n" +
                 "    EMAIL     VARCHAR(30)  NOT NULL DEFAULT('')\n" +
                 ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
