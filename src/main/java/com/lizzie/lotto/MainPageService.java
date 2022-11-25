package com.lizzie.lotto;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

@Service
public class MainPageService {

    public String getApi() throws IOException {
        String urladress = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";
        String number = "861";
        String adress = urladress + number;

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

    public int[] pick_a_number(String[] include_numbers, String[] except_numbers) {
        int pickedNumber[] = new int[6];
        int indexNum = 0;

        if (include_numbers != null) {
            indexNum = include_numbers.length;

            for (int k = 0; k < include_numbers.length; k++) {
                pickedNumber[k] = Integer.parseInt(include_numbers[k]);
            }
        }

        for (int i = indexNum; i < 6; ) {
            int num = (int) (Math.random() * 45) + 1;    // 1~46까지의 임의의 수를 받는다.
            if (contain(num, except_numbers)) {
                continue;
            }
            pickedNumber[i] = num;
            for (int j = 0; j < i; j++) {    // 중복된 번호가 있으면 이전 포문으로 돌아가 다시 시행한다.
                if (pickedNumber[i] == pickedNumber[j]) {
                    i--;
                    break;
                }
            }
            i++;
        }
        return pickedNumber;
    }

    private boolean contain(int num, String[] except_numbers) {
        if (except_numbers == null) {
            return false;
        }
        String number = num + "";
        return Arrays.asList(except_numbers).contains(number);
    }

}

