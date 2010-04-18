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
package com.thomas.problem0xx.problem3x;

import static com.thomas.util.Permutation.previousPermutation;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.11.2009
 */
public class Problem38 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 14.01.2010
     */
    @Override
    public String solve() {

        final char[] c = {'9', '8', '7', '6', '5', '4', '3', '2', '1'};
        
        do {
            lengths: for (int len = 1; len < 6; ++len) {
                
                final int n = Integer.parseInt(String.valueOf(c, 0, len));
                
                String s = String.valueOf(c, len, c.length - len);
                for (int i = 2; s.length() >= len; ++i) {
                    
                    final String tmp = Integer.toString(i * n);
                    
                    if (!s.startsWith(tmp)) continue lengths;
                    s = s.substring(tmp.length(), s.length());
                }
                if (s.length() == 0) return String.valueOf(c);
            }
        } while (previousPermutation(c));
        
        return null;
    }
    
   /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 07.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem38());
    }

}
