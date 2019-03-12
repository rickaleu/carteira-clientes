package br.com.ricardo.carteiraclientes;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ricardo.carteiraclientes.database.DadosOpenHelper;
import br.com.ricardo.carteiraclientes.dominio.entidades.Cliente;
import br.com.ricardo.carteiraclientes.dominio.repositorio.ClienteRepositorio;


public class CadastroCliente extends AppCompatActivity {

    private EditText editNome;
    private EditText editEndereco;
    private EditText editTelefone;
    private EditText editEmail;
    private ConstraintLayout constraintLayout;

    private ClienteRepositorio clienteRepositorio;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_cliente);
        editNome = (EditText) findViewById(R.id.editNome);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        editEmail = (EditText) findViewById(R.id.editEmail);

        criarConexao();
    }

    public void criarConexao(){

        try{

            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            Snackbar.make(constraintLayout, R.string.conexao_sucesso, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_ok, null).show();

            clienteRepositorio = new ClienteRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.conexao_erro_titulo);
            alert.setMessage(ex.getMessage());
            alert.setNeutralButton(R.string.action_ok, null);
            alert.show();
        }

    }

    private void confirmar(){

        try{
            if(validaCampos()){
                clienteRepositorio.inserir(cliente);
                finish();
            }

        }catch (SQLException ex){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.conexao_erro_titulo);
            alert.setMessage(ex.getMessage());
            alert.setNeutralButton(R.string.action_ok, null);
            alert.show();
        }
    }

    public boolean validaCampos(){

        boolean res = true;

        cliente = new Cliente();
        cliente.nome = editNome.getText().toString();
        cliente.endereco = editEndereco.getText().toString();
        cliente.telefone = editTelefone.getText().toString();
        cliente.email = editEmail.getText().toString();

        if(isCampoVazio(cliente.nome)){
            editNome.requestFocus();
            res = false;
        } else if (isCampoVazio(cliente.endereco)){
            editEndereco.requestFocus();
            res = false;
        } else if (isCampoVazio(cliente.telefone)){
            editTelefone.requestFocus();
            res = false;
        } else if (!isEmailValido(cliente.email)){
            editEmail.requestFocus();
            res = false;
        }

        if(!res){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.alert_title);
            alert.setMessage(R.string.alert_message);
            alert.setNeutralButton(R.string.action_ok, null);
            alert.show();
        }

        return res;
    }

    public boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    public boolean isEmailValido(String email){

        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_clientes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ok) {
            confirmar();
            return true;
        } else if(id == R.id.action_cancelar) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
