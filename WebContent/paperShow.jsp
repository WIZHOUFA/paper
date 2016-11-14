<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>富士通笔试试卷</title>
<style>
p{padding:0px; margin:0px; white-space:nowrap;display: inline; }
</style>
<script src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	function change(obj) {
		if(obj == 'A') {
			parent.scrollTo(0,0);
			$("#A").css("display","");
			$("#B").css("display","none");
			$("#B_A").css("display","none");
			$("#A_A").css("display","none");
		}
		if(obj == 'B') {
			parent.scrollTo(0,0);
			$("#B").css("display","");
			$("#A").css("display","none");
			$("#B_A").css("display","none");
			$("#A_A").css("display","none");
		}
		if(obj == 'A_A') {
			parent.scrollTo(0,0);
			$("#B").css("display","none");
			$("#A").css("display","none");
			$("#B_A").css("display","none");
			$("#A_A").css("display","");
		}
		if(obj == 'B_A') {
			parent.scrollTo(0,0);
			$("#B").css("display","none");
			$("#A").css("display","none");
			$("#B_A").css("display","");
			$("#A_A").css("display","none");
		}
	}
	function backTop(){
	   parent.scrollTo(0,0);
	   var msg = $('#msg').text();
	   //alert(msg);
	   if(null == msg || typeof(msg) == "undefined" || msg.length == 0){
	   }else{
		   $('#modal-title').html('消息提示框');
			$('#modal-body').html(msg);
			$('#modal_btn_2').hide();
			$('#modal').modal('toggle');
	   }
	}
</script>
</head>
<body onload="backTop()">
<div style="height:100%">
<form action="paperSave.do" method="post">
		<a href="javascript:change('A')">A卷</a>&nbsp;&nbsp;<a href="javascript:change('A_A')">A卷附答案卷</a>&nbsp;&nbsp;
		<a href="javascript:change('B')">B卷</a>&nbsp;&nbsp;<a href="javascript:change('B_A')">B卷附答案卷</a>
		&nbsp;&nbsp;
		<a href="paperReCreate.do">重新生成</a>
		&nbsp;&nbsp;
		<input type="submit" value="保存"></input>
		<div id="A" id="page-inner" style="height:100%">
		   
			<div class="row" style="height:50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						试卷信息设定
					</h3>
				</div>
				<%String html = (String)request.getAttribute("questionAHTML"); 
				String html2 = (String)request.getAttribute("questionBHTML"); 
				String html3 = (String)request.getAttribute("questionAAHTML"); 
				String html4 = (String)request.getAttribute("questionBAHTML"); 
				String msg = (String)request.getAttribute("msg");
				%>
				
				 <script id="editor" name="AContent"  type="text/plain" value=<%=html%>
				 ></script>
			</div>
		</div>
		<div id="A_A" id="page-inner" style="height:100%;display:none">
			<div class="row" style="height:50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						试卷信息设定
					</h3>
				</div>
				<script id="editor3" name="AAContent" type="text/plain" value=<%=html3%>
				 ></script>
			</div>
		</div>
		<div id="B" id="page-inner" style="height:100%;display:none">
			<div class="row" style="height:50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						试卷信息设定
					</h3>
				</div>
				<script id="editor2" name="BContent"  type="text/plain" value=<%=html2%>
				 ></script>
			</div>
		</div>
		<div id="B_A" id="page-inner" style="height:100%;display:none">
			<div class="row" style="height:50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						试卷信息设定
					</h3>
				</div>
				<script id="editor4" name="BAContent"  type="text/plain" value=<%=html4%>
				 ></script>
			</div>
		</div>
		</form>
</div>
<div style="display: none">
		<span id="msg"><%=msg %></span>
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
</body>
<script type="text/javascript">
var ue = UE.getEditor('editor');
var ue = UE.getEditor('editor2');
var ue = UE.getEditor('editor3');
var ue = UE.getEditor('editor4');
</script>
</html>
