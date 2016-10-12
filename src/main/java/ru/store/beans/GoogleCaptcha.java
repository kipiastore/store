package ru.store.beans;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.store.exceptions.NotFoundException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 */
@Component
public class GoogleCaptcha {

    private String secret;

    public GoogleCaptcha() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/baseSettings.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            secret = properties.getProperty("GoogleCaptchaPrivateKey");
        } catch (Exception ex) {
            throw new NotFoundException("baseSettings.properties not found!");
        }
    }

    public CaptchaResponse check(String reCaptchaResponse) {
        CaptchaResponse captchaResponse = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String url = "https://www.google.com/recaptcha/api/siteverify";
            HttpGet get = new HttpGet(url + "?secret=" + secret + "&response=" + reCaptchaResponse);
            HttpResponse response = httpClient.execute(get);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                result.append(line);
            captchaResponse = new Gson().fromJson(result.toString(), CaptchaResponse.class);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return captchaResponse;
    }

    public static class CaptchaResponse {
        public Boolean success;
        public String challenge_ts;
        public String hostname;
        public String[] errorCodes;

        @Override
        public String toString() {
            return "CaptchaResponse{" +
                    "success=" + success +
                    ", challenge_ts='" + challenge_ts + '\'' +
                    ", hostname='" + hostname + '\'' +
                    ", errorCodes=" + Arrays.toString(errorCodes) +
                    '}';
        }
    }

}
