

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Product Information</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet" />
<!--CUSTOM BASIC STYLES-->
<link href="css/basic.css" rel="stylesheet" />
<!--CUSTOM MAIN STYLES-->
<link href="css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link href="css/styleBackground.css" rel="stylesheet" />
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>

<script src="js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<%@include file="headerBackground.jsp"%>
	<script>
	 if(<c:out value="${commentAlready}" />==1){
		 alert("此評論已存在！！");
	 }
	</script>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="color: #252830;">
		<div class="row">
			<div class="col-lg-6">
				<h1 class="page-header">評論查看</h1>
			</div>
			<div class="col-lg-3">
			<form class="navbar-form navbar-right" role="search" action="csearch" method="POST">
				<div class="input-group">
					<input type="text" class="form-control" name="ckeyword" placeholder="Search for..." style="border-radius: 40px;"/>
						 <span class="input-group-btn">
						<button class="btn btn-default" type="submit" style="border-radius: 40px;">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</button>
					</span>
				</div>
				</form>
				<!-- /input-group -->
			</div>
			<!-- /.col-lg-6 -->
			<div class="col-lg-3">
				<div class="input-group">
					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myAddComment">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增評論
					</button>
				</div>
				<!-- /input-group -->
			</div>
			<!-- /.col-lg-6 -->
		</div>
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home"aria-controls="home" role="tab" data-toggle="tab"style="color: #252830;">評論</a></li>
		</ul>
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane fade in active" id="home">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#id</th>
								<th>帳號</th>
								<th>姓名</th>
								<th>產品名稱</th>
								<!-- <th>評論內容</th> -->
								<th>評分</th>
								<th>建立日期</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${CommentList}" var="comment">
								<tr>
									<td><c:out value="${comment.getComment_M_id()}"/></td>
									<td><span class="glyphicon glyphicon-user" aria-hidden="true"></span><c:out value="${comment.getMember().getM_idName()}"/></td>
									<td><c:out value="${comment.getMember().getM_name()}" /></td>
									<td><c:out value="${comment.getProduct().getP_name()}" /></td>
									<%-- <td><c:out value="${comment.getC_comment()}" /></td> --%>
									<td><c:out value="${comment.getScore()}" /></td>
									<td><c:out value="${comment.getC_create_date()}" /></td>
									<form action="deleteComment" method="post">
									<input type="hidden" value="<c:out value="${comment.getComment_M_id()}"/>"name="comment_M_id">
									<input type="hidden" value="<c:out value="${comment.getComment_p_id()}"/>"name="comment_p_id">
										<td><button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>
									</form>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		
	


	<!-- Modal -->

	<div class="modal fade" id="myAddComment" tabindex="-1" role="dialog"aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增評論</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post" action="insertComment">
						<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">評論者姓名</label>
                       <div class="col-sm-8">
                       <select name="comment_M_id" class="form-control">
	                     <c:forEach items="${memberList}" var="member">
	        					<option value="<c:out value="${member.getM_id()}"/>"><c:out value="${member.getM_name()}"/></option>
	        			</c:forEach>
					   </select>
                </div>
						</div>
						<div class="form-group">
				 <label for="inputEmail3" class="col-sm-3 control-label">產品名稱</label>
	                    <div class="col-sm-8">
		                <select name="comment_p_id" class="form-control">
	                     <c:forEach items="${ProductList}" var="Product">
	        					<option value="<c:out value="${Product.getP_id()}"/>"><c:out value="${Product.getP_name()}"/></option>
	        			</c:forEach>
					   </select>
				   </div>
					  	</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">評論內容</label>
							<div class="col-sm-8">
								<input type="text" class="form-control comment-input"id="inputPassword3" name="c_comment" placeholder="評論內容">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">評分</label>
							<div class="col-sm-8">
								<fieldset class="rating">
									<input type="radio" id="star5" name="score" value="5" />
									<label class="full" for="star5" title="Awesome - 5 stars"></label> 
									
									<input type="radio" id="star4" name="score" value="4" />
									<label class="full" for="star4" title="Pretty good - 4 stars"></label>
									
									<input type="radio"id="star3" name="score" value="3" />
									<label class="full"for="star3" title="Meh - 3 stars"></label> 
									
									<input type="radio" id="star2" name="score" value="2" />
									<label class="full" for="star2" title="Kinda bad - 2 stars"></label>
									
									<input type="radio"id="star1" name="score" value="1" />
									<label class="full"for="star1" title="Sucks big time - 1 star"></label> 
									
								</fieldset>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"data-dismiss="modal">Close</button>
							<input type="submit" class="btn btn-primary"name="type" value="insertComment"></input>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	

<!-- Modify -->
<%-- 	<c:forEach items="${CommentList}" var="comment1">
		<div class="modal fade" id="myModifyComment<c:out value="${comment1.getComment_M_id()}"/><c:out value="${comment1.getComment_p_id()}"/>" tabindex="-1"role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"aria-label="Close"><span aria-hidden="true">&times;</span></button>
				    
						<h4 class="modal-title" id="myModalLabel">修改評論</h4>
					</div>
						<form class="form-horizontal" action="updateComment" method="post">
						<input type="hidden" name="<c:out value="${comment1.getMember().getM_id()}"/>"/>
						<input type="hidden" name="<c:out value="${comment1.getP_id()}"/>"/>
					
					<div class="modal-body">
						<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">帳號</label>
						<div class="col-sm-8">
				        <input type="text" class="form-control" id="inputEmail3"name="" placeholder="帳號"value="<c:out value="${comment1.getMember().getM_idName()}"/>">
						</div>
						</div>
						</div>
						<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-8">
						<input type="text" class="form-control" id="inputEmail3"name="" placeholder="姓名"value="<c:out value="${comment1.getMember().getM_name()}"/>">
						</div>
						</div>
						<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">產品名稱</label>
						<div class="col-sm-8">
						<input type="text" class="form-control" id="inputPassword3"name="comment_p_id" placeholder="產品名稱"value="<c:out value="${comment1.getProduct().getP_name()}"/>">
						</div>
						</div>
						<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">評論內容</label>
						<div class="col-sm-8">
						<input type="text" class="form-control comment-input"id="inputPassword3" name="c_comment" placeholder="評論內容"value="<c:out value="${comment1.getC_comment()}"/>">
						</div>
						</div>
						<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">評分</label>
						<div class="col-sm-8">
							       <fieldset class="rating">
										<input type="radio" id="star5" name="score" value=5 />
										<label class="full" for="star5" title="Awesome - 5 stars"></label> 
										<input type="radio" id="star4half" name="score" value="4 and a half" />
										<label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
										<input type="radio" id="star4" name="score" value=4 />
										<label class="full" for="star4" title="Pretty good - 4 stars"></label>
										<input type="radio" id="star3half" name="score"value="3 and a half" />
										<label class="half" for="star3half"title="Meh - 3.5 stars"></label> 
										<input type="radio"id="star3" name="score" value="3" />
										<label class="full"for="star3" title="Meh - 3 stars"></label> 
										<input type="radio" id="star2half" name="score" value="2 and a half" />
										<label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
										<input type="radio" id="star2" name="score" value="2" />
										<label class="full" for="star2" title="Kinda bad - 2 stars"></label>
										<input type="radio" id="star1half" name="score"value="1 and a half" />
										<label class="half" for="star1half"title="Meh - 1.5 stars"></label> 
										<input type="radio"id="star1" name="score" value="1" />
										<label class="full"for="star1" title="Sucks big time - 1 star"></label>
										<input type="radio" id="starhalf" name="score" value="half" />
										<label class="half" for="starhalf"title="Sucks big time - 0.5 stars"></label>
									</fieldset>
								</div>
							</div>
					    
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-primary" name="type" value="modifyComment">
                    </div>
					</form>
					</div>
				</div>
			</div>
		
	</c:forEach> --%>
	<!-- delete -->
<%-- 	<c:forEach items="${CommentList}" var="comment2">
		<form action="deleteComment" method="post">
			<div class="modal fade" id="mydeleteComment<c:out value="${comment2.getComment_M_id()}" /><c:out value="${comment2.getComment_p_id()}" />" tabindex="-1"role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">刪除評論</h4>
						</div>
						<div class="modal-body">
							<table class="table table-striped">
								
								<tbody>
								
									<tr>
										<td><c:out value="${comment2.getComment_M_id()}" /></td>
										<td><span class="glyphicon glyphicon-user"aria-hidden="true"></span><c:out value="${comment2.getMember().getM_idName()}" /></td>
										<td><c:out value="${comment2.getMember().getM_Name()}" /></td>
										<td><c:out value="${comment2.getC_comment()}" /></td>
										<td><c:out value="${comment2.getScore()}" /></td>
										<td><c:out value="${comment2.getC_create_date()}" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"data-dismiss="modal">Close</button>
							<button type="submit" name="type"value="deleteComment" class="btn btn-primary">確認</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:forEach> --%>
	</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="../../assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
