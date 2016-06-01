package com.example.toshiba.gameapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import GameClasses.CrossGameHandler;

public class CrossGame extends AppCompatActivity {
    public Button readyButton;
    public EditText name_1;
    public EditText name_2;

    private static Context context;

    public void onCreate() {

    }

    public static Context getAppContext() {
        return CrossGame.context;
    }


    public CrossGameHandler gameReady(String name1,String name2){
        ArrayList<Button> Deck = new ArrayList<Button>();
        Button button1 = (Button) findViewById(R.id.button_1);
        Deck.add(button1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Deck.add(button2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Deck.add(button3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Deck.add(button4);
        Button button5 = (Button) findViewById(R.id.button_5);
        Deck.add(button5);
        Button button6 = (Button) findViewById(R.id.button_6);
        Deck.add(button6);
        Button button7 = (Button) findViewById(R.id.button_7);
        Deck.add(button7);
        Button button8 = (Button) findViewById(R.id.button_8);
        Deck.add(button8);
        Button button9 = (Button) findViewById(R.id.button_9);
        Deck.add(button9);
        TextView turnPlayer = (TextView)(this.findViewById(R.id.textViewPlayer));
        TextView turnText = (TextView)(this.findViewById(R.id.textViewTurn));
        return new CrossGameHandler(Deck,name1,name2,this,turnText,turnPlayer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_game);

        readyButton = (Button) findViewById(R.id.readyButton);
        name_1 = (EditText) findViewById(R.id.playerName_1);
        name_2 = (EditText) findViewById(R.id.playerName_2);

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name1 = name_1.getText().toString();
            String name2 = name_2.getText().toString();
            if(((!name1.equals(""))&&(!name2.equals("")))){
                readyButton.setEnabled(false);
                name_1.setEnabled(false);
                name_1.setEnabled(false);
                gameReady(name1,name2).start();
            }else{
                showUserNameTip();
            }
            }
        });
    }

    public void showUserNameTip(){
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setMessage("Моля въведете имената на двамата играчи.");
        dialog.setPositiveButton("Добре", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                int result = 0;
            }
        });
        dialog.show();
    }
}
