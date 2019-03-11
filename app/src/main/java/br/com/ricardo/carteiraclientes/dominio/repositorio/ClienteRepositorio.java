package br.com.ricardo.carteiraclientes.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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

        return null;
    }

    public Cliente buscarCliente(int codigo){

        return null;
    }
}
