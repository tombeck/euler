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
package com.thomas.problem108;

import com.thomas.util.PrimeUtils;


/**
 * 2310 is wrong
 * 30030
 * 510510
 * 9699690 is wrong
 * 223092870 is wrong
 * 
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 01.11.2009
 */
public class Main {

//    private static final long[] PRIMES = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23};
    
    /**
     *  258791569 = 3 * 7 * 13 * 17 * 19 * 23 * 31 * 47 * 59 * 71
     * 
     * <= 510510
     * <= 240240
     * <= 235620
     * <= 180180
     * 
     * TODO Method documentation 80434,403273723613794183213192077
     * 
     * @param args
     * @author Thomas
     * @throws InterruptedException 
     * @throws InterruptedException 
     * @since 01.11.2009
     */
    public static void main(String[] args) throws InterruptedException {

        for (long n = 180179L; n > 0L; --n) {
            int sum = 0;
            long n_sqr = n * n;
            for (long r = 1; r <= n; ++r) {
                if (n_sqr % r == 0) {
//                    long x = n + r;
//                    long y = n_sqr / r + n;
                    ++sum;
//                    System.out.println("1/" + x + " + 1/" + y + " = 1/" + n);
                }
            }
            System.out.println(n + " -> " + sum);
            if (sum > 1000) {
                System.out.println(PrimeUtils.getPrimeFactors(n));
                break;
            }
            Thread.sleep(1);
        }
//        int min = Integer.MAX_VALUE;
//        long min_n = 0;
//        for (int i0 = 1; i0 < 2; ++i0) {
//            for (int i1 = i0; i1 < 3; ++i1) {
//                for (int i2 = i1; i2 < 4; ++i2) {
//                    for (int i3 = i2; i3 < 8; ++i3) {
//                        for (int i4 = i3; i4 < 8; ++i4) {
//                            for (int i5 = i4; i5 < 8; ++i5) {
//                                for (int i6 = i5; i6 < 8; ++i6) {
//                                    //for (int i7 = i6; i7 < 10; ++i7) {
//                                        long n = PRIMES[i0] * PRIMES[i1] * PRIMES[i2] * PRIMES[i3] * PRIMES[i4] * PRIMES[i5] * PRIMES[i6];
//                                        int sum = 0;
//                                        long n_sqr = n * n;
//                                        for (long r = 1; r <= n; ++r) {
//                                            if (n_sqr % r == 0) {
//                                                //long x = n + r;
//                                                //long y = n_sqr / r + n;
//                                                ++sum;
//                                                //System.out.println("1/" + x + " + 1/" + y + " = 1/" + n);
//                                            }
//                                        }
//                                        if (sum > 1000 && sum < min) {
//                                            min = sum;
//                                            min_n = n;
//                                        }
//                                        System.out.println(n + " : " + sum);
//                                    //}
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(min_n + " : " + min);
//        System.out.println(Prime.getPrimeFactors(min_n));
//        for (long n = 2; n <= 510510; ++n) {
//            long sum = 0;
//            long n_sqr = n * n;
//            for (long r = 1; r <= n; ++r) {
//                if (n_sqr % r == 0) {
//                    long x = n + r;
//                    long y = n_sqr / r + n;
//                    ++sum;
//                    System.out.println("1/" + x + " + 1/" + y + " = 1/" + n);
//                }
//            }
//            System.out.println(n + " : " + Prime.getPrimeFactors(n_sqr) + " = " + sum);
//            if (sum > 1000) {
//                break;
//            }
//            Thread.sleep(1);
//        }
//        for (int n = 2; n <= 10; ++n) {
//            for (int x = n + 1; x <= n * (n + 1); ++x) {
//                int nom = n * x;
//                int denom = x - n;
//                if (nom % denom == 0) {
//                    int y = nom / denom;
//                    System.out.println("1/" + x + " + 1/" + y + " = 1/" + n);
//                }
//            }
//        }
    }

}
