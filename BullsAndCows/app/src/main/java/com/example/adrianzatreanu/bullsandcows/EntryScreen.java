package com.example.adrianzatreanu.bullsandcows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EntryScreen extends AppCompatActivity {

    private TextView rulesText;
    private EditText inputName;
    private Button confirmName;
    private Button startGame;
    private static String difficulty;
    private static String name;
    private Button easyButton;
    private Button normalButton;
    private Button hardButton;
    private Button scoresButton;
    private Boolean hasDifficulty;
    private Boolean hasName;

    public static String getDifficulty(){
        return difficulty;
    }

    public static String getName(){
        return name;
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor editor = getSharedPreferences("Bulls", MODE_PRIVATE).edit();
        editor.putString("username", inputName.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_screen);

        rulesText = (TextView) findViewById(R.id.textView);
        inputName = (EditText) findViewById(R.id.inputName);
        startGame = (Button) findViewById(R.id.newButton);
        easyButton = (Button) findViewById(R.id.easy);
        normalButton = (Button) findViewById(R.id.normal);
        hardButton = (Button) findViewById(R.id.hard);
        confirmName = (Button) findViewById(R.id.nameButton);
        scoresButton = (Button) findViewById(R.id.high_scores_button);

        inputName.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN);

        scoresButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(EntryScreen.this, ScoresTable.class);
                EntryScreen.this.startActivity(intent);
            }
        });

        // start with start button disabled
        startGame.setEnabled(false);
        hasDifficulty = false;
        hasName = false;
        confirmName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name = inputName.getText().toString();
                confirmName.setText("Welcome, " + name + "!");
                confirmName.setEnabled(false);
                hasName = true;
                if(hasDifficulty){
                    startGame.setEnabled(true);
                }
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                normalButton.setEnabled(false);
                hardButton.setEnabled(false);
                difficulty = "easy";
                hasDifficulty = true;
                if(hasName){
                    startGame.setEnabled(true);
                }
            }
        });

        normalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                easyButton.setEnabled(false);
                hardButton.setEnabled(false);
                difficulty = "medium";
                hasDifficulty = true;
                if(hasName){
                    startGame.setEnabled(true);
                }
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                normalButton.setEnabled(false);
                easyButton.setEnabled(false);
                difficulty = "hard";
                hasDifficulty = true;
                if(hasName){
                    startGame.setEnabled(true);
                }
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(EntryScreen.this, PlayScreen.class);
                EntryScreen.this.startActivity(intent);
            }
        });
    }
}
