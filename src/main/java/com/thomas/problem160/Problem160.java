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
package com.thomas.problem160;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 02.04.2010
 */
public class Problem160 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.04.2010
     */
    @Override
    public Long solve() {

        final int mod = 100000;
        final int pow = 12;
        final long max = NumberUtils.pow(10L, pow);
        
        long n = count(max, mod);

        n *= NumberUtils.modPow(2L, (count2(pow)-count5(pow)), mod);
        n %= mod;
        
        return n;
    }

    private long count(long max, int mod) {
        
        if (max == 1) return 1;
        
        return (countOdd(max, mod) * count(max/2, mod)) % mod;
    }

    private long countOdd(long max, int mod) {
        
        if (max <= 1) return 1;

        long n = countOdd(max/5, mod) % mod;
        long m = max / mod;

        if (m > 0) {
            long s = 1;
            for (long i = 1; i <= mod; i += 10) {
                s *= i % mod;
                s %= mod;
            }
            for (long i = 3; i <= mod; i += 10) {
                s *= i % mod;
                s %= mod;
            }
            for (long i = 7; i <= mod; i += 10) {
                s *= i % mod;
                s %= mod;
            }
            for (long i = 9; i <= mod; i += 10) {
                s *= i % mod;
                s %= mod;
            }
            for (; m > 0; --m) {
                n *= s;
                n %= mod;
            }
        }
        for (long i = 1; i <= (max % mod); i += 10) {
            n *= i % mod;
            n %= mod;
        }
        for (long i = 3; i <= (max % mod); i += 10) {
            n *= i % mod;
            n %= mod;
        }
        for (long i = 7; i <= (max % mod); i += 10) {
            n *= i % mod;
            n %= mod;
        }
        for (long i = 9; i <= (max % mod); i += 10) {
            n *= i % mod;
            n %= mod;
        }
        
//        CACHE.put(max, n);
        
        return n;
    }
    
    private long count2(int r) {
    
        final int max = (int)Math.floor((r * Math.log(10)) / Math.log(2));
        final long pow10 = NumberUtils.pow(10L, r);
        
        long count = 0;
        
        for (int i = 1; i <= max; ++i) {
            count += pow10 / NumberUtils.pow(2L, i);
        }
        
        return count;
    }
    
    private long count5(int r) {
        
        final int max = (int)Math.floor((r * Math.log(10)) / Math.log(5));
        final long pow10 = NumberUtils.pow(10L, r);
        
        long count = 0;
        
        for (int i = 1; i <= max; ++i) {
            count += pow10 / NumberUtils.pow(5L, i);
        }
        
        return count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 02.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem160());
    }

}
