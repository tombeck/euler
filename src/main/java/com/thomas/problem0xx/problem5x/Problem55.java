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
package com.thomas.problem0xx.problem5x;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.11.2009
 */
class Problem55 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 05.02.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;

        for (int n = 1; n < 10000; ++n) {
            if (isLychrel(n)) ++sum;
        }
        
        return sum;
    }

    private boolean isLychrel(int n) {

        BigInteger b = BigInteger.valueOf(n);
        final StringBuilder s = new StringBuilder(b.toString());
        s.reverse();
        
        for (int i = 0; i < 50; ++i) {
            b = b.add(new BigInteger(s.toString()));
            s.setLength(0);
            s.append(b.toString()).reverse();
            if (s.toString().equals(b.toString())) return false;
        }

        return true;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 07.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem55());
    }

}
