<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

    <script type="text/javascript">
        function list(p) {
            $("#currentPage").val(p);
            $("#staffForm").submit();
        }
    </script>

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

            <form id="staffForm" class="layui-form xbs layui-form-pane"
                  action="${pageContext.request.contextPath}/api/staff/staffList.html" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
                <div class="" style="text-align: center;">
                    <div class="layui-form-item" style="display: inline-block;">
                        <label class="layui-form-label xbs768">员工姓名</label>
                        <div class="layui-input-inline xbs768">
                            <input class="layui-input" name="params[staffName]" value="${page.params.staffName}"
                                   placeholder="员工姓名" id="LAY_demorange_s">
                        </div>
                        <div class="layui-input-inline" style="width:80px">
                            <button class="layui-btn" type="submit"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </div>
                </div>
            </form>

            <xblock>
                <c:if test="${sessionScope.staff.staffRole == 3}">
                    <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除
                    </button>
                    <a class="layui-btn" href="${pageContext.request.contextPath}/staff/staff-add.jsp"><i
                            class="layui-icon">&#xe608;</i>添加
                    </a>
                </c:if>
                <span class="x-right" style="line-height:40px">共有数据：${page.totalCount} 条</span></xblock>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>
                    </th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>账号</th>
                    <th>生日</th>
                    <th>手机号</th>
                    <c:if test="${sessionScope.staff.staffRole == 3}">
                        <th>工资</th>
                    </c:if>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>身份</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <form id="deleteForm" action="${pageContext.request.contextPath}/api/staff/deleteStaff.html"
                      method="post">
                    <c:forEach items="${page.list}" var="staff" varStatus="index">

                        <tr>
                            <td><input type="checkbox" name="ids" value="${staff.staffId}">
                            </td>
                            <td>${index.index+1}</td>
                            <td>${staff.staffName}</td>
                            <td>${staff.staffAccount}</td>
                            <td>${staff.staffBirth}</td>
                            <td>${staff.staffTelephone}</td>
                            <c:if test="${sessionScope.staff.staffRole == 3}">
                                <td>${staff.salary.salaryPrice}</td>
                            </c:if>
                            <td>
                                <c:if test="${staff.staffSex == 1}">男</c:if>
                                <c:if test="${staff.staffSex == 2}">女</c:if>
                            </td>
                            <td>${staff.staffAge}</td>
                            <td>
                                <c:if test="${staff.staffRole == 1}">员工</c:if>
                                <c:if test="${staff.staffRole == 3}">管理员</c:if>
                            </td>

                            <td class="td-manage">
                                <c:if test="${sessionScope.staff.staffRole == 3}">
                                    <a title="编辑" href="javascript:;" onclick="toEditStaff('${staff.staffId}')"
                                       class="ml-5" style="text-decoration:none">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>

                                    <a title="删除" href="javascript:;" onclick="deleteStaff('${staff.staffId}')"
                                       style="text-decoration:none">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>

                                    <a title="管理员切换" href="javascript:;"
                                       onclick="editRole('${staff.staffId}', ${staff.staffRole})"
                                       style="text-decoration:none">
                                        <i class="layui-icon">&#xe608;</i>
                                    </a>

                                    <a title="分配外送地址" href="javascript:;" onclick="addAddress('${staff.staffId}')"
                                       style="text-decoration:none">
                                        <i class="layui-icon">&#xe66c;</i>
                                    </a>
                                </c:if>
                                <a title="查看外送地址" href="javascript:;" onclick="getAddress('${staff.staffId}')"
                                   style="text-decoration:none">
                                    <i class="layui-icon">&#xe615;</i>
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
            <div style="float: right;">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${page.currentPage == 1}">
                            <li>
                                <a class="disabled" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPage != 1}">
                            <li>
                                <a href="javascript:void(0)" onclick="list(${page.currentPage - 1})"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${page.totalPage}" var="p">
                            <c:if test="${p == page.currentPage}">
                                <li class="active">
                                    <a href="javascript:void(0)">${p}</a>
                                </li>
                            </c:if>
                            <c:if test="${p != page.currentPage}">
                                <li>
                                    <a href="javascript:void(0)" onclick="list(${p})">${p}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${page.currentPage == page.totalPage}">
                            <li class="disabled">
                                <a aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPage != page.totalPage}">
                            <li>
                                <a href="javascript:void(0)" onclick="list(${page.currentPage + 1})"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    </span>
                    </ul>
                </nav>
            </div>
        </div>
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

    /*编辑*/
    function toEditStaff(id) {
        layer.confirm('确认要编辑吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/staff/toEdit" + id + ".html"
        });
    }

    /*删除*/
    function deleteStaff(id) {
        layer.confirm('确认要删除吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/staff/deleteStaff" + id + ".html"
        });
    }

    /*批量删除*/
    function delAll() {
        layer.confirm('确认要删除吗？', function (index) {
            $('#deleteForm').submit();
        });
    }

    /*管理员身份切换*/
    function editRole(id, role) {
        if (role == 1) {
            layer.confirm('确认要设为管理员吗？', function (index) {
                window.location.href = "${pageContext.request.contextPath}/api/staff/editRole" + id + ".html"
            });
        } else {
            layer.confirm('确认要取消管理员吗？', function (index) {
                window.location.href = "${pageContext.request.contextPath}/api/staff/editRole" + id + ".html"
            });
        }
    }

    /*分配外送地址*/
    function addAddress(id) {
        layer.confirm('确认要给该员工分配外送地址吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/staff/toAddAddress" + id + ".html"
        });
    }

    /*查看外送地址*/
    function getAddress(id) {
        var staffId = "{\"staffId\": \"" + id + "\"}";
        $.ajax({
            url: "${pageContext.request.contextPath}/api/address/getAddressByStaff.action",
            data: staffId,
            contentType: "application/json;charset=UTF-8",
            type: "post",
            dataType: "json",
            success: function (result) {
                var data = result.data;
                var name = "";
                for (var i = 0; i < data.length; i++) {
                    name += "  " + data[i].addressName;
                }

                layer.alert(name);
            }

        })
    }

</script>

</body>
</html>