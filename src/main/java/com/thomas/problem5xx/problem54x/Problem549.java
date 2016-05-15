/**
 * $Id$
 *
 * Copyright (c) 2016 Thomas Beckmann
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

package com.thomas.problem5xx.problem54x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @since 14.05.2016
 */
public class Problem549 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int max = 100000000;
        final int[] s = new int[max + 1];
        
        long sum = 0;
        
        for (int i = 2; i <= max; ++i) {
            if (s[i] == 0) {
                int factorial = i;
                int m_i = i << 1;
                for (long p = i, p_i = i; p <= max; p *= i) {
                    for (; p_i < p; m_i += i) {
                        for (int k = m_i; k % i == 0; k /= i) p_i *= i;
                        factorial += i;
                    }
                    for (int j = (int)p; j <= max; j += p) {
                        if (factorial > s[j]) s[j] = factorial;
                    }
                }
            }
            sum += s[i];
        }
        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem549());
    }

}
