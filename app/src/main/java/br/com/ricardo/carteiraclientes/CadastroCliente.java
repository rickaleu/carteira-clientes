package br.com.ricardo.carteiraclientes;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CadastroCliente extends AppCompatActivity {

    private EditText editNome;
    private EditText editEndereco;
    private EditText editTelefone;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editNome = (EditText) findViewById(R.id.editNome);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        editEmail = (EditText) findViewById(R.id.editEmail);

    }

    public void validaCampos(){

        boolean res = false;

        String nome = editNome.getText().toString();
        String endereco = editEndereco.getText().toString();
        String telefone = editTelefone.getText().toString();
        String email = editEmail.getText().toString();

        if(isCampoVazio(nome)){
            editNome.requestFocus();
            res = true;
        } else if (isCampoVazio(endereco)){
            editEndereco.requestFocus();
            res = true;
        } else if (isCampoVazio(telefone)){
            editTelefone.requestFocus();
            res = true;
        } else if (!isEmailValido(email)){
            editEmail.requestFocus();
            res = true;
        }

        if(res){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.alert_title);
            alert.setMessage(R.string.alert_message);
            alert.setNeutralButton(R.string.alert_button, null);
            alert.show();
        }
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
            validaCampos();
            return true;
        } else if(id == R.id.action_cancelar) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
