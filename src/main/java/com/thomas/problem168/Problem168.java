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
package com.thomas.problem168;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.01.2010
 */
class Problem168 implements Problem {

    /**
     * 10^p * y + x = q * (10x + y) => x = (y * (10^p - q)) / (10q - 1) 
     * 
     * 714285 = 10^5 * 7 + 14285 = 5 * (10 * 14285 + 7)
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 23.01.2010
     */
    @Override
    public Object solve() {

        final BigInteger max = TEN.pow(100);
        
        BigInteger HUNDRED_THOUSEND = TEN.pow(5);
        
        int sum = 0;

        for (BigInteger p = TEN; p.compareTo(max) < 0; p = p.multiply(TEN)) {
            for (BigInteger q = ONE; q.compareTo(TEN) < 0; q = q.add(ONE)) {
           
                final BigInteger den = TEN.multiply(q).subtract(ONE);
                final BigInteger diff = p.subtract(q);
                
                for (BigInteger y = ONE; y.compareTo(TEN) < 0; y = y.add(ONE)) {
                    
                    final BigInteger[] qr = y.multiply(diff).divideAndRemainder(den);
                    
                    if (qr[1].equals(ZERO)) {
                        
                        final BigInteger n = TEN.multiply(qr[0]).add(y);
                        
                        if (n.compareTo(p) > 0) {
                            sum += n.mod(HUNDRED_THOUSEND).intValue();
                            sum %= 100000;
                        }
                    }
                }
            }
        }

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem168());
    }

}
