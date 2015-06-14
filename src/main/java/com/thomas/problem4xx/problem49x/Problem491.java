/**
 * $Id$
 *
 * Copyright (c) 2015 Thomas Beckmann
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

package com.thomas.problem4xx.problem49x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @since 14.06.2015
 */
public class Problem491 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int[][] states = new int[][] {{1,0}, {0,0}, {0,1}};
        
        long count = 0;
        
        for (int[] x0: states) {
            for (int[] x1: states) {
                int d1 = x1[0]-x1[1];
                for (int[] x2: states) {
                    int d2 = d1 + 2 * (x2[0]-x2[1]);
                    for (int[] x3: states) {
                        int d3 = d2 + 3 * (x3[0]-x3[1]);
                        for (int[] x4: states) {
                            int d4 = d3 + 4 * (x4[0]-x4[1]);
                            for (int[] x5: states) {
                                int d5 = d4 + 5 * (x5[0]-x5[1]);
                                for (int[] x6: states) {
                                    int d6 = d5 + 6 * (x6[0]-x6[1]);
                                    for (int[] x7: states) {
                                        int d7 = d6 + 7 * (x7[0]-x7[1]);
                                        for (int[] x8: states) {
                                            int d8 = d7 + 8 * (x8[0]-x8[1]);
                                            for (int[] x9: states) {
                                                int d9 = d8 + 9 * (x9[0]-x9[1]);
                                                if (d9%11 == 0) {
                                                    int l = x0[0]+x1[0]+x2[0]+x3[0]+x4[0]+x5[0]+x6[0]+x7[0]+x8[0]+x9[0];
                                                    int r = x0[1]+x1[1]+x2[1]+x3[1]+x4[1]+x5[1]+x6[1]+x7[1]+x8[1]+x9[1];
                                                    if (l == r) {
                                                        long p = 3628800 / (1 << l);
                                                        long n = 3628800 / (1 << l);
                                                        if (x0[1] == 0) {
                                                            p -= 362880 / (1 << (l-x0[0]));
                                                        }
                                                        count += p*n;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return count;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem491());
    }

}
