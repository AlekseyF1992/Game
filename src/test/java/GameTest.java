package ru.netology.javaqa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

public class GameTest {

    // Тесты для метода register

    @Test
    public void registerNewPlayerAddsToCollection() {
        Game game = new Game();
        Player player = new Player(1, "Иван", 100);

        game.register(player);

        List<Player> registered = game.getRegisteredPlayers();
        Assertions.assertEquals(1, registered.size());
        Assertions.assertEquals(player, registered.get(0));
    }

    @Test
    public void registerSamePlayerTwiceDoesNotDuplicate() {
        Game game = new Game();
        Player player = new Player(1, "Иван", 100);

        game.register(player);
        game.register(player); // повторная регистрация

        List<Player> registered = game.getRegisteredPlayers();
        Assertions.assertEquals(1, registered.size());
    }

    @Test
    public void registerMultipleDifferentPlayers() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(2, "Мария", 150);
        Player p3 = new Player(3, "Пётр", 80);

        game.register(p1);
        game.register(p2);
        game.register(p3);

        List<Player> registered = game.getRegisteredPlayers();
        Assertions.assertEquals(3, registered.size());
        Assertions.assertTrue(registered.contains(p1));
        Assertions.assertTrue(registered.contains(p2));
        Assertions.assertTrue(registered.contains(p3));
    }

    // Тесты для метода round: победа первого игрока

    @Test
    public void roundFirstPlayerWinsByHigherStrength() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 150);
        Player p2 = new Player(2, "Мария", 100);

        game.register(p1);
        game.register(p2);

        int result = game.round("Иван", "Мария");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void roundFirstPlayerWinsWithMaxStrengthDifference() {
        Game game = new Game();
        Player p1 = new Player(1, "Сильный", 1000);
        Player p2 = new Player(2, "Слабый", 1);

        game.register(p1);
        game.register(p2);

        int result = game.round("Сильный", "Слабый");
        Assertions.assertEquals(1, result);
    }

    // Тесты для метода round: победа второго игрока

    @Test
    public void roundSecondPlayerWinsByHigherStrength() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(2, "Мария", 150);

        game.register(p1);
        game.register(p2);

        int result = game.round("Иван", "Мария");
        Assertions.assertEquals(2, result);
    }

    @Test
    public void roundSecondPlayerWinsWithMaxStrengthDifference() {
        Game game = new Game();
        Player p1 = new Player(1, "Слабый", 1);
        Player p2 = new Player(2, "Сильный", 1000);

        game.register(p1);
        game.register(p2);

        int result = game.round("Слабый", "Сильный");
        Assertions.assertEquals(2, result);
    }

    // Тесты для метода round: ничья

    @Test
    public void roundDrawWithEqualStrength() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(2, "Мария", 100);

        game.register(p1);
        game.register(p2);

        int result = game.round("Иван", "Мария");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void roundDrawWithZeroStrength() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 0);
        Player p2 = new Player(2, "Мария", 0);

        game.register(p1);
        game.register(p2);

        int result = game.round("Иван", "Мария");
        Assertions.assertEquals(0, result);
    }

    // Тесты для метода round: исключения NotRegisteredException

    @Test
    public void roundThrowsExceptionWhenFirstPlayerNotRegistered() {
        Game game = new Game();
        Player p2 = new Player(2, "Мария", 100);
        game.register(p2);

        NotRegisteredException exception = Assertions.assertThrows(
                NotRegisteredException.class,
                () -> game.round("Иван", "Мария")
        );
        Assertions.assertEquals("Player 'Иван' is not registered", exception.getMessage());
    }

    @Test
    public void roundThrowsExceptionWhenSecondPlayerNotRegistered() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 100);
        game.register(p1);

        NotRegisteredException exception = Assertions.assertThrows(
                NotRegisteredException.class,
                () -> game.round("Иван", "Мария")
        );
        Assertions.assertEquals("Player 'Мария' is not registered", exception.getMessage());
    }

    @Test
    public void roundThrowsExceptionWhenBothPlayersNotRegistered() {
        Game game = new Game();

        NotRegisteredException exception = Assertions.assertThrows(
                NotRegisteredException.class,
                () -> game.round("Иван", "Мария")
        );
        Assertions.assertEquals("Player 'Иван' is not registered", exception.getMessage());
    }

    // Граничные и тесты

    @Test
    public void roundWithSamePlayerNameTwice() {
        Game game = new Game();
        Player p = new Player(1, "Иван", 100);
        game.register(p);

        int result = game.round("Иван", "Иван");
        Assertions.assertEquals(0, result); // ничья с самим собой
    }

    @Test
    public void roundWithCaseSensitiveNames() {
        Game game = new Game();
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(2, "иван", 150); // другая регистровая запись

        game.register(p1);
        // p2 не зарегистрирован

        Assertions.assertThrows(
                NotRegisteredException.class,
                () -> game.round("Иван", "иван")
        );
    }

    @Test
    public void roundAfterMultipleRegistrations() {
        Game game = new Game();
        Player p1 = new Player(1, "А", 50);
        Player p2 = new Player(2, "Б", 100);
        Player p3 = new Player(3, "В", 150);

        game.register(p1);
        game.register(p2);
        game.register(p3);

        // Б vs В: победа В (второй игрок)
        int result = game.round("Б", "В");
        Assertions.assertEquals(2, result);
    }

    @Test
    public void getRegisteredPlayersReturnsDefensiveCopy() {
        Game game = new Game();
        Player p = new Player(1, "Иван", 100);
        game.register(p);

        List<Player> list1 = game.getRegisteredPlayers();
        list1.clear(); // пытаемся изменить копию

        List<Player> list2 = game.getRegisteredPlayers();
        Assertions.assertEquals(1, list2.size()); // оригинал не изменился
    }
}