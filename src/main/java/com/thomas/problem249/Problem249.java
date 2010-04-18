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
package com.thomas.problem249;

import static com.thomas.util.PrimeUtils.primes;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 26.12.2009
 */
public class Problem249 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 26.12.2009
     */
    @Override
    public Long solve() {

        final List<Integer> primes = primes(5000);

        int max = 0;
        for (int i : primes) max += i;

        final long[] cache = new long[max + 1];
        cache[0] = 1;

        max = 0;
        for (int prime : primes) {
            for (int j = (max += prime); j >= prime; --j) {
                if ((cache[j] += cache[j - prime]) >= 10000000000000000L) {
                    cache[j] -= 10000000000000000L;
                }
            }
        }
        
        long count = 0;
        
        for (int prime : primes(max + 1)) {
            if ((count += cache[prime]) >= 10000000000000000L) {
                count -= 10000000000000000L;
            }
        }
        
        return count;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem249());
    }

}
