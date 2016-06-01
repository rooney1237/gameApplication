package com.example.toshiba.gameapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import GameClasses.HangManGameHandler;

public class HangMan extends AppCompatActivity {

    public TextView wordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);
        this.wordBox = (TextView) findViewById(R.id.wordBox) ;
        HangManGameHandler game = new HangManGameHandler(this,wordBox);
    }
}
