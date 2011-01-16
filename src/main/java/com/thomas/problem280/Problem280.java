/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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
package com.thomas.problem280;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.NumberUtils;

/**
 * @author Thomas Beckmann
 * @since 15.01.2011
 */
public class Problem280 implements Problem {

    private static final int[][] P = {
            {2, 3, 3, 3, 2},
            {3, 4, 4, 4, 3},
            {3, 4, 4, 4, 3},
            {3, 4, 4, 4, 3},
            {2, 3, 3, 3, 2},
    };
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Double solve() {

        double exp = 0;
        double[][][][] prev = new double[1 << 5][1 << 5][5][5];

        prev[(1 << 5) - 1][0][2][2] = 1;
        
        for (int n = 1; ; ++n) {
            
            final double[][][][] next = new double[1 << 5][1 << 5][5][5];
            
            for (int p = 0; p < (1 << 5); ++p) {
                for (int d = 0; d < ((1 << 5) - 1); ++d) {
                    
                    final int f = Integer.bitCount(p) + Integer.bitCount(d);
                    
                    if (f < 4 || 5 < f) continue;
                    
                    for (int r = 0; r < 5; ++r) {
                        for (int c = 0; c < 5; ++c) {
                            
                            final double prob = prev[p][d][r][c] / P[r][c];
                            
                            if (r > 0) next[p(p, r - 1, 1 << (c), f)][d(d, r - 1, 1 << (c), f)][r - 1][c] += prob; 
                            if (c < 4) next[p(p, r, 1 << (c + 1), f)][d(d, r, 1 << (c + 1), f)][r][c + 1] += prob; 
                            if (r < 4) next[p(p, r + 1, 1 << (c), f)][d(d, r + 1, 1 << (c), f)][r + 1][c] += prob; 
                            if (c > 0) next[p(p, r, 1 << (c - 1), f)][d(d, r, 1 << (c - 1), f)][r][c - 1] += prob; 
                        }
                    }
                }
            }
            prev = next;
            
            final double[][] end = prev[0][(1 << 5) - 1];
            final double pexp = exp;
            
            exp += n * (end[0][0] + end[0][1] + end[0][2] + end[0][3] + end[0][4]);
            
            if (exp > 0 && exp == pexp) break;
        }
        
        return NumberUtils.round(exp, 6);
    }

    private int p(int p, int r, int c, int f) {
        
        return (f == 4 || r != 4 || (p & c) == 0) ? p : p & ~c;
    }
    
    private int d(int d, int r, int c, int f) {
        
        return (f != 4 || r != 0 || (d & c) != 0) ? d : d | c;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem280());
    }

}
