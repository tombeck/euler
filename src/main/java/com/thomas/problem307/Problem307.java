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
package com.thomas.problem307;

import static java.lang.Math.exp;
import static java.lang.Math.log;

import java.math.BigDecimal;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 23.12.2010
 */
public class Problem307 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        final int n = 1000000;
        final int k = 20000;
        
        final BigDecimal[] log_fac = new BigDecimal[n + 1];
                
        log_fac[0] = BigDecimal.ZERO;
        for (int i = 1; i <= n; ++i) {
            log_fac[i] = log_fac[i - 1].add(BigDecimal.valueOf(log(i)));
        }
        
        final BigDecimal x = log_fac[n].add(log_fac[k]).subtract(BigDecimal.valueOf(k).multiply(BigDecimal.valueOf(log(n))));
        final BigDecimal log2 = BigDecimal.valueOf(log(2));
        
        double prob = 1;
        
        for (int m = k / 2; m >= 0; --m) {
            
            final BigDecimal b = log_fac[m].add(log_fac[n - (k - m)]).add(log_fac[k - 2 * m]).add(log2.multiply(BigDecimal.valueOf(m)));
            
            prob -= exp(x.subtract(b).doubleValue());
        }
        
        return (double)Math.round(prob * 10000000000L) / 10000000000L;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem307());
    }

}
