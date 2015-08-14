package ba.bitcamp.texas.run;

import java.util.ArrayList;
import java.util.Collections;

import ba.bitcamp.texas.run.Card.Suit;
import ba.bitcamp.texas.run.Card.Value;

public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();
	
	public Deck() {
		for (int i = 0; i < Suit.values().length; i++) {
			for (int j = 0; j < Value.values().length; j++) {
				deck.add(new Card(Suit.values()[i], Value.values()[j]));
			}
		}
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public static void main(String[] args) {
		Deck d = new Deck();
		//System.out.println(d.deck);
		d.shuffle();
		System.out.println(d.deck + "\n");
		
		ArrayList<Card> table = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			table.add(d.deck.remove(i));
		}
		System.out.println("table: " + table + "\n");
		
		ArrayList<Card> p1 = new ArrayList<Card>(table);
		for (int i = 0; i < 2; i++) {
			p1.add(d.deck.remove(i));
		}
		System.out.println("p1: " + p1 + "\n");
		ArrayList<Card> p2 = new ArrayList<Card>(table);
		for (int i = 0; i < 2; i++) {
			p2.add(d.deck.remove(i));
		}
		System.out.println("p2: " + p2 + "\n");
		ArrayList<Card> p3 = new ArrayList<Card>(table);
		for (int i = 0; i < 2; i++) {
			p3.add(d.deck.remove(i));
		}
		System.out.println("p3: " + p3 + "\n");
		ArrayList<Card> p4 = new ArrayList<Card>(table);
		for (int i = 0; i < 2; i++) {
			p4.add(d.deck.remove(i));
		}
		System.out.println("p4: " + p4 + "\n");
		
		ArrayList<HandEvaluator> set = new ArrayList<HandEvaluator>();
		set.add(new HandEvaluator(p1));
		set.add(new HandEvaluator(p2));
		set.add(new HandEvaluator(p3));
		set.add(new HandEvaluator(p4));
		
		Collections.sort(set);
		for (HandEvaluator h : set) {
			System.out.println(h.hand);
		}
		
//		for (int i = 0; i < 5; i++) {
//			System.out.print(d.deck.remove(i) + ", ");
//		}	
//		System.out.println();
//		for (int j = 0; j < 4; j++) {
//			System.out.print("player " + (j + 1) + ": ");
//			for (int i = 0; i < 2; i++) {
//				System.out.print(d.deck.remove(i) + ", ");
//			}			
//			System.out.println();
//		}
//		System.out.println(d.deck);
		
	}
}
