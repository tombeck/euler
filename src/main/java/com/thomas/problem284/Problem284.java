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
package com.thomas.problem284;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.03.2010
 */
public class Problem284 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.03.2010
     */
    @Override
    public String solve() {

        final int max = 10000;
        final int[] x = new int[2 * max];
        
        int sum = 1 + 8 + 7;
        
        x[0] = 7;
        for (int n = 1, s = 0; n < max; ++n) {
            sqr(x, n - 1);
            s += x[n];
            if (x[n] < 13) sum += (n * 13 - s) + 8;
            if (x[n] >  0) sum += s + 7;
        }

        return Integer.toString(sum, 14);
    }

    private void sqr(int[] s, int m) {
        
        int carry = 0;
        
        for (int j = 0; j < m; ++j) {
            carry += 2 * s[m] * s[j] + s[m + j];
            s[m + j] = carry % 14;
            carry /= 14;
        }
        
        final int sqr = s[m] * s[m] + carry;
        
        s[2 * m] = sqr % 14;
        s[2 * m + 1] = sqr / 14;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem284());
    }

}
