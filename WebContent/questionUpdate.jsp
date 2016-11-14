<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改试题</title>
<!-- Bootstrap Styles-->
    <link href="css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="css/font-awesome.css" rel="stylesheet" />
     <!-- Morris Chart Styles-->
   
        <!-- Custom Styles    -->
    <link href="css/custom-styles.css" rel="stylesheet" />
     <!-- Google Fonts-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link href="js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
    <style>
         body{
    background-color:#fff;
   }
    </style>
<script type="text/javascript">
var ue = UE.getEditor('editor');
function change(){
	parent.scrollTo(0,0);
	 //alert(1);
	 /**
 	if($('#type').val() == '1'){
 		$('#select').show();
 	}else{
 		$('#select').hide();
 	}**/
}
//修改时
function mod(){
	 var msg="";
	 var ue = UE.getEditor('editor');
	 var flag = true;
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
		 $('#queryForm').submit();
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

</head>
<body onload="change()">
<div id="page-inner" style="height: 100%">
  <div style="margin:10px">
	<div class="table-responsive">
			<form id="queryForm" action="questionUpdate.do" enctype="multipart/form-data" method="post">
				<table class="table table-striped table-bordered table-hover" id="dataTables-example">
					<tr>
						<td style="width:10%">题目类型：</td>
						<td style="width:80%">
							<select name="ques.type.qtId" id="type" class="form-control" style="width:150px">
								<option value="${question.type.qtId}" selected>${question.type.typeName}</option>
		 					</select>
	 					</td>
					</tr>
					<tr>
						<td>知识点</td>
					    <td>
					        <select name="ques.knowledge.kId" id="kId" class="form-control" style="width:150px">
									<option value="${question.knowledge.kId}" selected>${question.knowledge.content}</option>
			 			    </select>
					    </td>
					</tr>
					<tr>
						<td>难度</td>
						<td id="level">
						    <input type="radio" name="ques.level" value="1" <s:if test="#request.question.level==1" >checked="checked"</s:if> />&nbsp;简单
							<input type="radio" name="ques.level" value="2" <s:if test="#request.question.level==2" >checked="checked"</s:if>  />&nbsp;较易
							<input type="radio" name="ques.level" value="3" <s:if test="#request.question.level==3" >checked="checked"</s:if> />&nbsp;一般
							<input type="radio" name="ques.level" value="4" <s:if test="#request.question.level==4" >checked="checked"</s:if>  />&nbsp;较难
							<input type="radio" name="ques.level" value="5" <s:if test="#request.question.level==5" >checked="checked"</s:if>  />&nbsp;难
							
						</td>
					</tr>
					<tr>
						<td>题目：</td>
						<td><div style="width：100%">
						    <script id="editor"  name="ques.content"  type="text/plain" style="width:100%;height:200px;" value=${question.content }></script>
						</div>
						</td>
					</tr>
					<!-- <tr id="select">
						<td>选项:</td>
						<td>
						<textarea name="ques.choice" id="choice" style="width:50%;height:150px;">${question.choice }</textarea>
					</tr>-->
					<tr>
						<td>参考答案:</td>
						<td>
						<textarea name="ques.answer" id="answer" style="width:50%;height:150px;">${question.answer }</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center">
						    <button type="button" class="btn btn-success" onclick="mod()" >修改</button>
							<a type="button" class="btn btn-info" href="javascript:history.go(-1);">返回</a>
						</td>
					</tr>
				</table>
			
				<input name="ques.qId" value="${question.qId }" hidden/>
		    </form>
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
function setContent(obj) {
        UE.getEditor('editor').setContent(obj, false);
    }
</script>
</html>
