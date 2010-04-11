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
package com.thomas.problem203;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 28.11.2009
     */
    public static void main(String[] args) {

        final long[] row = new long[51];
        final Set<Long> numbers = new HashSet<Long>();
        
        Arrays.fill(row, 1L);
        numbers.add(1L);
        
        for (int i = 0; i < 51; ++i) {
            for (int j = i; j-- > 1; ) {
                row[j] = row[j - 1] + row[j];
                numbers.add(row[j]);
            }
        }
        long sum = 0;
        for (long number : numbers) {
            if (isSquareFree(number)) sum += number;
        }
        System.out.println(sum);
    }

    public static boolean isSquareFree(long n) {
        
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                n /= i;
                if (n % i == 0) return false;
            }
        }

        return true;
    }

}
