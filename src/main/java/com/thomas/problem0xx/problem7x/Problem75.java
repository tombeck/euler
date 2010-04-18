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
package com.thomas.problem0xx.problem7x;

import static com.thomas.Util.gcd;
import static java.lang.Math.sqrt;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.11.2009
 */
public class Problem75 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 27.12.2009
     */
    @Override
    public Integer solve() {
        
        final int max = 1500000;
        final int[] count = new int[max + 1];
        final int limit = (int)((sqrt(2 * max + 1) - 1) / 2);
        
        for (int u = 1; u <= limit; ++u) {
            for (int v = (u & 1) + 1; v < u; v += 2) { // opposite parity
                if (gcd(u, v) == 1) { // relatively prime
                    for (int n = 2 * u * (u + v), i = n; i <= max; i += n) ++count[i];
                }
            }
        }
        
        int sum = 0;
        
        for (int i = 1; i <= max; ++i) {
            if (count[i] == 1) ++sum;
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem75());
    }

}
