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
     * 1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
     * 1  2  2  3  3  4  4  4  5  5  5  6  6  6  6  7  7  7  7
     * 
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long max = 1000000L;
        final long[] length = new long[10105257];

        long sum = 0;

        long g = 0;
        long l = 0;
        long n = 1;
        long n3 = 1;
        for (int j = 0, i = 1; ; ++i) {
            
            if (i > length[j]) ++j;
            length[i] = length[i - 1] + j;
            
            final long tmp = (n3 - l + i - 1) / i;
            
            if (tmp <= j) {
                sum += g + tmp;
                n3 += 3 * n * ++n + 1;
                if (n >= max) return sum;
            }
            l += (long)j * i;
            g += j;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem341());
    }

}
