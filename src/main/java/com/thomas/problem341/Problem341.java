/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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
package com.thomas.problem341;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 19.06.2011
 */
public class Problem341 implements Problem {

    /**
     * > 56098610614277014 in 1800874 ms
     * 
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long max = 1000000L;
        final long[] length = new long[10105269];

        int j = 0;
        long prev = length[0];
        long n = 1;
        long n3 = 1;
        long sum = 0;
        for (int i = 1; n < max && i < length.length; ++i) {
            if (i > length[j]) ++j;
            if ((length[i] = prev += j) >= n3) {
                sum += i;
                n3 += 3 * n * ++n + 1;
            }
        }
        for (long i = length.length; n < max; ++i) {
            if (i > length[j]) ++j;
            if ((prev += j) >= n3) {
                sum += i;
                n3 += 3 * n * ++n + 1;
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem341());
    }

}
