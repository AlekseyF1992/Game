package ru.netology.javaqa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PlayerTest {


    @Test
    public void shouldGetIdCorrectly() {
        Player player = new Player(1, "Иван", 100);
        Assertions.assertEquals(1, player.getId());
    }

    @Test
    public void shouldGetNameCorrectly() {
        Player player = new Player(1, "Иван", 100);
        Assertions.assertEquals("Иван", player.getName());
    }

    @Test
    public void shouldGetStrengthCorrectly() {
        Player player = new Player(1, "Иван", 100);
        Assertions.assertEquals(100, player.getStrength());
    }


    @Test
    public void shouldEqualItself() {
        Player player = new Player(1, "Иван", 100);

        Assertions.assertTrue(player.equals(player));
    }

    @Test
    public void shouldNotEqualNull() {
        Player player = new Player(1, "Иван", 100);

        Assertions.assertFalse(player.equals(null));
    }

    @Test
    public void shouldNotEqualDifferentClass() {
        Player player = new Player(1, "Иван", 100);

        Assertions.assertFalse(player.equals("Строка вместо игрока"));
    }

    @Test
    public void shouldEqualSameValues() {
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(1, "Иван", 100);

        Assertions.assertTrue(p1.equals(p2));
    }

    @Test
    public void shouldNotEqualDifferentId() {
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(2, "Иван", 100); // Только ID отличается
        Assertions.assertFalse(p1.equals(p2));
    }

    @Test
    public void shouldNotEqualDifferentName() {
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(1, "Мария", 100); // Только Имя отличается
        Assertions.assertFalse(p1.equals(p2));
    }

    @Test
    public void shouldNotEqualDifferentStrength() {
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(1, "Иван", 200); // Только Сила отличается
        Assertions.assertFalse(p1.equals(p2));
    }


    @Test
    public void shouldHaveSameHashCodeForEqualPlayers() {
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(1, "Иван", 100);

        Assertions.assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodeForDifferentPlayers() {
        Player p1 = new Player(1, "Иван", 100);
        Player p2 = new Player(2, "Мария", 200);

        Assertions.assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void hashCodeShouldWorkWithZeroStrength() {
        Player player = new Player(1, "Иван", 0);

        Assertions.assertNotNull(player.hashCode());
    }
}