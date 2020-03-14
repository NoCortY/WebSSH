package cn.objectspace.webssh.util;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


/**
 * 加密工具类
 *
 * @Auther: liaojl
 * @Date: 2019/9/8 14:22
 * @Description:
 */

public class EncryptUtil {
    private static final String KEY = "d98ac29b19cdba2fd9dcf20797f28938df8a79a1029726d6";
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
    private static Key convertSecretKey = null;
    // 此处写死，一旦出货之后，如果需要修改，需要先将数据库中的证件号码先解密之后在进行加密转换
    private static final String priKey = "MEECAQAwEwYHKoZIzj0CAQYIKoEcz1UBgi0EJzAlAgEBBCAVgvYvPw7x6l5AKmnC+WJNgSD1IHJd9JZ+PX5naBl8dA==";
    private static final String pubKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEoc3SJoq3dQCARXXKBNGHm8SuOtloppbWKda1tsWKNNNqw6kgKLGNzw3gMORf4btiAje7AVhUjdv3p9+jicrh5w==";

    static {
        Security.addProvider(new BouncyCastleProvider());

        // 获取密钥
        byte[] bytesKey = HexUtils.hex2Bytes(KEY);

        // KEY转换
        DESedeKeySpec desKeySpec = null;
        try {
            desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede", "BC");
            convertSecretKey = factory.generateSecret(desKeySpec);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * @param message
     * @param algorithm //选择SHA-1，也可以选择MD5
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encryptString(String message, String algorithm)
            throws NoSuchAlgorithmException {
        String outStr = null;
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] digest = md.digest(message.getBytes()); // 返回的是byet[]，要转化为String存储比较方便
        outStr = bytetoString(digest);
//        outStr = byte2HexString(digest);
        return outStr;
    }

    public static String encryptStringWithSHA1(String message)
            throws NoSuchAlgorithmException {
        return encryptString(message, "SHA-1");
    }

    public static String byte2HexString(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_UPPER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_UPPER[0x0F & data[i]];
        }
        return new String(out).toLowerCase();
    }


    // byet[]转化为String
    public static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";

        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }


    // 用bouncy castle实现:
    public static byte[] bcUnDES(byte[] bytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("DESede");
        // 解密
        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
        return cipher.doFinal(bytes);

    }

    // 用bouncy castle实现:
    public static byte[] bcEnDES(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 加密
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
        byte[] result = cipher.doFinal(message.getBytes());
        return result;

    }

    private static final String ENC_FLAG = "~|!&";

    public static byte[] enc3DES(byte[] inputData, byte[] key, boolean useIv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, new SecretKeySpec(key, "DES"));
        return cipher.doFinal(inputData);
    }

    public static byte[] dec3DES(byte[] input, byte[] key, boolean useIv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, new SecretKeySpec(key, "DES"));
        return cipher.doFinal(input);
    }

    public static String enc3DES(String input, byte[] key) throws Exception {
        return new String(Base64.encode(enc3DES((ENC_FLAG + input).getBytes(), key, true)));
    }

    public static String tryDec3DES(String input, byte[] key) throws Exception {
        try {
            String txt = new String(dec3DES(Base64.decode(input), key, true));
            return txt.startsWith(ENC_FLAG) ? txt.substring(ENC_FLAG.length(), txt.length()) : input;
        } catch (Exception e) {
            return input;
        }
    }

    public static void verifyCert(String strCert, String strRootCert) throws Exception {
        buildX509Cert(strCert).verify(buildX509Cert(strRootCert).getPublicKey());
    }

    public static X509Certificate buildX509Cert(String cert) throws Exception {
        return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(Base64.decode(cert)));
    }


    /**
     * 判断字符串是不是 Base64 字符串
     *
     * @param str 判断字符串
     * @return
     */
    public static synchronized boolean isBase64(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        } else {
            if (str.length() % 4 != 0) {
                return false;
            }

            char[] strChars = str.toCharArray();
            for (char c : strChars) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
                        || c == '+' || c == '/' || c == '=') {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

}
