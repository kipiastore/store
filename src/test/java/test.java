import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.store.entities.CompanyAddress;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;


/**
 *
 */
public class test {

    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
        //String str = "Тест";
        //System.out.println(URLEncoder.encode(str, "UTF-8"));
        //System.out.println(URLDecoder.decode(str, URLEncoder.encode(str, "UTF-8")));
        //System.out.println((7%2));

        Gson g = new Gson();
        List<CompanyAddress> companyAddresses = g.fromJson("{\"name\": \"John\"}", new TypeToken<List<CompanyAddress>>(){}.getType());

    }
}
