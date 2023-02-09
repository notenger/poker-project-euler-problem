import com.rimsha.poker.Card;
import com.rimsha.poker.Rank;
import com.rimsha.poker.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void cardShouldBeJackSpades() {
        Card card = new Card(
            Rank.fromChar('J'),
            Suit.fromChar('S')
        );
        assertEquals(new Card(Rank.JACK, Suit.SPADES), card);
    }

    @Test
    void cardShouldBeAceHearts() {
        Card card = new Card(
                Rank.fromChar('A'),
                Suit.fromChar('H')
        );
        assertEquals(new Card(Rank.ACE, Suit.HEARTS), card);
    }

}