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
package com.thomas.problem68;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 01.12.2009
 */
public class Main {

    static List<Integer> NUMBERS = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    
    static class Numbers implements Iterable<Numbers> {
        
        public static Iterable<Numbers> INITIAL = new Iterable<Numbers>() {

            @Override
            public Iterator<Numbers> iterator() {

                final Iterator<Integer> i = NUMBERS.iterator();
                
                return new Iterator<Numbers>() {

                    @Override
                    public boolean hasNext() { return i.hasNext(); }

                    @Override
                    public Numbers next() { return new Numbers(NUMBERS, i.next()); }

                    @Override
                    public void remove() { throw new UnsupportedOperationException(); }
                    
                };
            }
            
        };
        
        final int head;
        final Set<Integer> tail;

        public Numbers(Collection<Integer> source, int head) {

            this.head = head;
            this.tail = new LinkedHashSet<Integer>(source);
            if (!this.tail.remove(this.head)) throw new IllegalArgumentException();
        }
        
        @Override
        public Iterator<Numbers> iterator() {

            final Iterator<Integer> i = this.tail.iterator();
            final Set<Integer> set = this.tail;
            
            return new Iterator<Numbers>() {

                @Override
                public boolean hasNext() { return i.hasNext(); }

                @Override
                public Numbers next() { return new Numbers(set, i.next()); }

                @Override
                public void remove() { throw new UnsupportedOperationException(); }
                
            };
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 01.12.2009
     */
    public static void main(String[] args) {

        for (Numbers f : Numbers.INITIAL) {
            for (Numbers g : f) {
                if (f.head > g.head) continue;
                for (Numbers h : g) {
                    if (f.head > h.head) continue;
                    for (Numbers i : h) {
                        if (f.head > i.head) continue;
                        for (Numbers j : i) {
                            if (f.head > j.head) continue;
                            if (j.tail.contains(10)) continue;
                            for (Numbers a : j) {
                                for (Numbers c : a) {
                                    if (f.head + a.head != c.head + g.head) continue;
                                    for (Numbers e : c) {
                                        if (c.head + h.head != e.head + i.head) continue;
                                        for (Numbers b : c) {
                                            if (f.head + b.head != j.head + e.head) continue;
                                            for (Numbers d : b) {
                                                if (d.head + i.head != j.head + a.head) continue;
                                                if (b.head + g.head != d.head + h.head) continue;
                                                System.out.println("> " + f.head + a.head + b.head + g.head + b.head + c.head + h.head + c.head + d.head + i.head + d.head + e.head + j.head + e.head + a.head);
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
