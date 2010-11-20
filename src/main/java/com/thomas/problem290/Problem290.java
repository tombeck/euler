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
package com.thomas.problem290;

import static com.thomas.util.Digit.sumOfDigits;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem290 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        return count(18, 0, 0, new Long[18][137][2 * 9 * 18]);
    }

    private long count(int length, int carry, int diff, Long[][][] cache) {
    
        if (length-- == 0) return sumOfDigits(carry) == diff ? 1 : 0;

        final int key = diff + 9 * 18;
        
        Long count = cache[length][carry][key];
        
        if (count == null) {
            count = 0L;
            for (int d = 0; d < 10; ++d) {
                
                final int x = d * 137 + carry;
                
                count += count(length, x / 10, diff + d - x % 10, cache);
            }
        }
        
        return cache[length][carry][key] = count;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem290());
    }

}
