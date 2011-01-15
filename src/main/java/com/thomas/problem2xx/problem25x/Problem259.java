/**
 * $Id$
 *
 * Copyright (c) 2010 Thomas Beckmann 
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
package com.thomas.problem2xx.problem25x;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 01.04.2010
 */
public class Problem259 implements Problem {

    static class Rational implements Comparable<Rational> {

        final long num;
        final long den;
        
        private Rational(long num, long den) {

            this.num = num;
            this.den = den;
        }

        public Rational add(Rational other) {
            
            return new Rational(this.num * other.den + other.num * this.den, this.den * other.den);
        }
        
        public Rational subtract(Rational other) {
            
            return new Rational(this.num * other.den - other.num * this.den, this.den * other.den);
        }
        
        public Rational multiply(Rational other) {
        
            return new Rational(this.num * other.num, this.den * other.den);
        }
        
        public Rational divide(Rational other) {
            
            final int signum = Long.signum(other.num);
            
            return new Rational(this.num * other.den * signum, this.den * other.num * signum);
        }
        
        @Override
        public int compareTo(Rational other) {

            return Long.signum(this.num * other.den - other.num * this.den);
        }
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final Rational[][][] cache = new Rational[9][10][];
        
        for (int len = 1; len <= 9; ++len) {
            for (int first = 0, last; (last = first + len) <= 9; ++first) {

                final Set<Rational> tmp = new TreeSet<Rational>();
                
                for (int m = first + 1; m < last; ++m) {
                    for (Rational left : cache[first][m]) {
                        for (Rational right : cache[m][last]) {
                            if (right.num != 0) {
                                tmp.add(left.add(right));
                                tmp.add(left.subtract(right));
                                tmp.add(left.multiply(right));
                                tmp.add(left.divide(right));
                            } else {
                                tmp.add(left);
                                tmp.add(right);
                            }
                        }
                    }
                }
                tmp.add(concat(first, last));
                cache[first][last] = tmp.toArray(new Rational[tmp.size()]);
            }
        }
        
        long sum = 0;
        
        for (Rational r : cache[0][9]) {
            if (r.num > 0 && r.num % r.den == 0) sum += r.num / r.den;
        }

        return sum;
    }

    private Rational concat(int first, final int last) {
        
        int ret = 0;
        
        while (first != last) ret = ret * 10 + ++first;
        
        return new Rational(ret, 1);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem259());
    }

}
