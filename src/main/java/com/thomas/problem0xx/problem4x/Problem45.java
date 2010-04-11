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

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.11.2009
 */
class Problem45 implements Problem {

    /**
     * Each hexagonal number is also triangular:
     * T(2n - 1) = ((2n - 1)((2n - 1) + 1))/2 = n(2n - 1) = H(n)
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 24.01.2010
     */
    @Override
    public Long solve() {

        final Set<Long> hexagonal = new HashSet<Long>();
        
        for (long n = 144, p; ; ++n) {
            if (hexagonal.contains(p = (n * (3 * n - 1)) / 2)) return p;
            hexagonal.add(n * (2 * n - 1));
        }
    }

    /**
     * @param args
     * @author Thomas
     * @since 07.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem45());
    }

}
