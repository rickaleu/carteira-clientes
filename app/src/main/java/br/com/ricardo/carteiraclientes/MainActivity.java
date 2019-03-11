package br.com.ricardo.carteiraclientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import br.com.ricardo.carteiraclientes.database.DadosOpenHelper;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView listDados;
    private CoordinatorLayout coordinatorLayout;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_main);
        fab = findViewById(R.id.fab);
        listDados = (RecyclerView) findViewById(R.id.listDados);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroCliente.class);
                startActivity(intent);

            }
        });

        criarConexao();
    }

    public void criarConexao(){

        try{

            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            Snackbar.make(coordinatorLayout, R.string.conexao_sucesso, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_ok, null).show();

        }catch (SQLException ex){

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.conexao_erro_titulo);
            alert.setMessage(ex.getMessage());
            alert.setNeutralButton(R.string.action_ok, null);
            alert.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
