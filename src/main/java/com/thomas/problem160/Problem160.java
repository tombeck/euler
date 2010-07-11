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

import static com.thomas.util.NumberUtils.modPow;
import static com.thomas.util.NumberUtils.pow;

import com.thomas.util.Euler;
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
        final long max = 1000000000000L;
        
        return (countEven(max, mod) * modPow(2L, (countNumber(max, 2) - countNumber(max, 5)), mod)) % mod;
    }

    private long countEven(long max, int mod) {
        
        if (max == 1) return 1;
        
        return (countOdd(max, mod) * countEven(max / 2, mod)) % mod;
    }

    private long countOdd(long max, int mod) {
        
        if (max <= 1) return 1;

        long n = countOdd(max/5, mod);

        for (long i = 1; i <= (max % mod); i += 10) n = (n * i) % mod;
        for (long i = 3; i <= (max % mod); i += 10) n = (n * i) % mod;
        for (long i = 7; i <= (max % mod); i += 10) n = (n * i) % mod;
        for (long i = 9; i <= (max % mod); i += 10) n = (n * i) % mod;
        
        return n;
    }
    
    private long countNumber(long pow10, int n) {
        
        final int max = (int)((Math.log(pow10)) / Math.log(n));
        
        long count = 0;
        
        for (int i = 1; i <= max; ++i) {
            count += pow10 / pow((long)n, i);
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
