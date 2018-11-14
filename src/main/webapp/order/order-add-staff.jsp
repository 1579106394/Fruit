<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加配送员</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <style type="text/css">
        .layui-form-select dl {
            background-color: transparent;
        }
    </style>
</head>
<body>
<!-- 顶部开始 -->
<jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<div class="wrapper">
    <!-- 左侧菜单开始 -->
    <jsp:include page="${pageContext.request.contextPath}/left.jsp"></jsp:include>
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <form class="layui-form layui-form-pane"
                  action="${pageContext.request.contextPath}/api/order/assignStaff.html"
                  method="post">

                <input type="hidden" name="orderId" value="${order.orderId}"/>

                <div class="layui-form-item">
                    <label class="layui-form-label">
                        派送员
                    </label>
                    <div class="layui-input-inline">
                        <select name="courier.staffId">
                            <c:forEach items="${staffList}" var="staff">
                                <option value="${staff.staffId}">${staff.staffName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    </div>

                </div>


                <div class="layui-form-item">
                    </label>
                    <button class="layui-btn" type="submit">
                        分配
                    </button>
                </div>
            </form>
            <!-- 右侧内容框架，更改从这里结束 -->
        </div>
    </div>
    <!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
</div>
<!-- 底部结束 -->
<!-- 背景切换开始 -->
<jsp:include page="${pageContext.request.contextPath}/bg.jsp"></jsp:include>
<!-- 背景切换结束 -->
<!-- 页面动态效果 -->
<script>
    //执行一个laydate实例
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#birthday', //指定元素
            format: 'yyyy年MM月dd日'
        });
    });

</script>
</body>
</html>