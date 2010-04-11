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
package com.thomas.problem164;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 22.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 9,45,165,990,5445,27588,146586
     * @param args
     * @author Thomas
     * @since 22.11.2009
     */
    public static void main(String[] args) {

        int sum = 0;
        for (int i1 = 10; i1-- > 1; ) {
            for (int i2 = 10 - i1; i2-- > 0; ) {
                for (int i3 = 10 - (i2 + i1); i3-- > 0; ) {
                    for (int i4 = 10 - (i3 + i2); i4-- > 0; ) {
                        for (int i5 = 10 - (i4 + i3); i5-- > 0; ) {
                            for (int i6 = 10 - (i5 + i4); i6-- > 0; ) {
                                for (int i7 = 10 - (i6 + i5); i7-- > 0; ) {
                                    for (int i8 = 10 - (i7 + i6); i8-- > 0; ) {
                                        for (int i9 = 10 - (i8 + i7); i9-- > 0; ) {
                                            for (int i10 = 10 - (i9 + i8); i10-- > 0; ) {
                                                for (int i11 = 10 - (i10 + i9); i11-- > 0; ) {
                                                    for (int i12 = 10 - (i11 + i10); i12-- > 0; ) {
                                                        ++sum;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

}
