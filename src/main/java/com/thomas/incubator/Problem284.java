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
package com.thomas.incubator;

import java.util.Arrays;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.03.2010
 */
class Problem284 implements Problem {

    private static final char[] BASE14 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd'};
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @throws Exception
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.03.2010
     */
    @Override
    public Object solve() {

        for (int i = 0; i < 14; ++i) {
            if (i == (i * i) % 14) System.out.println(i);
        }
        return null;
    }

    private String toBase14(int n) {
    
        StringBuilder builder = new StringBuilder();
        
        for (; n > 0; n /= 14) {
            builder.append(BASE14[n % 14]);
        }
        
        return builder.reverse().toString();
    }
    
    private int toBase10(String s) {
    
        int n = 0;
        
        for (char c : s.toCharArray()) {
            n = n * 14 + Arrays.binarySearch(BASE14, c);
        }
        
        return n;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem284());
    }

}
