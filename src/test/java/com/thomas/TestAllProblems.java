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
package com.thomas;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thomas.problem0xx.problem0x.Problem1;
import com.thomas.problem0xx.problem0x.Problem2;
import com.thomas.problem0xx.problem0x.Problem3;
import com.thomas.problem0xx.problem0x.Problem4;
import com.thomas.problem0xx.problem0x.Problem5;
import com.thomas.problem0xx.problem0x.Problem6;
import com.thomas.problem0xx.problem0x.Problem7;
import com.thomas.problem0xx.problem0x.Problem8;
import com.thomas.problem0xx.problem0x.Problem9;
import com.thomas.problem0xx.problem1x.Problem10;
import com.thomas.problem0xx.problem1x.Problem11;
import com.thomas.problem0xx.problem1x.Problem12;
import com.thomas.problem0xx.problem1x.Problem13;
import com.thomas.problem0xx.problem1x.Problem14;
import com.thomas.problem0xx.problem1x.Problem15;
import com.thomas.problem0xx.problem1x.Problem16;
import com.thomas.problem0xx.problem1x.Problem17;
import com.thomas.problem0xx.problem1x.Problem18;
import com.thomas.problem0xx.problem1x.Problem19;
import com.thomas.problem0xx.problem2x.Problem20;
import com.thomas.problem0xx.problem2x.Problem21;
import com.thomas.problem0xx.problem2x.Problem22;
import com.thomas.problem0xx.problem2x.Problem23;
import com.thomas.problem0xx.problem2x.Problem24;
import com.thomas.problem0xx.problem2x.Problem25;
import com.thomas.problem0xx.problem2x.Problem26;
import com.thomas.problem0xx.problem2x.Problem27;
import com.thomas.problem0xx.problem2x.Problem28;
import com.thomas.problem0xx.problem2x.Problem29;
import com.thomas.problem0xx.problem3x.Problem30;
import com.thomas.problem0xx.problem3x.Problem31;
import com.thomas.problem0xx.problem3x.Problem32;
import com.thomas.problem0xx.problem3x.Problem33;
import com.thomas.problem0xx.problem3x.Problem34;
import com.thomas.problem0xx.problem3x.Problem35;
import com.thomas.problem0xx.problem3x.Problem36;
import com.thomas.problem0xx.problem3x.Problem37;
import com.thomas.problem0xx.problem3x.Problem38;
import com.thomas.problem0xx.problem3x.Problem39;
import com.thomas.problem0xx.problem4x.Problem40;
import com.thomas.problem0xx.problem4x.Problem41;
import com.thomas.problem0xx.problem4x.Problem42;
import com.thomas.problem0xx.problem4x.Problem43;
import com.thomas.problem0xx.problem4x.Problem44;
import com.thomas.problem0xx.problem4x.Problem45;
import com.thomas.problem0xx.problem4x.Problem46;
import com.thomas.problem0xx.problem4x.Problem47;
import com.thomas.problem0xx.problem4x.Problem48;
import com.thomas.problem0xx.problem4x.Problem49;
import com.thomas.problem0xx.problem5x.Problem50;
import com.thomas.problem0xx.problem5x.Problem51;
import com.thomas.problem0xx.problem5x.Problem52;
import com.thomas.problem0xx.problem5x.Problem53;
import com.thomas.problem0xx.problem5x.Problem54;
import com.thomas.problem0xx.problem5x.Problem55;
import com.thomas.problem0xx.problem5x.Problem56;
import com.thomas.problem0xx.problem5x.Problem57;
import com.thomas.problem0xx.problem5x.Problem58;
import com.thomas.problem0xx.problem6x.Problem62;
import com.thomas.problem0xx.problem6x.Problem63;
import com.thomas.problem0xx.problem6x.Problem65;
import com.thomas.problem0xx.problem6x.Problem69;
import com.thomas.problem0xx.problem7x.Problem71;
import com.thomas.problem0xx.problem7x.Problem72;
import com.thomas.problem0xx.problem7x.Problem75;
import com.thomas.problem0xx.problem7x.Problem76;
import com.thomas.problem0xx.problem9x.Problem90;
import com.thomas.problem0xx.problem9x.Problem91;
import com.thomas.problem0xx.problem9x.Problem92;
import com.thomas.problem0xx.problem9x.Problem97;
import com.thomas.problem0xx.problem9x.Problem98;
import com.thomas.problem0xx.problem9x.Problem99;
import com.thomas.problem101.Problem101;
import com.thomas.problem103.Problem103;
import com.thomas.problem105.Problem105;
import com.thomas.problem106.Problem106;
import com.thomas.problem107.Problem107;
import com.thomas.problem111.Problem111;
import com.thomas.problem114.Problem114;
import com.thomas.problem115.Problem115;
import com.thomas.problem118.Problem118;
import com.thomas.problem119.Problem119;
import com.thomas.problem122.Problem122;
import com.thomas.problem126.Problem126;
import com.thomas.problem127.Problem127;
import com.thomas.problem129.Problem129;
import com.thomas.problem130.Problem130;
import com.thomas.problem133.Problem133;
import com.thomas.problem134.Problem134;
import com.thomas.problem139.Problem139;
import com.thomas.problem140.Problem140;
import com.thomas.problem141.Problem141;
import com.thomas.problem144.Problem144;
import com.thomas.problem146.Problem146;
import com.thomas.problem147.Problem147;
import com.thomas.problem148.Problem148;
import com.thomas.problem149.Problem149;
import com.thomas.problem151.Problem151;
import com.thomas.problem153.Problem153;
import com.thomas.problem156.Problem156;
import com.thomas.problem160.Problem160;
import com.thomas.problem161.Problem161;
import com.thomas.problem162.Problem162;
import com.thomas.problem164.Problem164;
import com.thomas.problem165.Problem165;
import com.thomas.problem166.Problem166;
import com.thomas.problem168.Problem168;
import com.thomas.problem170.Problem170;
import com.thomas.problem171.Problem171;
import com.thomas.problem172.Problem172;
import com.thomas.problem178.Problem178;
import com.thomas.problem181.Problem181;
import com.thomas.problem185.Problem185;
import com.thomas.problem189.Problem189;
import com.thomas.problem193.Problem193;
import com.thomas.problem1xx.problem10x.Problem100;
import com.thomas.problem1xx.problem10x.Problem102;
import com.thomas.problem1xx.problem10x.Problem104;
import com.thomas.problem1xx.problem10x.Problem109;
import com.thomas.problem1xx.problem11x.Problem112;
import com.thomas.problem1xx.problem11x.Problem113;
import com.thomas.problem1xx.problem11x.Problem116;
import com.thomas.problem1xx.problem11x.Problem117;
import com.thomas.problem1xx.problem12x.Problem120;
import com.thomas.problem1xx.problem12x.Problem121;
import com.thomas.problem1xx.problem12x.Problem123;
import com.thomas.problem1xx.problem12x.Problem124;
import com.thomas.problem1xx.problem12x.Problem125;
import com.thomas.problem1xx.problem12x.Problem128;
import com.thomas.problem1xx.problem13x.Problem131;
import com.thomas.problem1xx.problem13x.Problem132;
import com.thomas.problem1xx.problem13x.Problem135;
import com.thomas.problem1xx.problem13x.Problem136;
import com.thomas.problem1xx.problem13x.Problem137;
import com.thomas.problem1xx.problem13x.Problem138;
import com.thomas.problem1xx.problem15x.Problem150;
import com.thomas.problem1xx.problem15x.Problem154;
import com.thomas.problem1xx.problem15x.Problem155;
import com.thomas.problem1xx.problem15x.Problem157;
import com.thomas.problem1xx.problem15x.Problem158;
import com.thomas.problem1xx.problem15x.Problem159;
import com.thomas.problem1xx.problem16x.Problem169;
import com.thomas.problem1xx.problem17x.Problem173;
import com.thomas.problem1xx.problem17x.Problem174;
import com.thomas.problem1xx.problem17x.Problem175;
import com.thomas.problem1xx.problem17x.Problem179;
import com.thomas.problem1xx.problem18x.Problem183;
import com.thomas.problem1xx.problem18x.Problem186;
import com.thomas.problem1xx.problem18x.Problem187;
import com.thomas.problem1xx.problem18x.Problem188;
import com.thomas.problem1xx.problem19x.Problem190;
import com.thomas.problem1xx.problem19x.Problem191;
import com.thomas.problem1xx.problem19x.Problem197;
import com.thomas.problem206.Problem206;
import com.thomas.problem209.Problem209;
import com.thomas.problem211.Problem211;
import com.thomas.problem213.Problem213;
import com.thomas.problem214.Problem214;
import com.thomas.problem215.Problem215;
import com.thomas.problem216.Problem216;
import com.thomas.problem218.Problem218;
import com.thomas.problem220.Problem220;
import com.thomas.problem222.Problem222;
import com.thomas.problem231.Problem231;
import com.thomas.problem234.Problem234;
import com.thomas.problem235.Problem235;
import com.thomas.problem238.Problem238;
import com.thomas.problem239.Problem239;
import com.thomas.problem244.Problem244;
import com.thomas.problem248.Problem248;
import com.thomas.problem249.Problem249;
import com.thomas.problem250.Problem250;
import com.thomas.problem252.Problem252;
import com.thomas.problem253.Problem253;
import com.thomas.problem266.Problem266;
import com.thomas.problem267.Problem267;
import com.thomas.problem271.Problem271;
import com.thomas.problem277.Problem277;
import com.thomas.problem280.Problem280;
import com.thomas.problem281.Problem281;
import com.thomas.problem282.Problem282;
import com.thomas.problem284.Problem284;
import com.thomas.problem287.Problem287;
import com.thomas.problem288.Problem288;
import com.thomas.problem290.Problem290;
import com.thomas.problem293.Problem293;
import com.thomas.problem2xx.problem20x.Problem200;
import com.thomas.problem2xx.problem20x.Problem203;
import com.thomas.problem2xx.problem20x.Problem204;
import com.thomas.problem2xx.problem20x.Problem205;
import com.thomas.problem2xx.problem20x.Problem207;
import com.thomas.problem2xx.problem21x.Problem210;
import com.thomas.problem2xx.problem22x.Problem225;
import com.thomas.problem2xx.problem22x.Problem227;
import com.thomas.problem2xx.problem23x.Problem230;
import com.thomas.problem2xx.problem23x.Problem237;
import com.thomas.problem2xx.problem24x.Problem243;
import com.thomas.problem2xx.problem24x.Problem247;
import com.thomas.problem2xx.problem25x.Problem259;
import com.thomas.problem2xx.problem26x.Problem260;
import com.thomas.problem2xx.problem26x.Problem265;
import com.thomas.problem2xx.problem26x.Problem268;
import com.thomas.problem2xx.problem28x.Problem286;
import com.thomas.problem2xx.problem29x.Problem297;
import com.thomas.problem301.Problem301;
import com.thomas.problem303.Problem303;
import com.thomas.problem306.Problem306;
import com.thomas.problem307.Problem307;
import com.thomas.problem310.Problem310;
import com.thomas.problem312.Problem312;
import com.thomas.problem323.Problem323;
import com.thomas.problem333.Problem333;
import com.thomas.problem341.Problem341;
import com.thomas.problem3xx.problem31x.Problem317;
import com.thomas.problem3xx.problem32x.Problem321;
import com.thomas.problem3xx.problem34x.Problem345;
import com.thomas.problem60.Problem60;
import com.thomas.problem61.Problem61;
import com.thomas.problem64.Problem64;
import com.thomas.problem66.Problem66;
import com.thomas.problem67.Problem67;
import com.thomas.problem68.Problem68;
import com.thomas.problem70.Problem70;
import com.thomas.problem73.Problem73;
import com.thomas.problem74.Problem74;
import com.thomas.problem77.Problem77;
import com.thomas.problem78.Problem78;
import com.thomas.problem80.Problem80;
import com.thomas.problem81.Problem81;
import com.thomas.problem82.Problem82;
import com.thomas.problem83.Problem83;
import com.thomas.problem84.Problem84;
import com.thomas.problem85.Problem85;
import com.thomas.problem86.Problem86;
import com.thomas.problem87.Problem87;
import com.thomas.problem88.Problem88;
import com.thomas.problem89.Problem89;
import com.thomas.problem93.Problem93;
import com.thomas.problem94.Problem94;
import com.thomas.problem95.Problem95;
import com.thomas.problem96.Problem96;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class TestAllProblems {

    @DataProvider
    public Object[][] problems() {
        
        return new Object[][] {
                { new Problem1(), "233168" },
                { new Problem2(), "4613732" },
                { new Problem3(), "6857" },
                { new Problem4(), "906609" },
                { new Problem5(), "232792560" },
                { new Problem6(), "25164150" },
                { new Problem7(), "104743" },
                { new Problem8(), "40824" },
                { new Problem9(), "31875000" },
                { new Problem10(), "142913828922" },
                { new Problem11(), "70600674" },
                { new Problem12(), "76576500" },
                { new Problem13(), "5537376230" },
                { new Problem14(), "837799" },
                { new Problem15(), "137846528820" },
                { new Problem16(), "1366" },
                { new Problem17(), "21124" },
                { new Problem18(), "1074" },
                { new Problem19(), "171" },
                { new Problem20(), "648" },
                { new Problem21(), "31626" },
                { new Problem22(), "871198282" },
                { new Problem23(), "4179871" },
                { new Problem24(), "2783915460" },
                { new Problem25(), "4782" },
                { new Problem26(), "983" },
                { new Problem27(), "-59231" },
                { new Problem28(), "669171001" },
                { new Problem29(), "9183" },
                { new Problem30(), "443839" },
                { new Problem31(), "73682" },
                { new Problem32(), "45228" },
                { new Problem33(), "100" },
                { new Problem34(), "40730" },
                { new Problem35(), "55" },
                { new Problem36(), "872187" },
                { new Problem37(), "748317" },
                { new Problem38(), "932718654" },
                { new Problem39(), "840" },
                { new Problem40(), "210" },
                { new Problem41(), "7652413" },
                { new Problem42(), "162" },
                { new Problem43(), "16695334890" },
                { new Problem44(), "5482660" },
                { new Problem45(), "1533776805" },
                { new Problem46(), "5777" },
                { new Problem47(), "134043" },
                { new Problem48(), "9110846700" },
                { new Problem49(), "296962999629" },
                { new Problem50(), "997651" },
                { new Problem51(), "121313" },
                { new Problem52(), "142857" },
                { new Problem53(), "4075" },
                { new Problem54(), "376" },
                { new Problem55(), "249" },
                { new Problem56(), "972" },
                { new Problem57(), "153" },
                { new Problem58(), "26241" },
//                { new Problem59(), "107359" },
                { new Problem60(), "26033" },
                { new Problem61(), "28684" },
                { new Problem62(), "127035954683" },
                { new Problem63(), "49" },
                { new Problem64(), "1322" },
                { new Problem65(), "272" },
                { new Problem66(), "661" },
                { new Problem67(), "7273" },
                { new Problem68(), "6531031914842725" },
                { new Problem69(), "510510" },
                { new Problem70(), "8319823" },
                { new Problem71(), "428570" },
                { new Problem72(), "303963552391" },
                { new Problem73(), "7295372" },
                { new Problem74(), "402" },
                { new Problem75(), "161667" },
                { new Problem76(), "190569291" },
                { new Problem77(), "71" },
                { new Problem78(), "55374" },
//                { new Problem79(), "73162890" },
                { new Problem80(), "40886" },
                { new Problem81(), "427337" },
                { new Problem82(), "260324" },
                { new Problem83(), "425185" },
                { new Problem84(), "101524" },
                { new Problem85(), "2772" },
                { new Problem86(), "1818" },
                { new Problem87(), "1097343" },
                { new Problem88(), "7587457" },
                { new Problem89(), "743" },
                { new Problem90(), "1217" },
                { new Problem91(), "14234" },
                { new Problem92(), "8581146" },
                { new Problem93(), "1258" },
                { new Problem94(), "518408346" },
                { new Problem95(), "14316" },
                { new Problem96(), "24702" },
                { new Problem97(), "8739992577" },
                { new Problem98(), "18769" },
                { new Problem99(), "709" },
                { new Problem100(), "756872327473" },
                { new Problem101(), "37076114526" },
                { new Problem102(), "228" },
                { new Problem103(), "20313839404245" },
                { new Problem104(), "329468" },
                { new Problem105(), "73702" },
                { new Problem106(), "21384" },
                { new Problem107(), "259679" },
//                { new Problem108(), "180180" },
                { new Problem109(), "38182" },
//                { new Problem110(), "9350130049860600" },
                { new Problem111(), "612407567715" },
                { new Problem112(), "1587000" },
                { new Problem113(), "51161058134250" },
                { new Problem114(), "16475640049" },
                { new Problem115(), "168" },
                { new Problem116(), "20492570929" },
                { new Problem117(), "100808458960497" },
                { new Problem118(), "44680" },
                { new Problem119(), "248155780267521" },
                { new Problem120(), "333082500" },
                { new Problem121(), "2269" },
                { new Problem122(), "1582" },
                { new Problem123(), "21035" },
                { new Problem124(), "21417" },
                { new Problem125(), "2906969179" },
                { new Problem126(), "18522" },
                { new Problem127(), "18407904" },
                { new Problem128(), "14516824220" },
                { new Problem129(), "1000023" },
                { new Problem130(), "149253" },
                { new Problem131(), "173" },
                { new Problem132(), "843296" },
                { new Problem133(), "453647705" },
                { new Problem134(), "18613426663617118" },
                { new Problem135(), "4989" },
                { new Problem136(), "2544559" },
                { new Problem137(), "1120149658760" },
                { new Problem138(), "1118049290473932" },
                { new Problem139(), "10057761" },
                { new Problem140(), "5673835352990" },
                { new Problem141(), "878454337159" },
//                { new Problem142(), "1006193" },
                
                { new Problem144(), "354" },
//                { new Problem145(), "608720" },
                { new Problem146(), "676333270" },
                { new Problem147(), "846910284" },
                { new Problem148(), "2129970655314432" },
                { new Problem149(), "52852124" },
                { new Problem150(), "-271248680" },
                { new Problem151(), "0.464399" },

                { new Problem153(), "17971254122360635" },
                { new Problem154(), "479742450" },
                { new Problem155(), "3857447" },
                { new Problem156(), "21295121502550" },
                { new Problem157(), "53490" },
                { new Problem158(), "409511334375" },
                { new Problem159(), "14489159" },
                { new Problem160(), "16576" },
                { new Problem161(), "20574308184277971" },

                { new Problem162(), "3D58725572C62302" },

                { new Problem164(), "378158756814587" },
                { new Problem165(), "2868868" },
                { new Problem166(), "7130034" },
                
                { new Problem168(), "59206" },
                { new Problem169(), "178653872807" },
                { new Problem170(), "9857164023" },
                { new Problem171(), "142989277" },
                { new Problem172(), "227485267000992000" },
                { new Problem173(), "1572729" },
                { new Problem174(), "209566" },
                { new Problem175(), "1,13717420,8" },

                { new Problem178(), "126461847755" },
                { new Problem179(), "986262" },
                
                { new Problem181(), "83735848679360680" },
                { new Problem183(), "48861552" },
                
                { new Problem185(), "4640261571849533" },
                { new Problem186(), "2325629" },
                { new Problem187(), "17427258" },
                { new Problem188(), "95962097" },
                { new Problem189(), "10834893628237824" },

                { new Problem190(), "371048281" },
                { new Problem191(), "1918080160" },

                { new Problem193(), "684465067343069" },

                { new Problem197(), "1.710637717" },

                { new Problem200(), "229161792008" },

                { new Problem203(), "34029210557338" },
                { new Problem204(), "2944730" },
                { new Problem205(), "0.5731441" },
                { new Problem206(), "1389019170" },
                { new Problem207(), "44043947822" },

                { new Problem209(), "15964587728784" },
                { new Problem210(), "1598174770174689458" },
                { new Problem211(), "1922364685" },
//                { new Problem212(), "328968937309" }, too slow
                { new Problem213(), "330.721154" },
                { new Problem214(), "1677366278943" },
                { new Problem215(), "806844323190414" },
                { new Problem216(), "5437849" },
                
                { new Problem218(), "0" },
                
                { new Problem220(), "139776,963904" },

                { new Problem222(), "1590933" },
                
                { new Problem225(), "2009" },
                
                { new Problem227(), "3780.618622" },
                
                { new Problem230(), "850481152593119296" },
                { new Problem231(), "7526965179680" },
                { new Problem234(), "1259187438574927161" },
                { new Problem235(), "1.002322108633" },

                { new Problem237(), "15836928" },
                { new Problem238(), "9922545104535661" },
                { new Problem239(), "0.001887854841" },

                { new Problem243(), "892371480" },
                { new Problem244(), "96356848" },

                { new Problem247(), "782252" },
                { new Problem248(), "23507044290" },
                { new Problem249(), "9275262564250418" },
                { new Problem250(), "1425480602091519" },

                { new Problem252(), "104924.0" },
                { new Problem253(), "11.492847" },

                { new Problem259(), "20101196798" },
                { new Problem260(), "167542057" },

                { new Problem265(), "209110240768" },
                { new Problem266(), "1096883702440585" },
                { new Problem267(), "0.999992836187" },
                { new Problem268(), "785478606870985" },
                
                { new Problem271(), "4617456485273129588" },

                { new Problem277(), "1125977393124310" },

                { new Problem280(), "430.088247" },
                { new Problem281(), "1485776387445623" },
                { new Problem282(), "1098988351" },

                { new Problem284(), "5a411d7b" },

                { new Problem286(), "52.6494571953" },
                { new Problem287(), "313135496" },
                { new Problem288(), "605857431263981935" },

                { new Problem290(), "20444710234716473" },

                { new Problem293(), "2209" },

                { new Problem297(), "2252639041804718029" },

                { new Problem301(), "2178309" },
//                { new Problem302(), "1170060" }, too slow

                { new Problem303(), "1111981904675169" },

                { new Problem306(), "852938" },
                { new Problem307(), "0.7311720251" },

                { new Problem310(), "2586528661783" },

                { new Problem312(), "324681947" },

                { new Problem317(), "1856532.8455" },

//                { new Problem320(), "278157919195482643" }, too slow
                { new Problem321(), "2470433131948040" },

                { new Problem323(), "6.3551758451" },
//                { new Problem324(), "96972774" }, too slow

                { new Problem333(), "3053105" },
                
                { new Problem341(), "56098610614277014" },
                
                { new Problem345(), "13938" },
        };
    }
    
    @Test(dataProvider = "problems", timeOut = 60000)
    public void test(Problem problem, String answer) throws Exception {
        
        final String name = problem.getClass().getSimpleName();
        
        System.out.println("solve " + name);
        
        Assert.assertEquals(String.valueOf(problem.solve()), answer, name);
    }

}
