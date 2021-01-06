<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线拍卖网站首页</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="sale">
			<h1 class="lf">在线拍卖系统</h1>
			<!--	排列在右边  -->
			<div class="logout right">
				<a href="loginOut" title="注销">注销</a>
<!-- <a href="${pageContext.request.contextPath}/loginOut" title="注销">注销</a> -->	
			</div>
		</div>
		<div class="forms">
			<label for="name">名称</label> 
			<input name="" type="text" class="nwinput" id="name" />
			<label for="names">描述</label> 
			<input name="" type="text" id="names" class="nwinput" /> 
			<label for="time">开始时间</label>
			<input name="" type="text" id="time" class="nwinput" /> <label
				for="end-time">结束时间</label> <input name="" type="text" id="end-time"
				class="nwinput" /> <label for="price">起拍价</label> <input name=""
				type="text" id="price" class="nwinput" /> <input name=""
				type="button" value="查询" class="spbg buttombg f14  sale-buttom" />

			<!--  判断用户的身份，如果是管理员就显示 -->
			<c:if test="${sessionScope.user.userisadmin==1}">
		<!--  	    一个发布商品的按钮-->
				<input type="button" value="发布"
				   onclick="location = 'addAuction.jsp'"
					class="spbg buttombg f14  sale-buttom buttomb" />
			</c:if>
		</div>
		<div class="items">
			<ul class="rows even strong">
				<li>名称</li>
				<li class="list-wd">描述</li>
				<li>开始时间</li>
				<li>结束时间</li>
				<li>起拍价</li>
				<li class="borderno">操作</li>
			</ul>
             
      <!-- 遍历输出 -->       
			<c:forEach var="auction" items="${auctionList}" varStatus="state">

				<ul <c:if test="${state.index%2!=0}">class="rows even"</c:if>
					<c:if test="${state.index%2==0}">class="rows"</c:if>>

					<li>${auction.auctionname}</li>
					<li class="list-wd">${auction.auctiondesc}</li>
					<li><fmt:formatDate value="${auction.auctionstarttime}"
							pattern="yyyy-MM-dd"></fmt:formatDate></li>
					<li><fmt:formatDate value="${auction.auctionendtime}"
							pattern="yyyy-MM-dd"></fmt:formatDate></li>
					<li>${auction.auctionstartprice}</li>
					<li class="borderno red">
						<!--  判断用户的身份，如果是管理员就显示 --> 
						<c:if test="${sessionScope.user.userisadmin==1}">
						<!--  onclick="dele();"-->	
							<a href="${pageContext.request.contextPath}/toUpdate/${auction.auctionid}" title="竞拍" >修改</a>|
						<!--  onclick="abc();"   -->	
					        <a href="${pageContext.request.contextPath}/deleteAuctionById/${auction.auctionid}" title="竞拍" >删除</a>
						</c:if>

					</li>
					<li class="borderno red">
						<!--  判断用户的身份，如果是普通用户就显示 --> 
						<c:if test="${sessionScope.user.userisadmin==0}">
				<!--  href="auctionDetail.jsp" auctionDetail.jsp-->	
							<a href="${pageContext.request.contextPath}/toDetail/${auction.auctionid}"  title="竞拍" onclick="location ='auctionDetail.jsp'">竞拍</a>|
					   </c:if>
					</li>
				</ul>

			</c:forEach>

       <!--        分页的设置--> 
			<div class="page">
				【当前第${page.pageNum}页，总共${page.total}条记录，总共${page.pages}页】
<a href="${pageContext.request.contextPath}/queryAll?pageNum=1" title="">首页</a>
<a href="${pageContext.request.contextPath}/queryAll?pageNum=${page.prePage}" title="">上一页</a> 
<a href="${pageContext.request.contextPath}/queryAll?pageNum=${page.nextPage}"title="">下一页</a> 
<a href="${pageContext.request.contextPath}/queryAll?pageNum=${page.pages}"title="">尾页</a>
			</div>
		</div>
		<script>
		<!-- 
   function abc(){
	   
	   if(confirm("你真的确认要删除吗？请确认")){
	    
		   return true;
		 }
		 else{
			 return false;
			 }
			 
	   };
	   function dele(){
		   if(confirm("你真的确认要修改吗？请确认")){
			   return true;
			   }
			   else{
				   return false;
				   }
		   }
	   -->
  </script>
		<!-- main end-->
	</div>
</body>
</html>