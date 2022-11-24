package com.lizzie.lotto;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/main")
    public String main(Model model) throws IOException, ParseException, org.json.simple.parser.ParseException {

       String data =  mainPageService.getApi();
        NumberEntity numbers = json(data);

        model.addAttribute("numbers", numbers);

        System.out.println(numbers.times);

        return "/main";
    }

    public NumberEntity json(String data) throws ParseException, org.json.simple.parser.ParseException {

            // JSONParser로 JSONObject 객체
        JSONObject objData = (JSONObject)new JSONParser().parse(data);

        int times =  Integer.parseInt ( objData.get("drwNo").toString() );
        int no1 =  Integer.parseInt (objData.get("drwtNo1").toString());
        int no2 =  Integer.parseInt (objData.get("drwtNo2").toString());
        int no3 =  Integer.parseInt (objData.get("drwtNo3").toString());
        int no4 =  Integer.parseInt ( objData.get("drwtNo4").toString());
        int no5 =  Integer.parseInt ( objData.get("drwtNo5").toString());
        int no6 =  Integer.parseInt (objData.get("drwtNo6").toString());
        int bonusNo = Integer.parseInt (objData.get("bnusNo").toString());

        NumberEntity numbers = new NumberEntity( times,no1,no2,no3,no4,no5,no6,bonusNo);

            return numbers;
        }


        @ResponseBody
        @GetMapping("/make")
        public int[] makenumber(@RequestParam Map<String,String> params, @RequestParam(value="include", required=false) String []include,
                                @RequestParam(value="except", required=false) String []except, HttpServletResponse response) throws IOException {

            String[] include_numbers = include;
            String[] except_numbers = except;

//            for(int i = 0; i < include.length ; i++) {
//                for(int j = 0; j< except.length; j++) {
//
//                    if(include[i].equals(except[j]) ) {
//                        response.setContentType("text/html; charset=utf-8");
//                        response.getWriter().print("<script>alert('로그인 후 이용해주세요');  location.href = \"/member/login\";</script>");
//
//
//                    }
//
//                }
//
//            }

            int[] numbers = mainPageService.pick_a_number(include,except);



        return numbers ;
        }

}
