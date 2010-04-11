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
package com.thomas.incubator;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 23.12.2009
 */
public class Problem210 implements Problem {

    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger FIVE = BigInteger.valueOf(5);
    
    /**
     * TODO Method documentation
     * 
     * 1562500000249999998L
     * 1598174770174689458

     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 23.12.2009
     */
    @Override
    public Object solve() {

        long n = 1000000000;
        long r_sqr = (n / 8) * (n / 8) * 2;
        long max = (long)Math.ceil(Math.sqrt((n / 8) * (n / 8) * 2));

        long sum = 0;
        for (long x = n / 8; x < max; --x) {
            long y = (long)Math.ceil(Math.sqrt(r_sqr - (x * x)));
            sum += y;
        }
        return sum;
    }

    private BigInteger getCount(BigInteger r) {
    
    //    return r.pow(2).multiply(BigInteger.valueOf(25)).divide(BigInteger.valueOf(16)).add(r.divide(BigInteger.valueOf(4))).subtract(BigInteger.valueOf(2));
        final BigInteger tmp = r.divide(FOUR);
        
        return tmp.multiply(FIVE).pow(2).add(tmp).subtract(TWO);
    }
    
    private long getCount(long r) {
        
        long tmp = r / 4;
        
        return (((tmp * tmp * 25)) + tmp) - 2;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem210());
    }

}
