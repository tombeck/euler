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

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 12.11.2009
 */
public class Problem44 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 22.01.2010
     */
    @Override
    public Integer solve() {

        for (int n = 3; ; ++n) {
            for (int j = 1, i; (i = n - j) > j; ++j) {
                
                final int penta_i = penta(i);
                final int penta_j = penta(j);
                
                if (isPenta(penta_i + penta_j)) {
                    
                    final int d = penta_i - penta_j;
                    
                    if (isPenta(d)) return d;
                }
            }
        }
    }
    
    private boolean isPenta(int n) {
        
        int tmp = 24 * n + 1;
        int sqrt = (int)Math.sqrt(tmp);
        if (sqrt * sqrt != tmp) return false;
        return (sqrt + 1) % 6 == 0;
    }
    
    private int penta(int n) {
        
        return ((3 * n - 1) * n) / 2;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 12.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem44());
    }

}
