<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <!-- 左侧菜单开始 -->
    <div class="left-nav">
        <div id="side-nav">
            <ul id="nav">
                <li class="list" current>
                    <a href="${pageContext.request.contextPath}/index.html">
                        <i class="iconfont">&#xe761;</i>
                        欢迎页面
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                </li>
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe70b;</i>
                        水果管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/api/fruit/fruitList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                水果列表
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">
                                <i class="iconfont">&#xe6a7;</i>
                                销售记录（没做）
                            </a>
                        </li>

                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        外送管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/api/address/addressList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                外送列表
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        用户管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        <li>
                            <a href="${pageContext.request.contextPath}/api/staff/staffList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                店铺员工
                            </a>
                        </li>

                        <li>
                            <a href="${pageContext.request.contextPath}/api/staff/userList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                普通用户
                            </a>
                        </li>

                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        订单管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        <li>
                            <a href="${pageContext.request.contextPath}/api/cart/cartList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                购物车
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        财务管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">

                        <li>
                            <a href="${pageContext.request.contextPath}/api/history/payList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                支出状况
                            </a>
                        </li>

                        <li>
                            <a href="${pageContext.request.contextPath}/api/history/incomeList.html">
                                <i class="iconfont">&#xe6a7;</i>
                                收入状况
                            </a>
                        </li>

                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        评价管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        <li>
                            <a href="./banner-list.html">
                                <i class="iconfont">&#xe6a7;</i>
                                轮播列表
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧菜单结束 -->