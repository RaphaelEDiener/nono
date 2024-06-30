package tests;

import src.game.*;

public class TestGameSafeLoad {

    private static void test_reading_written_empty_game_is_identical() {
        var filename = "test_new_empty";
        var original = new Game(10, 10, filename);
        original.save();
        var read = Game.fromSave(filename);

        assert read.isPresent() : "game could not be read";
        assert original.equals(read.get()) : "Games are not identical";
    }

    private static void test_reading_written_diagonal_game_is_identical() {
        var filename = "test_new_diagonal";
        var original = new Game(10, 10, filename);
        for (int i = 0; i < 10; i++) {
            original = original.confirm().down().right();
        }
        var read = Game.fromSave(filename);

        assert read.isPresent() : "game could not be read";
        assert original.equals(read.get()) : "Games are not identical";
    }

    public static void test() {
        test_reading_written_empty_game_is_identical();
        test_reading_written_diagonal_game_is_identical();
    }
}
