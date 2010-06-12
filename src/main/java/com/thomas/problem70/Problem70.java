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
package com.thomas.problem70;

import static com.thomas.util.Permutation.isPermutation;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem70 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int max = 10000000;
        final int[] totient = new int[max];
        
        int min = 2;
        
        for (int n = 1; n < max; ++n) totient[n] = n;

        for (int n = 2; n < max; ++n) {
            if (totient[n] == n) {
                for (int i = n; i < max; i += n) {
                    totient[i] -= totient[i] / n;
                }
            }
            if ((long)n * totient[min] < (long)min * totient[n] && isPermutation(n, totient[n])) {
                min = n;
            }
        }

        return min;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem70());
    }

}
