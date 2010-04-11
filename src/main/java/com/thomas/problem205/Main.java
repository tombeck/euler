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
package com.thomas.problem205;


/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.11.2009
 */
public class Main {

    private static final int CUBIC_N = 46656;
    private static final int[] CUBIC = new int[37];
    private static final int PYRAMIDAL_N = 262144;
    private static final int[] PYRAMIDAL =  new int[37];

    /**
     * TODO Method documentation
     * 
     * 0.3560898
     * 0.3560897
     * 
     * @param args
     * @author Thomas
     * @since 28.11.2009
     */
    public static void main(String[] args) {

        makeFrequencies(CUBIC, 6, 6, 0, 0);
        makeFrequencies(PYRAMIDAL, 9, 4, 0, 0);

        double win = 0.0;
        double cubic = 0;
        
        for (int i = 0; i < 37; ++i) {
            win += ((double)PYRAMIDAL[i] / PYRAMIDAL_N) * cubic;
            cubic += (double)CUBIC[i] / CUBIC_N;
        }
        System.out.println(win);
    }

    private static final void makeFrequencies(int[] frequencies, int dice, int sides, int index, int sum) {
        
        if (index == dice) ++frequencies[sum];
        else for (int i = sides; i > 0; --i) makeFrequencies(frequencies, dice, sides, index + 1, sum + i);
    }
    
}
