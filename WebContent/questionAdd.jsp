<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加试题</title>
	<!-- Bootstrap Styles-->
    <link href="css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="css/font-awesome.css" rel="stylesheet" />
     <!-- Morris Chart Styles-->
   
        <!-- Custom Styles-->
    <link href="css/custom-styles.css" rel="stylesheet" />
     <!-- Google Fonts-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link href="js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>

</head>
<style>
   body{
    background-color:#fff;
   }
</style>
 <script type="text/javascript">
 //页面加载进行判断
 	function panduan(){
 		parent.scrollTo(0,0);
 		change();
 		addPoint()
 }
 

 //知识点下拉框值改变
     function addPoint(){
	    //alert(1);
    	 if($('#kId').val() == '-1'){
      		$('#addPoint').show();
      	}else{
      		$('#addPoint').hide();
      	}
     }
//题型下拉框改变
     function addT(){
    	 if($('#type').val() == '-1'){
       		$('#addType').show();
       	}else{
       		$('#addType').hide();
       	}
	 }
     //添加知识点
     function addKp(){
    	 if($('#newPoint').val() !=null){
    		 var hre='questionAddKp.do?kpContent=' + $('#newPoint').val();
    			window.location.href=hre;
 		}else{
 			alert("输入为空");
 		}
     }
	 //添加题型
	 function addType(){
		 if($('#newType').val() !=null){
    		 var hre='questionAddType.do?typeContent=' + $('#newType').val();
    			window.location.href=hre;
 		}else{
 			alert("输入为空");
 		}
	 }
     //添加
     function add(){
    	 var msg="";
    	 var flag = true;
    	 var ue = UE.getEditor('editor');
    	 var level=$('#level input[name="ques.level"]:checked').val();
    	 if(level == null){
    		 msg="<div style='margin-left:50px'>题目难度未设定!<br>";
    		 flag=false;
    	 }
    	 if(ue.getContent() == null || ue.getContent().length == 0){
    		 msg+="题目未设定!<br>";
    		 flag=false;
    	 }
    	 if($('#answer').val()==null || $('#answer').val().length == 0){
    		 msg+="答案未设定!<br>";
    		 flag=false;
    	 }
    	 if(flag){
    		 $('#addForm').submit();
    	 }else{
    		 msg+="</div>";
    		 showM(msg);
    	 }
     }
     //弹出信息提示模态框
     function showM(msg){
        parent.scrollTo(0,0);
    	$('#modal-title').html('不符合提交规则,请补充完善题目信息：');
 		$('#modal-body').html(msg);
 		$('#modal_btn_2').hide();
 		$('#modal').modal('toggle');
     }
  </script>
<body style="backgroud-color:#fff" onload="panduan()">
	<div style="height: 100%">
	  <div id="page-inner" style="height: 100%">
		<div class="row" style="height: 50px">
			<div class="col-md-12">
				<h3 class="" id='controlTitle'>
					<a href="questionList.do">添加题目</a>
				</h3>
			</div>
		</div>
        <div class="col-md-12" style="border-color: #ddd;border-style: solid;border-width: 1px;">
			<div class="table-responsive" style="margin-top:40px;margin-left:15px;margin-right:15px;margin-buttom:50px">
				<form id="addForm" action="questionAdd.do" enctype="multipart/form-data" method="post">
					<table class="table table-striped table-bordered table-hover" id="dataTables-example">
						<tr>
							<td style="width:10%">题目类型：</td>
							<td style="width:80%">
								<select name="ques.type.qtId" id="type"  class="form-control" style="float:left;margin-right:10px;width:150px" onchange="addT()">
								<s:iterator value="#request.types">
									<option value="${qtId}"<s:if test="#request.typeChecked==qtId">selected</s:if>>${typeName}</option>
								 </s:iterator>
								 <option value=-1 >新建题型</option>
			 					</select>
			 					<div id="addType" style="float:left;display:none">
					 			    <input style="float:left;margin-right:5px;width:150px" class="form-control input-group-xs" id="newType" name="typeContent" type="text"/>
					 			    <button style="float:left" type="button" class="btn btn-success" onclick="addType()">添加</button>
				 			    </div>
		 					</td>
						</tr>
						<tr>
							<td>知识点</td>
						    <td>
						        <select style="float:left;margin-right:10px;width:150px" name="ques.knowledge.kId" id="kId" class="form-control" onchange="addPoint()">
				        	     	<s:iterator value="#request.kPoints">
										<option value="${kId}" <s:if test="#request.knowledgeChecked==kId">selected</s:if>>${content}</option>
									</s:iterator>
									    <option value=-1 >新建知识点</option>
				 			    </select>
				 			    <div id="addPoint" style="float:left;display:none">
					 			    <input style="float:left;margin-right:5px;width:150px" class="form-control input-group-xs" id="newPoint" name="kpContent" type="text"/>
					 			    <button style="float:left" type="button" class="btn btn-success" onclick="addKp()">添加</button>
				 			    </div>
						    </td>
						</tr>
						<tr>
							<td>难度</td>
							<td id="level">
								<input type="radio" name="ques.level" value="1" />&nbsp;简单
								<input type="radio" name="ques.level" value="2" />&nbsp;&nbsp;较易
								<input type="radio" name="ques.level" value="3" />&nbsp;&nbsp;一般
								<input type="radio" name="ques.level" value="4" />&nbsp;&nbsp;较难
								<input type="radio" name="ques.level" value="5" />&nbsp;&nbsp;难
							</td>
						</tr>
						<tr>
							<td>题目：</td>
							<td>
						<div style="width：100%">
						    <script id="editor"  name="ques.content" type="text/plain" style="width:100%;height:200px;"></script>
						</div>
						</tr>
						<!-- <tr id="select">
							<td>选项:</td>
							<td><textarea name="ques.choice" id="choice" style="width:50%;height:150px;"></textarea>
							</td>
						</tr>-->
						<tr>
							<td>参考答案:</td>
							<td><textarea name="ques.answer" id="answer" style="width:50%;height:150px;"></textarea></td>
						</tr>
						<!--<tr>
							<td>添加附件</td>
							<td><input type="file" name="image" accept="image/*" /></td>
						</tr>   -->
						<tr>
						    <td style="border-right:none;"></td>
							<td style="border-left:none;">
							  <button type="button" class="btn btn-success" onclick="add()">确定添加</button>
							  <a type="button" class="btn btn-info" href="javascript:history.go(-1);">返回</a>
							</td>
						</tr>
						
					</table>
			    </form>
			 </div>
			 <br>
		 </div>
	   </div>
	</div>
	
	<!-- 模态框 -->
		<div class="modal" id="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h3 class="modal-title" style="font-weight:blod;color:red" id="modal-title"></h3>
				</div>
				<div class="modal-body" id="modal-body" style="font-size:20px">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="modal_btn_1">关闭</button>
					<button type="button" class="btn btn-primary" id="modal_btn_2">修改</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  
		
</body>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
var ue = UE.getEditor('editor');
</script>
</html>
