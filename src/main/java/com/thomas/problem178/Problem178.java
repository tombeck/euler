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
package com.thomas.problem178;

import static java.lang.Integer.bitCount;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.12.2009
 */
class Problem178 implements Problem {

    private static final int DIGITS = 10;
    
    /**
     * TODO Method documentation
     * 126461847755
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 25.12.2009
     */
    @Override
    public Long solve() {

        final int max = 40;
        final long[][][] cache = new long[max][DIGITS][1 << DIGITS];
        
        long sum = 0;
        
        for (int f = 0; f < DIGITS; ++f) {
            cache[0][f][1 << f] = cache[0][f][0] = 1;
        }
        for (int n = 1; n < max; ++n) { // length
            for (int f = 0; f < DIGITS; ++f) { // first digit
                for (int d0 = 0; d0 < (1 << 10); ++d0) { // digits to be contained
                    if (bitCount(d0) <= n + 1) { // only those that may fit
                        
                        final int d1 = d0 & ~(1 << f);
                        
                        if (f > 0) cache[n][f][d0] += cache[n - 1][f - 1][d1];
                        if (f < 9) cache[n][f][d0] += cache[n - 1][f + 1][d1];
                    }        
                }
                if (f > 0) sum += cache[n][f][(1 << DIGITS) - 1];
            }
        }

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 25.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem178());
    }

}
