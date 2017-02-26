package com.codingblocks.unittestsinterestcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etPrincial, etInterest, etYears;
    Button btnCalcInterest;
    TextView tvAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //etPrincial = (EditText) findViewById(R.id.etPrincipal);
        etInterest = (EditText) findViewById(R.id.etInterest);
        etYears = (EditText) findViewById(R.id.etYears);
        tvAmount = (TextView) findViewById(R.id.tvAmount);
        btnCalcInterest = (Button) findViewById(R.id.btnCalcInterest);

        btnCalcInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float amount = calcAmount(
                        Float.valueOf(etPrincial.getText().toString()),
                        Float.valueOf(etInterest.getText().toString()),
                        Integer.valueOf(etYears.getText().toString())
                );

                tvAmount.setText(String.valueOf(amount));
            }
        });
    }

    public static float calcAmount (float p, float i, int y) {
        return (((p * i) / 100) * y) + p;
    }
}
