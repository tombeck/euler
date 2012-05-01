/**
 * $Id$
 *
 * Copyright (c) 2012 Thomas Beckmann
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
package com.thomas.problem347;

import static com.thomas.util.NumberUtils.toIntArray;
import static com.thomas.util.PrimeUtils.primes;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * @author Thomas Beckmann
 * @since 30.04.2012
 */
public class Problem347 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int N = 10000000;
        final int[] primes = toIntArray(primes((N >> 1) + 1));
        
        long S = 0;
        
        for (int i = 1; i < primes.length; ++i) {
            for (int j = 0; j < i; ++j) {
                
                final long p = primes[i];
                final long q = primes[j];
                
                long M = 0;
                
                for (long k = p * q; k <= N; k *= p) {
                    for (long l = k; l <= N; l *= q) {
                        if (l > M) M = l;
                    }
                }
                if (M == 0) break;
                
                S += M;
            }
        }
        
        return S;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem347());
    }

}
