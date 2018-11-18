<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--背景插件，转载请注明17素材网--%>

<!-- 背景切换开始 -->
<div class="bg-changer">
    <div class="swiper-container changer-list">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/a.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/b.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/c.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/d.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/e.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/f.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/g.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/h.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/i.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/j.jpg" alt=""></div>
            <div class="swiper-slide"><img class="item" src="${pageContext.request.contextPath}/images/k.jpg" alt=""></div>
            <div class="swiper-slide"><span class="reset">初始化</span></div>
        </div>
    </div>
    <div class="bg-out"></div>
    <div id="changer-set"><i class="iconfont">&#xe696;</i></div>
</div>
<!-- 背景切换结束 -->