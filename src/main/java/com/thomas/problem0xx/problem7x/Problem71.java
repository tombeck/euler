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
package com.thomas.problem0xx.problem7x;

import static com.thomas.util.rational.IntRational.ZERO;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.rational.IntRational;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem71 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        IntRational max = ZERO;
        
        for (int den = 1; den <= 1000000; ++den) {
            
            final IntRational y = new IntRational((3 * den - 1) / 7, den);
            
            if (y.compareTo(max) > 0) max = y;
        }

        return max.numerator();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem71());
    }

}
