package GameClasses;

import android.widget.Button;

/**
 * Created by toshiba on 4.4.2016 г..
 */
public class Player {
    private String name;
    private String sign;
    private boolean onTurn;

    public void markBlock(Button button){
        button.setText(this.sign);
        if((button.getText().toString()=="")&&(this.onTurn)){

        }
    }

    public void setOnTurn(boolean state){
        this.onTurn = state;
    }

    public boolean isOnTurn(){
        return this.onTurn;
    }

    public Player(String name,String sign){
        this.sign = sign;
        this.name = name;
    }

    public void setSign(String sign){
        this.sign=sign;
    }

    public String getSign(){
        return this.sign;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
