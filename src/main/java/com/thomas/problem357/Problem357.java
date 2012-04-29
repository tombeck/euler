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
package com.thomas.problem357;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 29.04.2012
 */
public class Problem357 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int max = 100000000;
        final boolean[] sieve = new boolean[max + 2];
        
        for (int i = 1, q = 1; (q += (i++ << 1) + 1) < sieve.length; ) {
            if (!sieve[i]) {
                for (int j = q; j < max; j += i) sieve[j] = true;
            }
        }

        long sum = 1;
        
        n: for (int n = 2; n <= max; n += 4) {
            for (int d = 1; d * d <= n; ++d) {
               if (n % d == 0 && sieve[d + (n / d)]) continue n;
            }
            sum += n;
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem357());
    }

}
