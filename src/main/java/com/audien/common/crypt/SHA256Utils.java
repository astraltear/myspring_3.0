package com.audien.common.crypt;
public class SHA256Utils {
	
	public static String getSHA256(String str) {
		byte input [] = str.getBytes();
		IMessageDigest md = HashFactory.getInstance("SHA-256");
		md.update(input, 0, input.length);
		byte [] digest  = md.digest();
		return base64Encode(digest);
	}
	

/*	public static void main(String[] args) {
		byte input [] = "abdedeee".getBytes();
		IMessageDigest md = HashFactory.getInstance("SHA-256");
		
		md.update(input, 0, input.length);
		byte [] digest  = md.digest();
	
		System.out.println(base64Encode(digest));
	}*/
	
	 public static String base64Encode(byte[] data) {
	     int len = data.length;
	     if (len <= 0)
	         return "";
	     char[] out = new char[len / 3 * 4 + 4];
	     int ridx = 0;
	     int widx = 0;
	     int rest = len;
	     while (rest >= 3) {
	         int i = ((data[ridx] & 0xff) << 16)
	                 + ((data[ridx + 1] & 0xff) << 8) + (data[ridx + 2] & 0xff);
	         out[widx++] = BASE64CHARS[i >> 18];
	         out[widx++] = BASE64CHARS[(i >> 12) & 0x3f];
	         out[widx++] = BASE64CHARS[(i >> 6) & 0x3f];
	         out[widx++] = BASE64CHARS[i & 0x3f];
	         ridx += 3;
	         rest -= 3;
	     }
	     if (rest == 1) {
	         int i = data[ridx] & 0xff;
	         out[widx++] = BASE64CHARS[i >> 2];
	         out[widx++] = BASE64CHARS[(i << 4) & 0x3f];
	         out[widx++] = BASE64PAD;
	         out[widx++] = BASE64PAD;
	     } else if (rest == 2) {
	         int i = ((data[ridx] & 0xff) << 8) + (data[ridx + 1] & 0xff);
	         out[widx++] = BASE64CHARS[i >> 10];
	         out[widx++] = BASE64CHARS[(i >> 4) & 0x3f];
	         out[widx++] = BASE64CHARS[(i << 2) & 0x3f];
	         out[widx++] = BASE64PAD;
	     }
	     return new String(out, 0, widx);
	 }
	 
	 private static final char[] BASE64CHARS = { 'A', 'B', 'C', 'D', 'E', 'F',
         'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
         'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
         'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
         't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
         '6', '7', '8', '9', '+', '/' };
	 
	 private static final char BASE64PAD = '=';

}
