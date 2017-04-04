package file;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
  /**
   * 동일한 파일명을 가진 파일이 존재하면 파일명 변경. a.txt -> a(1).txt -> a(2).txt
   * 
   * @param file
   * @return
   */
  public static File increaseFileName(File file) {
    return increaseFileName(file, 0);
  }

  public static File increaseFileName(File file, int loopCnt) {
    if (loopCnt > 1000) {// 무한루프 방어코드
      return file;
    }
    File result = file;
    if (file.exists()) {
      String src = file.getAbsolutePath();
      String regx = "(.*\\()(\\d+)(\\)\\.[^\\.]+)$"; // a(1).txt
      Pattern p = Pattern.compile(regx);
      Matcher m = p.matcher(src);

      if (m.find()) {
        int no = Integer.parseInt(m.group(2)) + 1;
        result = new File(src.replaceFirst(regx, "$1" + no + "$3"));
      } else {
        regx = "(.*)(\\.[^\\.]+)$";// a.txt
        p = Pattern.compile(regx);
        m = p.matcher(src);
        if (m.find()) {
          result = new File(src.replaceFirst(regx, "$1(" + "1" + ")$2"));
        } else {
          regx = "(.*\\()(\\d+)(\\)+)$";// a(1)
          p = Pattern.compile(regx);
          m = p.matcher(src);
          if (m.find()) {
            int no = Integer.parseInt(m.group(2)) + 1;
            result = new File(src.replaceFirst(regx, "$1" + no + "$3"));
          } else {// a
            result = new File(src + "(1)");
          }
        }
      }
      if (result.exists()) {
        result = increaseFileName(result, ++loopCnt);
      }
    }
    return result;
  }
}
