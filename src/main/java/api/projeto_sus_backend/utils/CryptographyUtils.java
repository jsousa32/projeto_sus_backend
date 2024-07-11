package api.projeto_sus_backend.utils;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * The Class CryptographyUtils
 *
 * @author João Lucas Silva de Sousa
 * @sincer 11/07/2024
 */
public class CryptographyUtils {

    public static final String ALGORITHM = "AES";

    public static String encrypt(String data, String secretKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);

            Cipher instance = Cipher.getInstance(ALGORITHM);

            instance.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] bytes = instance.doFinal(data.getBytes());

            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new ApplicationException("Não foi possível realizar a criptografia do dado", HttpStatus.BAD_REQUEST);
        }
    }

    public static String decrypt(String data, String secretKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);

            Cipher instance = Cipher.getInstance(ALGORITHM);

            instance.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(data);

            byte[] decryptedBytes = instance.doFinal(decodedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new ApplicationException("Não foi possível realizar a descriptografia do dado", HttpStatus.BAD_REQUEST);
        }
    }
}
