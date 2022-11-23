package com.lizzie.lotto;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MainPageService {

    public String getApi() throws IOException {
        String urladress = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";
        String number = "861";
        String adress = urladress+number;

        URL url = new URL(adress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);

         StringBuffer sb = null;
        try {
            sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while (br.ready()) {
                sb.append(br.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


}
