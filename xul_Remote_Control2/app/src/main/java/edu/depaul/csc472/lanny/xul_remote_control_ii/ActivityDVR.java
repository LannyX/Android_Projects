package edu.depaul.csc472.lanny.xul_remote_control_ii;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityDVR extends Activity
        implements CompoundButton.OnCheckedChangeListener {

    private enum dvrState{
        s, play, pau, ff, fr, re
    }

    private dvrState curState = dvrState.s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvr);

        TextView txtDvrPower = findViewById(R.id.dvrpower);
        txtDvrPower.setText(R.string.dvr_on);

        Switch switchDvrPower = findViewById(R.id.dvrswitch);
        switchDvrPower.setChecked(true);
        switchDvrPower.setOnCheckedChangeListener(this);

        TextView txtDvrState = findViewById(R.id.dvrstate);
        txtDvrState.setText(R.string.dvr_stop);

        Button btPlay = findViewById(R.id.butPlay);
        Button btStop = findViewById(R.id.butStop);
        Button btPause = findViewById(R.id.butPause);
        Button btFF = findViewById(R.id.butFF);
        Button btFR = findViewById(R.id.butFR);
        Button btRecord = findViewById(R.id.butRecord);
        Button btToTv = findViewById(R.id.totv);

        View.OnClickListener btplay = new View.OnClickListener() {
            public void onClick(View v) {
                setState(dvrState.play);
            }
        };

        View.OnClickListener btstop = new View.OnClickListener() {
            public void onClick(View v) {
                setState(dvrState.s);
            }
        };

        View.OnClickListener btpau = new View.OnClickListener() {
            public void onClick(View v) {
                setState(dvrState.pau);
            }
        };

        View.OnClickListener btff = new View.OnClickListener() {
            public void onClick(View v) {
                setState(dvrState.ff);
            }
        };

        View.OnClickListener btfr = new View.OnClickListener() {
            public void onClick(View v) {
                setState(dvrState.fr);
            }
        };

        View.OnClickListener btre = new View.OnClickListener() {
            public void onClick(View v) {
                setState(dvrState.re);
            }
        };

        btPlay.setOnClickListener(btplay);
        btStop.setOnClickListener(btstop);
        btPause.setOnClickListener(btpau);
        btFF.setOnClickListener(btff);
        btFR.setOnClickListener(btfr);
        btRecord.setOnClickListener(btre);

        btToTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void setState(dvrState state){
        TextView txtDvrState = findViewById(R.id.dvrstate);

        switch (state){
            case s:
                curState = dvrState.s;
                txtDvrState.setText(R.string.dvr_stop);
                break;
            case play:
                if (curState == dvrState.play) {
                    return;
                }else if (curState == dvrState.re){
                    Toast.makeText(this, "The DVR is recording, please stop it first!", Toast.LENGTH_SHORT).show();
                }else{
                    curState = dvrState.play;
                    txtDvrState.setText(R.string.dvr_play);
                }
                break;
            case pau:
                if (curState == dvrState.pau) {
                    return;
                }else if (curState == dvrState.play){
                    curState = dvrState.pau;
                    txtDvrState.setText(R.string.dvr_pause);
                }else if (curState == dvrState.re){
                    Toast.makeText(this, "Can't pause, the DVR is recording!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Can't pause, the DVR is not playing!", Toast.LENGTH_SHORT).show();
                }
                break;
            case ff:
                if (curState == dvrState.ff){
                    return;
                }else if (curState == dvrState.play){
                    curState = dvrState.ff;
                    txtDvrState.setText(R.string.dvr_ff);
                }else if (curState == dvrState.re){
                    Toast.makeText(this, "Can't fast forward, the DVR is recording!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Can't fast forward, the DVR is not playing!", Toast.LENGTH_SHORT).show();
                }
                break;
            case fr:
                if (curState == dvrState.fr){
                    return;
                }else if (curState == dvrState.play){
                    curState = dvrState.fr;
                    txtDvrState.setText(R.string.dvr_fr);
                }else if (curState == dvrState.re){
                    Toast.makeText(this, "Can't fast rewind, the DVR is recording!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Can't fast rewind, the DVR is not playing!", Toast.LENGTH_SHORT).show();
                }
                break;
            case re:
                if (curState == dvrState.re){
                    return;
                }else if (curState == dvrState.s){
                    curState = dvrState.re;
                    txtDvrState.setText(R.string.dvr_re);
                }else{
                    Toast.makeText(this, "Can't record, the DVR is not stopped!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        final TextView tvPower = findViewById(R.id.dvrpower);
        tvPower.setText((isChecked ? R.string.dvr_on : R.string.dvr_off));

        if (isChecked){
            TextView txtDvrState = findViewById(R.id.dvrstate);
            curState = dvrState.s;
            txtDvrState.setText(R.string.dvr_stop);
        }

        Button btPlay = findViewById(R.id.butPlay);
        Button btStop = findViewById(R.id.butStop);
        Button btPause = findViewById(R.id.butPause);
        Button btFF = findViewById(R.id.butFF);
        Button btFR = findViewById(R.id.butFR);
        Button btRecord = findViewById(R.id.butRecord);

        btPlay.setEnabled(isChecked);
        btStop.setEnabled(isChecked);
        btPause.setEnabled(isChecked);
        btFF.setEnabled(isChecked);
        btFR.setEnabled(isChecked);
        btRecord.setEnabled(isChecked);

    }


}
