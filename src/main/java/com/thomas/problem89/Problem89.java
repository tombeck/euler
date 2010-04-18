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
package com.thomas.problem89;

import static com.thomas.util.IOUtils.closeQuietly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.10.2009
 */
public class Problem89 implements Problem {

    static enum RomanNumeral {
        
        I('I',    1),
        V('V',    5),
        X('X',   10),
        L('L',   50),
        C('C',  100),
        D('D',  500),
        M('M', 1000);
        
        private final char literal;
        private final int value;
        
        private RomanNumeral(char literal, int value) {

            this.literal = literal;
            this.value = value;
        }
        
        public int getValue() {
        
            return this.value;
        }

        public static RomanNumeral valueOf(char literal) {
            
            for (RomanNumeral numeral : values()) {
                if (numeral.literal == literal) return numeral;
            }
            
            throw new IllegalArgumentException(literal + " is not a roman numeral.");
        }
    }
    
    private static final String[] ONE =     {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private static final String[] TEN =     {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] HUNDRED = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    
    private static RomanNumeral[] toRomanNumerals(String s) {
    
        final RomanNumeral[] numerals = new RomanNumeral[s.length()];
        
        for (int i = 0; i < s.length(); ++i) {
            numerals[i] = RomanNumeral.valueOf(s.charAt(i));
        }
        
        return numerals;
    }
    
    private static int toInteger(RomanNumeral[] numerals) {
    
        int ret = 0;
        RomanNumeral prev = numerals[0];
        
        for (int i = 1; i < numerals.length; ++i) {
            if (prev.compareTo(numerals[i]) < 0) ret -= prev.getValue();
            else ret += prev.getValue();
            prev = numerals[i]; 
        }
        ret += prev.getValue();
        
        return ret;
    }
    
    private static String format(int n) {
    
        final StringBuilder builder = new StringBuilder();
        
        for (int i = 0, l = n / 1000; i < l; ++i) {
            builder.append(RomanNumeral.M.name());
        }
        n %= 1000; builder.append(HUNDRED[n / 100]);
        n %=  100; builder.append(TEN[n / 10]);
        n %=   10; builder.append(ONE[n]);

        return builder.toString();
    }
    
    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(Problem89.class.getResourceAsStream ("roman.txt")));

        try {
            
            int oldSum = 0;
            int newSum = 0;
            
            for (String line; (line = reader.readLine()) != null; ) {
                oldSum += line.length();
                newSum += format(toInteger(toRomanNumerals(line))).length();
            }
            
            return oldSum - newSum;
            
        } finally {
            closeQuietly(reader);
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws IOException 
     * @since 25.10.2009
     */
    public static void main(String[] args) throws IOException {

        Euler.run(new Problem89());
    }

}
