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

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.10.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.10.2009
     */
    public static void main(String[] args) {

        int sum = 0;

        for (int i = 0; i < 1000000; ++i) {
            if (isMatch(new ArrayList<Integer>(), i)) ++sum;
        }
        
        System.out.println(sum);
    }

    private static boolean isMatch(List<Integer> prev, int c) {
        
        if (prev.contains(c)) return false;
        prev.add(c);
        
        if (prev.size() == 60) return true;
        
        int next = 0;
        for (char ch : String.valueOf(c).toCharArray()) {
            next += FACTORIAL[Character.digit(ch, 10)];
        }

        return isMatch(prev, next);
    }
    
}
