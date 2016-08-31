import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Asura on 31.08.2016.
 */
public class test {

    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
        String str = "Тест";
        System.out.println(URLEncoder.encode(str, "UTF-8"));
        System.out.println(URLDecoder.decode(str, URLEncoder.encode(str, "UTF-8")));
    }
}
