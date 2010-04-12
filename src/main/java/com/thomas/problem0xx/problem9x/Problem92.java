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
package com.thomas.problem0xx.problem9x;

import static com.thomas.util.Digit.sumOfDigitSquares;
import static com.thomas.util.Permutation.count;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.10.2009
 */
class Problem92 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 12.03.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int i1 = 9; i1 > 0; --i1) {
            for (int i2 = i1; i2 >= 0; --i2) {
                for (int i3 = i2; i3 >= 0; --i3) {
                    for (int i4 = i3; i4 >= 0; --i4) {
                        for (int i5 = i4; i5 >= 0; --i5) {
                            for (int i6 = i5; i6 >= 0; --i6) {
                                for (int i7 = i6; i7 >= 0; --i7) {
                                    if (is89(i1, i2, i3, i4, i5, i6, i7)) {
                                        sum += count(i1, i2, i3, i4, i5, i6, i7);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return sum;
    }
    
    private boolean is89(int... c) {
    
        for (int next ; ; ) {

            next = sumOfDigitSquares(c);
            
            if (next == 89) return true;
            if (next ==  1) return false;
            
            for (int i = 0; i < c.length; ++i) {
                c[i] = next % 10;
                next /= 10;
            }
        }        
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem92());
    }

}
