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
package com.thomas.problem4xx.problem40x;

import java.util.Arrays;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 01.06.2015
 */
public class Problem407 implements Problem {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int N = 10000000;
        
        final long[] M = new long[N+1];
        for (int n = 2; n <= N; ++n) M[n] = 1;
        
        final long[][] divisors = new long[N][];
        divisors[1] = new long[] {1L};

        final int[] biggestPrimeFactor = new int[N];
        
        for (int a = 2; a < N; ++a) {
            if (biggestPrimeFactor[a] == 0) {
                for (int i = a; i < N; i += a) {
                    biggestPrimeFactor[i] = a;
                }
            }
            
            final int prime = biggestPrimeFactor[a];
            final long[] divs = divisors[a / prime];

            divisors[a] = findDivisors(divs, prime);
            
            for (long d1: divisors[a-1]) {
                for (long d2: divisors[a]) {
                    long d = d1 * d2;
                    if (a < d && d <= N) {
                        M[(int)d] = a;
                    }
                }
            }
        }

        long sum = 0;
        
        for (int n = 1; n <= N; ++n) sum += M[n];

        return sum;
    }

    long[] findDivisors(long[] divs, int prime) {
        
        final int size = divs.length;
        final long[] divisors = Arrays.copyOf(divs, size * 2);
        
        for (int j = 0; j < size; ++j) {
            divisors[size + j] = divisors[j] * prime;
        }
        Arrays.sort(divisors);

        return unique(divisors);
    }
    
    long[] unique(long[] arr) {
        
        int dst = 0;
        for (int src = 1; src < arr.length; ++src) {
            if (arr[src] != arr[dst]) {
                ++dst;
                arr[dst] = arr[src];
            }
        }
        
        return Arrays.copyOf(arr, dst + 1);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem407());
    }

}
