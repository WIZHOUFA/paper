<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap Styles-->
    <link href="css/bootstrap.css" rel="stylesheet" />
<title>首页</title>
		<link rel="stylesheet" href="muban/css/bootstrap.css">
        <link rel="stylesheet" href="muban/css/font-awesome.css">
        <link rel="stylesheet" href="muban/css/animate.css">
        <link rel="stylesheet" href="muban/css/templatemo-misc.css">
        <link rel="stylesheet" href="muban/css/templatemo-style.css">
        <script src="muban/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>
	 <!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="#/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->


        <section id="pageloader">
            <div class="loader-item fa fa-spin colored-border"></div>
        </section> <!-- /#pageloader -->
        
        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>

        <header class="site-header container-fluid">
            <div class="top-header">
                <div class="logo col-md-6 col-sm-6" style="padding:0px;">
                    <img style="margin-left:20px;float:left;margin-left:50px"" width="170px" height="120px" alt="" src="images/fujitsu.jpg">
                    <span style="color:red;font-size:25px;float:left;margin-top:45px">——shaping tomorrow with you</span>
                </div> <!-- /.logo -->
                <div class="social-top col-md-6 col-sm-6">
                    <!-- 右上方模块 -->
                </div> <!-- /.social-top -->
            </div> <!-- /.top-header -->
            <div id="responsive-menu">
            </div>
        </header> <!-- /.site-header -->
        
        <div class="swiper-container">
            <div class="swiper-wrapper">

                <div class="swiper-slide" style="background-image: url(images/fnst.jpg);">
                    <div class="overlay-s"></div>
                    <div class="slider-caption">
                        <div class="inner-content">
                            <h2>FNST &nbsp;自动出卷系统</h2>
                            <p>The &nbsp;&nbsp;system&nbsp;&nbsp; of &nbsp;&nbsp; <b class="blue">Automatic&nbsp;&nbsp; </b><b class="green"> giving&nbsp;&nbsp; paper  &nbsp;&nbsp;</b> for &nbsp;&nbsp;FNST . </p>
                            <a href="/index.jsp" class="main-btn white">进入系统</a>
                            <p style="font-size:12px;margin-top:20px">Copyright from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">Group 7</a> - shaping our tomorrow  </p>
                        </div> <!-- /.inner-content -->
                    </div> <!-- /.slider-caption -->
                </div> <!-- /.swier-slide -->
            </div> <!-- /.swiper-wrapper -->
        </div> <!-- /.swiper-container -->

        <script src="muban/js/vendor/jquery-1.11.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="muban/js/vendor/jquery-1.11.0.min.js"><\/script>')</script>
        <script src="muban/js/plugins.js"></script>
        <script src="muban/js/main.js"></script>
        <!-- Preloader -->
        <script type="text/javascript">
            //<![CDATA[
            $(window).load(function() { // makes sure the whole site is loaded
                $('.loader-item').fadeOut(); // will first fade out the loading animation
                    $('#pageloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
                $('body').delay(350).css({'overflow-y':'visible'});
            })
            //]]>
        </script>
</body>
</html>
