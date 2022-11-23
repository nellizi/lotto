package com.lizzie.lotto;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

   // @ResponseBody
    @GetMapping("/main")
    public String main(Model model) throws IOException, ParseException, org.json.simple.parser.ParseException {

       String data =  mainPageService.getApi();
        NumberEntity numbers = json(data);
//
//        Numbers numbers = new Numbers(numberData[0],numberData[1],numberData[2],numberData[3],
//                                        numberData[4],numberData[5],numberData[6],numberData[7]);
        model.addAttribute("numbers", numbers);

        System.out.println(numbers.times);

        return "/main";
    }

    public NumberEntity json(String data) throws ParseException, org.json.simple.parser.ParseException {

            // JSONParser로 JSONObject 객체
        JSONObject objData = (JSONObject)new JSONParser().parse(data);

        String times = objData.get("drwNo").toString();
        String no1 = objData.get("drwtNo1").toString();
        String no2 = objData.get("drwtNo2").toString();
        String no3 = objData.get("drwtNo3").toString();
        String no4 = objData.get("drwtNo4").toString();
        String no5 = objData.get("drwtNo5").toString();
        String no6 = objData.get("drwtNo6").toString();
        String bonusNo = objData.get("bnusNo").toString();

        NumberEntity numbers = new NumberEntity( times,no1,no2,no3,no4,no5,no6,bonusNo);

            return numbers;
        }


}
