## My solution to Problem #54 from Project Euler

Suits, ranks and hand rankings have a predefined set of values and are well presented with Enums. An order (rating) is explicitly assigned to each member of ranks and hand rankings enums and is used by the corresponding comparators. 
Rank and suit must be specified to create a playing card. The seniority of the card is determined by its rank. 

Five cards form a hand. The values of the enums are mapped to the characters represented in the source data in order to initialize pairs of hands. 

The solution is based on the idea that each hand ranking is a combination of patterns:
- Same suit
- Continuous sequence of ranks
- The frequency of occurrence of a rank in a hand
- Seniority by rank

For example, the straight flush is a combination of two patterns: the continuous sequence of ranks and the same suit.

The logic of the poker game, by which the winner is determined, is encapsulated in the Game class.
When determining hand ranking, the value is also determined.

By defining the hand ranking along with its value, we can determine the winner. 
First hand rankings are compared. If they tie, then the values the rankings are made up of are compared, starting with the highest rank if there are several values (two pairs).
If there is a match again, pairs of cards corresponding to the rating in the hand are compared (the first in value in a hand, the second in value in a hand etc.).

The following OOP concepts were used in this solution:
- Creating classes with a clear and single area of responsibility
- Implementations of the core interfaces (Comparable and Comparator)
- Encapsulation

In this solution, I like its intuitiveness and the wide use of the Stream API and Collections API, what makes the code concise and readable. At the same time, initially I wanted one hand to have information in three parts: the name of the hand ranking, the value it's made up of and the highest card after excluding cards that form the ranking.
In this case, the information about the players would be decoupled. But the order of determining the highest card would strongly depend on the combination itself, because, for example, some
combinations are made up of all cards (full house), and some - only from a part (one pair). Because of this, the option was preferred when the highest card is determined in the context of contrasting sets of cards of two players and determining the winner.

I didn't have to use technologies that were completely unfamiliar to me, but I was glad to get more hands-on experience with JUnit. 
