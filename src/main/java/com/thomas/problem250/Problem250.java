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
package com.thomas.problem250;

import static com.thomas.util.NumberUtils.modPow;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 13.03.2010
 */
public class Problem250 implements Problem {

    static int x = 0;
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 13.03.2010
     */
    @Override
    public Long solve() {

        final long mod = 10000000000000000L;
        final int max = 250250;
        final long[][] cache = new long[max + 1][250];
        
        cache[0][0] = 1;
        for (int n = 1; n <= max; ++n) {
            for (int rem = 0, modPow = modPow(n, n, 250); rem < 250; ++rem) {
                if ((cache[n][rem] = (cache[n - 1][rem] + cache[n - 1][(rem + modPow) % 250])) > mod) {
                    cache[n][rem] -= mod; 
                }
            }
        }
        
        return cache[max][0] - 1;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 13.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem250());
    }

}
