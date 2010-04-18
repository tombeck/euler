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
package com.thomas.problem149;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.IntGenerator;
import com.thomas.util.random.LaggedFibonacciGenerator;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.02.2010
 */
public class Problem149 implements Problem {

    /** 
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 14.02.2010
     */
    @Override
    public Integer solve() {

        final IntGenerator gen = new LaggedFibonacciGenerator();
        final int[][] table = new int[2000][2000];

        for (int i = 0; i < 2000; ++i) {
            for (int j = 0; j < 2000; ++j) {
                table[i][j] = gen.next() - 500000;
            }
        }

        int max = 0;
        
        for (int i = 0; i < 2000; ++i) {
            for (int j = 0, r = 0, c = 0; j < 2000; ++j) {
                if ((r += table[i][j]) < 0) r = 0;
                else if (r > max) max = r;
                if ((c += table[j][i]) < 0) c = 0;
                else if (c > max) max = c;
            }
            for (int j, k = 0, l = 0, r = 0, ul = 0, ur = 0; (j = k + i) < 2000; ++k) {
                if ((l += table[j][k]) < 0) l = 0;
                else if (l > max) max = l;
                if ((ul += table[k][j]) < 0) ul = 0;
                else if (ul > max) max = ul;
                if ((r += table[j][1999 - k]) < 0) r = 0;
                else if (r > max) max = r;
                if ((ur += table[k][1999 - j]) < 0) ur = 0;
                else if (ur > max) max = ur;
            }
        }

        return max;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem149());
    }

}
