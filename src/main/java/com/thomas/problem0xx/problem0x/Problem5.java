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
package com.thomas.problem0xx.problem0x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 27.12.2009
 */
public class Problem5 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 27.12.2009
     */
    @Override
    public Integer solve() {

        //  2 = 2^1
        //  3 =       3^1
        //  4 = 2^2
        //  5 =             5^1
        //  6 = 2^1 * 3^1
        //  7 =                   7^1
        //  8 = 2^3
        //  9 =       3^2
        // 10 = 2^1       * 5^1
        // 11 =                         11^1
        // 12 = 2^2 * 3^1
        // 13 =                                13^1
        // 14 = 2^1             * 7^1
        // 15 =       3^1 * 5^1
        // 16 = 2^4
        // 17 =                                       17^1
        // 18 = 2^1 * 3^1
        // 19 =                                              19^1
        // 20 = 2^2       * 5^1
        
        //  n = 2^4 * 3^2 * 5^1 * 7^1 * 11^1 * 13^1 * 17^1 * 19^1
        
        return 2 * 2 * 2 * 2 * 3 * 3 * 5 * 7 * 11 * 13 * 17 * 19;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 27.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem5());
    }

}
