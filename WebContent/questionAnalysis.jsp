<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>questionAnalysis</title>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/echarts.js"></script>
<script>
   
</script>
<style>
body {
	background-color: #fff;
}
</style>
</head>
<body>
<% String statisType = request.getParameter("statisType");
   String charts = request.getParameter("charts");
%>
	<div style="height: 100%">
		<div id="page-inner" style="height: 100%">
			<div class="row" style="height: 50px">
				<div class="col-md-12">
					<h3 class="" id='controlTitle'>
						<a href="#">题库分析</a>
					</h3>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-12">
			    	<div class="panel panel-default">
						<div style="margin-top:10px;margin-bottom:10px;margin-left:15px">
						    <form>
						    	<div>
										<select id="statisType"  name="statisType" class="form-control" style="margin-right:5px;width:150px">
										    <option value="1" <% if (statisType.equals("1")) {%>selected<%} %>>题型</option>
											<option value="2"<% if (statisType.equals("2")) {%>selected<%} %>>知识点</option>
											<option value="3" <% if (statisType.equals("3")) {%>selected<%} %>>难度</option>
									    </select>
									    <br>
										<select id="charts" class="form-control" name="charts"  style="margin-right:15px;width:150px">
											<option value="1" <% if (charts.equals("1")) {%>selected<%} %>>柱状图</option>
											<option value="2" <% if (charts.equals("2")) {%>selected<%} %>>折线图</option>
										</select>
										<br>
									<div id="sel-btn" style="float:left;margin-right:15px">
										<button class="btn btn-success" onclick="createChart()">生成图表</button>
									</div>
								</div>
							 </form>
				         </div>
				          
						  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			              <div id="main" style="width: 600px;height:400px;"></div>
			         </div>
			    </div>
			</div>
		</div>
	</div>
	<script>
	
	 </script>
	 
	 <script>
	 var myChart = echarts.init(document.getElementById('main'));  
	 function createChart(){
		   var option;
		   $.ajax({
			   type:"GET",
			   url:"questionAnalysis.do",
			   async: false,
			   data:{
				   statisType:$("#statisType").val(),
		           charts:$("#charts").val()
			   },
			   success:function(result){
				    if(result) {
					   var data = result.data.data;
					   
					   option = {
					            title: {
					                text: data.title
					            },
					            tooltip: {},
					            legend: {
					                data:data.legend
					            },
					            xAxis: {
					                data: data.xAxis,
					                axisLabel:{  
					                    interval: 0  
					                }  
					            },
					            yAxis: {},
					            series: [{
					                name: data.chartSeries.name,
					                type: data.chartSeries.type,
					                data: data.chartSeries.data
					            }]
					        };
				    }
			   }
		   });
		   return option;
	   }
	 var option = createChart();
	 myChart.setOption(option);
</script>
</body>
</html>
