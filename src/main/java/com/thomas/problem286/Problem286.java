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
package com.thomas.problem286;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.fill;

import java.math.BigDecimal;
import java.math.BigInteger;

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
    public Object solve() {

        final BigInteger[][][] cache = new BigInteger[21][51][];
        final BigDecimal two = BigDecimal.valueOf(2L);
        
        final BigInteger[] poly = prob(20, 50, cache);
        final BigInteger[] den = zero(51);
        
        den[50] = BigInteger.ONE;
        
        final BigInteger[] norm = add(den, mul(poly, polynom(-50)));
        
        for (BigDecimal low = BigDecimal.valueOf(50), high = BigDecimal.valueOf(100), mid; ; ) {

            if (poly(norm, (mid = low.add(high).divide(two))).signum() < 0) low = mid;
            else high = mid;
            
            if ((low.setScale(10, HALF_UP).compareTo(high.setScale(10, HALF_UP)) == 0)) return low.setScale(10, HALF_UP);
        }
    }

    private BigInteger[] prob(final int points, final int shots, final BigInteger[][][] cache) {
    
        if (cache[points][shots] == null) {
            
            final int x = 50 - (shots -1);
            
            if (shots == 0) {
                cache[points][shots] = polynom(1);
            } else if (points == 0) {
                cache[points][shots] = mul(polynom(x), prob(points, shots - 1, cache));
            } else if (points == shots) {
                cache[points][shots] = mul(polynom(-x, 1), prob(points - 1, shots - 1, cache));
            } else {
                cache[points][shots] = add(mul(polynom(x), prob(points, shots - 1, cache)), mul(polynom(-x, 1), prob(points - 1, shots - 1, cache))); 
            }
        }
        
        return cache[points][shots];
    }
    
    private BigDecimal poly(BigInteger[] poly, BigDecimal x) {
        
        BigDecimal ret = BigDecimal.ZERO;
        
        for (int i = 0; i < poly.length; ++i) {
            ret = ret.add(x.pow(i).multiply(new BigDecimal(poly[i])));
        }
        
        return ret;
    }
    
    private BigInteger[] polynom(int... a) {
        
        final BigInteger[] polynom = new BigInteger[a.length];
        
        for (int i = 0; i < a.length; ++i) {
            polynom[i] = BigInteger.valueOf(a[i]);
        }
        
        return polynom;
    }
    
    private BigInteger[] add(BigInteger[] a, BigInteger[] b) {
        
        if (a.length < b.length) return add(b, a);
        
        final BigInteger[] s = a.clone();
        
        for (int i = 0; i < b.length; ++i) {
            s[i] = s[i].add(b[i]);
        }
        
        return s;
    }
    
    private BigInteger[] mul(BigInteger[] a, BigInteger[] b) {
        
        final BigInteger[] p = zero(a.length + b.length - 1);
        
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                p[i + j] = p[i + j].add(a[i].multiply(b[j]));
            }
        }
        
        return p;
    }
    
    private BigInteger[] zero(int len) {
        
        final BigInteger[] p = new BigInteger[len];
        
        fill(p, BigInteger.ZERO);
        
        return p;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem286());
    }

}
