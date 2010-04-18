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
package com.thomas.problem0xx.problem1x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 18.10.2009
 */
public class Problem17 implements Problem {

    private static final String[] WORDS1 = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };
    private static final String[] WORDS10 = {
        "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 30.12.2009
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int i = 1; i <= 1000; ++i) sum += countWords(i);
        
        return sum;
    }

    private int countWords(int n) {
        
        if (n == 1000) {
            
            return WORDS1[1].length() + "thousend".length();
        }
        if (n >= 100) {
            
            final int rest = n % 100;
            
            if (rest == 0) return WORDS1[n / 100].length() + "hundred".length();
            
            return WORDS1[n / 100].length() + "hundredand".length() + countWords(rest);
        }
        if (n >= 20) {
            
            final int rest = n % 10;
            
            if (rest == 0) return WORDS10[n /10].length();
            
            return WORDS10[n / 10].length() + countWords(rest);
        }
        
        return WORDS1[n].length();
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 18.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem17());
    }

}
