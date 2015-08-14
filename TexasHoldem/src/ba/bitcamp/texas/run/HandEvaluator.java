package ba.bitcamp.texas.run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import ba.bitcamp.texas.run.Card.Suit;
import ba.bitcamp.texas.run.Card.Value;

public class HandEvaluator implements Comparable<HandEvaluator> {

	private static final int MAX_NUM_OF_PAIRS = 2;
	
	//TODO
	public ArrayList<Card> hand;
	
	private int[] distibutionOfValues = new int[Value.values().length];
	private int[] distibutionOfSuits = new int[Suit.values().length];
	
	private int fourOfAKind;
	private int threeOfAKind;
	private int numOfPairs;
	private int[] pairs = new int[MAX_NUM_OF_PAIRS];

	private int straightRank;
	private int straightFlushRank;
	private Suit flushSuit;

	public HandEvaluator(ArrayList<Card> hand) {
		this.hand = hand;
		calculateDistributions();
		findDuplicates();
	}

	private void calculateDistributions() {
		for (Card card : hand) {
			distibutionOfValues[card.getValue().ordinal()]++;
			distibutionOfSuits[card.getSuit().ordinal()]++;
		}
	}

	private boolean hasStraight() {
		int counter = 0;
		for (int i = distibutionOfValues.length - 1; i >= 0; i--) {
			if (distibutionOfValues[i] != 0) {
				counter++;
				if (counter >= 5){
					straightRank = distibutionOfValues[i];
					return true;
				}
			} else {
				counter = 0;
			}
		}
		if (counter == 4) {
			if (distibutionOfValues[distibutionOfValues.length - 1] != 0)
				straightRank = Value.ACE.ordinal();
				return true;
		}
		return false;
	}

	private boolean hasFlush() {
		for (int i = 0; i < distibutionOfSuits.length; i++) {
			if (distibutionOfSuits[i] >= 5) {
				flushSuit = Suit.values()[i];
				return true;
			}
		}
		return false;
	}

	private void findDuplicates() {
		for (int i = Value.values().length - 1; i >= 0; i--) {
			if (distibutionOfValues[i] == 4) {
				fourOfAKind = i;
			} else if (distibutionOfValues[i] == 3) {
				threeOfAKind = i;
			} else if (distibutionOfValues[i] == 2) {
				if (numOfPairs < MAX_NUM_OF_PAIRS) {
					pairs[numOfPairs++] = i;
				}
			}
		}
	}
	
	private boolean hasStraightFlush() {
        if (hasStraight() && hasFlush()) {
        	TreeMap<Suit, ArrayList<Value>> set = new TreeMap<>();
        	for (Suit s : Suit.values()){
        		set.put(s, new ArrayList<Card.Value>());
        	}
        	for (Card card : hand) {
        		set.get(card.getSuit()).add(card.getValue());
        	}
        	for (Suit s : Suit.values()){
        		if (set.get(s).size() >= 5){
        			int counter = 0;
        			ArrayList<Value> tmp = set.get(s);
        			Collections.sort(tmp);
        			for (int i = tmp.size() - 1; i >= 1; i--) {
        				if (tmp.get(i).ordinal() - 1 == tmp.get(i - 1).ordinal()) {
        					counter++;
        					if (counter >= 4){
        						straightFlushRank = tmp.get(i - 1).ordinal();
        						return true;
        					}
        				} else {
        					counter = 0;
        				}
        			}
        			if (counter == 3 && tmp.get(tmp.size() - 1).equals(Value.ACE)) {
        				return true;
        			}
        		}
        	}
        }
        return false;
	}

	@Override
	public int compareTo(HandEvaluator o) {
		//straight flush
		if (this.compareStraightFlush(o) != null) {
			return this.compareStraightFlush(o);
		}
		//poker
		if (this.compareFourOfAKind(o) != null) {
			return this.compareFourOfAKind(o);
		}
		//full house
		if (this.compareFullHouse(o) != null) {
			return this.compareFullHouse(o);
		}
		//flush
		if (this.compareFlush(o) != null) {
			return this.compareFlush(o);
		}
		//straight
		if (this.compareStraight(o) != null) {
			return this.compareStraight(o);
		}
		//three of a kind
		if (this.compareThreeOfAKind(o) != null) {
			return this.compareThreeOfAKind(o);
		}
		//two pairs
		if (this.compareTwoPairs(o) != null) {
			return this.compareTwoPairs(o);
		}
		//pair
		if (this.comparePairs(o) != null) {
			return this.comparePairs(o);
		}
		//high card
		
		return 0;
	}

	private Integer comparePairs(HandEvaluator o) {
		if (this.numOfPairs == 1) {
			if (o.numOfPairs == 1) {
				//TODO veca karta
				System.out.println("pair");
			}
		}
		return null;
	}

	private Integer compareTwoPairs(HandEvaluator o) {
		if (this.numOfPairs == 2) {
			if(o.numOfPairs == 2) {
				//TODO
				System.out.println("two pairs");
			} else 
				return -1;
		} else if (o.numOfPairs == 2) {
			return 1;
		}
		return null;
	}

	private Integer compareThreeOfAKind(HandEvaluator o) {
		if (this.threeOfAKind != 0) {
			if (o.threeOfAKind != 0) {
				return o.threeOfAKind - this.threeOfAKind;
			} else {
				return -1;
			}
		} else if (o.threeOfAKind != 0) {
			return 1;
		}
		return null;
	}

	private Integer compareStraight(HandEvaluator o) {
		if (this.hasStraight()) {
			//TODO
			System.out.println("straight");
		}
		return null;
	}

	private Integer compareFlush(HandEvaluator o) {
		if (this.hasFlush()) {
			if(o.hasFlush()){
				Collections.sort(this.hand);
				Collections.sort(o.hand);
				for (int i = this.hand.size() - 1; i >= 0; i--) {
					if(this.hand.get(i).getSuit().equals(this.flushSuit)){
						for (int j = o.hand.size() - 1; j >= 0; j--) {
							if(o.hand.get(j).getSuit().equals(o.flushSuit)){
								return o.hand.get(j).getValue().ordinal() - this.hand.get(i).getValue().ordinal(); 
							}
						}
					}
				}
				return 0;
			}
			return -1;
		} else if (o.hasFlush()) {
			return 1;
		}
		return null;
	}

	private Integer compareFullHouse(HandEvaluator o) {
		if (this.threeOfAKind != 0 && this.numOfPairs != 0) {
			if (this.threeOfAKind != 0 && this.numOfPairs != 0) {
				if (this.threeOfAKind == o.threeOfAKind) {
					//TODO
					System.out.println("full house");
				} else {
					return o.threeOfAKind - this.threeOfAKind;
				}
			}
		} else if (o.threeOfAKind != 0 && o.numOfPairs != 0) {
			return 1;
		}
		return null;
	}

	private Integer compareFourOfAKind(HandEvaluator o) {
		if (this.fourOfAKind != 0) {
			if (o.fourOfAKind != 0) {
				if (o.fourOfAKind == this.fourOfAKind){
					Collections.sort(this.hand);
					Collections.sort(o.hand);
					return o.hand.get(o.hand.size() - 1).getValue().ordinal() - this.hand.get(this.hand.size() - 1).getValue().ordinal();
				}
				return o.fourOfAKind - this.fourOfAKind;
			} else {
				return -1;
			}
		} else if (o.fourOfAKind != 0) {
			return 1;
		}
		return null;
	}

	private Integer compareStraightFlush(HandEvaluator o) {
		if (this.hasStraightFlush()) {
			if (o.hasStraightFlush()) {
				if(this.straightFlushRank == Value.ACE.ordinal()) {
					if (o.straightFlushRank == Value.ACE.ordinal())
						return 0;
					else 
						return 1;
				} else if (o.straightFlushRank == Value.ACE.ordinal()) 
					return -1;
				return o.straightFlushRank - this.straightFlushRank;
			} else {
				return -1;
			}
		} else if (o.hasStraightFlush()) {
			return 1;
		}
		return null;
	}
	
}
