package string;

import java.io.UnsupportedEncodingException;

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
    System.out.println(String.format("%1$-3s", "가나다라") + "마");// rpad 숫자보다 많으면?
    System.out.println(String.format("%1$-10s", "가나 다라").replace(" ", "0"));// rpad 0

    System.out.println(String.format("%02X", (byte) '1'));// 31

    String.format("%,d", 1000000);
  }
}
