/**
 * $Id$
 *
 * Copyright (c) 2016 Thomas Beckmann
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

package com.thomas.problem5xx.problem55x;

import static com.thomas.util.Digit.sumOfDigits;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @since 17.05.2016
 */
public class Problem551 implements Problem {

    final int bound = 100000000;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long max = 1000000000000000L;
        
        long h_a = 0;
        long l_a = 1;
        
        for (long n = 2; n <= max; ) {
            
            final int ds_h_a = sumOfDigits(h_a);
            final long[] lower = lower((int)l_a, ds_h_a);
            
            if ((lower[1] + n) > max) {
                for (; n <= max; ++n) {
                    l_a += sumOfDigits(l_a) + ds_h_a;
                }
            } else {
                n += lower[1];
                l_a = lower[0];
                ++h_a;
            }
        }

        return l_a + h_a * bound;
    }

    final long[][][] cache = new long[150][100][];
    
    long[] lower(final int l_a, final int ds_h_a) {
        
        long[] lower =  cache[l_a][ds_h_a];
        
        if (lower == null) {
            
            long n = 0;
            long a = l_a;
            
            for (; a < bound; ++n) {
                a += sumOfDigits(a) + ds_h_a;
            }
            
            lower = new long[] {a - bound, n};
            cache[l_a][ds_h_a] = lower;
        }        
        
        return lower;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem551());
    }

}
