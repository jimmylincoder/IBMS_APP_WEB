package com.suntek.ibms.componet.controller.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Encryptor
 *
 * @author kutzhang@gmail.com
 */
@Component
public class Encryptor
{
    static
    {
        //添加密码算法提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    // iv
    private static final AlgorithmParameterSpec IV_SPEC = new IvParameterSpec(
            new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    );

    //关键算法
    private static final String KEY_ALGORITHM = "AES";

    //密码算法的提供者
    private static final String CIPHER_ALGORITHM_PROVIDER = "BC";

    //chpher 算法
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    /**
     * 加密/编码
     *
     * @param data data
     * @param key  key
     * @return encrypted data
     */
    public byte[] encode(byte[] data, String key)
    {
        //生成密钥
        byte[] keyBytes = generateKey(key);

        Cipher cipher;

        try
        {
            //密钥规范
            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            Security.addProvider(new BouncyCastleProvider());
            cipher = Cipher.getInstance(CIPHER_ALGORITHM, CIPHER_ALGORITHM_PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IV_SPEC);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        //加密数据
        byte[] encrypted;
        try
        {
            encrypted = cipher.doFinal(data);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return encrypted;
    }


    /**
     * 解密/解码
     *
     * @param encryptedData encrypted data
     * @param key           key
     * @return decrypted data
     */
    public byte[] decode(byte[] encryptedData, String key)
    {
        //生成密钥
        byte[] keyBytes = generateKey(key);

        Cipher cipher;
        try
        {
            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            cipher = Cipher.getInstance(CIPHER_ALGORITHM, CIPHER_ALGORITHM_PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, IV_SPEC);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        //解密数据
        byte[] decrypted;
        try
        {
            decrypted = cipher.doFinal(encryptedData);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return decrypted;
    }

    /**
     * 生成密钥
     *
     * @param password 密码
     * @return aes 密钥
     */
    private static byte[] generateKey(String password) throws RuntimeException
    {
        byte[] passwordBytes;
        try
        {
            passwordBytes = password.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }

        int length = passwordBytes.length > 32 ? 32 : passwordBytes.length;
        byte[] result = new byte[32];
        System.arraycopy(passwordBytes, 0, result, 0, length);

        return result;
    }
}
