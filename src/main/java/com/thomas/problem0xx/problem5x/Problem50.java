/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
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
package com.thomas.problem0xx.problem5x;

import static com.thomas.util.PrimeUtils.isPrime;
import static com.thomas.util.PrimeUtils.primes;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 10.11.2009
 */
public class Problem50 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws InterruptedException 
     * @since 10.11.2009
     */
    public static void main(String[] args) throws InterruptedException {

        Euler.run(new Problem50());
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 25.01.2010
     */
    @Override
    public Integer solve() {

        final int n = 1000000;
        final List<Integer> primes = primes(n);
        
        int max = 0;
        int len = 0;
        for (int i = 0; max < n; ++i) {
            max += primes.get(i);
            ++len;
        }
        for(;;) {
            max -= primes.get(--len);
            for (int sum = max, shift = 0; shift < primes.size() - len && sum < n; ++shift) {
                if (isPrime(sum, primes)) return sum;
                sum += primes.get(len + shift) - primes.get(shift);
            } 
        }
    }

}
