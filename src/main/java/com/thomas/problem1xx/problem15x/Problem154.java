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
package com.thomas.problem1xx.problem15x;

import static java.lang.Math.min;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 16.12.2010
 */
public class Problem154 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int max = 200000;
        final int[][] c = new int[2][max + 1];
        
        for (int i = 0; i <= max; ++i) {
            c[0][i] = count(2, i);
            c[1][i] = count(5, i);
        }

        int count = 0;
        
        for (int i = (max + 2) / 3; i <= max; ++i) {
            
            final int d2 = c[0][max] - c[0][i] - 12;
            final int d5 = c[1][max] - c[1][i] - 12;
            
            for (int r = max - i, j = (r + 1) / 2, m = min(r, i); j <= m; ++j) {
                
                final int k = r - j;

                if (d5 >= c[1][j] + c[1][k] && d2 >= c[0][j] + c[0][k]) {
                    count += (i == j || j == k) ? 3 : 6;
                }
            }
        }
        
        return count;
    }

    private int count(int d, int n) {
    
        int count = 0;
        
        for (int p = d; p <= n; p *= d) count += n / p;
        
        return count;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem154());
    }

}
