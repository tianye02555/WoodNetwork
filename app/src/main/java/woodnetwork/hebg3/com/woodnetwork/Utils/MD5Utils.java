package woodnetwork.hebg3.com.woodnetwork.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	public static String getMD5(String str){
		StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte [] by = digest.digest(str.getBytes());
			for (int i = 0; i < by.length; i++) {
				int result = by[i]&0xff;
				if(result<16){
					buffer.append("0");
				}
				buffer.append(Integer.toHexString(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
}
