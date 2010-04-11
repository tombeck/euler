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
package com.thomas.util;

import static java.lang.Integer.numberOfTrailingZeros;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.03.2010
 */
public class BitUtils {

    public static int swapBits(int b, int i, int j) {
        
        final int x = ((b >> i) ^ (b >> j)) & 1; // XOR temporary

        return b ^ ((x << i) | (x << j));
    }
 
    public static int nextPermutation(int v) {
        
        final int t = v | (v - 1); // t gets v's least significant 0 bits set to 1
        // Next set to 1 the most significant bit to change, 
        // set to 0 the least significant ones, and add the necessary 1 bits.
        return (t + 1) | (((~t & -~t) - 1) >> (numberOfTrailingZeros(v) + 1));  
    }
    
    public static int rotateLeft(int i, int first, int len) {
        
        int mask = ((1 << len) - 1);
        
        int tmp = (i >> first) & mask;
        tmp = (((tmp & 1) << len) | tmp) >> 1;
        
        return (i & ~(mask << first)) | (tmp << first);
    }
    
    public static int rotateRight(int i, int first, int len) {
        
        int mask = ((1 << len) - 1);
        
        int tmp = (i >> first) & mask;
        tmp = ((tmp << 1) | (tmp >> (len - 1))) & mask;
        
        return (i & ~(mask << first)) | (tmp << first);
    }
    
    private BitUtils() {
        // Utility class
    }

    public static void main(String[] args) {

        int i = 38;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(rotateRight(i, 1, 4)));
        System.out.println(Integer.toBinaryString(rotateLeft(i, 1, 4)));
    }
    
}
