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
        System.out.println(String.format("%02X ", bytes[0]));
        System.out.println(String.format("%02X ", bytes[1]));
        System.out.println(String.format("%02X ", bytes[2]));
        String.format("%,d", 1000000);
    }
}
