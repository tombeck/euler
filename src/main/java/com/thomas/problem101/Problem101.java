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
package com.thomas.problem101;

import static java.math.BigInteger.ONE;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 08.01.2010
 */
public class Problem101 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 08.01.2010
     */
    @Override
    public Long solve() {

        final long[] u = {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
        
        long sum = 0;
        
        for (int k = 1; k <= 10; ++k) sum += fit(u, op(k, u));

        return sum;
    }

    private long fit(long[] op, long[] bop) {
        
        for (int n = 1; ; ++n) {
            
            final long fit = poly(bop, n);
            
            if (poly(op, n) != fit) return fit;
        }
    }
    
    private long[] op(int k, long[] u) {
    
        final long[] p = new long[k];
        final BigInteger[][] m = createMatrix(k, u);
        
        normalize(m);
        
        for (int i = 0; i < k; ++i) {
            p[i] = m[i][k].longValue();
        }
        
        return p;
    }
    
    private BigInteger[][] createMatrix(int n, long[] u) {
    
        final BigInteger[][] m = new BigInteger[n][n + 1];
        
        for (int i = 0; i < n; ++i) {
            m[i][n] = BigInteger.valueOf(poly(u, i + 1));
            long p = 1;
            for (int j = n; j-- > 0; ) {
                m[i][j] = BigInteger.valueOf(p);
                p *= i + 1;
            }
        }
        
        return m;
    }
    
    private long poly(long[] p, long n) {
    
        long f = 0;
        
        for (long c : p) f = c + n * f;
        
        return f;
    }
    
    private void normalize(BigInteger[][] m) {
    
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m.length; ++j) {
                if (j != i) {
                    BigInteger gcd = m[i][i].gcd(m[j][i]);
                    BigInteger fi = m[j][i].divide(gcd);
                    BigInteger fj = m[i][i].divide(gcd);
                    for (int k = 0; k < m[i].length; ++k) {
                        m[j][k] = m[i][k].multiply(fi).subtract(m[j][k].multiply(fj));
                    }
                }
            }
        }
        for (int i = 0; i < m.length; ++i) {
            m[i][m[i].length - 1] = m[i][m[i].length - 1].divide(m[i][i]);
            m[i][i] = ONE;
        }
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 08.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem101());
    }

}
