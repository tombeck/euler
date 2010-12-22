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
package com.thomas.problem74;

import static com.thomas.util.Digit.FACTORIAL;

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.10.2009
 */
public class Problem74 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        final Set<Integer> set = new HashSet<Integer>();
        
        for (int n = 0; n < 1000000; ++n) {
            set.clear();
            for (int c = n; set.add(c); c = sumOfFactorials(c)) {
                if (set.size() == 60) {
                    ++sum;
                    break;
                }
            }
        }
        
        return sum;
    }

    private int sumOfFactorials(int n) {
        
        int sum = 0;
        
        for (; n >= 10; n /= 10) {
            sum += FACTORIAL[n % 10];
        }
        sum += FACTORIAL[n];
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem74());
    }

}
