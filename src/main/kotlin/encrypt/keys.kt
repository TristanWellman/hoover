package encrypt

import encrypt.symenc.*;
import java.util.Random

class keys {

    fun genkey(): String {

        val keywords = arrayOf(
            "suffer ", "faint ", "word ", "ear ",
            "wonder ", "satisfied ", "vigorous ", "stride ",
            "stereotype ", "coat ", "qualification ", "judicial ",
            "desk ", "appetite ", "record ", "brand ",
            "connection ", "unity ", "soak ", "urgency ",
            "marketing ", "bench ", "confront ", "law ",
            "humanity ", "ban ", "competence ", "profile ",
            "handicap ", "ladder ", "sweater ", "party ",
            "reinforce ", "impact ", "chaos ", "budget ",
            "skilled ", "twilight ", "convert ", "pace ",
            "contemporary ", "ceremony ", "ordinary ", "plagiarize ",
            "sunshine ", "auction ", "design ", "flash ",
            "cable ", "tempt ", "apple ", "horse ");

        val rand: Random = Random();
        val max_randsize: Int = 50;

        val int_rand: Int = rand.nextInt(max_randsize);

        val rand2: Random = Random();
        val rand3: Random = Random();
        val rand4: Random = Random();
        val rand5: Random = Random();

        val int_rand1: Int = rand2.nextInt(max_randsize);
        val int_rand2: Int = rand3.nextInt(max_randsize);
        val int_rand3: Int = rand4.nextInt(max_randsize);
        val int_rand4: Int = rand5.nextInt(max_randsize);

        var keyarr = arrayOfNulls<String>(5);

        var loop: Int = 0;
        while(loop != keyarr.size) {
            loop += 1
            if(loop == 0) {
                keyarr[loop] = keywords[int_rand];
            } else if(loop == 1) {
                keyarr[loop] = keywords[int_rand1];
            } else if(loop == 2) {
                keyarr[loop] = keywords[int_rand2];
            } else if(loop == 3) {
                keyarr[loop] = keywords[int_rand3];
            } else if(loop == 4) {
                keyarr[loop] = keywords[int_rand4];
            }
        }

        val rand1: Random = Random();
        val random2: Random = Random();
        val random3: Random = Random();
        val random4: Random = Random();
        val random5: Random = Random();

        val max: Int = 5

        var rand_int: Int = rand.nextInt(max);
        var rand_int1: Int = random2.nextInt(max);
        var rand_int2: Int = random3.nextInt(max);
        var rand_int3: Int = random4.nextInt(max);
        var rand_int4: Int = random5.nextInt(max);

        var keyarr1 = arrayOfNulls<Int>(5);

        var loop2: Int = 0;

        var ks1: Int = 0;
        var ks2: Int = 0;
        var ks3: Int = 0;
        var ks4: Int = 0;
        var ks5: Int = 0;

        while(loop2 != 5) {

            loop2 += 1

            if(loop2 == 0) {
                ks1 = rand_int;
            } else if(loop2 == 1) {
                ks2 = rand_int1;
            } else if(loop2 == 2) {
                ks3 = rand_int2;
            } else if(loop2 == 3) {
                ks4 = rand_int3;
            } else if(loop2 == 4) {
                ks5 = rand_int4;
            }

        }

        val s1 = keyarr[ks1];
        val s2 = keyarr[ks2];
        val s3 = keyarr[ks3];
        val s4 = keyarr[ks4];
        val s5 = keyarr[ks5]

        val key: String = "$s5" + "$s1" + "$s2" + "$s3" + "$s4"

        return key
    }

}