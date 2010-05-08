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
package com.thomas.problem127;

import static com.thomas.Util.gcd;
import static com.thomas.util.NumberUtils.radical;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.01.2010
 */
public class Problem127 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.01.2010
     */
    @Override
    public Integer solve() {

        final int max = 120000;
        
        final int[] rad = new int[max];
        
        for (int i = 1; i < max; ++i) rad[i] = radical(i);
        
        int sum = 0;
        
        for (int c = max; c-- > 1; ) {
            
            final int t = c / rad[c];
            
            if (t > 2) for (int a = 1, b; (b = c - a) > a; ++a) {
                if ((long)rad[a] * rad[b] < t && gcd(rad[a], rad[b]) == 1) {
                    sum += c;
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
     * @since 16.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem127());
    }

}