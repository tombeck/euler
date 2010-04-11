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
package com.thomas.problem162;

import static java.lang.Integer.bitCount;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.12.2009
 */
class Problem162 implements Problem {

    private static final int MAX = 16;
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 25.12.2009
     */
    @Override
    public String solve() {

        final long[][][] cache = new long[MAX][16][1 << 3];
        
        for (int f = 0; f < 0x10; ++f) cache[0][f][mask(f)] = cache[0][f][0] = 1;

        long sum = 0;
        
        for (int n = 1; n < MAX; ++n) { // length
            for (int f = 0; f < 0x10; ++f) { // first digit
                for (int d0 = 0; d0 < (1 << 3); ++d0) { // digits to be contained
                    if (bitCount(d0) <= n + 1) {
                        
                        final int d1 = d0 & ~(mask(f));
                        for (int s = 0; s < 0x10; ++s) {
                            cache[n][f][d0] += cache[n - 1][s][d1];
                        }
                    }        
                }
                if (f > 0) sum += cache[n][f][(1 << 3) - 1];
            }
        }

        return String.format("%X", sum);
    }

    private int mask(int d) {
    
        switch(d) {
        case 0x0: return 1 << 0;
        case 0x1: return 1 << 1;
        case 0xa: return 1 << 2;
        }
        
        return 0;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 25.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem162());
    }

}
