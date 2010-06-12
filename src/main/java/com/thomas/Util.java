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
package com.thomas;


/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 17.10.2009
 */
public class Util {

    public static int gcd(int u, int v) {
        
        /* GCD(0,v) := v, GCD(u,0) := u */
        if (u == 0 || v == 0) return u | v;

        int shift;

        /* Let shift := lg K, where K is the greatest power of 2
        dividing both u and v. */
        for (shift = 0; ((u | v) & 1) == 0; ++shift) {
            u >>= 1;
            v >>= 1;
        }

        while ((u & 1) == 0) u >>= 1;

        /* From here on, u is always odd. */
        do {
            while ((v & 1) == 0)  /* Loop X */
              v >>= 1;
    
            /* Now u and v are both odd, so diff(u, v) is even.
               Let u = min(u, v), v = diff(u, v)/2. */
            if (u < v) {
                v -= u;
            } else {
                int diff = u - v;
                u = v;
                v = diff;
            }
            v >>= 1;
        } while (v != 0);
    
        return u << shift;
    }
    
    public static long lcm(long u, long v) {
    
        return (u / gcd(u, v)) * v;
    }
    
    public static long gcd(long u, long v) {
        
        /* GCD(0,v) := v, GCD(u,0) := u */
        if (u == 0 || v == 0) return u | v;

        long shift;

        /* Let shift := lg K, where K is the greatest power of 2
        dividing both u and v. */
        for (shift = 0; ((u | v) & 1) == 0; ++shift) {
            u >>= 1;
            v >>= 1;
        }

        while ((u & 1) == 0) u >>= 1;

        /* From here on, u is always odd. */
        do {
            while ((v & 1) == 0)  /* Loop X */
              v >>= 1;
    
            /* Now u and v are both odd, so diff(u, v) is even.
               Let u = min(u, v), v = diff(u, v)/2. */
            if (u < v) {
                v -= u;
            } else {
                long diff = u - v;
                u = v;
                v = diff;
            }
            v >>= 1;
        } while (v != 0);
    
        return u << shift;
    }
    
    public static boolean isPalindrome(String s) {
    
        return new StringBuffer(s).reverse().toString().equals(s);
    }
    
    public static boolean isPalindrome(int n) {
        
        return isPalindrome(Integer.toString(n));
    }
    
    public static boolean isPalindrome(long n) {
        
        return isPalindrome(Long.toString(n));
    }
    
    private Util() { /* don't instantiate since this is a utility class */ }
    
}
