package GameClasses;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.gameapplication.HangMan;
import com.example.toshiba.gameapplication.R;

/**
 * Created by toshiba on 5.4.2016 г..
 */
public class HangManGameHandler {
    private char[] word;
    private int remaining;
    private int endGame;
    private boolean started=false;
    private TextView wordbox;
    private Activity activity;

    public void searchForCharacter(char character){
        if(!checkIfalreadyTyped(String.valueOf(character))) {
            for (int i = 0; i < this.word.length; i++) {
                String characterFromArray = String.valueOf((word[i]));
                if (String.valueOf(character).equals(characterFromArray)) {
                    String wordboxtext = wordbox.getText().toString();
                    StringBuilder builder = new StringBuilder(wordboxtext);
                    builder.setCharAt(i, character);
                    wordbox.setText(builder);
                    boolean hasMoreUnknownChars = wordbox.getText().toString().contains("_");
                    if (!hasMoreUnknownChars) {
                        endGame = 1;
                        showfinish(1);
                    }
                }
            }
            if (remaining > 1) {
                TextView text = (TextView) activity.findViewById(R.id.usedChars);
                text.setText(text.getText().toString() + String.valueOf(character) + ",");
            } else {
                TextView text = (TextView) activity.findViewById(R.id.usedChars);
                text.setText(text.getText().toString() + String.valueOf(character));
            }
            decreaseRemaining();
        }
    }

    public boolean checkIfalreadyTyped(String character){
        TextView text = (TextView) activity.findViewById(R.id.usedChars);
        if(text.getText().toString().contains(character)){
            return true;
        }
        return false;
    }
    public void showfinish(int State) {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(activity);
        switch (State) {
            case 1:
                dialog.setMessage("Поздравления, успяхте да познаете думата на противника.");
                break;
            case 0:
                dialog.setMessage("Дума:" + new String(word));
                break;
        }
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

    private void restartGame() {
        showMainDialog();
        TextView remainingText = (TextView)activity.findViewById(R.id.remaining);
        remainingText.setText("Оставащи опити:");
        TextView text = (TextView) activity.findViewById(R.id.usedChars);
        text.setText("Използвани символи");
    }

    public void setWord(String text){
        char[] array = new char[text.length()];
        for(int i = 0; i < text.length(); i++) {
            String firstChar = String.valueOf(text.charAt(0));
            String lastChar = String.valueOf(text.charAt(text.length() - 1));
            char c = text.charAt(i);
            array[i] = c;
            if (i == 0) {
                wordbox.setText(String.valueOf(c));
            } else if ((i == (text.length() - 1))) {
                String part = wordbox.getText().toString();
                part = part + String.valueOf(c);
                wordbox.setText(part);
            } else {
                String part = wordbox.getText().toString();
                String currentChar = String.valueOf(text.charAt(i));
                if (currentChar.equals(firstChar)) {
                    part = part + firstChar;
                    wordbox.setText(part);
                } else if (currentChar.equals(lastChar)) {
                    part = part + lastChar;
                    wordbox.setText(part);
                } else {
                    part = part + "_";
                    wordbox.setText(part);
                }
            }
        }
        this.word = array;
    }

    public HangManGameHandler(final Activity activity,TextView wordbox){
        this.activity = activity;
        this.wordbox = wordbox;
        showMainDialog();
        final EditText edit = (EditText)activity.findViewById(R.id.addChar);
        Button buttonAdd = (Button)activity.findViewById(R.id.addCharButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = edit.getText().toString();
                edit.setText("");
                if ((input.length() > 1) || (input.length() == 0)) {
                    Toast.makeText(activity.getApplicationContext(), "Въведеният символ трябва да бъде точно един на брой!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity.getApplicationContext(), String.valueOf(input.toCharArray()[0]), Toast.LENGTH_SHORT).show();
                    searchForCharacter(input.toCharArray()[0]);

                    if(remaining==0){
                        endGame = 0;
                        showfinish(0);
                    }
                }
            }
        });
    }

    public void decreaseRemaining(){
        if(remaining>=1) {
            remaining = remaining - 1;
            TextView remainingText = (TextView)activity.findViewById(R.id.remaining);
            remainingText.setText("Оставащи опити:"+String.valueOf(remaining));
        }
    }

    protected void showMainDialog()
    {
        LinearLayout lila1= new LinearLayout(activity);
        lila1.setOrientation(LinearLayout.VERTICAL);
        final EditText input = new EditText(activity);
        input.setSingleLine(true);
        lila1.addView(input);
        final AlertDialog d = new AlertDialog.Builder(activity)
                .setView(lila1)
                .setTitle("Въведете думa за отгатване")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .create();
        d.setCancelable(false);
        d.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        started = true;
                        String inputword;
                        inputword = input.getText().toString();
                        if (inputword.length() < 4) {
                            Toast.makeText(activity.getApplicationContext(), "Въведената дума трябва да има най-малко 4 символа.", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            setWord(inputword);
                            remaining = inputword.length() + 3;
                            TextView remainingText = (TextView) activity.findViewById(R.id.remaining);
                            String text = remainingText.getText().toString() + String.valueOf(remaining);
                            remainingText.setText(text);
                            d.dismiss();
                        }
                    }
                });
            }
        });
        d.show();
    }
}
