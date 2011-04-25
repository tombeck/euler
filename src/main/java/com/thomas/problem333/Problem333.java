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
package com.thomas.problem333;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.PrimeUtils;

/**
 * @author Thomas Beckmann
 * @since 23.04.2011
 */
public class Problem333 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int sum = 5; // 2^1*3^0 + 2^0*3^1
        
        for (int q : PrimeUtils.primes(1000000)) {
            
            final int count = count(q, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
            
            if (count == 1) {
                System.out.println(q);
                sum += q;
            }
        }
        
        return sum;
    }

    private int count(int q, int max2, int max3, int count) {
    
        for (int m3 = 3; m3 < max3 && m3 <= q; m3 *= 3) {
            for (int m2 = 2; m2 < max2 && m2 + m3 <= q; m2 <<= 1) {
                if (m2 + m3 == q) {
                    if (++count > 1) return count;
                } else {
                   
                    int r = (q - (m2 + m3));
                    
                    for (int d3 = 3; d3 < m3; d3 *= 3) {
                        if (r % d3 == 0) {
                            for (int d2 = 2; d2 < m2; d2 <<= 1) {
                                if (r % d2 == 0) {
                                    
                                    final int s = r / (d2 * d3);
                                    
                                    if (s == 1) {
                                        if (++count > 1) return count;
                                    } else {
                                        if ((count = count(s, m2 / d2, m3 / d3, count)) > 1) return count;
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

        Euler.run(new Problem333());
    }

}
