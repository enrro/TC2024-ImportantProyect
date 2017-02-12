package com.example.gdaalumno.parcial;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        editText = (EditText) findViewById(R.id.editText2);
        editText.setText(message);
    }

    public void finishActivity(View v){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("message", editText.getText().toString());
            Main2Activity.this.setResult(RESULT_OK, i);
            finish();
    }

}


