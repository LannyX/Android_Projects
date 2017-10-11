package edu.depaul.csc472.lanny.xul_calculator_hw2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String display;
    private int val1 = -1, val2 = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.text1);
        //set the display to empty string
        display ="";

        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);
        Button add = findViewById(R.id.buttonadd);
        Button b11 = findViewById(R.id.button11);
        Button eq = findViewById(R.id.buttoneq);

        View.OnClickListener listener = new View.OnClickListener () {
            public void onClick (View v) {
                //to display number more than one digit
                display += ((Button) v).getText().toString();
                tv.setText(display);

            }
        };

        View.OnClickListener listenerAdd = new View.OnClickListener () {
            public void onClick(View v) {
                if (val1 >= 0) {
                    val2 = Integer.parseInt((tv.getText().toString()));
                    int sum = val1 + val2;
                    tv.setText(String.valueOf(sum) + "");
                    val1 = sum;
                    val2 = -1;
                    display = "";
                } else {
                    //No number was entered before
                    val1 = Integer.parseInt(tv.getText().toString());
                    display = "";
                    tv.setText(display);
                }
            }
        };

        View.OnClickListener listenerEq = new View.OnClickListener () {
            public void onClick (View v) {
                if (val1 >= 0) {
                    val2 = Integer.parseInt((tv.getText().toString()));
                    int sum = val1 + val2;
                    tv.setText(String.valueOf(sum) + "");
                    val1 = -1;
                    val2 = -1;
                    display = "";
                }
            }
        };

        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);
        b5.setOnClickListener(listener);
        b6.setOnClickListener(listener);
        b7.setOnClickListener(listener);
        b8.setOnClickListener(listener);
        b9.setOnClickListener(listener);
        add.setOnClickListener(listenerAdd);
        b11.setOnClickListener(listener);
        eq.setOnClickListener(listenerEq);


    }

}
