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

package com.thomas.problem3xx.problem38x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.PrimeUtils;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 17.01.2014
 */
public class Problem381 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        long sum = 0;
        
        for (int p : PrimeUtils.primes(100000000)) {
            if (p >= 5) {
                sum += ((p*(p + 4L*(9 - p%6)) - 9)/24)%p;
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem381());
    }

}
