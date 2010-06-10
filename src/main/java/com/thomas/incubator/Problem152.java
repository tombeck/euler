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

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.rational.BigRational;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 17.01.2010
 */
class Problem152 implements Problem {

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
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 17.01.2010
     */
    @Override
    public Object solve() {

        final BigRational[][] fractions = new BigRational[45 - 1][2];
        
        for (int i = 0; i < fractions.length; ++i) {
            fractions[i][0] = new BigRational(1, (i + 2) * (i + 2));
        }
        fractions[fractions.length - 1][1] = fractions[fractions.length - 1][0];
        for (int i = fractions.length - 1; i-- > 0; ) {
            fractions[i][1] = fractions[i + 1][1].add(fractions[i][0]);
        }
        
        return count(fractions, 0, HALF);
    }

    private int count(BigRational[][] fractions, int i, BigRational cur) {

        final int c = cur.compareTo(BigRational.ZERO);
        
        if (c < 0) return 0;
        if (c == 0) return 1;
        if (i >= fractions.length) return 0;
        if (cur.compareTo(fractions[i][1]) > 0) return 0;
        
        
        return count(fractions, i + 1, cur) + count(fractions, i + 1, cur.subtract(fractions[i][0]));
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
