package ba.bitcamp.texas.run;

public class Card implements Comparable<Card> {

	public enum Suit {
		HEARTS, CLUBS, DIAMONDS, SPADES
	}
	
	public enum Value {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	}
	
	private Suit suit;
	private Value value;
	
	/**
	 * @param suit
	 * @param value
	 */
	public Card(Suit suit, Value value) {
		super();
		this.suit = suit;
		this.value = value;
	}
	
	/**
	 * @return the suit
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString() + " of " + suit.toString();
	}

	@Override
	public int compareTo(Card o) {
		if(this.value.ordinal() < o.value.ordinal())
			return -1;
		else if(this.value.ordinal() > o.value.ordinal())
			return 1;
		if(this.suit.ordinal() < o.suit.ordinal())
			return -1;
		else if(this.suit.ordinal() > o.suit.ordinal())
			return 1;
		return 0;
	}
}
