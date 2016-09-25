import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
/**
 *
 */
public class test {

    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
        //String str = "Тест";
        //System.out.println(URLEncoder.encode(str, "UTF-8"));
        //System.out.println(URLDecoder.decode(str, URLEncoder.encode(str, "UTF-8")));
        //System.out.println((7%2));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("Asura333");
        System.out.println(hashedPassword);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
}
