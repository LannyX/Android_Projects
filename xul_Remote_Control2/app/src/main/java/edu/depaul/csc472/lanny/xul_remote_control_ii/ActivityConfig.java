package edu.depaul.csc472.lanny.xul_remote_control_ii;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ActivityConfig extends Activity
        implements CompoundButton.OnCheckedChangeListener {

    String pos = "";
    int currentChan = 1;
    final List<Integer> chanNo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        RadioButton butLeft = findViewById(R.id.confLeft);
        RadioButton butMid = findViewById(R.id.confMid);
        RadioButton butRight = findViewById(R.id.confRight);

        butLeft.setOnCheckedChangeListener(this);
        butMid.setOnCheckedChangeListener(this);
        butRight.setOnCheckedChangeListener(this);

        final TextView tvChan = findViewById(R.id.fav_Chan);
        tvChan.setText(String.valueOf(currentChan));

        Button bt0 =  findViewById(R.id.cbut0);
        Button bt1 =  findViewById(R.id.cbut1);
        Button bt2 =  findViewById(R.id.cbut2);
        Button bt3 =  findViewById(R.id.cbut3);
        Button bt4 =  findViewById(R.id.cbut4);
        Button bt5 =  findViewById(R.id.cbut5);
        Button bt6 =  findViewById(R.id.cbut6);
        Button bt7 =  findViewById(R.id.cbut7);
        Button bt8 =  findViewById(R.id.cbut8);
        Button bt9 =  findViewById(R.id.cbut9);
        Button btPlus =  findViewById(R.id.cchplus);
        Button btMin =  findViewById(R.id.cchminus);
        Button btCancel = findViewById(R.id.fav_Cancel);
        Button btSave = findViewById(R.id.fav_Save);

        //set channel buttons
        View.OnClickListener chanButtons = new View.OnClickListener() {
            public void onClick(View v) {
                int num = Integer.parseInt(((Button) v).getText().toString());
                chanNo.add(num);

                if (chanNo.size() == 3) {
                    String strchanNo = "";
                    for (int number : chanNo) {
                        strchanNo += String.valueOf(number);
                    }
                    currentChan = Integer.parseInt(strchanNo);

                    if (currentChan < 1){
                        currentChan = 1;
                    }

                    tvChan.setText(String.valueOf(currentChan));
                    chanNo.clear();
                }
            }
        };

        //set next channel button
        View.OnClickListener btplus = new View.OnClickListener() {
            public void onClick(View v) {

                currentChan = currentChan + 1;
                if (currentChan < 1) {
                    currentChan = 1;
                }
                if (currentChan > 999) {
                    currentChan = 999;
                }

                tvChan.setText(String.valueOf(currentChan));
                chanNo.clear();
            }
        };
        //set previous channel button
        View.OnClickListener btmin = new View.OnClickListener() {
            public void onClick(View v) {
                currentChan = currentChan - 1;
                if (currentChan < 1) {
                    currentChan = 1;
                }
                if (currentChan > 999) {
                    currentChan = 999;
                }

                tvChan.setText(String.valueOf(currentChan));
                chanNo.clear();
            }
        };

        View.OnClickListener btsave = new View.OnClickListener() {
            public void onClick(View v) {
                if (!pos.equals("Left") && !pos.equals("Middle") && !pos.equals("Right")) {
                    Toast.makeText(ActivityConfig.this, "Select one favorite channel button to begin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etLabel = findViewById(R.id.lableName);
                String lable = String.valueOf(etLabel.getText());
                if (lable == null || lable.isEmpty()){
                    Toast.makeText(ActivityConfig.this, "Input a label name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lable.length() > 4 || lable.length() < 2){
                    Toast.makeText(ActivityConfig.this, " The label must be between 2-4 letters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();

                Favorites favs = new Favorites();
                favs.setPosition(pos);
                favs.setLabel(lable);
                favs.setChan(currentChan);

                intent.putExtra("favorite", favs);
                setResult(RESULT_OK, intent);
                finish();
            }
        };

        bt0.setOnClickListener(chanButtons);
        bt1.setOnClickListener(chanButtons);
        bt2.setOnClickListener(chanButtons);
        bt3.setOnClickListener(chanButtons);
        bt4.setOnClickListener(chanButtons);
        bt5.setOnClickListener(chanButtons);
        bt6.setOnClickListener(chanButtons);
        bt7.setOnClickListener(chanButtons);
        bt8.setOnClickListener(chanButtons);
        bt9.setOnClickListener(chanButtons);
        btPlus.setOnClickListener(btplus);
        btMin.setOnClickListener(btmin);
        btSave.setOnClickListener(btsave);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }

    public void onCheckedChanged(CompoundButton button, boolean isChecked){
        if (isChecked){
            String butText = String.valueOf(button.getText());
            if (butText.equals(getResources().getString(R.string.config_left))){
                pos = "Left";
            }else if (butText.equals(getResources().getString(R.string.config_right))){
                pos = "Right";
            }else if (butText.equals(getResources().getString(R.string.config_mid))){
                pos = "Middle";
            }
        }
    }
}
