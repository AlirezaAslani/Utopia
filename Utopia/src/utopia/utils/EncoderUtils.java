package utopia.utils;

import java.util.Base64;

public class EncoderUtils {
	public static String encoder(String string) {
		String encodedString = Base64.getEncoder().encodeToString(string.getBytes());
		return encodedString;
	}
	
	public static String decoder(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	
	public static void main(String[] args) {
		String alireza = "BMI1401DEV";
		String en = encoder(alireza);
		System.out.println(en);
		System.out.println("---------");
		System.out.print(decoder(en));
	}
}


