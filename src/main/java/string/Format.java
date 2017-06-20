package string;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class Format {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String a = "똥";

		final byte[] bytes = a.getBytes("UTF-8");
		System.out.println(String.format("%02X", bytes[0]));
		System.out.println(String.format("%02X", bytes[1]));
		System.out.println(String.format("%02X", bytes[2]));
		System.out.println(String.format("%-10s", a) + "물");// rpad 공백
		System.out.println(String.format("%1$-3s", "가나다라") + "마");// rpad 숫자보다
																	// 많으면?
		System.out.println(String.format("%1$-10s", "가나 다라").replace(" ", "0"));// rpad
																				// 0

		System.out.println(String.format("%02X", (byte) '1'));// 0x31
		System.out.println(String.format("%02X", (byte) 0));// 0x00
		System.out.println(String.format("%02X", (byte) ' '));// 0x20
		char b[] = new char[1];
		System.out.println(String.format("%02X", (byte) b[0]));// 0x00

		String.format("%,d", 1000000);
	}

	public static void doubleTest() {
		double a = 1000000000.00;
		System.out.println(new Double(a).toString()); // 1.0E9
		System.out.println(a + ""); // 1.0E9
		System.out.println(String.valueOf(a)); // 1.0E9
		System.out.println(Double.toString(a)); // 1.0E9
		System.out.println(String.format("%f", a)); // 1000000000.000000
		System.out.println(String.format("%1$,.2f", a)); // 1,000,000,000.00
		System.out.println(String.format("%1$,.0f", a)); // 1,000,000,000
		System.out.println(new DecimalFormat("###.#####").format(a)); // 1000000000
	}
}
