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
package com.thomas.incubator;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 17.01.2010
 */
class Problem152 implements Problem {

    static class BigRational implements Comparable<BigRational> {

        private final BigInteger num;
        private final BigInteger den;
        
        /**
         * @param num
         * @param den
         */
        public BigRational(BigInteger num, BigInteger den) {

            this.num = num;
            this.den = den;
        }

        /**
         * @param num
         * @param den
         */
        public BigRational(long num, long den) {

            this(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }

        /**
         * @param num
         * @param den
         */
        public BigRational(long num) {

            this(BigInteger.valueOf(num), BigInteger.ONE);
        }

        public BigRational add(BigRational other) {
        
            return new BigRational(this.num.multiply(other.den).add(other.num.multiply(this.den)), this.den.multiply(other.den));
        }

        public BigRational subtract(BigRational other) {
            
            return new BigRational(this.num.multiply(other.den).subtract(other.num.multiply(this.den)), this.den.multiply(other.den));
        }

        public int signum() {
            
            return this.num.signum();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(BigRational other) {
            
            return this.num.multiply(other.den).compareTo(other.num.multiply(this.den));
        }

        public boolean less(BigRational other) {
            
            return this.num.multiply(other.den).compareTo(other.num.multiply(this.den)) < 0;
        }

        @Override
        public String toString() {
        
            return this.num + (BigInteger.ONE.equals(this.den) ? "" : "/" + this.den);
        }

    }
    
    static final BigRational ZERO = new BigRational(0);
    static final BigRational ONE = new BigRational(1);
    static BigRational HALF = new BigRational(1, 2);
    
    /**
     * TODO Method documentation
     * 
     *  2^2 = 2^2 
     *  3^3 = 3^2 : 2^2 * 3^2
     *  4^4 = 2^4 : 2^4 * 3^2
     *  5^5 = 5^5 : 2^4 * 3^2 * 5^2
     *  6^6 = 2^2 * 3^2 : 2^4 * 3^2 * 5^2
     *  7^7 = 7^7 : 2^4 * 3^2 * 5^2 * 7^2
     *  8^8 = 2^6 : 2^6 * 3^2 * 5^2 * 7^2
     *  9^2 = 3^4 : 2^6 * 3^4 * 5^2 * 7^2
     * 10^2 = 2^2 * 5^2 : 2^6 * 3^4 * 5^2 * 7^2
     * 11^2 = 11^2 : 2^6 * 3^4 * 5^2 * 7^2 * 11^2
     * 12^2 = 2^4 * 3^2 : 2^6 * 3^4 * 5^2 * 7^2 * 11^2
     * 13^2
     * 
     * 35 > 1 in    4137 ms
     * 36 > 1 in    7813 ms
     * 37 > 1 in   14481 ms
     * 38 > 1 in   26867 ms
     * 45 > 3 in 3914081 ms
     * 
     * a/b + x/y = 1/2 <=> 2(ay + xb) = yb
     * 
     * 1/a^2 + 1/b^2 = (a^2+b^2)/(a^2*b^2)
     * 
     * 1/a^2 + 1/(a + 1)^2 = (2a(a+1)+1)/(a(a+1))^2 = 2/(a(a+1))+1/((a(a+1))^2)
     * 
     * 1/a + 1/b + 1/c = (bc + ac + ab) / abc
     * 
     * 
     * 
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 17.01.2010
     */
    @Override
    public Object solve() {

        final BigInteger[][] fractions = new BigInteger[35 - 1][2];
        
        BigInteger sum = BigInteger.ZERO;
        BigInteger lcm = BigInteger.valueOf(2);
        
        for (int i = fractions.length; i-- > 0; ) {
            lcm = lcm(lcm, BigInteger.valueOf((i + 2) * (i + 2)));
        }
        for (int i = fractions.length; i-- > 0; ) {
            fractions[i][0] = lcm.divide(BigInteger.valueOf((i + 2) * (i + 2)));
            sum = sum.add(fractions[i][0]);
            fractions[i][1] = sum;
            if (i + 2 == 25) continue;
            if (i + 2 == 32) continue;
            if (i + 2 == 16) continue;
            System.out.println((i + 2) + ":" + fractions[i][0].mod(BigInteger.TEN.pow(4)));
        }
        return count(fractions, 0, lcm.divide(BigInteger.valueOf(2)), 0);
    }

    private BigInteger lcm(BigInteger a, BigInteger b) {
    
        return a.divide(a.gcd(b)).multiply(b);
    }
    
    private int count(BigInteger[][] fractions, int i, BigInteger cur, int d) {

        ++d;
        
        //if (d < 7) System.out.println(d + ", " + i);
        
        if (cur.signum() == 0) return 1;
        
        int count = 0;
        
        for (; i < fractions.length && cur.compareTo(fractions[i][0]) < 0; ++i) {
        }
        for (; i < fractions.length && cur.compareTo(fractions[i][1]) <= 0; ++i) {
            if (i + 2 == 25) continue;
            if (i + 2 == 32) continue;
            if (i + 2 == 16) continue;
            count += count(fractions, i + 1, cur.subtract(fractions[i][0]), d);
        }

        return count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 17.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem152());
    }

}
