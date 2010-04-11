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
package com.thomas.problem95;

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
     * @throws InterruptedException 
     * @since 28.11.2009
     */
    public static void main(String[] args) throws InterruptedException {

        final int[] numbers = new int[1000000];
        
        Arrays.fill(numbers, 1);
        numbers[0] = 0;
        for (int n = 2; n < 1000000; ++n) {
            for (int j = 2 * n; j < 1000000; j += n) {
                numbers[j] += n;
            }
        }
        for (int n = 1; n < 1000000; ++n) {
            if (numbers[n] >= 1000000) numbers[n] = 0;
        }
        for (int n = 1; n < 1000000; ++n) {
            final Set<Integer> chain = new HashSet<Integer>();
            
            int x;
            for (x = n; chain.add(x); x = numbers[x]) { /**/ }
            if (x == n) {
                System.out.println(n + " : " + chain);
            }
            Thread.sleep(1);
        }
    }

}
