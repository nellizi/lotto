package com.lizzie.lotto;

import org.attoparser.trace.MarkupTraceEvent;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Service
public class MainPageService {

    public String getApi() throws IOException {

        String urladress = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";
        int turn = 1043;
//        String date = DateUtil.getDate(2022, 11, 28);
//
//        if(LocalDate.now().toString() .equals(date)){
//            turn = 1043;
//            number = turn+"";
//        }

        //경과 일수 구하기

        LocalDateTime toDay = LocalDateTime.now();

        Date nowDate = java.sql.Timestamp.valueOf(toDay);
        Date settedDate = DateUtil.getRealDate(2021,10,9);

        long diffSec = (nowDate.getTime() - settedDate.getTime()) / 1000; //초 차이
        int diffDays = (int) (diffSec / (24 * 60 * 60));

         turn = 984 + diffDays/7;

        String number = turn+"";


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

