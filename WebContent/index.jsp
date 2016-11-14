<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>富士通自动出卷系统</title>
    <!-- Bootstrap Styles-->
    <link href="css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="css/custom-styles.css" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <script>
    function backTop(){
    	window.scrollTo(0,0);
    	}
    </script>
</head>
<body onload="backTop()">
	<div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">
                	<i><big>FUJITSU</big></i> 
                	<!--<strong>出卷系统</strong> -->
                </a>
            </div>
            <div  style="float:right;margin-right:10px" >
              <p style="color:#fff;font-size:22px">欢迎进入FNST自动出卷系统</p>
            </div>
        </nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <a id = "tiku" href="questionList.do?searchFlag=all&page=1&rows=10" target="rightIframe">
                            <i class="glyphicon glyphicon-book"></i> 题库管理
                        </a>
                    </li>
                     <li>
                        <a id = "tiku" href="questionAnalysis.jsp?charts=1&statisType=1" target="rightIframe">
                            <i class="glyphicon glyphicon-book"></i> 题库分析
                        </a>
                    </li>
                    <li>
                        <a id = "shij" href="paperAddPage.do" target="rightIframe">
                            <i class="glyphicon glyphicon-list-alt"></i>
                                                                        出卷管理
                        </a>
                    </li>
					<li>
                        <a id = "history" href="paperList.do" target="rightIframe">
                        	<i class="glyphicon glyphicon-signal"></i> 历史分析
                        </a>
                    </li>
                    <li>
                        <a id = "idear" href="our.jsp" target="rightIframe">
                        	<i class="glyphicon glyphicon-envelope"></i> 联系我们
                        </a>
                    </li>
                    <li>
                        <a href="/home.jsp">
                        	<i class="glyphicon glyphicon-log-out"></i> 退出系统
                        </a>
                    </li>
                    
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <!-- /. PAGE INNER  -->
			<div id = "page-inner" style="width:100%;height:100%">
		        <iframe src="questionList.do?page=1&rows=10" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" width="100%" onLoad="iFrameHeight()"id="rightIframe" name="rightIframe"></iframe>
			</div>
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    
    
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="js\jquery-1.10.2.js"></script>
    <!-- Bootstrap Js -->
    <script src="js/bootstrap.min.js"></script>
	 
    <!-- Metis Menu Js -->
    <script src="js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="js/morris/raphael-2.1.0.min.js"></script>
    <script src="js/morris/morris.js"></script>
	
	
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	
	
	
	
    <!-- Custom Js -->
    <script src="js/custom-scripts.js"></script>
    <script>
    function onmover(id){
    	document.getElementById(id).addClass = "active-menu";
    }
    function onmout(id){
    	document.getElementById(id).removeClass = "active-menu";
    }
    function iFrameHeight() {   
    	var ifm= document.getElementById("rightIframe");   
    	var subWeb = document.frames ? document.frames["rightIframe"].document : ifm.contentDocument;   
    	if(ifm != null && subWeb != null) {
    	   ifm.height = subWeb.body.scrollHeight;
    	}   
    	} 
    </script>

</body>
</html>
