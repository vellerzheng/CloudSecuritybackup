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
        url:"/js/AJAX.js/adviceUpload",
        type:"post",
        data:JSON.stringify(formToJson),
        contentType:"application/json",

        success:function(data){
            alert(data);
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


function  userRegisterPost() {
    var formToJson =$("#userRegister").serializeObject();
    $.ajax({
        url:"/js/AJAX.js/userRegister",
        type:"post",
        data:JSON.stringify(formToJson),
        contentType:"application/json",

        success:function(result){
           if(result.resultCode=="2000"){
               alert(result.resultMsg+" 点击确定，5秒后跳转页面！");
               // 5秒后跳转，单位毫秒
              setTimeout(redirectUrl("/clouds/users/login"),5000);
           }
           if(result.resultCode=="5000"){
               alert(result.resultMsg);
               setTimeout(redirectUrl("/clouds/users/register"),5000);
           }
        },
        error:function(e){
            alert('注册失败，用户已经存在，请重试');
            console.log(e);
            setTimeout(redirectUrl("/clouds/users/register"),5000);
        }
    });
    document.getElementById("userRegister").reset();
}


function addCloudConfig(name,__curl){
    var jspAttr ='#'+name;
    var obj = $(jspAttr).serializeObject();


    $.ajax({
        url:__curl,
        type:"post",
        data:JSON.stringify(obj),
        contentType:"application/json",

        success:function(result){
            if(result.resultCode=="2000"){
                alert(result.resultMsg +" 点击确定，2秒后跳转页面！");
                var f=document.referrer;
                window.location.href=f;
            }
            if(result.resultCode=="5000"){
                alert(result.resultMsg);
                setTimeout(redirectUrl("/clouds/users/register"),5000);
            }
        },
        error:function(e){
            alert('设置失败，请重试');
            console.log(e);
            setTimeout(redirectUrl("/clouds/users/default/"+name),5000);
        }
    });
/*    document.getElementById("aliyunConfig").reset();
    document.getElementById("neteaseConfig").reset();
    document.getElementById("qcloudConfig").reset();
    document.getElementById("qiniuConfig").reset();
    document.getElementById("upyunConfig").reset();*/


}


function redirectUrl(url)
{
    location.href=url;
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


