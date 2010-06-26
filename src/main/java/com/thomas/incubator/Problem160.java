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

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 02.04.2010
 */
class Problem160 implements Problem {

    /**
     * TODO Method documentation
     * 
     * f(100) = 16864
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.04.2010
     */
    @Override
    public Object solve() {

        long n = 1;
        
        for (int i = 1; i <= 1000000; ++i) {
            
            long tmp = removeTrailingZeros(i);
            int pow = 5 + Math.max(count(tmp, 2), count(tmp, 5));
            tmp %= NumberUtils.pow(10L, pow);
            n *= tmp;
            n = removeTrailingZeros(n);
            n %= 100000;
        }
        
        System.out.println(n);
        
        return null;
    }

    private int count(long n, int x) {
    
        int count = 0;
        
        for (; n % x == 0; n /= x) ++count;
        
        return count;
    }
    
    private long removeTrailingZeros(long n) {
        
        while(n % 10 == 0) n /= 10;
        
        return n;
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
