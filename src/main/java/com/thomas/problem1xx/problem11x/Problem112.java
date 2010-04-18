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
package com.thomas.problem1xx.problem11x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 19.11.2009
 */
public class Problem112 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int n = 1; ; ++n) {
            if (isBouncy(n)) ++sum;
            if (sum * 100 == n * 99) return n;
        }
    }
    
    private boolean isBouncy(int n) {
        
        final char[] a = Integer.toString(n).toCharArray();
        
        for (int i = 1, flag = 3; i < a.length; ++i) {
            if (a[i] < a[i - 1]) flag &= 1; 
            if (a[i] > a[i - 1]) flag &= 2;
            if (flag == 0) return true;
        }

        return false;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 19.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem112());
    }

}
