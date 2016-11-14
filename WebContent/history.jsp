<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.8.3.min.js"></script>
   <script src="/bootstrap/js/bootstrap.min.js"></script>
   <link href="js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" >
    	function show(obj) {
    		var val = $("#"+obj).text();
    		val = val.replace(/\&it/gm,'<');
    		UE.getEditor('editor').execCommand('insertHtml', val);
    		$("#loading").css("display","");
    		$("#showList").css("display","none");
    	}
    	function showA(obj) {
    		var val = obj;
    		val = val.replace(/\&it/gm,'<');
    		UE.getEditor('editor').execCommand('insertHtml', val);
    		$("#loading").css("display","");
    		$("#showList").css("display","none");
    	}
    	function showList() {
    		$("#loading").css("display","none");
    		$("#showList").css("display","");
    	}
    	function myfu(){
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
<body onload="myfu()">
<div id="loading" style="position:absolute;background:#F0F8FF;width:100%;height:100%;top:0px;display:none">
	<div class="modal-header">
					<button type="button" onClick="showList()" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modal-title">试卷预览</h4>
	</div>
	<div style="width：100%">
	    <script id="editor"  type="text/plain" style="width:100%;height:400px;"></script>
	</div>
</div>
<div style="display: none">
	<span id="msg"><s:property value="#request.msg" /></span>
</div>
<div id="showList" style="height: 100%">
		<div id="page-inner" style="height: 100%">
			<div class="row" style="height: 50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						<a href="#">历史考卷</a>
					</h3>
				</div>
			</div>
			<div class="col-md-12" style="border-color: #ddd;border-style: solid;border-width: 1px;">
	    	<br>
			<div style="height:100%">
				<table class="table table-bordered">
					<thead>
						<tr>
						<th>试卷名称</th>
						<th>试卷类型</th>
						<th>年份</th>
						<th>操作</th>
						</tr>
					</thead>
					<s:iterator value="#request.papers">
	    				<tr>
	    				<td>${paperName}</td>
	    				<td>${type}</td>
	    				<td>${createTime}</td>
	    				</td>
	    				<td><a href="javascript:show(${paperId})">预览</a>/<a href="paperDelete.do?paperId=${paperId}">删除</a>
	    				<span id="${paperId}" style="display:none">${content}</span>
	    				</td>
	    				</tr>
					</s:iterator>
				</table>
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
<script type="text/javascript">
var ue = UE.getEditor('editor');
</script>
</body>
</html>
				
