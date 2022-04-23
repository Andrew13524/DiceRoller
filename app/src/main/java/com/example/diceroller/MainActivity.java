package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    Spinner diceSpinner;
    ArrayList<Die> dice;
    ArrayAdapter diceArrayAdapter;
    SwitchCompat rollsSwitchCompat;
    TextView rollResultTextView;
    EditText customEditText;
    Button customButton;
    Button rollButton;

    boolean rollTwice;
    Die selectedDie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice = new ArrayList<>();
        dice.add(new Die("d4", 4));
        dice.add(new Die("d6", 6));
        dice.add(new Die("d8", 8));
        dice.add(new Die("d10", 10));
        dice.add(new Die("d12", 12));
        dice.add(new Die("d20", 20));

        // Initializing view components
        diceSpinner = findViewById(R.id.diceSpinner);
        rollsSwitchCompat = findViewById(R.id.rollsSwitchCompat);
        customEditText = findViewById(R.id.customEditText);
        customButton = findViewById(R.id.customButton);
        rollButton  = findViewById(R.id.rollButton);
        rollResultTextView = findViewById(R.id.rollResultTextView);

        // Initializing listeners
        diceArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dice);
        diceSpinner.setAdapter(diceArrayAdapter);
        rollsSwitchCompat.setOnCheckedChangeListener(this);
        diceSpinner.setOnItemSelectedListener(this);
        customButton.setOnClickListener(this);
        rollButton.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedDie = dice.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.rollButton:
                if(!rollTwice) rollResultTextView.setText(Integer.toString(selectedDie.roll()));
                else rollResultTextView.setText(String.format("%d    %d",selectedDie.roll(),selectedDie.roll()));
                break;
            case R.id.customButton:
                if(customEditText.getText().toString().matches("[0-9]+")){
                    dice.add(new Die("d"+customEditText.getText().toString(), Integer.parseInt(customEditText.getText().toString())));
                    customEditText.setText("");
                }
                else
                    Toast.makeText(this, "Please enter a valid number of sides", Toast.LENGTH_LONG)
                            .show();
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.isChecked()) rollTwice = true;
        else                           rollTwice = false;
    }
}