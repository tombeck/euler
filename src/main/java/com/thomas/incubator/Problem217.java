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

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.03.2010
 */
class Problem217 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.03.2010
     */
    @Override
    public Object solve() {

        final BigInteger[][] cache = new BigInteger[22 * 9 + 1][22 + 1];
        
        for (int sum = 0; sum <= 9 * 22; ++sum) {
            System.out.println(sum + ": " + count(cache, sum, 22) + ": " + countWithoutLeadingZero(cache, sum, 22));
        }
        
        return null;
    }

    
    private BigInteger count(BigInteger[][] cache, int sum, int digits) {
        
        if (digits == 0) return sum == 0 ? BigInteger.ONE : BigInteger.ZERO;
        
        if (cache[sum][digits] == null) {
            cache[sum][digits] = BigInteger.ZERO;
            for (int d = 0; d <= Math.min(9, sum); ++d) {
                cache[sum][digits] = cache[sum][digits].add(count(cache, sum - d, digits - 1));
            }
        }
        
        return cache[sum][digits];
    }
    
    private BigInteger countWithoutLeadingZero(BigInteger[][] cache, int sum, int digits) {
        
        BigInteger count = BigInteger.ZERO;
        
        for (int d = 1; d <= Math.min(9, sum); ++d) {
            count = count.add(count(cache, sum - d, digits - 1));
        }
        
        return count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem217());
    }

}
