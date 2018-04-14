
     //排序函数
     function controlOrder(e){
     //点击的key
     var orderKeyVar = $(e).children("input").val();
     //传递排序顺序
     if( orderKeyVar == $("#orderKey").val() ){
          if( $("#ascDesc").val()=="DESC"){
              $("#ascDesc").val("ASC");
          }else{
              $("#ascDesc").val("DESC");
          }
     }
     //切换点击排序时  默认是倒序排列
     if( orderKeyVar != $("#orderKey").val() ){
              $("#ascDesc").val("DESC");
     }
     //判断完毕后改变hidden中的传递排序key
     $("#orderKey").val(orderKeyVar); 
  
     //调用首页方法
     controlPage(1,'${pager.getSizePerPage()}');
     
     }

     //赋值给pager的hidden变量
	 function controlPageHql(){
		  
	      var selectVarQ = " from ";
	      var orderVarQ = " order by ";
	      var ascDescVarQ = "DESC";
	      var orderKeyVarQ = "id";
	      var whereStringVarQ = ""; 
		 //from 提交前 组合查询部分和排序部分的参数 
	      controlHql(); 
		//查询字符传递即使没有值也该传输一个位置
		  $("input.search").each(function(){
			  whereStringVarQ += $(this).val() +"#|";
		     }); 
		//查询语句
		$("#selectHql").val(selectVarQ);
         $("#whereHql").val(whereVarQ);
         //排序语句
         $("#orderHql").val(orderVarQ);
         //检索部分的参数
         $("#whereString").val(whereStringVarQ);
         //排序key和排序顺序
         $("#orderKey").val(orderKeyVarQ);
         $("#ascDesc").val(ascDescVarQ);
		//调用首页方法
		controlPage(1,'${pager.getSizePerPage()}');
	 }

     //翻页方法
     function controlPage(varNowPageNo,varSizePerPage){
       //设置一下   跳转到第几页
  	   $("#nowPageNo").val(varNowPageNo);
  	   //调用页面的自定义翻页部分的变量方法
	    pageVarChange();	
	 
  	   //form提交
	   $("#pageForm").attr("action","/clouds/users/admin/adviceInfo/list");
	   $("#actType").val("list");
	   $("#pageForm").submit();
     }

     //第几页的输入修正
     //判断是否为正整数isPositiveNum
     function controlPageNo(e,varBefore,varSizePerPage){
         if( e == null || e.value.trim()=="" || isPositiveNum(e.value.trim()) ){
           e.value = varBefore;
           return false;
           }
    	 controlPage(e.value.trim(),varSizePerPage);
     }
     
     //每页多少条数的输入修正
     //判断是否为正整数isPositiveNum
     function controlPageSize(e,varNowPageNo,varBefore){
    	 if( e == null || e.value.trim()=="" || isPositiveNum(e.value.trim()) ){
    	   e.value = varBefore;
           return false;
           }
    	 controlPage(varNowPageNo,e.value.trim());
     }
     
     //判断是否为正整数  
     function isPositiveNum(s){ 
     var re = /^[0-9]*[1-9][0-9]*$/ ;  
     return !re.test(s);  
     } 
     
     //可以在本页面定制翻页部分的 参数   和  设置方法
	 function pageVarChange(){
	   //对每页条数最大值的定制
	   if( $("#sizePerPage")!=null&&$("#sizePerPage").val()!=""&&$("#sizePerPage").val()>50 ){
	      $("#sizePerPage").val(50);
	    }  
	   
	 }
	 
	 
	 // 页面加载完毕后的响应事件代码列表如下：
		$(document).ready(function(){ 
			
			
			
			
//			//对hr进行变色
//			var j=0;
//			 $("hr").each(function(){
//				 j++;
//				 if(j%2==0){
//				 
//					 $(this).attr("style","height:0px; border:none; border-top:1px  #bbbbbb solid ;margin:0");
//				 }else{
//					 $(this).attr("style","height:0px; border:none; border-top:1px  #000000 dotted ;margin:0");
//				 }
//				 
//			 });
			 
			 
			 
			//排序的图标
			 $("a.order").each(function(){
			    if ( $(this).find("input").val()==$("#orderKey").val() ) {
			         $(this).find("img").hide();
			         if ($("#ascDesc").val()=="DESC" ){
				         $(this).find("img.down").show();
				         }else{
				         $(this).find("img.up").show();
				         }
			    }else{
			         $(this).find("img").hide();
			         $(this).find("img.down2").show();
			    }
			  });
			 
			 //检索的内容带回来
			var whereStringVar = $("#whereString").val().split("|");
			var i=0;
			$("input.search").each(function () {
				if(whereStringVar[i]!=null) {
					$(this).val(whereStringVar[i].replace("#", ""));
					i++;
				}
			});
            $("select.search").each(function () {
                if(whereStringVar[i]!=null) {
                    $(this).val(whereStringVar[i].replace("#", ""));
                    i++;
                }
            });

			
			
				
		});
		//详细  编辑  删除 
		function controlCkBox(actVar){
		     var i=0;
		     var idVar ="";
		     $("#ckBox.listCont").each(function(){
		         if( $(this).is(":checked") ){
		         idVar += $(this).val()+",";
		         i++; 
		         }
		      });
		      if( i==0 ){
		      alert("请选中记录后点击此操作！");
		      return false;
		      }
		      if( actVar!="deleteFromList"&&i>1 ){
		      alert("请仅选一条记录点击此操作！");
		      return false;
		      } 
		      $("#idList").val(idVar);
		      //form提交
		      if(actVar == "editFromList"){
		    	   $("#actType").val("edit");
		           $("#pageForm").attr("action","edit?id="+idVar.substring(0, idVar.length-1)); 
		       }
		      if(actVar == "detailFromList"){
		    	   $("#actType").val("detail");
			       $("#pageForm").attr("action","edit?id="+idVar.substring(0, idVar.length-1));
			   }
		      if(actVar == "deleteFromList"){ 
		    	   $("#actType").val("delete");
		    	   $("#pageForm").attr("action","list");
			   }
		     
			   $("#pageForm").submit();
			   
		     }
		 