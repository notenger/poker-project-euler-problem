import com.rimsha.poker.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

class GameTest {

    @Test
    void handRankingShouldBeFullHouseTens() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Card> cards = List.of(
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.TEN, Suit.HEARTS),
            new Card(Rank.TEN, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.DIAMONDS),
            new Card(Rank.TEN, Suit.SPADES)
        );
        Hand hand = new Hand(cards);

        Method method = Hand.class.getDeclaredMethod("determineHandRanking");
        method.setAccessible(true);

        Map<HandRanking, List<Rank>> expectedResult = Collections.singletonMap(HandRanking.FULL_HOUSE, List.of(Rank.TEN));
        assertEquals(expectedResult, method.invoke(hand, new Object[]{}));
    }

    @Test
    void handRankingShouldBeTwoPairsKingsAndJacks() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Card> cards = List.of(
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.HEARTS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.KING, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.SPADES)
        );
        Hand hand = new Hand(cards);

        Method method = Hand.class.getDeclaredMethod("determineHandRanking");
        method.setAccessible(true);

        Map<HandRanking, List<Rank>> expectedResult = Collections.singletonMap(HandRanking.TWO_PAIRS, List.of(Rank.KING, Rank.JACK));
        assertEquals(expectedResult, method.invoke(hand, new Object[]{}));
    }

    @Test
    void secondShouldWin() {
        List<Card> cardsFirst = List.of(
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.DEUCE, Suit.HEARTS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.KING, Suit.DIAMONDS),
            new Card(Rank.DEUCE, Suit.SPADES)
        );

        Hand handOne = new Hand(cardsFirst);

        List<Card> cardsSecond = List.of(
            new Card(Rank.SIX, Suit.SPADES),
            new Card(Rank.SEVEN, Suit.SPADES),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.FIVE, Suit.SPADES),
            new Card(Rank.FOUR, Suit.SPADES)
        );

        Hand handTwo = new Hand(cardsSecond);

        Player.FIRST.setHand(handOne);
        Player.SECOND.setHand(handTwo);

        Game game = new Game(Player.FIRST, Player.SECOND);
        assertEquals(Player.SECOND, game.chooseWinner());
    }

    @Test
    void ranksShouldBeConsecutive() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Card> cards = List.of(
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.TEN, Suit.DIAMONDS),
            new Card(Rank.NINE, Suit.SPADES)
        );

        Hand hand = new Hand(cards);
        Method method = Hand.class.getDeclaredMethod("areConsecutive");
        method.setAccessible(true);
        assertEquals(true, method.invoke(hand, new Object[]{}));
    }

}