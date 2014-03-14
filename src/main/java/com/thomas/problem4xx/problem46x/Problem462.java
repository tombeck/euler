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

package com.thomas.problem4xx.problem46x;

import static com.thomas.util.NumberUtils.round;
import static java.lang.Math.exp;
import static java.lang.Math.log;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 09.03.2014
 */
public class Problem462 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public String solve() {

        long N = 1000000000000000000L;
        
        double m = 0;
        for (int n = 0, i = 0; ; ++i) {
            int a = (int)((log(N) - i * log(3))/log(2));
            for (int j = 0; j <= a; ++j) {
                n += 1;
                m += log(n) - log((int)((log(N) - i * log(3) - j * log(2))/log(3)) + (a + 1 - j));
            }
            if (a == 1) break;
        }
        int e = (int)(m / log(10));
        
        return round(exp(m - e * log(10)), 10) + "e" + e;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem462());
    }

}
