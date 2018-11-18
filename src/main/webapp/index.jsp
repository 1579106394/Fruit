<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<jsp:include page="/header.jsp"></jsp:include>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<div class="wrapper">
    <!-- 左侧菜单开始 -->
    <jsp:include page="/left.jsp"></jsp:include>
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <blockquote class="layui-elem-quote" style="color: #666666;">
                欢迎 ${sessionScope.staff.staffName} 使用苏果水果店管理系统
            </blockquote>
            <c:if test="${sessionScope.staff.staffRole != 2}">
                <blockquote class="layui-elem-quote" style="color: #666666;">
                    公司总资产： ￥ ${adminPrice}
                </blockquote>
            </c:if>

            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend><a name="default">信息统计</a></legend>
            </fieldset>
            <c:if test="${sessionScope.staff.staffRole != 2}">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>水果</th>
                        <th>员工</th>
                        <th>用户</th>
                        <th>管理员</th>
                        <th>外送地址</th>
                        <th>订单</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>总数</td>

                        <td>${dataMap.fruitCount}</td>
                        <td>${dataMap.staffCount}</td>
                        <td>${dataMap.userCount}</td>
                        <td>${dataMap.adminCount}</td>
                        <td>${dataMap.addressCount}</td>
                        <td>${dataMap.orderCount}</td>
                    </tr>

                    </tbody>
                </table>
            </c:if>
            <!-- 右侧内容框架，更改从这里结束 -->
            <%--引入时钟插件--%>
            <jsp:include page="/clock.jsp"></jsp:include>
        </div>
    </div>
    <!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<!-- 底部开始 -->
<jsp:include page="/footer.jsp"></jsp:include>
<!-- 底部结束 -->
<!-- 背景切换开始 -->
<jsp:include page="/bg.jsp"></jsp:include>
<!-- 背景切换结束 -->
<script>
    window.onload = function () {
        var oderFlag1 = '${orderFlag1}'
        var orderFlag2 = '${orderFlag2}'

        if (oderFlag1 == 1) {
            layer.open({
                content: '您有新的订单需发货，请及时查看',
                cancel: function(index){
                    if (orderFlag2 == 1) {
                        layer.alert('您有新的订单需要送货，请及时送货')
                    }
                    layer.close(index)
                },
                yes: function(index){
                    if (orderFlag2 == 1) {
                        layer.alert('您有新的订单需要送货，请及时送货')
                    }
                    layer.close(index)
                }
            });
        }else {
            if (orderFlag2 == 1) {
                layer.alert('您有新的订单需要送货，请及时送货')
            }
        }
    }
</script>
</body>
</html>