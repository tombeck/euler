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
package com.thomas.problem145;

import com.thomas.util.ArrayUtils;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 25.11.2009
     */
    public static void main(String[] args) {

        int sum = 0;
        for (int n = 100000; n < 1000000; ++n) {
            if (n % 10 != 0) { 
                char[] tmp = Integer.toString(n).toCharArray();
                ArrayUtils.reverse(tmp, 0, tmp.length);
                int r = Integer.parseInt(String.valueOf(tmp));
                if (odd(n + r)) {
                    ++sum;
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean odd(int n) {
    
        for (char ch: Integer.toString(n).toCharArray()) {
            if ((Character.digit(ch, 10) & 1) == 0) return false;
        }
        
        return true;
    }
    
}
