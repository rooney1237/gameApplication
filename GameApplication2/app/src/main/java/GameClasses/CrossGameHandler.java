package GameClasses;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toshiba.gameapplication.CrossGame;
import com.example.toshiba.gameapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toshiba on 4.4.2016 г..
 */
public class CrossGameHandler {
    private ArrayList<Button> Deck;
    private Player player_1;
    private Player player_2;
    private int turn = 1;
    private Activity activity;
    public TextView turnText;
    public TextView turnPlayer;
    public boolean win=false;

    public void setActivity(Activity activity){
        this.activity = activity;
    }
    public Activity getActivity(){
        return this.activity;
    }

    public CrossGameHandler(ArrayList<Button> Deck, String player1, String player2,Activity activity,TextView turnText,TextView turnPlayer){
        this.player_1 = new Player(player1,"X");
        this.player_2 = new Player(player2,"O");
        this.Deck=Deck;
        this.activity = activity;
        this.turnPlayer = turnPlayer;
        this.turnText =turnText;
    }

    public void turn(){
        turn++;
        if((turn == 9)&&(!win)){
            showDraw();
        }
    }

    public boolean checkForWin(){
        boolean result = false;
        if(checkCombination(1,2,3)||checkCombination(4,5,6)||checkCombination(7,8,9)
        ||checkCombination(7,4,1)||checkCombination(8,5,2)||checkCombination(9,6,3)
        ||checkCombination(1,5,9)||checkCombination(7,5,3)){
                result=true;
        }
        return result;
    }

    public void restartGame(){
        for(int i = 0;i<=8;i++){
            this.Deck.get(i).setEnabled(true);
            this.Deck.get(i).setText("");
            setPlayerOnTurn(1);
            turn = 1;
            start();
        }
    }

    public void enableButtions(){
        this.turnText.setVisibility(View.VISIBLE);
        this.turnPlayer.setText(player_1.getName());
        for(int i = 0;i<=8;i++){
            this.Deck.get(i).setEnabled(true);
            this.Deck.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Player p = determinePlayerOnTurn();
                Button b = (Button) v;
                p.markBlock((Button) v);
                b.setEnabled(false);
                boolean win = checkForWin();
                if (win) {
                    win=true;
                    showWinDialog(p.getName());
                }
                turn();
                }
            });
        }
    }

    public Player determinePlayerOnTurn(){
        Player p = null;
        if(turn%2==1){
            setPlayerOnTurn(1);
            p =  player_1;
            this.turnPlayer.setText(p.getName());
        }else{
            setPlayerOnTurn(2);
            p =  player_2;
            this.turnPlayer.setText(p.getName());
        }
        return p;
    }

    public void start(){
        enableButtions();
    }

    public void setDeck(ArrayList<Button> deck){
        this.Deck=deck;
    }

    public ArrayList<Button> getDeck(){
        return this.Deck;
    }

    public void setPlayerOnTurn(int player){
        if(player == 1){
            this.player_2.setOnTurn(false);
            this.player_1.setOnTurn(true);
        }else{
            this.player_2.setOnTurn(true);
            this.player_1.setOnTurn(false);
        }
    }

    public void showDraw(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage("Равен резултат. ");
        dialog.setPositiveButton("нова игра", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                restartGame();
            }
        });
        dialog.setNegativeButton("край", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                activity.finish();
            }
        });
        dialog.show();
    }

    public void showWinDialog(String Name){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage("Край на играта, победител е: "+ Name);
        dialog.setPositiveButton("нова игра", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                restartGame();
            }
        });
        dialog.setNegativeButton("край", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                activity.finish();
            }
        });
        dialog.show();
    }

    public String getVal(int i){
        return getDeck().get(i-1).getText().toString();
    }

    public boolean checkCombination(int first,int sec,int third){
        boolean result = false;
        if((!getVal(first).equals(""))&&(!getVal(sec).equals(""))&&(!getVal(third).equals(""))) {
            if((getVal(first).equals(getVal(sec)))&&(getVal(first).equals(getVal(third)))){
                result = true;
            };
        }
        return result;
    }


}
