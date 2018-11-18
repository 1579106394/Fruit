<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑员工</title>
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
            <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/api/staff/editStaff.html"
                  method="post">
                <input type="hidden" name="staffId" value="${staff.staffId}"/>
                <div class="layui-form-item">
                    <label for="L_staff_account" class="layui-form-label">
                        员工账号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_staff_account" value="${staff.staffAccount}" name="staffAccount" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_staff_password" class="layui-form-label">
                        密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="L_staff_password" value="${staff.staffPassword}" name="staffPassword" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_staff_name" class="layui-form-label">
                        员工姓名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_staff_name" value="${staff.staffName}" name="staffName" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="birthday" class="layui-form-label">
                        生日
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="birthday" value="${staff.staffBirth}" name="staffBirth" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_staff_age" class="layui-form-label">
                        年龄
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_staff_age" value="${staff.staffAge}" name="staffAge" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_staff_telephone" class="layui-form-label">
                        手机号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_staff_telephone" value="${staff.staffTelephone}" name="staffTelephone" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        性别
                    </label>
                    <div class="layui-input-inline">
                        <c:if test="${staff.staffSex == 1}">
                            <input type="radio" name="staffSex" value="1" title="男" checked="">
                            <input type="radio" name="staffSex" value="2" title="女">
                        </c:if>
                        <c:if test="${staff.staffSex == 2}">
                            <input type="radio" name="staffSex" value="1" title="男">
                            <input type="radio" name="staffSex" value="2" title="女" checked="">
                        </c:if>
                    </div>

                </div>

                <div class="layui-form-item">
                    <label for="L_staff_salary" class="layui-form-label">
                        工资
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" value="${staff.salary.salaryPrice}" id="L_staff_salary" name="salary.salaryPrice" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>

                <div class="layui-form-item">
                    </label>
                    <button class="layui-btn" type="submit">
                        修改
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
    <jsp:include page="/footer.jsp"></jsp:include>
</div>
<!-- 底部结束 -->
<!-- 背景切换开始 -->
<jsp:include page="/bg.jsp"></jsp:include>
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