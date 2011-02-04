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
package com.thomas.problem2xx.problem28x;

import static com.thomas.util.NumberUtils.round;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 02.02.2011
 */
public class Problem286 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        for (double l = 50, h = 100, m; ; ) {

            if (prob(m = (l + h) / 2, 20, 1, new double[21][51]) < 0.02) h = m;
            else                                                         l = m;
            
            if (round(l, 10) == round(h, 10)) return round(l, 10);
        }
    }

    private double prob(double q, int p, int x, double[][] prob) {

        if (x == 51) return 1;
        
        if (prob[p][x] != 0) return prob[p][x];
        
        if (p == 0)      return prob[p][x] = (x * prob(q, p, x + 1, prob)) / q;
        if (p == 51 - x) return prob[p][x] =                               ((q - x) * prob(q, p - 1, x + 1, prob)) / q;
        return                  prob[p][x] = (x * prob(q, p, x + 1, prob) + (q - x) * prob(q, p - 1, x + 1, prob)) / q; 
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem286());
    }

}
