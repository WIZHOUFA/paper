<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Bootstrap Styles-->
<link href="css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<!-- Custom Styles-->
<link href="css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="ueditor/themes/default/css/ueditor.css">
<!-- TABLE STYLES-->
<link href="js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<title>试题管理</title>
<style>
body {
	background-color: #fff;
}

.info-top {
	font-size: 14px;
	font-weight: bold;
	margin-top: 10px;
	margin-left: 10px;
	display: block;
	overflow: hidden;
	overflow-x: hidden;
	overflow-y: hidden;
	margin：0px;
}

.info {
	border-color: #ddd;
	border-style: solid;
	border-width: 1px;
}

.info_question {
	margin-left: 10px;
	margin-top: 10px;
	margin-bottom: 10px
}

.info_answer {
	margin-bottom: 10px;
	margin-left: 10px;
	margin-top: 10px
}

a:link {
	text-decoration: none;
}

strong {
	color: #156479;
}
</style>
<script type="text/javascript">
   function myfu(){
	   parent.scrollTo(0,0);
	   var msg = $('#msg').text();
	   //alert(msg);
	   if(null == msg || typeof(msg) == "undefined" || msg.length == 0){
	   }else{
		   $('#modal-title').html('消息提示框')
			$('#modal-body').html(msg);
			$('#modal_btn_2').hide();
			$('#modal').modal('toggle');
	   }
   }
   function query(){
	   if($('#sel-type').val() == -1 && $('#sel-kPoints').val() == -1 &&  $('#sel-level').val() == -1){
		   if($('#sel-content').val() == null || $('#sel-content').val().length == 0){
			   window.location.replace("questionList.do?rows=10&searchFlag=all");
			   //var msg = "现已查询出全部题目，无需再次检索！";
			   // $('#modal-title').html('消息提示框')
				//$('#modal-body').html(msg);
				//$('#modal_btn_2').hide();
				//$('#modal').modal('toggle');
		   }else{
			   $('#queryForm').submit();
		   }
	   }else{
		   $('#queryForm').submit();
	   }
   }
</script>
</head>
<body onload="myfu()">
	<div style="height: 100%">
		<div id="page-inner" style="height: 100%">
			<div class="row" style="height: 50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						<a href="questionList.do">题库管理</a>
					</h3>
				</div>
			</div>
			<!-- /. ROW  -->
			<div class="row">
				<div class="col-md-12">
					<!-- Advanced Tables -->
					<div class="panel panel-default">
						<div style="margin-top:10px;margin-bottom:10px;margin-left:15px">
					    <form id="queryForm" action="questionList.do?searchFlag=condition" enctype="multipart/form-data" method="post">
								<div>
								   <div style="float:left;margin-right:5px">
									    <select id="sel-type"  name="searchType" class="form-control" style="width:150px" >
									    	<option value=-1 <s:if test="#request.searchT==-1">selected</s:if>>全部题目类型</option>
									    	<s:iterator value="#request.types">
											<option value="${qtId}" <s:if test="#request.searchT==qtId">selected</s:if>>${typeName}</option>
										 </s:iterator>
									    </select>
									</div>
									<div style="float:left;margin-right:5px" >
										<select  id="sel-kPoints" name="searchKnowledge" class="form-control" style="width:150px">
										    <option value=-1 <s:if test="#request.searchK==-1">selected</s:if> >全部知识点</option>
											<s:iterator value="#request.kPoints">
													<option value="${kId}"<s:if test="#request.searchK==kId">selected</s:if>>${content}</option>
										    </s:iterator>
									    </select>
									</div>
									<div style="float:left;margin-right:5px">
										<select id="sel-level" name="searchLevel" class="form-control" style="width:150px">
										    <option value=-1 <s:if test="#request.searchL==-1">selected</s:if>>全部题目难度</option>
											<option value="1"<s:if test="#request.searchL==1">selected</s:if>>简单</option>
											<option value="2"<s:if test="#request.searchL==2">selected</s:if>>较易</option>
											<option value="3"<s:if test="#request.searchL==3">selected</s:if>>一般</option>
											<option value="4"<s:if test="#request.searchL==4">selected</s:if>>较难</option>
											<option value="5"<s:if test="#request.searchL==5">selected</s:if>>难</option>
									    </select>
									</div>
									<div style="float:left;margin-right:5px">
									    <input id="sel-content"  class="form-control" style="width:150px" name="searchContent"
									     placeholder="请输入题目内容"/>
									</div>
								</div>
								<div id="sel-btn" style="float:left">
								<button type="button" class="btn btn-success" onclick="query()">查询</button>
								</div>
							</form>
					<br>
				</div>
						<div class="panel-heading" style="height: 40px">
							<div style="float: left;">
								<span>每页显示 </span>
								 <select name="rows" id="selectrows">
										<option value="1"<s:if test="rows==1">selected</s:if>>1</option>
										<option value="5" <s:if test="rows==5">selected</s:if>>5</option>
										<option value="10"<s:if test="rows==10">selected</s:if>>10</option>
										<option value="20" <s:if test="rows==20">selected</s:if>>20</option>
										<option value="30" <s:if test="rows==30">selected</s:if>>30</option>
								</select> <span>条,共${totalQues}道题</span>
								<input id="hiddenSF" type="hidden" value="${searchFlag}"/>
								<input id="hiddenSC" type="hidden" value="${searchC}"/>
								<input id="hiddenST" type="hidden" value="${searchT}"/>
								<input id="hiddenSK" type="hidden" value="${searchK}"/>
								<input id="hiddenSL" type="hidden" value="${searchL}"/>
							</div>
							<div style="float: right;">
								<a href="questiontoAdd.do" id="addQuestion">添加考题&nbsp;&nbsp;</a>
							</div>
						</div>
						<div class="panel-body">
							<s:iterator value="#request.questions">
								<div class="info">
									<div
										style="border-color: #ddd; border-style: solid; border-width: 1px; background-color: #ddd">
										<div class="info-top" style="margin-bottom:5px">
											&nbsp;<strong>试题类型：</strong>${type.typeName}&nbsp;&nbsp;&nbsp;&nbsp;
											<strong>试题难度：</strong>
											<s:if test="level==1">简单</s:if>
											<s:if test="level==2">较易</s:if>
											<s:if test="level==3">一般</s:if>
											<s:if test="level==4">较难</s:if>
											<s:if test="level==5">难</s:if>
											&nbsp;&nbsp;&nbsp;&nbsp; <strong>更新时间：</strong>${createTime }
											<a class="glyphicon glyphicon-remove"
												style="color: red; font-size: 20px; float: right; margin-right: 20px"
												onclick="delet(${qId})"></a> <a
												class="glyphicon glyphicon-pencil"
												style="color: green; font-size: 20px; float: right; margin-right: 20px"
												onclick="mod(${qId})"></a>
										</div>
									</div>
									<div
										style="border-color: #ddd; border-style: solid; border-width: 1px;">
										<div
											style="font-size: 14px; font-weight: bold; margin-left: 10px; margin-top: 8px; margin-bottom: 5px">
											<strong style="text-decoration: none;"> 【知识点】</strong>${knowledge.content }
										</div>
									</div>
									<div
										style="border-color: #ddd; border-style: solid; border-width: 1px;">
										<div class="info_question">
											<strong>【题&nbsp;&nbsp;&nbsp;&nbsp;目】</strong>&nbsp;&nbsp;${content }
											<!--<s:if test="level==1">
												<br>
												<div style="margin-left: 55px">
													<span id="select">&nbsp;&nbsp;${choice }</span>
												</div>
											</s:if>-->
										</div>
									</div>
									<div
										style="border-color: #ddd; border-style: solid; border-width: 1px;">
										<div class="info_answer">
											<strong style="text-decoration: none;">【答&nbsp;&nbsp;&nbsp;&nbsp;案】</strong> <br>
											<div style="margin-left: 55px">&nbsp;&nbsp;${answer}</div>
										</div>
									</div>
								</div>
								<br>
							</s:iterator>
							<!-- 分页  -->
							<div id="foot" style="float: left">
								<span>当前页： ${page}</span>
							</div>
							<div id="foot" style="float: right">
								<ul class="pager">
									<s:if test="page==1">
									</s:if>
									<s:else>
										<li><a href="questionList.do?rows=${rows}&page=${page-1}&searchFlag=${searchFlag}&searchContent=${searchC}&searchType=${searchT}&searchKnowledge=${searchK}&searchLevel=${searchL}">Previous</a></li>
									</s:else>
									<s:if test="page==#request.totalPage">
									</s:if>
									<s:else>
										<li><a href="questionList.do?rows=${rows}&page=${page+1}&searchFlag=${searchFlag}&searchContent=${searchC}&searchType=${searchT}&searchKnowledge=${searchK}&searchLevel=${searchL}">Next</a></li>
									</s:else>
								</ul>
							</div>

						</div>
					</div>
					<!--End Advanced Tables -->
				</div>
			</div>
			<!-- /. PAGE INNER  -->
		</div>

	</div>
	<div style="display: none">
		<span id="msg"><s:property value="#request.msg" /></span>
	</div>

	<!-- 模态框 -->
	<div class="modal" id="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modal-title"></h4>
				</div>
				<div class="modal-body" id="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="modal_btn_1">关闭</button>
					<button type="button" class="btn btn-primary" id="modal_btn_2">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 
	<script type="text/javascript">
		var ue = UE.getEditor("ueditor");
	</script>
	    <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
	 -->
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/questionList.js"></script>
</body>
</html>
