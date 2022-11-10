function askVerifyCode(){
    $.get('verify-code', {
        email:$("#input-email").val()
    })

}