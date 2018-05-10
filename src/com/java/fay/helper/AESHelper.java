package com.java.fay.helper;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * AES加密解密类
 *
 * @author xiaofei9
 */
public class AESHelper {
    /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/ECB/NoPadding";

    /**
     * 加密(结果为16进制字符串)
     *
     * @param content
     *            要加密的字符串
     * @param password
     *            密钥
     * @return 加密后的16进制字符串
     */
    public static String encrypt(String content, String key) {
		try {
			Cipher cipher = Cipher.getInstance(CipherMode);
			int blockSize = cipher.getBlockSize();

			byte[] dataBytes = content.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength
						+ (blockSize - (plaintextLength % blockSize));
			}

			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");

			cipher.init(Cipher.ENCRYPT_MODE, keyspec);
			byte[] encrypted = cipher.doFinal(plaintext);

			return new BASE64Encoder().encode(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
  
    public static String decrypt(String content,String key) throws Exception {
        try
        {
            byte[] encrypted1 = Base64.decode(content.getBytes());
            
            Cipher cipher = Cipher.getInstance(CipherMode);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            
            cipher.init(Cipher.DECRYPT_MODE, keyspec);
 
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}