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
package com.thomas.problem0xx.problem2x;

import static com.thomas.util.Digit.FACTORIAL;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.12.2009
 */
class Problem24 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.12.2009
     */
    @Override
    public String solve() {

        final List<Integer> digits = new ArrayList<Integer>(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        final StringBuilder builder = new StringBuilder();
        
        for(int index = 1000000; !digits.isEmpty(); ) {
            
            final int factorial = FACTORIAL[digits.size() - 1];
            final int i = (index - 1) / factorial;
            
            builder.append(digits.remove(i));
            index -= i * factorial;
        }
        
        return builder.toString();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem24());
    }

}
