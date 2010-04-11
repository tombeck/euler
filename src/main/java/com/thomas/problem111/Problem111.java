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
package com.thomas.problem111;

import static com.thomas.util.PrimeUtils.isPrime;
import static com.thomas.util.PrimeUtils.primes;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 22.12.2009
 */
class Problem111 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 22.12.2009
     */
    @Override
    public Long solve() {

        final List<Integer> primes = primes(100000);
        
        long sum = 0;
        
        for (int digit = 0; digit < 10; ++digit) {
            long tmp = 0;
            for (int rem = 9; tmp == 0; --rem) {
                for (int i = 1; i < 10; ++i) {
                    tmp += generate(primes, digit, i, i == digit ? rem - 1 : rem, 9);
                }
            }
            sum += tmp;
        }
        
        return sum;
    }

    private long generate(List<Integer> primes, int d, long cur, int rem, int len) {
        
        if (len == 0) return isPrime(cur, primes) ? cur : 0;
        if (rem == len) return generate(primes, d, cur * 10 + d, rem - 1, len - 1);

        long sum = 0;
        
        for (int i = 0; i < 10; ++i) {
            sum += generate(primes, d, cur * 10 + i, i == d ? rem - 1 : rem, len - 1);
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 22.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem111());
    }

}
