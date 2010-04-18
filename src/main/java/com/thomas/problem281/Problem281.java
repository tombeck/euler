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
package com.thomas.problem281;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.asList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 06.03.2010
 */
public class Problem281 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 06.03.2010
     */
    @Override
    public BigInteger solve() {

        final BigInteger max = BigInteger.valueOf(1000000000000000L);
        
        BigInteger sum = ZERO;

        toppings: for (int t = 2; ; ++t) {
            
            final List<BigInteger> cache = new ArrayList<BigInteger>(asList(ZERO));
            
            for (;;) {
                BigInteger f = factorial(t * cache.size()).divide(factorial(cache.size()).pow(t));
                BigInteger count = ZERO;
                for (int d = 1; d < cache.size(); ++d) {
                    if (cache.size() % d == 0) {
                        f = f.subtract(cache.get(d).multiply(BigInteger.valueOf(t * d)));
                    }
                }
                count = f.divide(BigInteger.valueOf(t * cache.size()));
                if (count.compareTo(max) > 0) break;
                cache.add(count);
            }
            for (int s = 1; ; ++s) {
                
                BigInteger count = ZERO;
                
                for (int d = 1; d <= s; ++d) {
                    if (s % d == 0) {
                        if (d >= cache.size() || (count = count.add(cache.get(d))).compareTo(max) > 0) {
                            if (s == 1) break toppings;
                            continue toppings;
                        }
                    }
                }
                sum = sum.add(count);
            }
        }

        return sum;
    }

    private BigInteger factorial(int n) {
        
        BigInteger f = ONE;
        
        while (n > 1) f = f.multiply(BigInteger.valueOf(n--));

        return f;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 06.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem281());
    }

}
