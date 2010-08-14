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
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.03.2010
 */
class Problem217 implements Problem {

    /**
     * TODO Method documentation 1901534424
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.03.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int x = 1; x < 10; ++x) { // 1
            sum += x;
        }
        System.out.println(sum); sum = 0;
        for (int a = 1; a < 10; ++a) { // 2
            sum += 11 * a;
        }
        System.out.println(sum); sum = 0;
        for (int x = 0; x < 10; ++x) { // 3
            for (int a = 1; a < 10; ++a) {
                sum += 101 * a + 10 * x;
            }
        }
        System.out.println(sum); sum = 0;
        for (int a = 1; a < 10; ++a) { // 4
            for (int b = 0; b < 10; ++b) {
                for (int c = Math.max(a + b - 9, 0); c <= Math.min(a + b, 9); ++c) {
                    sum += 1001 * a + 101 * b + 9 * c;
                }
            }
        }
        System.out.println(sum); sum = 0;
        for (int x = 0; x < 10; ++x) { // 5
            for (int a = 1; a < 10; ++a) {
                for (int b = 0; b < 10; ++b) {
                    for (int c = Math.max(a + b - 9, 0); c <= Math.min(a + b, 9); ++c) {
                        sum += 10001 * a + 1001 * b + 100 * x + 9 * c;
                    }
                }
            }
        }
        System.out.println(sum); sum = 0;
        for (int a = 1; a < 10; ++a) { // 6
            for (int b = 0; b < 10; ++b) {
                for (int c = 0; c < 10; ++c) {
                    for (int d = 0; d <= 9; ++d) {
                        for (int e = Math.max(a + b + c - d - 9, 0); e <= Math.min(a + b + c - d, 9); ++e) {
                            sum += 100001 * a + 10001 * b + 1001 * c + 99 * d + 9 * e;
                        }
                    }
                }
            }
        }
        //a + b + c - 9 >= d
        System.out.println(sum);
        
        return null;
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
