import java.security.*;
import java.util.Base64;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;

@Slf4j
@Getter
@NoArgsConstructor
public class RSAUtils {
    private static final String KEY_ALGORITHM = "RSA";

    private static final int KEY_SIZE = 2048;

    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    /**
     * 生成密钥
     *
     * @return publicKey 公钥
     */

    public static PublicKey generateKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            return publicKey;
        } catch (Exception e) {
            throw new RuntimeException("生成密钥对失败！");
        }
    }

    /**
     * 明文加密
     *
     * @param content   明文内容
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String content, Key publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptByte = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getMimeEncoder().encodeToString(encryptByte);
        } catch (Exception e) {
            throw new RuntimeException("加密失败！");
        }
    }

    /**
     * 密文解密
     *
     * @param content   密文内容
     * @param privateKey    私钥
     * @return  明文
     */

    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            if (content == null || privateKey == null) {
                throw new IllegalArgumentException("Arguments must not be null");
            }
            // 64位解码加密后的字符串
            byte[] decryptByte = Base64.getMimeDecoder().decode(content);
            // RSA解密
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return new String(cipher.doFinal(decryptByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密失败！", e);
        }
    }

    @Test
    public void test(){
        RSAUtils rsaUtils = new RSAUtils();
        String code = encrypt("13", rsaUtils.generateKey());
        System.out.println(rsaUtils.decrypt(code, rsaUtils.privateKey));
    }
}