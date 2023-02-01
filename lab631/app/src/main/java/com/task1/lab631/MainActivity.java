package com.task1.lab631;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kotlin.random.Random;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.textView2);
        etInput = (EditText)findViewById(R.id.editTextNumber2);
        bControl = (Button)findViewById(R.id.button2);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        number = Random.Default.nextInt(0, 100);



        if(!pref.contains("appid")) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("appid", "uniq");
            editor.commit();
        }
    }

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int number = 0;

    public void playListener(View v){
        SharedPreferences.Editor editor = pref.edit();
        int tries = 0;
        pref.getInt("tries", tries);
        editor.putInt("tries",tries + 1);
        editor.commit();
        if(etInput.getText().length() < 1) {
            tvInfo.setText(getResources().getString(R.string.error));
            return;
        }

        int input = Integer.parseInt(etInput.getText().toString());

        if(input < 0 || input > 100) {
            tvInfo.setText(getResources().getString(R.string.error));
            return;
        }
        if(number > input)
            tvInfo.setText(getResources().getString(R.string.behind));
        else if(number < input)
            tvInfo.setText(getResources().getString(R.string.ahead));
        else
            tvInfo.setText(getResources().getString(R.string.hit));
    }
}