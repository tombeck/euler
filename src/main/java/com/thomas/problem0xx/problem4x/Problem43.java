/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
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
package com.thomas.problem0xx.problem4x;

import static com.thomas.util.Permutation.nextPermutation;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 08.11.2009
 */
class Problem43 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 19.01.2010
     */
    @Override
    public Long solve() {

        long sum = 0;
        
        final int[] d = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        do {
            if (getNumber(d[7], d[8], d[9]) % 17 != 0) continue;
            if (getNumber(d[6], d[7], d[8]) % 13 != 0) continue;
            if (getNumber(d[5], d[6], d[7]) % 11 != 0) continue;
            if (getNumber(d[4], d[5], d[6]) %  7 != 0) continue;
            if (getNumber(d[3], d[4], d[5]) %  5 != 0) continue;
            if (getNumber(d[2], d[3], d[4]) %  3 != 0) continue;
            if (getNumber(d[1], d[2], d[3]) %  2 != 0) continue;
            sum += getNumber(d);
        } while (nextPermutation(d));

        return sum;
    }
    
    private long getNumber(int... digits) {
    
        long ret = 0;
        
        for (int digit : digits) {
            ret = ret * 10 + digit;
        }
        
        return ret;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 08.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem43());
    }

}
