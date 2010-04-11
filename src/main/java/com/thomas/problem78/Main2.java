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
package com.thomas.problem78;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 27.11.2009
 */
public class Main2 {

    private static final Map<Integer, Map<Integer, BigInteger>> CACHE = new HashMap<Integer, Map<Integer, BigInteger>>();

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws InterruptedException 
     * @since 27.11.2009
     */
    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i < 2048; ++i) {
            CACHE.remove(i - 1);
            BigInteger sum = BigInteger.ONE;
            add(i, i, BigInteger.ONE);
            for (int k = i; k-- > 1;) {
                if (i >= 2 * k) {
                    sum = sum.add(CACHE.get(i).get(k));
                } else {
                    Thread.sleep(1);
                }
                add(k, i, sum);
            }
            System.out.println(i + " : " + sum);
        }
    }

    private static void add(int k, int n, BigInteger sum) {
    
        Map<Integer, BigInteger> cache = CACHE.get(n + k);
        
        if (cache == null) {
            cache = new HashMap<Integer, BigInteger>();
            CACHE.put(n + k, cache);
        }
        cache.put(k, sum);
    }
    
}
