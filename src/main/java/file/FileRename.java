package file;

import java.io.File;

public class FileRename {
    public static void main(String[] args) {
        final String pathname = "D:/Goni/Downloads/킹덤 25~28번역본/킹덤 306-311/311/";
        File dir = new File(pathname);
        for (File file : dir.listFiles()) {
            String newFileName = "킹덤311_";
            int idx = Integer.parseInt(file.getName().replaceAll("긌깛긐__311", "").replaceAll("[^\\d]", ""));
            if (idx < 10)
                newFileName += "00" + idx;
            else if (idx < 100)
                newFileName += "0" + idx;
            else
                newFileName += idx;
            newFileName += ".jpg";
            file.renameTo(new File(pathname + newFileName));
            System.out.println(pathname + newFileName);
        }
    }
}
