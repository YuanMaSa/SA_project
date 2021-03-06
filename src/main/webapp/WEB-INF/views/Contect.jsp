<%@ page contentType="text/html;charset=UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Strawberry</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <link href="dashboard.css" rel="stylesheet">
    
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	<link href="css/main.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/header_css.css">
</head>

<body>
<b>
	<%@include file="header.jsp"%>
<!-- Page Wrapper -->
	<div id="page-wrapper">
<!-- Banner -->

<!-- Footer -->
		<section id="footer">
			<div class="inner">
				<h2 class="major">Get in touch</h2>
				<p>Cras mattis ante fermentum, malesuada neque vitae, eleifend erat. Phasellus non pulvinar erat. Fusce tincidunt, nisl eget mattis egestas, purus ipsum consequat orci, sit amet lobortis lorem lacus in tellus. Sed ac elementum arcu. Quisque placerat auctor laoreet.</p>
				<form method="post" action="#">
					<div class="field">
						<label for="name">Name</label>
						<input type="text" name="name" id="name" />
					</div>
					<div class="field">
						<label for="email">Email</label>
						<input type="email" name="email" id="email" />
					</div>
					<div class="field">
						<label for="message">Message</label>
						<textarea name="message" id="message" rows="4"></textarea>
					</div>
					<ul class="actions">
						<li><input type="submit" value="Send Message" /></li>
					</ul>
				</form>
				<ul class="contact">
					<li class="fa-home">
						Untitled Inc<br />
						1234 Somewhere Road Suite #2894<br />
						Nashville, TN 00000-0000
					</li>
					<li class="fa-phone">(000) 000-0000</li>
					<li class="fa-envelope"><a href="#">information@untitled.tld</a></li>
					<li class="fa-twitter"><a href="#">twitter.com/untitled-tld</a></li>
					<li class="fa-facebook"><a href="#">facebook.com/untitled-tld</a></li>
					<li class="fa-instagram"><a href="#">instagram.com/untitled-tld</a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; Untitled Inc. All rights reserved.</li>
				</ul>
			</div>
		</section>
	</div>

<!-- Scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</b>
</body>
</html>
