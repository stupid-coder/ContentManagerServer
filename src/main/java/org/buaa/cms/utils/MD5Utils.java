package org.buaa.cms.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by stupid-coder on 7/18/16.
 */
public class MD5Utils {

    Log logger = LogFactory.getLog(MD5Utils.class);

    public static String getMD5(byte[] bytes)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);

            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
