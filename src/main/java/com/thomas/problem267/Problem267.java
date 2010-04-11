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
package com.thomas.problem267;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.round;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 01.02.2010
 */
class Problem267 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 01.02.2010
     */
    @Override
    public Double solve() {

        double min = 0.1;
        double max = 1 - min;
        
        for (int i = 0; i < 10; ++i) { // found 10 by observation
            
            final double mid = (min + max) / 2;
            
            if (dp(mid) < 0) min = mid; else max = mid;
        }
        
        double n = 0;
        
        for (int k = 1000, m = (int)ceil(p((min + max) / 2)); k >= m; --k) {
            n += binomialCoefficient(1000, k);
        }
        
        return (double)round((n / pow(2, 1000)) * 1000000000000L) / 1000000000000L;
    }

    private double f(double x) {
    
        return 9 * log(10) - 1000 * log(1 - x);
    }
    
    private double g(double x) {
        
        return log(1 + 2 * x) - log(1 - x);
    }
    
    private double p(double x) {
    
        return f(x) / g(x);
    }
    
    private double df(double x) {
    
        return 1000 / (1 - x);
    }
    
    private double dg(double x) {
        
        return 3 / ((1 + 2 * x) * (1 - x));
    }
    
    private double dp(double x) {
        
        return (df(x) * g(x) - f(x) * dg(x)) / pow(g(x), 2);
    }

    private double binomialCoefficient(int n, int k) {
        
        if (2 * k > n) k = n - k;
        
        double bc = 1.0;
        
        for (int i = 1; i <= k; ++i) {
            bc = (bc * ((n + 1) - i)) / i;
        }
        
        return bc;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 01.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem267());
    }

}
