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
package com.thomas.problem1xx.problem18x;

import static com.thomas.Util.gcd;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 18.01.2014
 */
public class Problem182 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int p = 1009;
        final int q = 3643;
        final int phi = (p - 1) * (q - 1);
        
        long sum = 0;
        
        for (int e = 2; e < phi; ++e) {
            if (gcd(e, phi) == 1 && (1 + gcd(e-1, p-1)) * (1 + gcd(e-1, q-1)) == 9) {
                sum += e;
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem182());
    }

}
