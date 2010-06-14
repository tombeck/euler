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
package com.thomas.problem147;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem147 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int count = 0;
        
        for (int rows = 1; rows <= 47; ++rows) {
            for (int cols = 1; cols <= 43; ++cols) {
                count += count(rows, cols);
            }
        }
        
        return count;
    }

    private int count(int rows, int cols) {
        
        int count = 0;
        
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (r == c) count += (rows - r) * (cols - c) * (r == 0 ? 1 : 6 * r);
                else if (r + 1 == c) count += (rows - r) * (cols - c) * (r + 2);
                else if (c + 1 == r) count += (rows - r) * (cols - c) * (c + 2);
                else count += (rows - r) * (cols - c);
            }
        }
        
        return count;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem147());
    }

}
