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
package com.thomas.problem166;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 *   1  0  0  0  0  0  0 -1  0  0  0 -1  0 -1 -1 -1  1
 *   0  1  0  0  0  0  0 -1  0  1 -1 -1  0  0 -1 -2  1  
 *   0  0  1  0  0  0  0  1  0 -1  1  1  0  1  2  2 -2  
 *   0  0  0  1  0  0  0  1  0  0  0  1  0  0  0  1 -1  
 *   0  0  0  0  2  0  0  2  0 -2 -2  0  0  0  0  1  0  
 *   0  0  0  0  0  1  0  1  0  0  1  1  0  1  1  2 -2
 *   0  0  0  0  0  0  1 -1  0  1  0 -1  0 -1 -1 -2  1
 *   0  0  0  0  0  0  0  0  1  1  1  1  0  0  0  0 -1
 *   0  0  0  0  0  0  0  0  0  0  0  0  1  1  1  1 -1  
 *   0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
 *   
 *   
 *   
 *  x  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p
 * -1  1  1  1  1  0  0  0  0  0  0  0  0  0  0  0  0  
 * -1  0  0  0  0  1  1  1  1  0  0  0  0  0  0  0  0  
 * -1  0  0  0  0  0  0  0  0  1  1  1  1  0  0  0  0  
 * -1  0  0  0  0  0  0  0  0  0  0  0  0  1  1  1  1  
 * -1  1  0  0  0  1  0  0  0  1  0  0  0  1  0  0  0  
 * -1  0  1  0  0  0  1  0  0  0  1  0  0  0  1  0  0  
 * -1  0  0  1  0  0  0  1  0  0  0  1  0  0  0  1  0  
 * -1  0  0  0  1  0  0  0  1  0  0  0  1  0  0  0  1  
 * -1  1  0  0  0  0  1  0  0  0  0  1  0  0  0  0  1  
 * -1  0  0  0  1  0  0  1  0  0  1  0  0  1  0  0  0
 *   
 *   
 *  1 -1 -1 -1 -1  0  0  0  0  0  0  0  0  0  0  0  0
 *  0 -1 -1 -1 -1  1  1  1  1  0  0  0  0  0  0  0  0  
 *  0 -1 -1 -1 -1  0  0  0  0  1  1  1  1  0  0  0  0  
 *  0 -1 -1 -1 -1  0  0  0  0  0  0  0  0  1  1  1  1  
 *  0  0 -1 -1 -1  1  0  0  0  1  0  0  0  1  0  0  0  
 *  0 -1  0 -1 -1  0  1  0  0  0  1  0  0  0  1  0  0  
 *  0 -1 -1  0 -1  0  0  1  0  0  0  1  0  0  0  1  0  
 *  0 -1 -1 -1  0  0  0  0  1  0  0  0  1  0  0  0  1  
 *  0  0 -1 -1 -1  0  1  0  0  0  0  1  0  0  0  0  1  
 *  0 -1 -1 -1  0  0  0  1  0  0  1  0  0  1  0  0  0
 *   
 *   
 *  1  0  0  0  0 -1 -1 -1 -1  0  0  0  0  0  0  0  0
 *  0  1  1  1  1 -1 -1 -1 -1  0  0  0  0  0  0  0  0
 *  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  0  0  0  0  
 *  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0  1  1  1  1  
 *  0  0  0  0  1 -1 -1  0 -1  0  1  0  0  1  0  0  0
 *  0  0  1  0  0 -1  0 -1 -1  0  1  0  0  0  1  0  0  
 *  0  0  0  1  0 -1 -1  0 -1  0  0  1  0  0  0  1  0  
 *  0  0  0  0  1 -1 -1 -1  0  0  0  0  1  0  0  0  1  
 *  0  0 -1 -1 -1  1  0  0  0  1  0  0  0  1  0  0  0  
 *  0  0 -1 -1 -1  0  1  0  0  0  0  1  0  0  0  0  1  
 *   
 *   
 *  1  0  0  0  0 -1 -1 -1 -1  0  0  0  0  0  0  0  0
 *  0 -1  0 -1 -1  0  1  0  0  0  1  0  0  0  1  0  0
 *  0  0  1  0  0 -1  0 -1 -1  0  1  0  0  0  1  0  0
 *  0  0  0 -1 -1  0  0 -1 -1  1  1  0  0  1  1  0  0  
 *  0  0  0 -1 -1 -1  1 -1 -1  0  1  1  0  0  1  0  1  
 *  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  0  0  0  0  
 *  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0  1  1  1  1  
 *  0  0  0  0  1 -1 -1  0 -1  0  1  0  0  1  0  0  0
 *  0  0  0  1  0 -1 -1  0 -1  0  0  1  0  0  0  1  0  
 *  0  0  0  0  1 -1 -1 -1  0  0  0  0  1  0  0  0  1  
 *   
 *   
 *  1  0  0  0  0 -1 -1 -1 -1  0  0  0  0  0  0  0  0
 *  0 -1  0  0 -1 -1  0  0 -1  0  1  1  0  0  1  1  0
 *  0  0  1  0  0 -1  0 -1 -1  0  1  0  0  0  1  0  0
 *  0  0  0  1  0 -1 -1  0 -1  0  0  1  0  0  0  1  0
 *  0  0  0  0 -1 -1 -1 -1 -2  1  1  1  0  1  1  1  0  
 *  0  0  0  0 -1 -2  0 -1 -2  0  1  2  0  0  1  1  1  
 *  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  0  0  0  0  
 *  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0  1  1  1  1  
 *  0  0  0  0  1 -1 -1  0 -1  0  1  0  0  1  0  0  0
 *  0  0  0  0  1 -1 -1 -1  0  0  0  0  1  0  0  0  1  
 *   
 *   
 *  1  0  0  0  0 -1 -1 -1 -1  0  0  0  0  0  0  0  0
 *  0 -1  0  0  0 -2 -1  0 -2  0  2  1  0  1  1  1  0
 *  0  0  1  0  0 -1  0 -1 -1  0  1  0  0  0  1  0  0
 *  0  0  0  1  0 -1 -1  0 -1  0  0  1  0  0  0  1  0
 *  0  0  0  0  1 -1 -1  0 -1  0  1  0  0  1  0  0  0
 *  0  0  0  0  0  0  0  1 -1  0  1  0 -1  1  0  0 -1  
 *  0  0  0  0  0 -2 -2 -1 -3  1  2  1  0  2  1  1  0  
 *  0  0  0  0  0 -3 -1 -1 -3  0  2  2  0  1  1  1  1  
 *  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  0  0  0  0  
 *  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0  1  1  1  1  
 *   
 *   
 *  1  0  0  0  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0
 *  0 -1  0  0  0  0  1  2  0 -2  0 -1 -2  1  1  1  0
 *  0  0  1  0  0  0  1  0  0 -1  0 -1 -1  0  1  0  0
 *  0  0  0  1  0  0  0  1  0 -1 -1  0 -1  0  0  1  0
 *  0  0  0  0  1  0  0  1  0 -1  0 -1 -1  1  0  0  0
 *  0  0  0  0  0  1  1  1  1 -1 -1 -1 -1  0  0  0  0
 *  0  0  0  0  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  
 *  0  0  0  0  0  0  0  1 -1 -1  0 -1 -2  2  1  1  0  
 *  0  0  0  0  0  0  2  2  0 -3 -1 -1 -3  1  1  1  1  
 *  0  0  0  0  0  0  0  1 -1  0  1  0 -1  1  0  0 -1  
 *   
 *   
 *  1  0  0  0  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0
 *  0 -2  0  0  0  0  0  2  0 -1  1 -1 -1  1  1  1 -1
 *  0  0  2  0  0  0  0 -2  0  1  1 -1  1 -1  1 -1 -1
 *  0  0  0  1  0  0  0  1  0 -1 -1  0 -1  0  0  1  0
 *  0  0  0  0  1  0  0  1  0 -1  0 -1 -1  1  0  0  0
 *  0  0  0  0  0  2  0  0  2  1 -1 -1  1 -1 -1 -1 -1
 *  0  0  0  0  0  0 -2 -2  0  3  1  1  3 -1 -1 -1 -1
 *  0  0  0  0  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  
 *  0  0  0  0  0  0  0  1 -1 -1  0 -1 -2  2  1  1  0  
 *  0  0  0  0  0  0  0  1 -1  0  1  0 -1  1  0  0 -1  
 *   
 *   
 *  1  0  0  0  0  0  0  0  0 -1 -1 -1 -1  0  0  0  0
 *  0 -2  0  0  0  0  0  0  2  1  1  1  3 -3 -1 -1 -1
 *  0  0 -2  0  0  0  0  0  2  1 -1  3  3 -3 -3 -1  1
 *  0  0  0  1  0  0  0  0  1  0 -1  1  1 -2 -1  0  0
 *  0  0  0  0  1  0  0  0  1  0  0  0  1 -1 -1 -1  0
 *  0  0  0  0  0  2  0  0  2  1 -1 -1  1 -1 -1 -1 -1
 *  0  0  0  0  0  0  2  0  2 -1 -1  1  1 -3 -1 -1  1
 *  0  0  0  0  0  0  0 -1  1  1  0  1  2 -2 -1 -1  0
 *  0  0  0  0  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  
 *   
 *   
 *   
 * 12  6  3  3  0  5  0  4  3  0  7  1  4  1  2  4  5
 * 
 * -a + h  + l - m
 *  x  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p
 * -1  0  0  0  0  0  0  0  0  0  0  0  0  1  1  1  1
 *  0 -1  0  0  0  0  0  0  1  0  0  0  1 -1  0  0  0
 *  
 *  0  0 -1  0  0  0  0  0  1  0 -1  1  1 -1 -1  0  1
 *  0  0  0  1  0  0  0  0  1  0 -1  1  1 -2 -1  0  0
 *  0  0  0  0  1  0  0  0  1  0  0  0  1 -1 -1 -1  0
 *  0  0  0  0  0  1  0  0  1  0 -1 -1  0  0  0  0  0
 *  0  0  0  0  0  0 -1  0 -1  0  0 -1 -1  2  1  1  0
 *  0  0  0  0  0  0  0 -1  1  0 -1  0  1 -1  0  0  1
 *  0  0  0  0  0  0  0  0  0 -1 -1 -1 -1  1  1  1  1  
 *   
 *   
 * @author Thomas
 * @since 01.01.2010
 */
public class Problem166 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @throws Exception
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 01.01.2010
     */
    @Override
    public Object solve() throws Exception {

        long sum = 0;
        for (int m = 0; m < 10; ++m) {
            for (int n = 0; n < 10; ++n) {
                for (int o = 0; o < 10; ++o) {
                    for (int p = 0; p < 10; ++p) {
                        
                        final int x = m + n + o + p;
                        
                        for (int i = max(0, x - 27); i <= min(9, x); ++i) {
                            for (int j = max(0, x - (i + 18)); j <= min(9, x - i); ++j) {
                                for (int k = max(0, x - (i + j + 9)); k <= min(9, x - (i + j)); ++k) {
                                    
                                    final int l = x - (i + j + k);
                                    
                                    for (int h = max(0, j - l + m - p); h <= min(9, 9 + j - l + m - p); ++h) {
                                    
                                        final int f = 2 * m + n + o - h - k - l;
                                        final int e = j + k - h;
                                        final int d = m + n + o - h - l;
                                        final int c = 2 * m + n - h + j - k - l;
                                        final int b = h - j + k + l - m - n + p;
                                        final int a = h  + l - m;

                                        if (       0 <= f && f <= 9
                                                && 0 <= e && e <= 9
                                                && 0 <= d && d <= 9
                                                && 0 <= c && c <= 9
                                                && 0 <= b && b <= 9
                                                && 0 <= a && a <= 9) ++sum;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // TODO Auto-generated method stub
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 01.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem166());
    }

}
