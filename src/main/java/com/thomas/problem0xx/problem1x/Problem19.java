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
 * @since 17.10.2009
 */
public class Problem19 implements Problem {

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

        int day = 365;
        int sum = 0;
        for (int year = 1901; year < 2001; ++year) {
            for (int month = 1; month <= 12; ++month) {
                day += getDaysOfMonth(year, month);
                if (day % 7 == 6) sum += 1;
            }
        }
        
        return sum;
    }
    
    private int getDaysOfMonth(int year, int month) {
        
        switch(month) {
        case  1: return 31; //Januar
        case  2: return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28; // Februar
        case  3: return 31; // März
        case  4: return 30; // April
        case  5: return 31; // Mai
        case  6: return 30; // Juni
        case  7: return 31; // Juli
        case  8: return 31; // August
        case  9: return 30; // September
        case 10: return 31; // Oktober
        case 11: return 30; // November
        case 12: return 31; // Dezember
        default: throw new IllegalArgumentException();
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 17.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem19());
    }

}
