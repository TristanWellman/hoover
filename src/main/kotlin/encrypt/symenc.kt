package encrypt

import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class symenc {

    private val SSize: Int = 12;
    private val iterations: Int = 32767;

    private fun encByteArr(input: ByteArray, key: ByteArray): ByteArray{
        val rand = SecureRandom();
        val nonce = ByteArray(12);
        rand.nextBytes(nonce);

        val ciph: Cipher = Cipher.getInstance("AES/GCM/NoPadding");
        ciph.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(key, "AES"),
            GCMParameterSpec(128, nonce)
        );
        val ciphText: ByteArray = ciph.doFinal(input);
        val ciphTextNonce = ByteArray(nonce.size + ciphText.size);
        System.arraycopy(nonce, 0, ciphTextNonce, 0, nonce.size);
        System.arraycopy(ciphText, 0, ciphTextNonce, nonce.size, ciphText.size);

        return ciphTextNonce;
    }

    fun encrypt(input: String, secret: String): String {

        val rand = SecureRandom();
        val salt = ByteArray(SSize);

        rand.nextBytes(salt);

        val PWS = PBEKeySpec(secret.toCharArray(), salt, iterations, 256);
        val keyFac: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        val key: ByteArray = keyFac.generateSecret(PWS).encoded;

        val ciphNonce: ByteArray = encByteArr(
            input.toByteArray(StandardCharsets.UTF_8),
            key
        );
        val ciphNonceSalt = ByteArray(salt.size + ciphNonce.size);

        System.arraycopy(salt, 0, ciphNonceSalt, 0, salt.size);
        System.arraycopy(ciphNonce, 0, ciphNonceSalt, salt.size, ciphNonce.size);

        return Base64.getEncoder().encodeToString(ciphNonceSalt);

    }

    private fun decByteArr(ciphTextNonce: ByteArray, key: ByteArray): ByteArray {

        val nonce = ByteArray(12);
        val ciphText = ByteArray(ciphTextNonce.size - 12);
        System.arraycopy(ciphTextNonce, 0, nonce, 0, nonce.size);
        System.arraycopy(ciphTextNonce, nonce.size, ciphText, 0, ciphText.size);

        val ciph: Cipher = Cipher.getInstance("AES/GCM/NoPadding");
        ciph.init(
            Cipher.DECRYPT_MODE,
            SecretKeySpec(key, "AES"),
            GCMParameterSpec(128, nonce)
        );

        return ciph.doFinal(ciphText);

    }

    fun decrypt(ciphText: String, secret: String): String {

        val ciphTextNonceSalt: ByteArray = Base64.getDecoder().decode(ciphText);

        val salt = ByteArray(SSize)
        val ciphTextNonce = ByteArray(ciphTextNonceSalt.size - SSize);
        System.arraycopy(ciphTextNonceSalt, 0, salt, 0, salt.size);
        System.arraycopy(ciphTextNonceSalt, salt.size, ciphTextNonce, 0, ciphTextNonce.size);

        val PWS = PBEKeySpec(secret.toCharArray(), salt, iterations, 256);
        val keyFac: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        val key: ByteArray = keyFac.generateSecret(PWS).encoded;

        return String(decByteArr(ciphTextNonce, key));

    }

}