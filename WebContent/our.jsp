<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Emil To Us</title>
		<link rel="stylesheet" href="muban/css/bootstrap.css">
        <link rel="stylesheet" href="muban/css/font-awesome.css">
        <link rel="stylesheet" href="muban/css/animate.css">
        <link rel="stylesheet" href="muban/css/templatemo-misc.css">
        <link rel="stylesheet" href="muban/css/templatemo-style.css">
        <script src="muban/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>
        <div class="content-wrapper">
            <div class="inner-container container">
                <div class="row">
                    <div class="section-header col-md-12">
                        <h2>Contact Page</h2>
                        <span>Subtitle Goes Here</span>
                    </div> <!-- /.section-header -->
                </div> <!-- /.row -->
                <div class="contact-form">
                    <div class="box-content col-md-12">
                        <div class="row">
                            <div class="col-md-7">
                                <p>如果对我们的系统有什么意见和建议，麻烦您通过邮件告诉我们，我们会做出相应改善！</p>
                                <h3 class="contact-title">Send Us Email</h3>
                                <div class="contact-form-inner">
                                    <form method="post" action="" name="contactform" id="contactform">
                                        <p>
                                            <label for="name">Your Name:</label>
                                            <input name="name" type="text" id="name">
                                        </p>
                                        <p>
                                            <label for="email">Email Address:</label>
                                            <input name="email" type="text" id="email"> 
                                        </p>
                                        <p>
                                            <label for="phone">Phone Number:</label>
                                            <input name="phone" type="text" id="phone">   
                                        </p>
                                        <p>
                                            <label for="comments">Your message:</label>
                                            <textarea name="comments" id="comments"></textarea>    
                                        </p>
                                        <input type="button" class="mainBtn" value="Send Message" />
                                    </form>
                                </div> <!-- /.contact-form-inner -->
                                <div id="message"></div>
                            </div> <!-- /.col-md-7 -->
                            <div class="col-md-5">
                                <div class="googlemap-wrapper">
                                    <div id="map_canvas" class="map-canvas"></div>
                                </div>
                            </div> <!-- /.col-md-5 -->
                        </div> <!-- /.row -->
                    </div> <!-- /.box-content -->
                </div> <!-- /.contact-form -->
            </div> <!-- /.inner-content -->
        </div> <!-- /.content-wrapper -->
        <script src="muban/js/vendor/jquery-1.11.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="muban/js/vendor/jquery-1.11.0.min.js"><\/script>')</script>
        <script src="muban/js/plugins.js"></script>
        <script src="muban/js/main.js"></script>
        <!-- Preloader -->
</body>
</html>
