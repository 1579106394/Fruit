<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>

    <style type="text/css">
        .login-box {
            width: 320px;
            height: auto;
            padding: 20px 15px 15px;
            background-color: rgba(0, 0, 0, 0.25);
            border: 1px solid rgba(255, 255, 255, 0.3);
            margin: 0 auto;
        }

        .layui-form-pane .login-form {
            width: 55px;
        }
    </style>

</head>
<body>
<div class="login-logo"><h1>苏果水果店信息管理系统</h1></div>
<div class="login-box">
    <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/login.html" method="post">

        <h3>登录你的帐号</h3>
        <div class="layui-form-item">
            <label class="layui-form-label login-form">账号</label>
            <div class="layui-input-inline login-inline">
                <input type="text" name="staffAccount" lay-verify="required" placeholder="请输入你的帐号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label login-form">密码</label>
            <div class="layui-input-inline login-inline">
                <input type="password" name="staffPassword" lay-verify="required" placeholder="请输入你的密码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <span style="color: red;">${error}</span>
        <div class="form-actions">
            <button class="btn btn-warning pull-right" type="submit">登录</button>
        </div>
    </form>
</div>
</body>
</html>