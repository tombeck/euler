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
package com.thomas.problem2xx.problem20x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import static java.lang.Math.*;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.11.2009
 */
public class Problem205 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.04.2010
     */
    @Override
    public Double solve() {

        final int[] c = frequencies(new int[37], 6, 6, 0, 0);
        final int[] p = frequencies(new int[37], 9, 4, 0, 0);

        double win = 0.0;
        double cubic = 0;

        for (int i = 0; i < 37; ++i) {
            win += ((double)p[i] / 262144) * cubic;
            cubic += (double)c[i] / 46656;
        }
        
        return (double)round(win * 10000000) / 10000000;
    }

    private int[] frequencies(int[] freq, int dice, int sides, int index, int sum) {

        if (index == dice) ++freq[sum];
        else for (int i = sides; i > 0; --i) {
            frequencies(freq, dice, sides, index + 1, sum + i);
        }
        
        return freq;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 28.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem205());
    }

}
