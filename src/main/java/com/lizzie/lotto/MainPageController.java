package com.lizzie.lotto;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;


    @GetMapping("/test")
    public String test(){

        return "main3";
    }
    @GetMapping("/main")
    public String main(Model model) throws IOException, ParseException, org.json.simple.parser.ParseException {

       String data =  mainPageService.getApi();   //당첨번호 불러오기
        NumberEntity numbers = json(data);

        model.addAttribute("numbers", numbers);  //numbers = 당첨번호

        return "/main3";
    }

    public NumberEntity json(String data) throws ParseException, org.json.simple.parser.ParseException {

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


        @PostMapping("/make")
        public String makenumber(@RequestParam Map<String,String> params, @RequestParam(value="include", required=false) String []include,
                                      @RequestParam(value="except", required=false) String []except, Model model) {

            String[] include_numbers = include;
            String[] except_numbers = except;

            List<PickedNumberEntity> pickedNumberEntityList = new ArrayList<>();

            for(int i = 1; i<6; i++) {
                int[] numbers = mainPageService.pick_a_number(include, except);
                Arrays.sort(numbers);
                PickedNumberEntity pickedNumberEntity = new PickedNumberEntity(i,numbers[0],numbers[1],numbers[2],numbers[3],numbers[4],numbers[5]);
                pickedNumberEntityList.add(pickedNumberEntity);
            }

            model.addAttribute("pickedNumberEntityList",pickedNumberEntityList);

            System.out.println(pickedNumberEntityList.get(0));

            return "/main3 :: #resultDiv";

        }

//    @PostMapping("/send")
//    public String ajaxHome(@RequestParam Map<String,String> params, @RequestParam(value="include", required=false) String []include,
//    @RequestParam(value="except", required=false) String []except, Model model){
//
//        String msg = "메세지 확인";
//        model.addAttribute("msg",msg);
//        return "/main :: #resultDiv";
//    }


}
