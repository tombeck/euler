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
package com.thomas.incubator;

import static com.thomas.util.NumberUtils.modPow;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 13.03.2010
 */
class Problem250 implements Problem {

    static int x = 0;
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 13.03.2010
     */
    @Override
    public Object solve() {

        final int[] mod = new int[250];
        final long[][] cache = new long[250][251];
        final Map<Integer, long[]> map = new HashMap<Integer, long[]>();
        
        for (int n = 1; n <= 250250; ++n) {
            ++mod[modPow(n, n, 250)];
        }

        Set<Integer> set = new HashSet<Integer>();
        for (int x : mod) {
            set.add(x);
        }
        System.out.println(set);
        return null;
//        return count(cache, mod, 0, 0);
    }

    private BigInteger count(long[][] cache, int[] mod, int min, int rem) {
        
        if (min >= mod.length) {
            return rem == 0 ? BigInteger.ONE : BigInteger.ZERO;
        }
        
        BigInteger count = BigInteger.valueOf(cache[min][rem]);
        
        if (count.equals(BigInteger.ZERO)) {
            System.out.println(++x);
            for (int i = 0; i <= mod[min]; ++i) {
                count = count.add(nCk(mod[min], i).multiply(count(cache, mod, min + 1, (i * min + rem) % 250)));
            }
        }
        cache[min][rem] = count.mod(BigInteger.valueOf(10000000000000000L)).longValue();
        
        return count;
    }
    
    private BigInteger nCk(int n, int k) {
        
        if (k == 0) return BigInteger.ONE;
        if (2 * k > n) return nCk(n, n - k);
        
        BigInteger ret = BigInteger.valueOf(n);
        
        for (int i = 2; i <= k; ++i) {
            ret = ret.multiply(BigInteger.valueOf(n + 1 - i));
            ret = ret.divide(BigInteger.valueOf(i));
        }
        
        return ret.mod(BigInteger.valueOf(10000000000000000L));
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 13.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem250());
    }

}
