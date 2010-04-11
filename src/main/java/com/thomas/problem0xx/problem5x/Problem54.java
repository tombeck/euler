/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.thomas.problem0xx.problem5x;

import static com.thomas.util.IOUtils.closeQuietly;
import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 22.10.2009
 */
class Problem54 implements Problem {

    static enum Suit {
        
        DIAMONDS("D"), HEARTS("H"), SPADE("S"), CLUBS("C");
        
        private final String label;
        
        private Suit(String label) {
            
            this.label = label;
        }
        
        static Suit getSuit(char c) {
            
            for (Suit suit : values()) {
                if (suit.label.charAt(0) == c) return suit;
            }

            throw new IllegalArgumentException(c + " is not a valid suit");
        }
        
    }
    
    static enum Value {
        
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("T"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");
        
        private final String label;
        
        private Value(String label) {
            
            this.label = label;
        }
        
        static Value getValue(char c) {
            
            for (Value value : values()) {
                if (value.label.charAt(0) == c) return value;
            }

            throw new IllegalArgumentException(c + " is not a valid value");
        }
        
        public boolean follows(Value previous) {
            
            return this.ordinal() - previous.ordinal() == 1;
        }
        
    }
    
    static enum Rank {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH;
    }
    
    static class Card implements Comparable<Card> {

        private final Value value;
        private final Suit suit;
        
        public Card(Value value, Suit suit) {
        
            this.value = value;
            this.suit = suit;
        }
        
        public Card(char[] chars) {
            
            this(Value.getValue(chars[0]), Suit.getSuit(chars[1]));
        }
        
        public Card(String s) {
            
            this(s.toCharArray());
        }
        
        public Value getValue() {
            
            return this.value;
        }
        
        public Suit getSuit() {
            
            return this.suit;
        }
        
        @Override
        public int compareTo(Card o) {

            int ret = this.value.compareTo(o.value);
            
            return ret == 0 ? this.suit.compareTo(o.suit) : ret;
        }

    }
    
    static abstract class Hand implements Comparable<Hand> {
        
        protected final Card[] cards;
        
        public Hand(Card[] cards) {

            this.cards = cards;
        }

        public Value[] getValuesOfPairs() { return new Value[2]; }
        
        public Value getValueOfThreeOfAKind() { return null; }
        
        public Value getValueOfFourOfAKind() { return null; }
        
        public Rank getRank() { throw new UnsupportedOperationException(); }
        
        @Override
        public int compareTo(Hand o) {
            
            int diff = getRank().compareTo(o.getRank());

            if (diff == 0) {
                switch (getRank()) {
                case HIGH_CARD: break;
                case TWO_PAIRS:
                    
                    final Value[] thisValues = getValuesOfPairs();
                    final Value[] thatValues = o.getValuesOfPairs();
                    
                    diff = thisValues[1].compareTo(thatValues[1]);
                    
                    if (diff == 0) thisValues[0].compareTo(thatValues[0]);
                    break;
                case ONE_PAIR: diff = getValuesOfPairs()[0].compareTo(o.getValuesOfPairs()[0]); break;
                case THREE_OF_A_KIND: diff = getValueOfThreeOfAKind().compareTo(o.getValueOfThreeOfAKind()); break;
                case STRAIGHT: break;
                case FLUSH: break;
                case FULL_HOUSE:
                    diff = getValueOfThreeOfAKind().compareTo(o.getValueOfThreeOfAKind());
                    if (diff == 0) diff = getValuesOfPairs()[0].compareTo(o.getValuesOfPairs()[0]); break;
                case FOUR_OF_A_KIND: diff = getValueOfFourOfAKind().compareTo(o.getValueOfFourOfAKind()); break;
                case STRAIGHT_FLUSH: break;
                case ROYAL_FLUSH: break;
                }
            }
            for (int i = 0; diff == 0 && i < 5; ++i) {
                diff = this.cards[4 - i].getValue().compareTo(o.cards[4 - i].getValue());
            }
            
            return diff;
        }
        
        abstract int getEqualityIndex();
        
        abstract Hand addNextCard();
        
        public Suit getSuit() {
        
            final int first = getFirstIndex();
            final Suit suit = this.cards[first].getSuit();
            
            for (int i = first + 1, last = getLastIndex(); i < last; ++i) {
                if (this.cards[i].getSuit() != suit) return null;
            }
            
            return suit;
        }

        public boolean hasMoreCards() { return getLastIndex() < 5; }
        
        public abstract int getFirstIndex();
        
        public abstract int getLastIndex();

   }
    
    static class EmptyHand extends Hand {

        public EmptyHand(String[] s, int offset) {
            
            super(new Card[5]);
            
            for (int i = 0; i < 5; ++i) {
                this.cards[i] = new Card(s[i + offset]);
            }
            sort(this.cards);
        }
        
        @Override
        public Hand addNextCard() { return new OneCard(this.cards, 0); }

        @Override
        public Suit getSuit() { return null; }

        @Override
        public int getFirstIndex() { return 0; }

        @Override
        public int getLastIndex() { return 0; }

        @Override
        public int getEqualityIndex() { return 0; }
        
    }
    
    static abstract class SimpleHand extends Hand {
        
        public SimpleHand(Card[] cards) {

            super(cards);
        }

        @Override
        Hand addNextCard() {

            final Card lastCard = this.cards[getLastIndex() - 1];
            final Card nextCard = this.cards[getLastIndex()];
            
            if (nextCard.getValue() == lastCard.getValue()) return addNextEqualCard();
            if (nextCard.getValue().follows(lastCard.getValue())) return addNextConsecutiveCard();
            
            return addNextUnrelatedCard();
        }
        
        public abstract Hand addNextEqualCard();
        public abstract Hand addNextConsecutiveCard();
        public abstract Hand addNextUnrelatedCard();
        
    }
    
    static class OneCard extends SimpleHand {

        private final int index;
        
        public OneCard(Card[] cards, int index) {

            super(cards);
            this.index = index;
        }

        @Override
        public Hand addNextConsecutiveCard() { return new ConsecutiveCards(this.cards, this.index, this.index + 2); }

        @Override
        public Hand addNextEqualCard() { return new EqualCards(this.cards, this.index, this.index + 2); }

        @Override
        public Hand addNextUnrelatedCard() { return new UnrelatedCards(this.cards, this.index, this.index + 2); }

        @Override
        public int getFirstIndex() { return this.index; }
        
        @Override
        public int getLastIndex() { return this.index + 1; }
        
        @Override
        int getEqualityIndex() { return 0; }
        
    }

    static abstract class MulipleCards extends SimpleHand {

        protected final int first;
        protected final int last;
        
        public MulipleCards(Card[] cards, int first, int last) {

            super(cards);
            this.first = first;
            this.last = last;
        }

        @Override
        public int getFirstIndex() { return this.first; }
        
        @Override
        public int getLastIndex() { return this.last; }
        
    }

    static class UnrelatedCards extends MulipleCards {

        public UnrelatedCards(Card[] cards, int first, int last) {

            super(cards, first, last);
        }

        @Override
        public Hand addNextConsecutiveCard() { return addNextUnrelatedCard(); }

        @Override
        public Hand addNextEqualCard() {

            return new CompoundHand(this.cards,
                    new UnrelatedCards(this.cards, this.first, this.last - 1),
                    new EqualCards(this.cards, this.last - 1, this.last + 1));
        }

        @Override
        public Hand addNextUnrelatedCard() { return new UnrelatedCards(this.cards, this.first, this.last + 1); }
        
        @Override
        int getEqualityIndex() { return 0; }
        
        @Override
        public Rank getRank() {
            
            if (getSuit() == null) return Rank.HIGH_CARD;
            return Rank.FLUSH;
        }
        
    }
    
    static class EqualCards extends MulipleCards {

        public EqualCards(Card[] cards, int first, int last) {

            super(cards, first, last);
        }

        @Override
        public Hand addNextConsecutiveCard() { return addNextUnrelatedCard(); }

        @Override
        public Hand addNextEqualCard() { return new EqualCards(this.cards, this.first, this.last + 1); }

        @Override
        public Hand addNextUnrelatedCard() {

            return new CompoundHand(this.cards, this, new UnrelatedCards(this.cards, this.last, this.last));
        }
        
        @Override
        int getEqualityIndex() {
        
            return 2 * (getLastIndex() - getFirstIndex()) - 3;
        }
        
        @Override
        public Value[] getValuesOfPairs() {
            
            return new Value[] {
                    (getLastIndex() - getFirstIndex() == 2 ? this.cards[getFirstIndex()].getValue() : null),
                    null};
        }
        
        @Override
        public Value getValueOfThreeOfAKind() {
        
            return getLastIndex() - getFirstIndex() == 3 ? this.cards[getFirstIndex()].getValue() : null;
        }
        
        @Override
        public Value getValueOfFourOfAKind() {
        
            return getLastIndex() - getFirstIndex() == 4 ? this.cards[getFirstIndex()].getValue() : null;
        }
        
   }
    
    static class ConsecutiveCards extends MulipleCards {
        
        public ConsecutiveCards(Card[] cards, int first, int last) {

            super(cards, first, last);
        }

        @Override
        public Hand addNextConsecutiveCard() { return new ConsecutiveCards(this.cards, this.first, this.last + 1); }

        @Override
        public Hand addNextEqualCard() {

            return new CompoundHand(this.cards,
                    new UnrelatedCards(this.cards, this.first, this.last - 1),
                    new EqualCards(this.cards, this.last - 1, this.last + 1));
        }

        @Override
        public Hand addNextUnrelatedCard() { return new UnrelatedCards(this.cards, this.first, this.last + 1); }

        @Override
        public Rank getRank() {
            
            if (getSuit() == null) return Rank.STRAIGHT;
            if (this.cards[getFirstIndex()].getValue() == Value.TEN) return Rank.ROYAL_FLUSH;
            return Rank.FLUSH;
        }
        
        @Override
        int getEqualityIndex() { return 0; }
        
    }
    
    static class CompoundHand extends Hand {

        private final Hand hand1;
        private final Hand hand2;
        
        public CompoundHand(Card[] cards, Hand hand1, Hand hand2) {

            super(cards);
            this.hand1 = hand1;
            this.hand2 = hand2;
        }

        @Override
        public int getFirstIndex() { return this.hand1.getFirstIndex(); }

        @Override
        public int getLastIndex() { return this.hand2.getLastIndex(); }

        @Override
        Hand addNextCard() { return new CompoundHand(this.cards, this.hand1, this.hand2.addNextCard()); }

        @Override
        public Rank getRank() {
            
            if (this.getLastIndex() == 5) {
                if (getSuit() == null) {
                    switch(getEqualityIndex()) {
                    case 1: return Rank.ONE_PAIR;
                    case 2: return Rank.TWO_PAIRS;
                    case 3: return Rank.THREE_OF_A_KIND;
                    case 4: return Rank.FULL_HOUSE;
                    case 5: return Rank.FOUR_OF_A_KIND;
                    }
                } else {
                    switch(getEqualityIndex()) {
                    case 1:
                    case 2:
                    case 3: return Rank.THREE_OF_A_KIND;
                    case 4: return Rank.FULL_HOUSE;
                    case 5: return Rank.FOUR_OF_A_KIND;
                    }
                }
            }
            return Rank.HIGH_CARD;
        }
        
        @Override
        public Value[] getValuesOfPairs() {
            
            final Value[] values1 = this.hand1.getValuesOfPairs();
            
            if (values1[1] != null) return values1;
            
            final Value[] values2 = this.hand2.getValuesOfPairs();
            
            if (values1[0] == null) return values2;
            if (values2[0] == null) return values1;
            
            return new Value[] {values1[0], values2[0]};
        }

        @Override
        public Value getValueOfThreeOfAKind() {
        
            final Value value = this.hand1.getValueOfThreeOfAKind();
            
            return value == null ? this.hand2.getValueOfThreeOfAKind() : value;
        }
        
        @Override
        public Value getValueOfFourOfAKind() {
        
            final Value value = this.hand1.getValueOfFourOfAKind();
            
            return value == null ? this.hand2.getValueOfFourOfAKind() : value;
        }
        
        @Override
        int getEqualityIndex() { return this.hand1.getEqualityIndex() + this.hand2.getEqualityIndex(); }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException 
     * @since 05.02.2010
     */
    @Override
    public Integer solve() throws IOException {

        int count = 0;

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("poker.txt")));

        try {
            for (String line; (line = reader.readLine()) != null; ) {
                
                final String[] cards = line.split(" ");
                
                Hand player1 = new EmptyHand(cards, 0);
                Hand player2 = new EmptyHand(cards, 5);

                while(player1.hasMoreCards()) {
                    player1 = player1.addNextCard();
                }
                while(player2.hasMoreCards()) {
                    player2 = player2.addNextCard();
                }

                if (player1.compareTo(player2) > 0) {
                    ++count;
                }
            }
        } finally {
            closeQuietly(reader);
        }
        
        return count;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 22.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem54());
    }

}
