import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.store.entities.CompanyAddress;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class test {

    private List<Integer> intList = new ArrayList<>();

    public test() {
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
    }

    public List<Integer> getList() {
        System.out.println("get list");
        return intList;
    }

    public static void main(String[] args) {


        test tt = new test();
        for (Integer val : tt.getList()) {
            System.out.println("get val " + val);
        }


    }
}
