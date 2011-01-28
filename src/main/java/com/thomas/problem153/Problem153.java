/**
 * $Id$
 *
 * Copyright (c) 2010 Thomas Beckmann 
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
package com.thomas.problem153;

import static com.thomas.Util.gcd;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 27.03.2010
 */
public class Problem153 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int max = 100000000;
        
        long sum = 0;
        
        for (int n = 2; n <= max; n += 2) {
            sum += 2 * (max / n) * n;
        }
        for (int n = 1; n <= max; n += 2) {
            sum += (max / n) * n;
        }
        for (int a = 1, aa = 1; 2 * aa <= max; aa += 2 * a++ + 1) {
            for (int b = a + 1, bb = b * b; aa + bb <= max; bb += 2 * b++ + 1) {
                if (gcd(b, a) == 1) {
                    for (int x = 1; x * (aa + bb) <= max; ++x) {
                        sum += 2 * (max / (x * (aa + bb))) * x * (a + b);
                    }
                }
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem153());
    }

}
