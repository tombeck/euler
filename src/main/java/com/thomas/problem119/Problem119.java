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
package com.thomas.problem119;

import static com.thomas.util.Digit.sumOfDigits;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 29.12.2009
 */
public class Problem119 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 29.12.2009
     */
    @Override
    public Long solve() {

        /*
         * compute all powers that are less then Long.MAX_VALUE
         * and hope that we get at least 30 that match the condition. 
         */
        final List<Long> numbers = new ArrayList<Long>();
        
        for (long b = 2, n; (n = b) < 100; ++b) {
            for (int p = 2; n <= Long.MAX_VALUE / b; ++p) {
                if (sumOfDigits(n *= b) == b) numbers.add(n);
            }
        }
        
        sort(numbers);

        return numbers.get(29);
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 29.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem119());
    }

}
