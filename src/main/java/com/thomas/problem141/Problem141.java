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
package com.thomas.problem141;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 03.04.2010
 */
public class Problem141 implements Problem {

    /**
     * q * d + r = m^2 = n, 0 < r < d < q
     * 
     * q * r = d^2 => q = d^2 / r,   r = d^2 / q
     * 
     * q^2 * d + d^2 = m^2 * q   <=>   d * (q^2 + d) = m^2 * q
     * 
     * d^3 + r^2 = m^2 * r   <=>   d^3 = r * (m^2 - r)
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 03.04.2010
     */
    @Override
    public Long solve() {

        final long max = 1000000;
        
        long sum = 0;

        for (long d = 2; d < max; ++d) {
            sum += find(makeArray(NumberUtils.toLongArray(PrimeUtils.getPrimeFactors(d))), 0, d, 1, 1, max * max);
        }

        return sum;
    }

    private long[][] makeArray(long[] primes) {
    
        Arrays.sort(primes);
        
        final List<long[]> a = new ArrayList<long[]>();
        
        long[] e = new long[2];
        
        for (long p : primes) {
            if (p == e[0]) e[1] += 2;
            else a.add(e = new long[] {p, 2});
        }
        
        return a.toArray(new long[0][]);
    }
    
    private long find(long[][] primes, int i, long d, long r, long q, long max) {
        
        long sum = 0;
        
        if (r < d) {
            if (i == primes.length) {
                
                final long n = d * q + r;
                
                if (n < max && NumberUtils.isSquare(n)) {
                    
                    //System.out.println(String.format("%s + %s * %s = %s^2", r, d, q, (int)Math.sqrt(n)));
                    return n;
                }
                
            } else {
                for (int j = 0; j <= primes[i][1]; ++j) {
                    sum += find(primes, i + 1, d, r * NumberUtils.pow(primes[i][0], j), q * NumberUtils.pow(primes[i][0], (int)primes[i][1] - j), max);
                }
            }
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 03.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem141());
    }

}
