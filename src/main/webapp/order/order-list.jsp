<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
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
            $("#orderForm").submit();
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

            <form id="orderForm" class="layui-form xbs layui-form-pane"
                  action="${pageContext.request.contextPath}/api/staff/staffList.html" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
                <div class="" style="text-align: center;">
                </div>
            </form>

            <xblock>
                <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除
                </button>
                <span class="x-right" style="line-height:40px">共有数据：${page.totalCount} 条</span></xblock>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>
                    </th>
                    <th>编号</th>
                    <th>买家昵称</th>
                    <th>创建时间</th>
                    <th>订单金额</th>
                    <th>派送员</th>
                    <th>收货地址</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <form id="deleteForm" action="${pageContext.request.contextPath}/api/order/deleteOrder.html"
                      method="post">
                    <c:forEach items="${page.list}" var="order" varStatus="index">

                        <tr>
                            <td><input type="checkbox" name="ids" value="${order.orderId}">
                            </td>
                            <td>${index.index+1}</td>
                            <td>${order.staff.staffName}</td>
                            <td>${order.orderCreatedTime}</td>
                            <td>${order.orderPrice}</td>
                            <td>${order.courier.staffName}</td>
                            <td>${order.address.addressName}</td>
                            <td>
                                <c:if test="${order.orderFlag == 1}">
                                    正常
                                </c:if>
                                <c:if test="${order.orderFlag == 3}">
                                    已下单
                                </c:if>
                                <c:if test="${order.orderFlag == 4}">
                                    已发货
                                </c:if>
                                <c:if test="${order.orderFlag == 5}">
                                    已收货
                                </c:if>
                            </td>

                            <td class="td-manage">

                                <a title="删除" href="javascript:;" onclick="deleteOrder('${order.orderId}')"
                                   style="text-decoration:none">
                                    <i class="layui-icon">&#xe640;</i>
                                </a>

                                <c:if test="${sessionScope.staff.staffRole == 2}">
                                    <c:if test="${order.orderFlag == 1}">
                                        <a title="下单" href="javascript:;" onclick="placeOrder('${order.orderId}')"
                                           style="text-decoration:none">
                                            <i class="layui-icon">&#xe659;</i>
                                        </a>
                                    </c:if>
                                    <c:if test="${order.orderFlag == 4}">
                                        <a title="确定收货" href="javascript:;" onclick="collect('${order.orderId}')"
                                           style="text-decoration:none">
                                            <i class="layui-icon">&#xe657;</i>
                                        </a>
                                    </c:if>
                                </c:if>

                                <c:if test="${sessionScope.staff.staffRole == 3}">
                                    <c:if test="${order.courier == null}">
                                        <a title="分配派送员" href="javascript:;" onclick="assignStaff('${order.orderId}')"
                                           style="text-decoration:none">
                                            <i class="layui-icon">&#xe66f;</i>
                                        </a>
                                    </c:if>
                                    <c:if test="${order.orderFlag == 3}">
                                        <a title="发货" href="javascript:;" onclick="deliver('${order.orderId}')"
                                           style="text-decoration:none">
                                            <i class="layui-icon">&#xe609;</i>
                                        </a>
                                    </c:if>
                                </c:if>
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


    /*删除*/
    function deleteOrder(id) {
        layer.confirm('确认要删除吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/order/deleteOrder" + id + ".html"
        });
    }

    /*批量删除*/
    function delAll() {
        layer.confirm('确认要删除吗？', function (index) {
            $('#deleteForm').submit();
        });
    }

    /*下单*/
    function placeOrder(id) {
        layer.confirm('确认要下单吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/order/placeOrder" + id + ".html"
        });
    }

    /*发货*/
    function deliver(id) {
        layer.confirm('确认要发货吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/order/deliver" + id + ".html"
        });
    }

    /*收货*/
    function collect(id) {
        layer.confirm('确认要收货吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/order/collect" + id + ".html"
        });
    }

    /*分配外送员*/
    function assignStaff(id) {
        layer.confirm('确认要分配外送员吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/order/toAssignStaff" + id + ".html"
        });
    }


</script>

</body>
</html>