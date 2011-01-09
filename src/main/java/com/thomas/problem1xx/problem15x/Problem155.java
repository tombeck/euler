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
package com.thomas.problem1xx.problem15x;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 29.12.2009
 */
public class Problem155 implements Problem {

    static class Capacitor implements Comparable<Capacitor> {
    
        final int num;
        final int den;

        private Capacitor(int num, int den) {

            if (num < den) {
                this.num = num;
                this.den = den;
            } else {
                this.num = den;
                this.den = num;
            }
        }

        @Override
        public int compareTo(Capacitor other) {

            return this.num * other.den - other.num * this.den;
        }

    }
    
    final static Capacitor UNIT = new Capacitor(1, 1);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final Map<Integer, List<Capacitor>> cache = new HashMap<Integer, List<Capacitor>>(singletonMap(1, singletonList(UNIT)));
        final Set<Capacitor> capacitors = new TreeSet<Capacitor>();
        
        for (int n = 2; n <= 18; ++n) {
            
            capacitors.clear();
            
            for (int i = 1; 2 * i <= n; ++i) {
                for (Capacitor l : cache.get(i)) {
                    for (Capacitor r : cache.get(n - i)) {
                        capacitors.add(new Capacitor(l.num * r.den + l.den * r.num,  l.den * r.den));
                        capacitors.add(new Capacitor(l.den * r.den + l.num * r.num,  l.num * r.den));
                        capacitors.add(new Capacitor(l.num * r.num + l.den * r.den,  l.den * r.num));
                        capacitors.add(new Capacitor(l.den * r.num + l.num * r.den,  l.num * r.num));
                    }
                }
            }

            cache.put(n, new ArrayList<Capacitor>(capacitors));
        }

        final Set<Capacitor> all = new TreeSet<Capacitor>();
        
        for (List<Capacitor> set : cache.values()) all.addAll(set);
        
        return 2 * all.size() - 1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem155());
    }

}
