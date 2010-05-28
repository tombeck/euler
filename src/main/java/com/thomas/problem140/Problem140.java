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
package com.thomas.problem140;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem140 implements Problem {

    /**
     * A(x) = (x + 3x^2)/(1 - x - x^2)
     * 
     * 5x^2 + 14x + 1 - y^2 = 0 
     * 
     * X0 = 0, Y0 = -1 [152, 343]
     * X0 = 0, Y0 = 1 [296, 665]
     * X0 = -3, Y0 = -2 [21, 50]
     * X0 = -3, Y0 = 2 [5, 14]
     * X0 = -4, Y0 = -5 [42, 97]
     * X0 = -4, Y0 = 5 [2, 7]
     * X0 = 2, Y0 = -7 [2, -7]
     * X0 = 2, Y0 = 7 [2, 7]
     * 
     * Xn+1 = P*Xn + Q*Yn + K, Yn+1 = R*Xn + S*Yn + L
     * 
     * P = -9
     * Q = -4
     * K = -14
     * R = -20
     * S = -9
     * L = -28
     * 
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        Set<Long> s = new TreeSet<Long>();
        
        long[][] y = {{152, 343}, {296, 665}, {21, 50}, {5, 14}, {42, 97}, {2, 7}, {2, -7}};
        
        for (long[] x : y) {
            while (x[0] > 0) {
                s.add(x[0]);
                x = new long[] {-9*x[0] + -4*x[1] + -14, -20*x[0] + -9*x[1] + -28};
                x = new long[] {-9*x[0] + -4*x[1] + -14, -20*x[0] + -9*x[1] + -28};
            }
        }

        long sum = 0;
        int i = 0;
        for (long l : s) {
            sum += l;
            ++i;
            if (i == 30) break;
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem140());
    }

}
