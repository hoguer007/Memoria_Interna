package com.holamundo.hoguer.memoria_interna;
//librerias que se ocupan
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//clases
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class principal extends ActionBarActivity implements DialogInterface.OnClickListener, View.OnClickListener {
//tipo variables y declaracion de variables variables
    private EditText Texto; private
    Button Guardar, Abrir;
    private static final int READ_BLOCK_SIZE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Texto = (EditText) findViewById(R.id.Archivo);
        Guardar = (Button)findViewById(R.id.Guardar);
        Abrir = (Button)findViewById(R.id.Abrir);
        Guardar.setOnClickListener(this);
        Abrir.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onClick(View v) {


        if (v.equals(Guardar))
        { String str = Texto.getText().toString();

            FileOutputStream fout=null;
            try{

            fout = openFileOutput("archivoTexto.txt", MODE_WORLD_READABLE);

            OutputStreamWriter ows = new OutputStreamWriter(fout);
            ows.write(str);
            ows.flush();
            ows.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(),"El archivo se ha almacenado!!!", Toast.LENGTH_SHORT).show(); Texto.setText("");
        }
        if (v.equals(Abrir)){
            try {
                FileInputStream fin = openFileInput("archivoTexto.txt"); InputStreamReader isr = new InputStreamReader(fin);

                char[] inputBuffer = new char[READ_BLOCK_SIZE	];
                String str= "";

                int charRead; while ((charRead=isr.read(inputBuffer))>0){

                    String strRead = String.copyValueOf(inputBuffer, 0, charRead); str += strRead;
                    inputBuffer = new char [READ_BLOCK_SIZE];
                }

                Texto.setText(str);
                isr.close();
                Toast.makeText(getBaseContext(),"El archivo ha sido cargado", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
    }

