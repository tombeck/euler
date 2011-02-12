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
package com.thomas.problem323;

import static com.thomas.util.NumberUtils.nCk;
import static com.thomas.util.NumberUtils.round;
import static java.lang.Math.pow;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 12.02.2011
 */
public class Problem323 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        double e = 0;
        
        for (int n = 1; ; ++n) {
            
            double prev = e;
            double prob = 0;
            
            for (int i = 1; i <= 32; ++i) {
                prob += pow(pow(0.5, n), i) * pow((1 - pow(0.5, n - 1)), 32 - i) * nCk(32L, i);
            }
            
            e += n * prob;
            
            if (round(prev, 10) == round(e, 10)) return round(e, 10);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem323());
    }

}
