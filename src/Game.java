public class Game {

    private int[] rolls = new int[21];
    private int currentRoll;
    public void roll(int pins){
        rolls[currentRoll++] += pins;
    }
    public int score(){
        int score = 0;
        int i = 0;

        for (int frames = 0; frames < 10; frames++) {

            if(isSpare(i)){
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
