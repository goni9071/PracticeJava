package byteBuffer;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocateDirect(10);
        System.out.println("limit:" + bb.limit());
        System.out.println("posotion:" + bb.position());
        bb.put((byte) 0);
        System.out.println("limit:" + bb.limit());
        System.out.println("posotion:" + bb.position());
        bb.rewind();
        System.out.println("limit:" + bb.limit());
        System.out.println("posotion:" + bb.position());
        bb.put((byte) 0);
        System.out.println("limit:" + bb.limit());
        System.out.println("posotion:" + bb.position());
        bb.clear();
        System.out.println("limit:" + bb.limit());
        System.out.println("posotion:" + bb.position());
        bb.put((byte) 0);
        System.out.println("limit:" + bb.limit());
        System.out.println("posotion:" + bb.position());
        bb.flip();
        ByteBuffer bc = bb.duplicate();
        bc.clear();
        System.out.println("limit:" + bc.limit());
        System.out.println("posotion:" + bc.position());
    }
}
