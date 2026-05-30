import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTests {
    private Game game;

    @BeforeEach
    protected void initialize() {
        game = new Game();
    }

    @Test
    void testWorstGame() {
        rollMany(20, 0);
        assertEquals(0, game.score());
    }

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++) {
            game.roll(pins);
        }
    }

    @Test
    void testRollOnlyOnes() {
        rollMany(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    void testSpare() {
        rollSpare();
        game.roll(4);
        rollMany(17, 0);
        assertEquals(18, game.score());
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    @Test
    void testStrike() {
        rollStrike();
        game.roll(3);
        game.roll(5);
        rollMany(17, 0);
        assertEquals(26, game.score());
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    void testPerfectGame() {
        rollMany(20, 10);
        assertEquals(300, game.score());
    }

    @Test
    void testNegativePins() {
        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
    }

    @Test
    void testMoreThanTenPins(){
        assertThrows(IllegalArgumentException.class, () -> game.roll(11));
    }

    @Test
    void testMoreThanTenPinsInOneFrame(){
        assertThrows(IllegalArgumentException.class, () -> {
            game.roll(5);
            game.roll(6);
        });
    }

}
