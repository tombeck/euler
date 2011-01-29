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
package com.thomas.problem218;

import static com.thomas.Util.gcd;
import static java.lang.Math.abs;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem218 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int x = 1; x < 10000; ++x) {
            for (int y = (x & 1) + 1; y < x && x * x + y * y <= 100000000; y += 2) { // opposite parity
                if (gcd(x, y) == 1) { // relatively prime
                    
                    final int m = (2 * x * y) % 84;
                    final int n = (x * x - y * y) % 84; // 84 = lcm(6, 28)
                    
                    final int a = m * n;
                    final int b = abs(m * m - n * n);

                    if ((a * b) % 6 != 0 || (a * b) % 28 != 0) ++sum;
                }
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem218());
    }

}
