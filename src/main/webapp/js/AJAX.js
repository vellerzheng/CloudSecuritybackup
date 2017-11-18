/**
 * Created by vellerzheng on 2017/11/10.
 */
function mover(){
    event.srcElement.focus();
    event.srcElement.select();
}

function checkEmail(){
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
    var obj = document.getElementById("email"); //要验证的对象
    if(obj.value === ""){ //输入不能为空
        alert("输入不能为空!");
        return false;
    }else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
        alert("邮箱验证不通过!");
        return false;
    }else{
        return true;
    }
}

function advicePostJson(){
    var formToJson =$("#adviceId").serializeObject();
    $.ajax({
        url:"http://localhost:8080/api/publicUser/adviceUpload",
        type:"post",
        data:JSON.stringify(formToJson),
        contentType:"application/json",

        success:function(data){
            alert("提交成功！");
            console.log(data);
            console.log("over..");
        },
        error:function(e){
            console.log('wrong');
            console.log(e);
        }
    });
    document.getElementById("adviceId").reset();
    //get();//此处为上传文件的进度条
}


$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};