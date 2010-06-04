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
package com.thomas.problem213;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem213 implements Problem {

    static int count = 0;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        double[][] e = new double[30][30];

        for (int i = 0; i < 30; ++i) {
            for (int j = 0; j < 30; ++j) {
                e[i][j] = 1;
            }
        }
        for (int r = 0; r < 30; ++r) {
            for (int s = 0; s < 30; ++s) {
                
                double[][] pp = new double[30][30];
                
                pp[r][s] = 1;
                
                for (int n = 0; n < 50; ++n) {
                    
                    final double[][] pn = new double[30][30];
                    
                    for (int i = 0; i < 30; ++i) {
                        
                        for (int j = 0; j < 30; ++j) {
                            
                            int b = 4;
                            
                            if (i == 0 || i == 29) b -= 1; 
                            if (j == 0 || j == 29) b -= 1;
                            
                            
                            if (i >  0) pn[i - 1][j] += pp[i][j] / b; 
                            if (i < 29) pn[i + 1][j] += pp[i][j] / b; 
                            if (j >  0) pn[i][j - 1] += pp[i][j] / b; 
                            if (j < 29) pn[i][j + 1] += pp[i][j] / b;
                        }
                    }
                    
                    pp = pn;
                }
                for (int i = 0; i < 30; ++i) {
                    for (int j = 0; j < 30; ++j) {
                        e[i][j] *= 1 - pp[i][j];
                    }
                }
            }
        }
        
        double[][] cache = new double[900][900];
        
        for (int i = 0; i < 900; ++i) {
            for (int j = 0; j < 900; ++j) {
                cache[i][j] = -1;
            }
        }
        double exp = 0;
        
        for (int n = 0; n < 900; ++n) {
            exp += prob(e, 0, n, cache) * n;
        }
        
        return Math.round(exp * 1000000) / 1000000.0;
    }

    static int c = 0;
    
    private double prob(double[][] e, int i, int n, double[][] cache) {
    
        if (i == 900) return n == 0 ? 1 : 0;
        
        if (cache[i][n] < 0) {

            cache[i][n] = 0;
            
            final int r = i % 30;
            final int s = i / 30;
            
            cache[i][n] += (1 - e[r][s]) * prob(e, i + 1, n, cache);
            if (n > 0) cache[i][n] += e[r][s] * prob(e, i + 1, n - 1, cache);
        }
        
        return cache[i][n];
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem213());
    }

}
