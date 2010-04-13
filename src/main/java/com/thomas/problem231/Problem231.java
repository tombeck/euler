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
package com.thomas.problem231;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.01.2010
 */
class Problem231 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws InterruptedException 
     * @since 23.01.2010
     */
    @Override
    public Object solve() throws InterruptedException {

        final List<Integer> primes = PrimeUtils.primes(4473);
        
        int n = 20000000;
        int k = n - 15000000;
        
        long[] cache = new long[k + 1];
        
        long sum = 0;
        
        for (int i = 1; i <= k; ++i) {
            int num = (n + 1) - i;
            int den = i;
            for (int p : PrimeUtils.getPrimeFactors(num, primes)) {
                if (p > k) sum += p;
                else ++cache[p];
            }
            for (int p : PrimeUtils.getPrimeFactors(den, primes)) {
                --cache[p];
            }
            System.out.println(i);
            if (i % 200 == 0) Thread.sleep(1);
        }
        
        for (int i = 1; i <= k; ++i) {
            sum += i * cache[i];
        }

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem231());
    }

}
