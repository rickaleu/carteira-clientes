package br.com.ricardo.carteiraclientes.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.carteiraclientes.dominio.entidades.Cliente;

public class ClienteRepositorio {

    private SQLiteDatabase conexao;

    //construtor
    public ClienteRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", cliente.nome);
        contentValues.put("ENDERECO", cliente.endereco);
        contentValues.put("TELEFONE", cliente.telefone);
        contentValues.put("EMAIL", cliente.email);

        conexao.insertOrThrow("CLIENTE", null, contentValues);

    }

    public void excluir(int codigo){

        String[] parametro = new String[1];
        parametro[0] = String.valueOf(codigo);

        conexao.delete("CLIENTE", "CLIENTE = ?", parametro);

    }

    public void alterar(Cliente cliente){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", cliente.nome);
        contentValues.put("ENDERECO", cliente.endereco);
        contentValues.put("TELEFONE", cliente.telefone);
        contentValues.put("EMAIL", cliente.email);

        String[] parametro = new String[1];
        parametro[0] = String.valueOf(cliente.codigo);

        conexao.update("CLIENTE", contentValues, "CLIENTE = ?", parametro);

    }

    public List<Cliente> buscarTodos(){

        List<Cliente> cliente = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, ENDERECO, TELEFONE, EMAIL");
        sql.append("FROM CLIENTE");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do{
                Cliente c = new Cliente();
                c.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                c.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                c.endereco = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
                c.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                c.email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));

                cliente.add(c);

            }while(resultado.moveToNext());
        }

        return cliente;
    }

    public Cliente buscarCliente(int codigo){

        Cliente cliente = new Cliente();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CLIENTE");
        sql.append("WHERE CODIGO = ?");

        String[] parametro = new String[1];
        parametro[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametro);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            cliente.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
            cliente.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
            cliente.endereco = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
            cliente.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
            cliente.email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));

            return cliente;
        }

        return null;
    }
}
