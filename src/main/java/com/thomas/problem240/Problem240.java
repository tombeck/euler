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
package com.thomas.problem240;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 29.03.2010
 */
public class Problem240 implements Problem {

    private static final long[] FACTORIAL = {
        1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L,
        39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L,
        20922789888000L, 355687428096000L, 6402373705728000L,
        121645100408832000L, 2432902008176640000L
    };
    
    private static int SIDES = 12;
    private static int DICE = 20;
    private static int SUM = 70;
    private static int TOP = 10;
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 29.03.2010
     */
    @Override
    public Long solve() {

        return count(new int[SIDES], DICE, SIDES - 1, SUM);
    }

    private long count(int[] d, int roll, int max, int sum) {
        
        long count = 0;

        if (roll == 0) {
            count = FACTORIAL[DICE];
            for (int i = 0; i < SIDES; ++i) count /= FACTORIAL[d[i]];
        } else if (roll != DICE - TOP || sum == 0) {
            for (; max >= 0; --max) {
                ++d[max];
                count += count(d, roll - 1, max, sum - (max + 1));
                --d[max];
            }
        }
        
        return count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 29.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem240());
    }

}
