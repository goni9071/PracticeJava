package bit;

/**
 * <pre>
 * 1 bit = -128 ~ 127
 * & 0x80 = 첫번째 비트만 빼고 모두 0으로 변환
 * & 0x7F = 1바이트인 경우는 의미 없지만 2바이트 짜리 숫자라면 음수로 판별되면 안되므로 첫번째 비트만 0으로 변환
 * </pre>
 * 
 * @author user
 * 
 */
public class BitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte a = -128;
		byte b = 1;
		byte[] as = new byte[1000];
		as[0] = a;
		as[1] = b;
		System.out.println("1. " + decodeRemainingLength(as));
		System.out.println("2. 37 & 0x80 :" + (a & 0x80));
		System.out.println("2. 37 & 0x7F :" + (a & 0x7F));
		System.out.println("2. a :" + a);

	}

	static int decodeRemainingLength(byte[] bs) {
		int multiplier = 1;
		int value = 0;
		byte digit;
		int i = 0;
		do {
			if (bs[i] == '\0') {
				return -1;
			}
			digit = bs[i++];
			value += (digit & 0x7F) * multiplier;
			multiplier *= 128;
		} while ((digit & 0x80) != 0);
		return value;
	}
}
