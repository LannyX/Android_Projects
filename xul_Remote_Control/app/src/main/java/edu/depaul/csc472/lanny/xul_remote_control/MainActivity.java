package edu.depaul.csc472.lanny.xul_remote_control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
        implements CompoundButton.OnCheckedChangeListener {

    int fav1 = 101;
    int fav2 = 233;
    int fav3 = 300;
    int currentChan = 187;
    final List<Integer> chanNo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvPower = findViewById(R.id.tvpower);
        tvPower.setText(R.string.tv_off);
        final TextView tvVol = findViewById(R.id.tvvolume);
        tvVol.setText(R.string.tv_vol_50);
        final TextView tvChan = findViewById(R.id.tvchannel);
        tvChan.setText(String.valueOf(currentChan));

        powerSwitch(false);

        Switch switch1 = findViewById(R.id.powerswitch);
        switch1.setChecked(false);
        switch1.setOnCheckedChangeListener(this);

        final SeekBar seekBar1 = findViewById(R.id.seekbar);
        seekBar1.setProgress(50);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvVol.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //set buttons
        Button bt0 =  findViewById(R.id.but0);
        Button bt1 =  findViewById(R.id.but1);
        Button bt2 =  findViewById(R.id.but2);
        Button bt3 =  findViewById(R.id.but3);
        Button bt4 =  findViewById(R.id.but4);
        Button bt5 =  findViewById(R.id.but5);
        Button bt6 =  findViewById(R.id.but6);
        Button bt7 =  findViewById(R.id.but7);
        Button bt8 =  findViewById(R.id.but8);
        Button bt9 =  findViewById(R.id.but9);
        Button btPlus =  findViewById(R.id.chplus);
        Button btMin =  findViewById(R.id.chminus);
        Button btFav1 =  findViewById(R.id.fav1);
        Button btFav2 =  findViewById(R.id.fav2);
        Button btFav3 =  findViewById(R.id.fav3);

        //set channel buttions
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

                final TextView tvChan = findViewById(R.id.tvchannel);
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

                final TextView tvChan = findViewById(R.id.tvchannel);
                tvChan.setText(String.valueOf(currentChan));
                chanNo.clear();
            }
        };
        //set favorite channel button
        View.OnClickListener btfav1 = new View.OnClickListener() {
            public void onClick(View v) {
                currentChan = fav1;

                final TextView tvChan = findViewById(R.id.tvchannel);
                tvChan.setText(String.valueOf(currentChan));
                chanNo.clear();
            }
        };
        //set favorite channel button
        View.OnClickListener btfav2 = new View.OnClickListener() {
            public void onClick(View v) {
                currentChan = fav2;

                final TextView tvChan = findViewById(R.id.tvchannel);
                tvChan.setText(String.valueOf(currentChan));
                chanNo.clear();
            }
        };
        //set favorite channel button
        View.OnClickListener btfav3 = new View.OnClickListener() {
            public void onClick(View v) {
                currentChan = fav3;

                final TextView tvChan = findViewById(R.id.tvchannel);
                tvChan.setText(String.valueOf(currentChan));
                chanNo.clear();

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
        btFav1.setOnClickListener(btfav1);
        btFav2.setOnClickListener(btfav2);
        btFav3.setOnClickListener(btfav3);

    }

    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        powerSwitch(isChecked);
    }

    private void powerSwitch(boolean isChecked) {
        final TextView tvPower = findViewById(R.id.tvpower);
        tvPower.setText((isChecked ? R.string.tv_on : R.string.tv_off));

        SeekBar seekBar1 = findViewById(R.id.seekbar);
        seekBar1.setEnabled(isChecked);

        Button bt0 =  findViewById(R.id.but0);
        Button bt1 =  findViewById(R.id.but1);
        Button bt2 =  findViewById(R.id.but2);
        Button bt3 =  findViewById(R.id.but3);
        Button bt4 =  findViewById(R.id.but4);
        Button bt5 =  findViewById(R.id.but5);
        Button bt6 =  findViewById(R.id.but6);
        Button bt7 =  findViewById(R.id.but7);
        Button bt8 =  findViewById(R.id.but8);
        Button bt9 =  findViewById(R.id.but9);
        Button btPlus =  findViewById(R.id.chplus);
        Button btMin =  findViewById(R.id.chminus);
        Button btFav1 =  findViewById(R.id.fav1);
        Button btFav2 =  findViewById(R.id.fav2);
        Button btFav3 =  findViewById(R.id.fav3);

        bt0.setEnabled(isChecked);
        bt1.setEnabled(isChecked);
        bt2.setEnabled(isChecked);
        bt3.setEnabled(isChecked);
        bt4.setEnabled(isChecked);
        bt5.setEnabled(isChecked);
        bt6.setEnabled(isChecked);
        bt7.setEnabled(isChecked);
        bt8.setEnabled(isChecked);
        bt9.setEnabled(isChecked);
        btPlus.setEnabled(isChecked);
        btMin.setEnabled(isChecked);
        btFav1.setEnabled(isChecked);
        btFav2.setEnabled(isChecked);
        btFav3.setEnabled(isChecked);
        }

    }

