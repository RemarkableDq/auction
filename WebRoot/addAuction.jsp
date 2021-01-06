<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加拍卖品</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/WebCalendar.js" type="text/javascript"></script>

<script type="text/javascript">
	var loadImageFile = (function() {
		if (window.FileReader) {
			var oPreviewImg = null, oFReader = new window.FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

			oFReader.onload = function(oFREvent) {
				if (!oPreviewImg) {
					var newPreview = document.getElementById("imagePreview");
					oPreviewImg = new Image();
					oPreviewImg.style.width = (newPreview.offsetWidth)
							.toString()
							+ "px";
					oPreviewImg.style.height = (newPreview.offsetHeight)
							.toString()
							+ "px";
					if(newPreview.childNodes.length != 0){
						newPreview.removeChild(document.getElementById("imgid"));
					}
					newPreview.appendChild(oPreviewImg);
				}
				oPreviewImg.src = oFREvent.target.result;
			};

			return function() {
				var aFiles = document.getElementById("imageInput").files;
				if (aFiles.length === 0) {
					return;
				}
				if (!rFilter.test(aFiles[0].type)) {
					alert("You must select a valid image file!");
					return;
				}
				oFReader.readAsDataURL(aFiles[0]);
			}

		}
		if (navigator.appName === "Microsoft Internet Explorer") {
			return function() {
				alert(document.getElementById("imageInput").value);
				document.getElementById("imagePreview").filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = document
						.getElementById("imageInput").value;

			}
		}
	})();
	
</script>
<style type="text/css">
#imagePreview {
	width: 160px;
	height: 120px;
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale
		);
}
</style>
</head>

<body>
<form action="${pageContext.request.contextPath}/publishAuctions" 
		enctype="multipart/form-data" method="post">
<div class="wrap">
  <!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>

  </div>
      <div class="login logns produce">
        <h1 class="blues">拍卖品信息</h1>
          <dl>
            <dd >
              <label>名称：</label>
              <input type="text" name="auctionname" class="inputh lf" value="" />
              <div id="nameid" class="lf red laba">名称不能为空</div>
            </dd>
            <dd>
              <label>起拍价：</label>
              <input type="text"  name="auctionstartprice" class="inputh lf" value="" />
              <div id="startPriceid" class="lf red laba">必须为数字</div>
            </dd>
            <dd>
              <label>底价：</label>
              <input type="text" name="auctionupset" class="inputh lf"  value="" />
              <div id="upsetid" class="lf red laba">必须为数字</div>
            </dd>
            <dd>
              <label>开始时间：</label>
              <input type="text" name="auctionstarttime" class="inputh lf" readonly onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
              <div id="startTimeid" class="lf red laba">格式：2010-05-05 12:30:00</div>
            </dd>
            <dd>
              <label>结束时间：</label>
              <input type="text" name="auctionendtime" class="inputh lf"  onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
              <div id="endTimeid" class="lf red laba">格式：2010-05-06 16:30:00</div>
            </dd>
            <dd class="dds">
              <label>拍卖品图片：</label>
               <div class="lf salebd">
                <div id="imagePreview" >
                	<img id="imgid" src="images/ad20.jpg" width="100" height="100" />
                </div>
               	<!--  <img id="imgid" src="images/ad20.jpg" width="100" height="100" /> -->
               </div>
              <input id="imageInput" onchange="loadImageFile();" name="pic" type="file" class="offset10 lf" />
             <div id="picid" class="lf red laba">请上传图片</div>
            </dd>
             <dd class="dds">
              <label>描述：</label>
              <textarea name="auctiondesc" cols="" rows="" class="textarea"></textarea>
            </dd>
            <dd class="hegas">
                <input type="submit" value="保 存" class="spbg buttombg buttombgs buttomb f14 lf" />
                <input type="reset" value="取 消" onclick="location='listAuction.action'" class="spbg buttombg buttombgs buttomb f14 lf" />
            </dd>
          </dl>
    </div>
<!-- main end-->
<!-- footer begin-->

</div>
 <!--footer end-->
  </form>
</body>
</html>
