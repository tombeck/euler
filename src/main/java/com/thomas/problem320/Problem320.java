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
package com.thomas.problem320;

import static java.lang.Math.min;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 14.02.2011
 */
public class Problem320 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int max = 1000000 + 1;
        final long[] N = new long[max];
        final boolean[] sieve = new boolean[max];

        long sum = 0;
        
        for (int p = 2; p < max; ++p) {
            if (!sieve[p]) {
                
                long s = p;
                
                for (int i = p; i < max; i += p) {
                    
                    s = findSmallest(p, count(p, i) * 1234567890, s);
                    
                    for (int j = min(max, i + p); j-- > i; ) {
                        if (s > N[j]) N[j] = s;
                    }
                    sieve[i] = true;
                }
            }
        }
        for (int i = 10; i < max; ++i) {
            if ((sum += N[i]) >= 1000000000000000000L) sum -= 1000000000000000000L;
        }
        
        return sum;
    }

    private long findSmallest(final int p, final long t, long lo) {

        long hi = p * t;
        
        while (lo + 1 < hi) {
            
            final long mid = (lo + hi) >> 1;
            
            if (count(p, mid) < t) lo = mid;
            else hi = mid;
        }
        
        return hi;
    }
    
    private long count(final int p, long i) {
    
        long count = 0;
        
        while (i >= p) count += (i /= p);

        return count;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem320());
    }

}
