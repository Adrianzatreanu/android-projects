package com.example.adrianzatreanu.bullsandcows;

/**
 * Created by adrianzatreanu on 10/11/2016.
 */

public class GameMechanics {
    public static String getHint(String secret, String guess) {
        if(secret.equals(guess)){
            return "You have guessed the number!";
        }
        int noBulls = 0;
        int noCows = 0;
        char[] secretString = secret.toCharArray();
        char[] guessString = guess.toCharArray();
        for(int i = 0; i < secretString.length; i++){
            if(secretString[i] == guessString[i]){
                noBulls += 1;
                secretString[i] = 'x';
                guessString[i] = 'j';
            }
        }
        for(int i = 0; i < secretString.length; i++){
            for(int j = 0; j < guessString.length; j++){
                if(secretString[j] == guessString[i]){
                    noCows += 1;
                    secretString[j] = 'z';
                    break;
                }
            }
        }
        String finalString = Integer.toString(noBulls) + " bulls and " +
                Integer.toString(noCows) + " cows. Try again!";
        return finalString;
    }
}
