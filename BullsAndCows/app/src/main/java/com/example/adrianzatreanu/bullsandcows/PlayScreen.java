package com.example.adrianzatreanu.bullsandcows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlayScreen extends AppCompatActivity {

    private EditText inputView;
    private TextView showHint;
    private TextView lastNumber;
    private TextView livesNumber;
    private Button showMeTheAnswer;
    private Button tryMyNumber;
    private String input;
    private String secretNumber;
    private boolean hasWon = false;
    private Random generator = new Random();
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        inputView = (EditText) findViewById(R.id.input_number);
        showHint = (TextView) findViewById(R.id.hint_text);
        lastNumber = (TextView) findViewById(R.id.last_number);
        livesNumber = (TextView) findViewById(R.id.lives_number);
        showMeTheAnswer = (Button) findViewById(R.id.show_answer);
        tryMyNumber = (Button) findViewById(R.id.try_number);
        myContext = this;

        setInputLinesColor(inputView);

        int secretInt = generator.nextInt(1000);
        this.setTitle(EntryScreen.getName().toUpperCase() + " is playing on " +
                EntryScreen.getDifficulty().toUpperCase());

        if(EntryScreen.getDifficulty().equals("easy")){
            livesNumber.setText("9");
            secretInt = generator.nextInt(1000);
        }

        if(EntryScreen.getDifficulty().equals("medium")){
            livesNumber.setText("7");
            secretInt = generator.nextInt(10000);
        }

        if(EntryScreen.getDifficulty().equals("hard")){
            livesNumber.setText("5");
            secretInt = ThreadLocalRandom.current().nextInt(10000, 100000);
        }


        secretNumber = String.valueOf(secretInt);
        showHint.setText("Your number must have " + secretNumber.length() + " digits!");
        tryMyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                input = inputView.getText().toString();
                if(input.length() == secretNumber.length()) {
                    String hint = GameMechanics.getHint(secretNumber, input);
                    showHint.setText(hint);
                    lastNumber.setText(input + " had the result of: " + hint);
                    if(hint.equals("You have guessed the number!")){
                        SharedPreferences sharedPref = myContext.getSharedPreferences("a", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(EntryScreen.getName(), Integer.parseInt(livesNumber.getText().toString()));
                        editor.commit();

                        tryMyNumber.setText(R.string.playAgain);
                        tryMyNumber.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Intent intent = new Intent(PlayScreen.this, EntryScreen.class);
                                PlayScreen.this.startActivity(intent);
                            }
                        });
                        showMeTheAnswer.setText(R.string.guessed);
                        showMeTheAnswer.setEnabled(false);
                    }
                    else {
                        int currentNoOfLives = Integer.parseInt(livesNumber.getText().toString()) - 1;
                        livesNumber.setText(String.valueOf(currentNoOfLives));
                        if (currentNoOfLives == 0) {
                            showMeTheAnswer.setText("The number was " + secretNumber + ".");
                            showHint.setText(R.string.concede);
                        }
                    }
                }
                else{
                    showHint.setText("Your number must have " + secretNumber.length() + " digits!");
                }
            }
        });

        showMeTheAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                showMeTheAnswer.setText("The number was " + secretNumber + ".");
                showHint.setText(R.string.concede);
                livesNumber.setText("0");
                tryMyNumber.setText(R.string.retry);
                tryMyNumber.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(PlayScreen.this, EntryScreen.class);
                        PlayScreen.this.startActivity(intent);
                    }
                });
            }
        });
    }

    public void setInputLinesColor(EditText editText){
        editText.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark),
                PorterDuff.Mode.SRC_IN);
    }
}
