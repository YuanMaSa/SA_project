<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Order</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/mindy_style.css">
  <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.js"></script>
  <script type="text/javascript" src="js/myscript.js"></script>
  <link rel="stylesheet" type="text/css" href="css/header_css.css">
</head>
<body style="background-color: #434856;color: #181A19;">
    
 <%@include file="header.jsp"%>
 
  <div class="container" style="margin-top: 80px;">
    <div class="row" style="margin-top: 120px;">
    <div class="col-lg-1 col-md-1"></div>
    <div class="col-lg-10 col-md-10" style="border: 1px solid #c8c8c8;border-radius: 5px;box-shadow: 6px 0px 73px -6px rgba(0,0,0,0.75);background-color: #FFFFFF;color: #252830;">
      <h3>訂單編號：<c:out value="${order_id}"></c:out></h3>
      <table class="table table-hover" style="margin-top: 20px;">
      <c:forEach items="${orderDetailList}" var="orderDetail">
      <c:if test="${orderDetail.getOrderDetail_id()==order_id}">
        <tr>
          <td><img style="width:140px;" src="<c:out value="${orderDetail.getProduct().getP_image()}"></c:out>"></td>
          <td><c:out value="${orderDetail.getProduct().getP_name()}"></c:out></td>
          <td><c:out value="${orderDetail.getProduct().getP_price()}"></c:out></td> 
          <td><c:out value="${orderDetail.getP_amount()}"></c:out></td>
          <td><c:out value="${orderDetail.getP_total()}"></c:out></td>
        </tr>
       </c:if>
       </c:forEach>
      </table>
      <hr>
      <c:forEach items="${orderList}" var="order1">
      <c:if test="${order1.getOrder_id()==order_id}">
      <div class="row">
        <div class="col-lg-8 ">
          <h6>欲使用銀行轉帳付款，請致電 0800-020-021。 </h6>
          <h6>預計出貨時間七天，如未收取，請填寫線上客服。</h6>
        </div>
        <div class="col-lg-4 ">
          <h4>總金額 ： NT . <c:out value="${order1.getTotal()}"></c:out></h4>
        </div>
      </div>
      <div class="row">
      <div class="col-lg-4 ">
      </div>
        <div class="col-lg-8 ">
          <h4>收件人姓名 :<c:out value="${order1.getReceiver_name()}"></c:out></h4>
        </div>
      </div>
      <div class="row">
      <div class="col-lg-4 ">
      </div>
        <div class="col-lg-8 ">
          <h4>收件人電話 :  <c:out value="${order1.getReceiver_phone()}"></c:out></h4>
        </div>
        
      </div>
      <div class="row">
      <div class="col-lg-4 ">
      </div>
        <div class="col-lg-8 ">
          <h4>收件人地址 : <c:out value="${order1.getReceiver_address()}"></c:out></h4>
        </div>
      </div>
      </c:if>
      </c:forEach>
      <div class="row">
        <div class="col-lg-12">
          <table class="table table-striped" style="margin-top: 20px;">
        <tr>
          <td><img src="img/question.png">&nbsp&nbsp我要如何查詢配送進度？</td>
        </tr>
        <tr>
          <td>您於登入會員後，至『訂單管理』查詢配送狀況，訂單狀態：配送中-表示已通知廠商出貨；已配送-表示廠商已送交郵局或貨運寄送。</td>
        </tr>
        <tr>
          <td><img src="img/question.png">&nbsp&nbsp我可以指定送貨地點嗎？</td>
        </tr>
        <tr>
          <td>目前僅限台灣本島可正常配送，離島全區恕無法配送。</td>
        </tr>
        <tr>
          <td><img src="img/question.png">&nbsp&nbsp請問離島的範圍有哪些？</td>
        </tr>
        <tr>
          <td>您於登入會員後，至『訂單管理』查詢配送狀況，訂單狀態：配送中-表示已通知廠商出貨；已配送-表示廠商已送交郵局或貨運寄送。</td>
        </tr>
      </table>
        </div>
      </div>
    </div>
    </div>
    <div class="col-lg-1 col-md-1"></div>
    </div>
    <div class="row" style="margin-top: 50px;">
    <div class="col-lg-12 col-md-12" style="border: 1px solid #c8c8c8;border-radius: 5px;box-shadow: 6px 0px 73px -6px rgba(0,0,0,0.75);background-color: #FFFFFF;color: #252830;">
      <h3>&nbsp&nbsp瀏覽其他產品</h3>
      <hr>
      <center>
      <div class="row container">
      <c:forEach items="${HotProductList}" var="hotproduct">
      <div class="col-sm-3 col-md-3">
        <div class="thumbnail">
          <img  src="${hotproduct.getP_image()}" alt="...">
          <div class="caption">
            <h3><c:out value="${hotproduct.getP_name()}"></c:out></h3>
            <center>
           
            <p style="margin-top:30px;"><a href="Product?id=<c:out value="${hotproduct.getP_id()}"></c:out>" class="btn btn-default" role="button">加入購物車</a></p>
            </center>
          </div>
        </div>
      </div>
      </c:forEach>
      
    </div>
    </center>
    <div class="row">
        <center>
          <p style="margin-top: 30px;"><a href="Product/iPhone" class="btn btn-default btn-lg" role="button">More&nbsp></a></p>
        </center>
    </div>
    </div>
    <div class="col-lg-2 col-md-1"></div>
    </div>
</body>
</html>