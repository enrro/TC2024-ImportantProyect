package com.example.gdaalumno.parcial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "test";

    private static String PROPERTY_NAME = "exam.xml";
    private Properties properties;
    public EditText editText;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        File file = new File(getFilesDir(), PROPERTY_NAME);
        properties = new Properties();

        try{
            if (file.exists()){
                FileInputStream fis = openFileInput(PROPERTY_NAME);
                properties.loadFromXML(fis);
                fis.close();
                textView.setText(properties.getProperty("demo"));


            }else{

            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }


    public void saveProperty(View v){
        properties.setProperty("demo", editText.getText().toString());
        Toast.makeText(this, "PROPERTY SET",Toast.LENGTH_SHORT ).show();
    }

    public void loadProperty(View v){
        editText.setText(properties.getProperty("demo"));
        Toast.makeText(this, "PROPERTY LOADED",Toast.LENGTH_SHORT ).show();
    }

    public void saveToStorage(View v){
        try{
            saveProperties();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    private void saveProperties() throws IOException{
        FileOutputStream fos = openFileOutput(PROPERTY_NAME, 0);
        properties.storeToXML(fos, null);
        fos.close();

        Toast.makeText(this, "FILE SAVED TO STORAGE", Toast.LENGTH_SHORT).show();
    }

    public void changeActivityButton(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_MESSAGE, editText.getText().toString());
        startActivityForResult(intent,0);
    }

    public void onActivityResult(int RequestCode, int ResultCode, Intent i){
        if (RequestCode == 0 && ResultCode == Activity.RESULT_OK){
            Toast.makeText(this, i.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }
    }

}


