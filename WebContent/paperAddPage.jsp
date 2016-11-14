<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Home</title>
    <!-- Bootstrap Styles-->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/loading.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
	var selectedList = [];
	var deletedList = [];
	var num;
	var selectNum = 0;
	var beforeNum = 0;
	var score = 0;
	var orginalNum = 0;
	$(document).ready(function(){
		$("#knowledgePointAddButtom").click(function(){
			var selectId = $("#knowledgePointSelect").val();
			selectedList.push(selectId);
			
			$("#knowledgeTable").append("<tr id='"+selectId+"' align='center'><td>"+$("#"+ selectId).attr("name")
					+"</td><td><input type='text'style='width:45px;' onMouseDown='storeNum(this)' onChange='checkQuestionNum(this)' value='0' name='"+selectId+"'></input></td><td>"+
					"<a id='knowledgePointDeleteButtom' name1='"+selectId+"' name2='"
					+$("#"+ selectId).attr("name")+
					"' class='glyphicon glyphicon-minus' onClick='deleteItem(this)' style='color:green;font-size:20px'></a></td></tr>");
			$("#"+ selectId).remove();
		});
	});
	function deleteItem(obj) {
		var deleteId = obj.attributes.name1.nodeValue;
		var deleteName = obj.attributes.name2.nodeValue;
		deletedList.push(deleteId);
		selectNum = selectNum - obj.parentElement.parentElement.cells[1].children[0].value;
		$("#leftNum").text("剩余"+(parseInt(num)-parseInt(selectNum)) +"题可选");
		$("#"+deleteId).remove();
		$("#knowledgePointSelect").append("<option id='"+deleteId+"' value='"+deleteId
				+"' name='"+deleteName+"' selected>"+deleteName+"</option>");
	}
	
	function loadData() {
		if($("#paperName").val() == null || Trim($("#paperName").val()) == "") {
			parent.scrollTo(0,0);
			$("#nameText").text("请输入试卷名称！");
			return false;
		}
		if($("#totalScore").val() == null || Trim($("#totalScore").val()) == '0') {
			parent.scrollTo(0,0);
			$("#scoreText").text("考卷分数请设置大于0");
			return false;
		}
		if(score != $("#totalScore").val()) {
			 parent.scrollTo(0,0);
			 $('#modal-title').html('消息提示框');
			 $('#modal-body').html('题目总分没有达到试卷总分！');
			 $('#modal_btn_2').hide();
			 $('#modal').modal('toggle');
	        return false;
		}
		if (selectNum != num) {
			parent.scrollTo(0,0);
			$('#modal-title').html('消息提示框');
			 $('#modal-body').html('知识点分布总题数目没有达到题型分布总题数目！');
			 $('#modal_btn_2').hide();
			 $('#modal').modal('toggle');
	        return false;
		}
		$("#paperCreateForm").append("<input name='selectedList' type='text' value='"+selectedList+"'/>")
		.append("<input name='deletedList' type='text' value='"+deletedList+"'/>")
		.append("<input name='totalNum' type='text' value='"+num+"'/>");
		document.getElementById("loading").style.display="";
		$("#paperCreateForm").submit();
	}
	
	function changeNum(input){
        
		var chooseNum = $("#chooseNum").val();
		if (chooseNum == "") {
			chooseNum = 0;
			$("#chooseNum").val("0");
		}
		var blankNum = $("#blankNum").val();
		if (blankNum == "") {
			blankNum = 0;
			$("#blankNum").val("0");
		}
		var answerNum = $("#answerNum").val();
		if (answerNum == "") {			
			answerNum = 0;
			$("#answerNum").val("0");
		}
		var chooseScore = $("#chooseScore").val();
		if (chooseScore == "") {			
			chooseScore = 0;
			$("#chooseScore").val("0");
		}
		var blankScore = $("#blankScore").val();
		if (blankScore == "") {			
			blankScore = 0;
			$("#answerScore").val("0");
		}
		var answerScore = $("#answerScore").val();
		if (answerScore == "") {			
			answerScore = 0;
			$("#answerScore").val("0");
		}
		num = parseInt(chooseNum) + parseInt(blankNum) + parseInt(answerNum);
		score = parseInt(chooseNum)*parseInt(chooseScore) + parseInt(blankNum)*parseInt(blankScore)
		+ parseInt(answerNum)*parseInt(answerScore);
		if (score > $("#totalScore").val()) {
			 parent.scrollTo(0,0);
			 $('#modal-title').html('消息提示框');
			 $('#modal-body').html('总分已超出！');
			 $('#modal_btn_2').hide();
			 $('#modal').modal('toggle');
	        document.getElementById(input).value = "0";
	        return false;
		}
		$("#totalNum").text("共" + num + "题," + score + "分");
		$("#leftNum").text("剩余"+ num +"题可选");
	}
	
	function storeNum(obj){
		orginalNum = obj.value;
	}
	
	function checkQuestionNum(obj){
		var snum = obj.value;
		beforeNum = selectNum;
		selectNum = parseInt(selectNum) + parseInt(snum) - parseInt(orginalNum);
		var leftNum = parseInt(num) - parseInt(selectNum);
		if (leftNum < 0) {
			 parent.scrollTo(0,0);
			 $('#modal-title').html('消息提示框');
			 $('#modal-body').html('选题已超出总题目数！');
			 $('#modal_btn_2').hide();
			 $('#modal').modal('toggle');
	        obj.value=orginalNum;
	        selectNum = beforeNum;
	        $("#leftNum").text("剩余"+(parseInt(num)-parseInt(beforeNum)) +"题可选");
	        return false;
		}
		$("#leftNum").text("剩余"+ leftNum +"题可选");
		
	}
	
	function clickNum(){
		var totalScore = $("#totalScore").val();
		if(totalScore == 0 || totalScore == null || Trim(totalScore) == "") {
			parent.scrollTo(0,0);
			$('#modal-title').html('消息提示框')
			$('#modal-body').html('请先填写试卷分数！');
			$('#modal_btn_2').hide();
			$('#modal').modal('toggle');
		}
	}
	function Trim(str)
    { 
        return str.replace(/(^\s*)|(\s*$)/g, ""); 
	}
	
	function checkRate(input)
	{
	    var re = /^[0-9]*$/;     
	    var number = document.getElementById(input).value;
	    if(input == 'totalScore' && (number == null ||Trim(number)=='0' || Trim(number) == "")) {
	    	document.getElementById(input).value = "0";
	    	$("#disTr1").css("display","none");
		    $("#disTr2").css("display","none");
	    	return false;
	    }
	     if (!re.test(number))
	    {    
	    	 parent.scrollTo(0,0);
	    	 $('#modal-title').html('消息提示框');
			 $('#modal-body').html('请输入数字！');
			 $('#modal_btn_2').hide();
			 $('#modal').modal('toggle');
	        document.getElementById(input).value = "0";
	        return false;
	     }
	     $("#scoreText").text("");
	     $("#disTr1").css("display","");
	     $("#disTr2").css("display","");
	}
	function backTop(){
		parent.scrollTo(0,0);
	}
</script>
</head>
<body onload="backTop()">
<% 
	SimpleDateFormat formart = new SimpleDateFormat("yyyy/MM/dd");
%>
   <div style="margin:20px">
	   <div id="page-inner" style="height:100%">
			<div class="row" style="height:50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle' style="color:#337ab7">
						试卷信息设定
					</h3>
				</div>
		</div>
	    <div class="col-md-12" style="border-color: #ddd;border-style: solid;border-width: 1px;">
	    <br>
			<div style="height:100%">
			<div>
				<p style="font-size:16px;font-weight:bold;">请认真填写试卷相关要求</p>
			</div>
			 <form id="paperCreateForm" action="paperCreate.do" method="post">
				<table class="table table-bordered" class="col-md-10">
			  
			   <tbody>
			   
			      <tr>
			         <td class="col-md-2"> <label for="paperName">试卷名称</label></td>
			         <td class="col-md-8"><input type="text" class="form-control" id="paperName" name="paperName" 
			         placeholder="请输入试卷名称" style="width:60%" ><span id="nameText" style="color:red"></span></td>
			         
			      </tr>
			      <tr>
			         <td><label for="createTime">创建时间</label></td>
			         <td><input placeholder="创建时间" style="width:40%" value="<%=formart.format(new Date())%>" class="form-control" type="text" id="createDate" name="createDate" onmousedown=
			        	 "$(this).datetimepicker({format:'yyyy/mm/dd',todayBtn:1,autoclose: 1,todayHighlight: 1,startView: 2,minView: 2,forceParse: 0});" /></td>
			         
			      </tr>
			      <tr>
			         <td><label for="totalScore">总分</label></td>
			         <td><input type="text" class="form-control" onChange="checkRate(this.id)" id="totalScore" name="totalScore" 
			         placeholder="请输入试卷总分" value="0" style="width:20%"><span id="scoreText" style="color:red"></span></td>
			         
			      </tr>
			      
			      <tr>
			         <td><label for="paperLevel">难易度</label></td>
			         <td> <input type="radio" name="paperLevel" id="level" 
			         value="1" checked> 容易   &nbsp;&nbsp;&nbsp;
			         <input type="radio" name="paperLevel" id="level" 
			         value="2" > 中等 &nbsp;&nbsp;&nbsp;
			         <input type="radio" name="paperLevel" id="level" 
			         value="3" > 困难    
			      </tr>
			      
			      <tr id="disTr1" style="display:none">
			         <td><label for="questionDis">题目分布</label></td>
			         <td>
			         &nbsp; &nbsp;<span id="totalNum" style="color:#FF0000">共0题，0分</span>
			         <br><br>
			         <div class="form-group">
					      <div class="col-sm-10">
					              选择题：<input type="text" style="width:45px;" onClick="clickNum()" onchange="changeNum(this.id)" onKeyup="checkRate(this.id)" id="chooseNum" name="chooseNum" value="0" > 题×
					      <input type="text" style="width:45px;" onClick="clickNum()" onchange="changeNum(this.id)" onKeyup="checkRate(this.id)" id="chooseScore" id="chooseScore" name="chooseScore"  value="0"> 分
					      </div>
					 </div>
					 <br>
					 <div class="form-group">
					      
					      <div class="col-sm-10">
					              填空题：<input type="text" style="width:45px;" onClick="clickNum()" onchange="changeNum(this.id)" onKeyup="checkRate(this.id)" id="blankNum" id="blankNum"  name="blankNum" value="0" > 题×
					      <input type="text" style="width:45px;" onClick="clickNum()" onchange="changeNum(this.id)" onKeyup="checkRate(this.id)" id="blankScore" id="blankScore" name="blankScore"  value="0"> 分
					      </div>
					 </div>
					 <br>
					 <div class="form-group">
					      
					      <div class="col-sm-10">
					              问答题：<input type="text" style="width:45px;" onClick="clickNum()" onchange="changeNum(this.id)" onKeyup="checkRate(this.id)" id="answerNum" id="answerNum" name="answerNum" value="0" > 题×
					      <input type="text" style="width:45px;"  onClick="clickNum()" onchange="changeNum(this.id)" onKeyup="checkRate(this.id)" id="answerScore" id="answerScore" name="answerScore"  value="0"> 分
					      </div>
					 </div>
			         </td>
			         
			      </tr>
			      
			       <tr id="disTr2" style="display:none">
			         <td><label for="totalScore">知识点分布</label></td>
			         <td>
			          <div class="form-group">
			            <div style="float:left">
				            <label for="knowledgePointAddButtom" class="control-label" style="width:20px;height:20px">
				            <a id="knowledgePointAddButtom" class="glyphicon glyphicon-plus" style="color:green;font-size:20px">
				            </a> </label>
			            </div>
			          	<div style="float:left" class="col-sm-8">
				          	<select class="form-control" id="knowledgePointSelect" name="knowledgePointSelect" style="width:30%">
					           <s:iterator value="#request.knowledges">
					             <option id="${kId}" value="${kId}" name="${content}" selected>${content}</option>
					           </s:iterator>
				           </select>  
			          	</div><div style="float:left">
				            <span style="color:#FF0000" id="leftNum"></span>
			            </div>
			          	<div>
			          	<br>
				          <table align="left" class="table table-bordered" id="knowledgeTable" style="width:40%;margin-top:10px;margin-left:36px">
					          <tr align="center">
					             <td>知识点</td>
					             <td>题目数</td>
					             <td>操作</td> 
					           </tr>
				          </table> 
			          </div> 
			          </div>
			          <br>
			         </td>
			      </tr>
			      
			      <tr>
			         <td><label for="contactRatio">与上一年重合度不高于</label></td>
			         <td><select class="form-control" id="matchDegree" name="matchDegree" style="width:20%">
			         		<option value="10">10%</option>
			         		<option value="20">20%</option>
			         		<option value="30">30%</option>
			         		<option value="40">40%</option>
			         		<option value="50">50%</option>
			             </select>
			         </td>
			         
			      </tr>
			      
			      <tr align="center"> <td colspan="2"><input type="button" onClick="loadData()" value="生成考卷" class="btn btn-primary btn-lg "/></td></tr>
			   </tbody>
			</table>
			</form>
			</div>
			</div>
		</div>
	</div>
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
<div id="loading" style="position:absolute;background:#F0F8FF;width:100%;height:100%;top:0px;opacity:0.7;display:none">
<div class="spinner" style="position:absolute;right:50%;top:10%;">
  <div class="rect1"></div>
  <div class="rect2"></div>
  <div class="rect3"></div>
  <div class="rect4"></div>
  <div class="rect5"></div>
</div>
</div>
</body>
</html>
