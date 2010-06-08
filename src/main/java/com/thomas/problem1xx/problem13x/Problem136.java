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
package com.thomas.problem1xx.problem13x;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.03.2010
 */
public class Problem136 implements Problem {

    /**
     * x^2 - y^2 - z^2 = n
     * n < 50 * 10^6
     * x > y > z > 0
     * 
     * x = y + r > y => r > 0
     * z = y - r > 0 => r < y   0 < r < y
     * 
     * x^2 - y^2 - z^2 = (y + r)^2 - y^2 - (y - r)^2 = 4ry - y^2 = (2r)^2 - (2r - y)^2 = n
     * 
     * =>  y^2 - 4ry + n = 0
     * =>  y = 2r +- sqrt(4*r^2 - n)
     * 
     * 4*r^2 - n = d^2 <=> 4*r^2 - d^2 = n <=> (2r + d)(2r - d) = n = pq <=> 4r = p + q, d = (p - q)/2, p >= q
     * 
     * y = 2r +- sqrt(4*r^2 - n) = (p + q)/2 +- sqrt(4*((p + q)/4)^2 - pq) = (p + q)/2 +- (p - q)/2 
     * 
     * y1 = (p + q + p - q)/2 = p
     * y2 = (p + q - p + q)/2 = q
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.03.2010
     */
    @Override
    public Integer solve() {

        final int max = 50000000;
        final int[] solutions = new int[max];
        
        for (int p = 1, s = (int)ceil(sqrt(max)); p < s; ++p) {
            for (int q = p + (p & 1) * 2, n; (n = p * q) < max; q += 4) {
                solutions[n] += (p < q && q < 3 * p) ? 2 : 1;
            }
        }
        
        int count = 0;
        
        for (int i = 0; i < max; ++i) {
            if (solutions[i] == 1) ++count;
        }
        
        return count;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem136());
    }

}
