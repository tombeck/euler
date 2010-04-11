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
package com.thomas.problem1xx.problem12x;

import static com.thomas.util.PrimeUtils.isPrime;
import static com.thomas.util.PrimeUtils.primes;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 21.03.2010
 */
class Problem128 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public Long solve() {

        final int max = 2000;
        final List<Integer> primes = primes(10000);
        
        int count = 2; // 1 and 2
        
        for (long n = 12; ; n += 6) {
            if ((isPrime(n - 1, primes))) {
                if (isPrime(n + 1, primes) && isPrime(2 * n + 5, primes)) {
                    if (++count == max) return (n * (n - 6)) / 12 + 2;
                }
                if (isPrime(2 * n - 7, primes) && isPrime(n + 5, primes)) {
                    if (++count == max) return (n * (n + 6)) / 12 + 2;
                }
            }
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem128());
    }

}
