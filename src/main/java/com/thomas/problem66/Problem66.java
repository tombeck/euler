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
package com.thomas.problem66;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 04.12.2009
 */
public class Problem66 implements Problem {

    /**
     * expression of the form (sqrt(sqr) - n) / d 
     */
    final static class Remainder {
    
        final int sqr;
        final int n;
        final int d;
        
        private Remainder(int sqr, int n, int d) {

            this.sqr = sqr;
            this.n = n;
            this.d = d;
        }
        
        public Remainder invert() {
            
            return new Remainder(this.sqr, -this.n, (this.sqr - (this.n * this.n)) / this.d);
        }
        
        public int toInt() {
            
            return ((int)Math.sqrt(this.sqr) - this.n) / this.d;
        }
        
        public Remainder subtract(int i) {
        
            return new Remainder(this.sqr, this.n + (this.d * i), this.d);
        }
        
        public static Remainder init(int sqr) {
            
            return new Remainder(sqr, (int)Math.sqrt(sqr), 1);
        }
        
        @Override
        public boolean equals(Object o) {
        
            if (this == o) return true;
            if (!(o instanceof Remainder)) return false;
            
            final Remainder other = (Remainder)o;
            
            return (this.sqr == other.sqr && this.n == other.n && this.d == other.d);
        }
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() throws Exception {

        BigInteger max_x = BigInteger.ZERO;
        int max_d = 0;
        for (int n = 2; n <= 1000; ++n) {
            if (!isSquare(n)) {
                
                final Remainder init = Remainder.init(n);
                BigInteger[] c1 = {BigInteger.ONE, BigInteger.ZERO};
                BigInteger[] c2 = {BigInteger.valueOf((int)Math.sqrt(n)), BigInteger.ONE};
                
                Remainder rem = init;
                do {
                    if (c2[0].pow(2).subtract(c2[1].pow(2).multiply(BigInteger.valueOf(n))).equals(BigInteger.ONE)) {
                        if (c2[0].compareTo(max_x) > 0) {
                            max_x = c2[0];
                            max_d = n;
                        }
                        break;
                    }
                    rem = rem.invert();
                    int rest = rem.toInt();
                    BigInteger[] tmp = {BigInteger.valueOf(rest).multiply(c2[0]).add(c1[0]), BigInteger.valueOf(rest).multiply(c2[1]).add(c1[1])};
                    c1 = c2;
                    c2 = tmp;
                    rem = rem.subtract(rest);
                } while (true);
            }
        }
        
        return max_d;
    }
    
    public static boolean isSquare(int n) {
        
        final int sqrt = (int)Math.sqrt(n);
        
        return sqrt * sqrt == n;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 04.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem66());
    }

}
