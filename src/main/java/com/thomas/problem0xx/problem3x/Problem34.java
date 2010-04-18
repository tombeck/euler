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
package com.thomas.problem0xx.problem3x;

import static com.thomas.util.Digit.FACTORIAL;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 21.10.2009
 */
public class Problem34 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 13.01.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int x1 = 1; x1 < 10; ++x1) {
            
            final int n1 = FACTORIAL[x1];
            
            for (int x2 = x1; x2 < 10; ++x2) {
            
                final int n2 = n1 + FACTORIAL[x2];
                
                if (n2 > 9 && sumOfFactorials(n2) == n2) sum += n2;
                for (int x3 = x2; x3 < 10; ++x3) {
                
                    final int n3 = n2 + FACTORIAL[x3];
                    
                    if (n3 > 99 && sumOfFactorials(n3) == n3) sum += n3;
                    for (int x4 = x3; x4 < 10; ++x4) {
                    
                        final int n4 = n3 + FACTORIAL[x4];
                        
                        if (n4 > 999 && sumOfFactorials(n4) == n4) sum += n4;
                        for (int x5 = x4; x5 < 10; ++x5) {
                        
                            final int n5 = n4 + FACTORIAL[x5];
                            
                            if (n5 > 9999 && sumOfFactorials(n5) == n5) sum += n5;
                            for (int x6 = x5; x6 < 10; ++x6) {
                            
                                final int n6 = n5 + FACTORIAL[x6];
                                
                                if (n6 > 99999 && sumOfFactorials(n6) == n6) sum += n6;
                                for (int x7 = x6; x7 < 10; ++x7) {
                                
                                    final int n7 = n6 + FACTORIAL[x7];
                                    
                                    if (n7 > 999999 && sumOfFactorials(n7) == n7) sum += n7;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return sum;
    }
    
    private int sumOfFactorials(int n) {
        
        int sum = 0;
        
        for (; n > 0; n /= 10) sum += FACTORIAL[n % 10];

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem34());
    }

}
