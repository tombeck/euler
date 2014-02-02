/**
 * $Id$
 * 
 * Copyright (c) 2014 Thomas Beckmann
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

package com.thomas.problem3xx.problem35x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 01.02.2014
 */
public class Problem351 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int n = 100000000;
        final int[] sieve = new int[n+1];
        
        for (int i = 1; i <= n; ++i) sieve[i] = i;
        
        long count = 1;

        for (int i = 2; i <= n; ++i) {
            if (sieve[i] == i) {
                for (int j = i; j <= n; j += i) {
                    sieve[j] -= sieve[j] / i;
                }
            }
            count += sieve[i];
        }
        
        return (((n * (n + 1L)) / 2) - count) * 6;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem351());
    }

}
