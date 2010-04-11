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

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 18.10.2009
 */
public class Problem29 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @throws Exception
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 12.01.2010
     */
    @Override
    public Integer solve() throws Exception {

        final Set<BigInteger> numbers = new HashSet<BigInteger>();
        
        for (int a = 2; a <= 100; ++a) {
            for (int b = 2; b <= 100; ++b) {
                numbers.add(BigInteger.valueOf(a).pow(b));
            }
        }

        return numbers.size();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 18.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem29());
    }

}
