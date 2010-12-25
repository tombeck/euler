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
package com.thomas.incubator;

import static java.lang.Math.pow;
import static java.lang.Math.round;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 23.12.2010
 */
public class Problem307 implements Problem {

    /**
     * {@inheritDoc}
     * 
     *   0.7311720251
     * > 0.7311720252 in 231939 ms
     * > 0.1229520503 in 3611 ms
     */
    @Override
    public Object solve() {

        final int n_max = 100000;
        final int k_max = 2000;
        
        double[] p0 = new double[k_max + 1];
        double[] p1 = new double[k_max + 1];
        
        p0[1] = p0[2] = 1;
        p1[1] = p1[2] = 1;

        for (long n = 1; n <= n_max; n += 2) {
            
            final double a1 = 1.0 / n;
            final double b1 = 1 - a1;
            
            double pow1 = 1;
            int sum1 = 1;
            
            for (int k = 3; k <= k_max; ++k) {
                pow1 *= b1;
                sum1 += k - 1;
                p1[k] = pow1 * (b1 * b1 * p0[k] + a1 * b1 * k * p0[k - 1] + a1 * a1 * sum1 * p0[k - 2]) ;
            }
            
            System.out.println(n + ", " + (1 - p1[k_max]));
            
            final double a2 = 1.0 / (n + 1);
            final double b2 = 1 - a2;
            
            double pow2 = 1;
            int sum2 = 1;
            
            for (int k = 3; k <= k_max; ++k) {
                pow2 *= b2;
                sum2 += k - 1;
                p0[k] = pow2 * (
                        b2 * b2 * p1[k] +
                        a2 * b2 * k * p1[k - 1] +
                        a2 * a2 * sum2 * p1[k - 2]) ;
            }

//            final double[] tmp = p0;
//            
//            p0 = p1;
//            p1 = tmp;

            System.out.println((n + 1) + ", " + (1 - p0[k_max]));
        }
        
        return (double)round((1 - p0[k_max]) * 10000000000L) / 10000000000L;
    }

    private double p(int k, int n) {
        
        if (k < 3) return 1;
        if (n == 1) return 0;

        final double a = 1.0 / n;
        final double b = 1 - a;
        
        return pow(b, k - 2) * (
            + b * b * p(k,     n - 1)
            + a * b * p(k - 1, n - 1) * k
            + a * a * p(k - 2, n - 1) * ((k * (k - 1)) / 2)
            );
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem307());
    }

}
