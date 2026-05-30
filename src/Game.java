public class Game {

    private int[] rolls = new int[21];
    private int currentRoll;

    private int frameCounter;
    private int currentFramePins;
    public void roll(int pins){
        if(pins<0) throw new IllegalArgumentException("pins can't be negative");
        if(pins>10) throw new IllegalArgumentException("You can't roll more than 10 pins");

        if(currentRoll < 18){
            if(frameCounter==0 && pins<10){
                currentFramePins = pins;
                frameCounter++;
            } else if(frameCounter == 1){
                if(currentFramePins + pins > 10)
                    throw new IllegalArgumentException("Sum of pins in the frame can't be more than 10");
                frameCounter = 0;
                currentFramePins = 0;
            } else if(pins==10){
                frameCounter = 0;
                currentFramePins = 0;
            }
        }

        rolls[currentRoll++] = pins;

    }
    public int score(){
        int score = 0;
        int i = 0;

        for (int frames = 0; frames < 10; frames++) {
            if(isStrike(i)){
                score += strikeBonus(i);
                i++;
            }
            else if(isSpare(i)){
                score += spareBonus(i);
                i+=2;
            }
            else{
                score += noBonus(i);
                i+=2;
            }

        }
        return score;
    }

    private int strikeBonus(int i) {
        return 10 + rolls[i + 1] + rolls[i + 2];
    }

    private boolean isStrike(int i) {
        return rolls[i] == 10;
    }

    private int spareBonus(int i) {
        return 10 + rolls[i + 2];
    }

    private int noBonus(int i) {
        return rolls[i] + rolls[i + 1];
    }

    private boolean isSpare(int i) {
        return rolls[i] + rolls[i + 1] == 10;
    }
}
