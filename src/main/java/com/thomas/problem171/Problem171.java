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
package com.thomas.problem171;

import static com.thomas.util.Digit.SQUARE;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 18.12.2009
 */
public class Problem171 implements Problem {

    private static final int LIMIT = 1000000000;
    private static final int LENGTH = 20;
    private static final long[] FACTORIAL = {
        1L,
        1L,
        2L,
        6L,
        24L,
        120L,
        720L,
        5040L,
        40320L,
        362880L,
        3628800L,
        39916800L,
        479001600L,
        6227020800L,
        87178291200L,
        1307674368000L,
        20922789888000L,
        355687428096000L,
        6402373705728000L,
        121645100408832000L,
        2432902008176640000L
    };

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 18.02.2010
     */
    @Override
    public Object solve() {

        final int len = 20;
        final int max = 9 * 9 * len;
        final int[] digits = {len, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        int sum = 0;
        
        for (int i = 0, sqr; (sqr = i * i) <= max; ++i) {
            if ((sum += sum(digits, len, sqr, 9)) >= LIMIT) sum -= LIMIT;
        }
        
        return sum;
    }
    
    private int sum(int[] digits, int len, int rem, int max) {
        
        if (rem == 0) {
            
            long f = FACTORIAL[LENGTH];
            int n = 0;
            
            for (int i = 0; i < digits.length; ++i) {
                n += digits[i] * i;
                f /= FACTORIAL[digits[i]];
            }
            
            return (int)((111111111 * (((f * n) / LENGTH) % LIMIT)) % LIMIT);
        }
        
        if (len-- == 0) return 0;
        
        int sum = 0;
        
        for (int i = max; i > 0; --i) {
            
            final int diff = rem - SQUARE[i];
            
            if (diff < 0) continue;
            if (len * SQUARE[i] < diff) break;
            --digits[0];
            ++digits[i];
            if ((sum += sum(digits, len, diff, i)) >= LIMIT) sum -= LIMIT;
            --digits[i];
            ++digits[0];
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 18.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem171());
    }

}
