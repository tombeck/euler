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
package com.thomas.problem235;

import static java.lang.Math.pow;
import static java.lang.Math.round;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 03.01.2010
 */
class Problem235 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 03.01.2010
     */
    @Override
    public Double solve() {

        double min = 1.0;
        double max = 1.1;
        
        do {
            
            final double mid = (min + max) / 2;
            final double s = s(mid, 5000);

            if (s > -600000000000.0) min = mid;
            else max = mid;
            
        } while (round(min * 1000000000000L) != round(max * 1000000000000L));
        
        return (double)round(min * 1000000000000L) / 1000000000000L;
    }

    private double s(double r, int n) {
        
        double sum = 0.0;
        
        for (int k = 1; k <= n; ++k) {
            sum += (900 - 3 * k) * pow(r, k - 1);
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 03.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem235());
    }

}
