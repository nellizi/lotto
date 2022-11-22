package com.lizzie.lotto;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String mainpage() {

        return "/main";
    }


    @ResponseBody
    @GetMapping("/api")
    public StringBuffer api() throws IOException {
        URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=861");
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

        return sb;
    }


}
