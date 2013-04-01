/**
 * $Id$
 *
 * Copyright (c) 2013 Thomas Beckmann
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
package com.thomas.problem4xx.problem41x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * 
 * @author <a href="mailto:beckmann.thomas.mail@gmail.com">Thomas Beckmann</a>
 * @since 18.03.2013
 */
public class Problem414 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        long sum = 0;
        
        for (int k = 2; k <= 300; ++k) {
            sum = (sum + S(6 * k + 3)) % 1000000000000000000L;
        }
        
        return sum;
    }

    long S(int base) {
        
        final long[][] cache = new long[base][base];
        
        long S = 0;
        
        for (int d04 = 1; d04 < base; ++d04) {
            S += (base - d04) * s(base, d04,   0, cache) * (20 * d04 - 10);
            for (int d13 = 1; d13 < d04; ++d13) {
                S += (base - d04) * s(base, d04, d13, cache) * (120 * d13 * (d04 - d13) - 20);
            }
            S += (base - d04) * s(base, d04, d04, cache) * (30 * d04 - 10);
        }

        return S - 1;
    }

    long s(int base, int d04, int d13, long[][] cache) {
        
        if (d04 == 0) return 0;
        
        long s = cache[d04][d13];
        
        if (s == 0) {
            if (d13 == 0) {
                if (base > 2 * d04) {
                    s = 1 + s(base, base - d04, d04 - 1, cache);
                } else {
                    s = 1 + s(base, d04 - 1, base - d04, cache);
                }
            } else if (d04 > d13 + 1) {
                if (base > 2*d04) {
                    s = 1 + s(base, base - d13, base - d13 - 1 - d04, cache);
                } else if (base > d04 + d13 + 1) {
                    s = 1 + s(base, base - d13, d04 - d13 - 1, cache);
                } else if (d04 + d13 == base && 3 * d04 == 2 * base) {
                    s = 1;
                } else if (base > d04 + d13 - 1) {
                    s = 1 + s(base, base - d13, 2*d04 - base, cache);
                } else if (base > 2*d13) {
                    s = 1 + s(base, d04 - 1, d04 - d13 + 1, cache);
                } else {
                    s = 1 + s(base, d04 - 1, d04 - base + d13 + 1, cache);
                }
            } else if (d04 > d13) { // d04 = d13 + 1
                if (base > 2*d04) {
                    s = 1 + s(base, base - d13, base - 2*d04, cache);
                } else if (base > 2*d13) {
                    s = 1 + s(base, d04, d04 - d13, cache);
                } else {
                    s = 1 + s(base, d13, 2 * d04 - base, cache);
                }
            } else { // d04 = d13
                if (base > 2*d04) {
                    s = 1 + s(base, base - d04, base - 2*d04, cache);
                } else {
                    s = 1 + s(base, d04, 2*d04 - base, cache);
                }
            }
            cache[d04][d13] = s;
        }
        
        
        return s;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem414());
    }

}
