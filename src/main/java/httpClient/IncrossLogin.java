package httpClient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IncrossLogin {
    public static void main(String[] args) {
        try {
            StringBuffer postDataBuilder = new StringBuffer();
            postDataBuilder.append("{\"userId\":\"goni9071\",\"password\":\"a12345\",\"langidx\":0}");

            byte[] postData = postDataBuilder.toString().getBytes("UTF-8");
            // //
            URL url = new URL("http://gw.incross.com/ajaxpro/WorkCrew.Web.Root.Login,WorkCrew.Web.Root.ashx");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Language", "ko");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("x-ajaxpro-method", "GetDepartInfo");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            // conn.setRequestProperty("Cookie",
            // "LoginInfo=LoginRbLang=ko&LoginUserId=goni9071&LoginUserIdCheck=True&MailDomain=incross.com; HNEWPopUp=35|31|13|29|17|5|40|57|58|60; VOTEPopUp=5; ASP.NET_SessionId=3dtphbujxy5jfies1p20g3c1; huchems_sso=; UserInfo=CookieId=599dbbb1a30d4beea10f6391ec223e1e&LanguageId=1&DepartIndex=1&MenuCd=000; LangIndex=1; UserID=; UserName=7KCV7ZiE7Jqw; PositionID=300; PositionName=64yA66as; DepartID=0025; DepartName=7ZSM656r7Y+87IKs7JeF7YyA; GradeID=300; GradeName=64yA66as; GroupCode=RzAwMQ==; CMAGAuth=NDAw; LoginID=; MessengerLoginID=");
            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0; .NET4.0C; .NET4.0E)");

            OutputStream out = conn.getOutputStream();
            out.write(postData);
            out.close();
            InputStream inputStream = conn.getInputStream();
            byte[] b = new byte[1024];
            while (inputStream.read(b) > 0) {
                System.out.print(new String(b, "utf-8"));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
