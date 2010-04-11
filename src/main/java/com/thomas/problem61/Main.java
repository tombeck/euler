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
package com.thomas.problem61;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.11.2009
 */
public class Main {

    private static class Number {
    
        private final int head;
        private final int tail;
        private final int type;
        private final int index; // only used for toString
        
        public Number(int n, int type, int index) {
        
            this.head = n / 100;
            this.tail = n % 100;
            this.type = type;
            this.index = index;
        }

        public int getType() { return this.type; }
        public int getHead() { return this.head; }
        public int getTail() { return this.tail; }
        public int getNumber() { return this.head * 100 + this.tail; }
        
        @Override
        public String toString() { return "P(" + this.type + ", " + this.index + ") = " + this.head + this.tail; }
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.11.2009
     */
    public static void main(String[] args) {

        final List<Number> numbers1 = new ArrayList<Number>();
        
        for (int n = 45, t; (t = triangle(n)) < 10000; ++n) numbers1.add(new Number(t, 3, n));
        for (int n = 32, t; (t = square(n)) < 10000; ++n) numbers1.add(new Number(t, 4, n));
        for (int n = 26, t; (t = pentagonal(n)) < 10000; ++n) numbers1.add(new Number(t, 5, n));
        for (int n = 23, t; (t = hexagonal(n)) < 10000; ++n) numbers1.add(new Number(t, 6, n));
        for (int n = 21, t; (t = heptagonal(n)) < 10000; ++n) numbers1.add(new Number(t, 7, n));
        for (int n = 19, t; (t = octagonal(n)) < 10000; ++n) numbers1.add(new Number(t, 8, n));
        
        for (Number n1 : numbers1) {
            final List<Number> numbers2 = remove(numbers1, n1);
            for (Number n2 : numbers2) {
                if (n1.getTail() == n2.getHead()) {
                    final List<Number> numbers3 = remove(numbers2, n2);
                    for (Number n3 : numbers3) {
                        if (n2.getTail() == n3.getHead()) {
                            final List<Number> numbers4 = remove(numbers3, n3);
                            for (Number n4 : numbers4) {
                                if (n3.getTail() == n4.getHead()) {
                                    final List<Number> numbers5 = remove(numbers4, n4);
                                    for (Number n5 : numbers5) {
                                        if (n4.getTail() == n5.getHead()){
                                            final List<Number> numbers6 = remove(numbers5, n5);
                                            for (Number n6 : numbers6) {
                                                if (n5.getTail() == n6.getHead()) {
                                                    if (n6.getTail() == n1.getHead()) {
                                                        System.out.println(n1 + ", " + n2 + ", " + n3 + ", " + n4 + ", " + n5 + ", " + n6);
                                                        System.out.println(n1.getNumber() + n2.getNumber() + n3.getNumber() + n4.getNumber() + n5.getNumber() + n6.getNumber());
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static List<Number> remove(List<Number> numbers, Number n) {
    
        final List<Number> ret = new ArrayList<Number>();
        
        for (Number number : numbers) {
            if (n.getType() != number.getType()) ret.add(number);
        }
        
        return ret;
    }
    
    private static final int triangle(int n) { return (n * (n + 1)) / 2; }
    private static final int square(int n) { return n * n; }
    private static final int pentagonal(int n) { return (n * (3 * n - 1)) / 2; }
    private static final int hexagonal(int n) { return n * (2 * n - 1); }
    private static final int heptagonal(int n) { return (n * (5 * n - 3)) / 2; }
    private static final int octagonal(int n) { return n * (3 * n - 2); }
    
}
