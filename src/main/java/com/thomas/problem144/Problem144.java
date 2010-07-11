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
package com.thomas.problem144;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem144 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        double[] a = {0.0, 10.1};
        double[] b = {1.4, -9.6};

        for (int i = 1; ; ++i) {
            
            final double c[] = next(a, b);
            
            if (-0.01 <= c[0] && c[0] <= 0.01 && c[1] > 0) return i; 
            
            a = b;
            b = c;
        }
    }

    private double[] next(double[] a, double[] b) {
        
        double[] i = {a[0] - b[0], a[1] - b[1]};
        
        double m = (-4 * b[0]) / b[1];
        double sin2 = (-2 * m) / (m * m + 1);
        double cos2 = (m * m - 1) / (m * m + 1);
        
        double[] o = {cos2 * i[0] + sin2 * i[1], sin2 * i[0] - cos2 * i[1]};
        
        double q = o[1] / o[0];
        
        double x = ((q * q - 4) * b[0] - 2 * q * b[1]) / (q * q + 4);
        
        double c[] = {x, q * x + b[1] - b[0] * q};
        
        return c;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem144());
    }

}
