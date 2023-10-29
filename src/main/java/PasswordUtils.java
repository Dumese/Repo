import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileNotFoundException;

public class PasswordUtils {
    /**
     *  加密
     * @param data  明文
     * @return  密文
     * @throws FileNotFoundException
     */
    public static String Encryption(String data) throws FileNotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(data);
    }

    /**
     * 密码校验
     * @param cipherText    密文
     * @param clearText     明文
     * @return
     * @throws FileNotFoundException
     */
    public static boolean  Calibration(String cipherText, String clearText) throws FileNotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(cipherText, cipherText);
    }
}
