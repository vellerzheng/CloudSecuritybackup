/**
 * Created by vellerzheng on 2017/11/10.
 */


function advicePost(){
    alert("333");
    var form = new FormData(document.getElementById("advice"));
//             var req = new XMLHttpRequest();
//             req.open("post", "${pageContext.request.contextPath}/public/testupload", false);
//             req.send(form);

    $.ajax({
        url:"${pageContext.request.contextPath}/public/adviceUpload",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        success:function(data){
            window.clearInterval(timer);
            console.log("over..");
        },
        error:function(e){
            alert("错误！！");
            window.clearInterval(timer);
        }
    });
    get();//此处为上传文件的进度条
}

//从后台API获取信息
function storage(pub_topic,pub_text,sub_topic,sub_text){
    var pub_topic = pub_topic;
    var pub_text = pub_text;
    var sub_topic = sub_topic;
    var sub_text = sub_text;
    var result;
    jQuery.ajax({
        url:'http://121.42.174.208/wxc/MQTT_test/nodev1_2_2/mysql.php',
        //url:'http://121.42.174.208/hmt/cpsApi/webservice.php',
        type:"post",
        async: false,
        data:{
            pub_topic:pub_topic,
            pub_text:pub_text,
            sub_topic:sub_topic,
            sub_text:sub_text
        },
        success: function (success) {
            console.log("成功"+pub_topic);

        },
        error:function(){
            console.log("错误");
        }
    });
    return ;
}