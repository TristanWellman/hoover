package encrypt

import encrypt.symenc
import encrypt.keys
import java.io.File

class hoovercrypt {

    public fun hoover_encrypt_message_toData(input: String): Array<String> {

        /**
         *      !key2 ------------\
         *                        !enckey1
         *      exkey ---\-------/
         *                encrypt ------------\
         *      input ---/                     \
         *                key3 ---\             !encrypt2
         *                          enckey3 ---/
         *                !key4 ---/
         */

        val key = keys();
        val exkey = key.genkey();

        val symencr = symenc();
        val encrypt = symencr.encrypt(input = input, secret = exkey);

        val key2 = key.genkey();
        val enckey1 = symencr.encrypt(input = exkey, secret = key2);

        val key3 = key.genkey();
        val key4 = key.genkey();
        val enckey3 = symencr.encrypt(input = key3, secret = key4)
        val encrypt2 = symencr.encrypt(input = encrypt, secret = key3);

        val arr: Array<String> = arrayOf(key2, enckey1, key4, enckey3, encrypt2);
        /*arr[0] = key2;
        arr[1] = enckey1;

        arr[2] = key4;
        arr[3] = enckey3;
        arr[4] = encrypt2*/
        return arr;
    }

    public fun hoover_decrypt(
        /*exkey: String, encrypt: String, //original key and encrypted input
        key2: String, enckey1: String, //second key, and the encrypted first key
        key3: String, key4: String, //the two keys to make  enckey3
        enckey3: String, encrypt2: String // enckey3: encrypted key3
        // key 4 accesses*/

        arr: Array<String>
    ): String {

        val symencr = symenc();

        val exkey = symencr.decrypt(ciphText = arr[1], secret = arr[0]);
        val key3 = symencr.decrypt(ciphText = arr[3], secret = arr[2]);
        val encrypt = symencr.decrypt(ciphText = arr[4], secret = key3);
        val input = symencr.decrypt(ciphText = encrypt, secret = exkey)

        val decrypted_message: String = input
        return decrypted_message;
    }

}