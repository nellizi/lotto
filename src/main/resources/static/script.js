        var number = document.getElementsByClassName("lottocircle");
        for (var i = 0 ; i < number.length ; i++) {
            if(parseInt(number[i].textContent)>=40){
                number[i].style="background-color:#8b00ff";
            }else if(parseInt(number[i].textContent)>=30){
                number[i].style="background-color:#146eb4";
            }else if(parseInt(number[i].textContent)>=20){
                number[i].style="background-color:#c1d82f";
            }else if(parseInt(number[i].textContent)>=10){
                number[i].style="background-color:#fbb034";
            }
            else{
                number[i].style="background-color:#ff0000";
            }
        }


    function count_check(obj){
        var chkBox = document.getElementsByName("include");
        var chkCnt = 0;
        var num = obj.value;

        if(document.getElementById(num).disabled ==true){
            document.getElementById(num).disabled =false;
            }else{
            document.getElementById(num).disabled =true;
        }

        for(var i=0; i<chkBox.length; i++){
            if(chkBox[i].checked){
                chkCnt++;
            }
        }
        if(chkCnt > 5){
            alert("최대 5개까지 선택할 수 있습니다.");
            obj.checked=false;
            return false;
        }
    }

    function count_check2(obj){
        var chkBox = document.getElementsByName("except");
        var chkCnt = 0;

        for(var i=0; i<chkBox.length; i++){
            if(chkBox[i].checked){
                chkCnt++;
            }
        }
        if(chkCnt > 39){
            alert("최대 39개까지 선택할 수 있습니다.");
            obj.checked=false;
            return false;
        }
    }

function submit1(){
    alert("실행");
    var formValues = $("form[name=checked_number]").serialize() ;
    $.ajax({
        url: "/make",
        type: "POST",
        data: c,
        dataType : 'json',
     }).done(function (data) {
        $('#resultDiv').replaceWith(data);
    });
}


function submit2(){
    alert("추첨");
    var formValues = $("form[name=checked_number]").serialize() ;
       $.ajax({
                    url: "/make",
                    data: formValues,
                    type: 'POST',
                }).done(function (data){
                    $("#resultDiv").replaceWith(data);
                });
}



    function reset_disabled(){

        const disabled = document.getElementsByName('except');

        const ckeckedIn = document.getElementsByName('include');
        const ckeckedEx = document.getElementsByName('except');

        disabled.forEach((checkbox) => {
        checkbox.disabled = false;
        })

         ckeckedIn.forEach((checkbox) => {
        checkbox.checked = false;
        })

         ckeckedEx.forEach((checkbox) => {
        checkbox.checked = false;
        })




    }