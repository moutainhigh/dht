<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>会员中心</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="bge6">
    <div class="box2">
        <div class="user-header">
            <a href="/user/userDetailInfo" class="icon-change">
                <div class="user-img">
                    <img src="/img/user-header.png" alt=""/>
                </div>
            </a>
            <p class="user-name">56dfsdf</p>
            <div class="vip">
                <i class="icon-vip"></i>
                <span>普通会员</span>
            </div>
            <i class="news">
                <span></span>
            </i>
        </div>

        <div class="user-order">
            <div class="title">
                <i class="icon-order"></i>
                <span>我的订单</span>
            </div>
            <a href="">全部订单></a>
        </div>

        <div class="user-order-state">
            <div class="state">
                <a href="">
                    <i class="icon-state1">
                        <span>1</span>
                    </i>
                    <p>未付款</p>
                </a>
            </div>
            <div class="state">
                <a href="">
                    <i class="icon-state2">
                        <span>1</span>
                    </i>
                    <p>待处理</p>
                </a>
            </div>
            <div class="state">
                <a href="">
                    <i class="icon-state3">
                        <span>1</span>
                    </i>
                    <p>可评价</p>
                </a>
            </div>
        </div>

        <div class="user-order">
            <div class="title">
                <i class="icon-care"></i>
                <span>购物车</span>
            </div>
            <a href="">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list2"></i>
                <span>足迹</span>
            </div>
            <a href="">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list3"></i>
                <span>余额</span>
            </div>
            <a href="/user/userWallet">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list4"></i>
                <span>会员卡</span>
            </div>
            <a href="/user/userMember">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list5"></i>
                <span>优惠券</span>
            </div>
            <a href="/user/userCoupon">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list6"></i>
                <span>返现</span>
            </div>
            <a href="">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list7"></i>
                <span>售后服务</span>
            </div>
            <a href="">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>



    </div>
    <!-- 底部 -->
    <div class="placeholder-footer"></div>
    <div id="footer"></div>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/footer.js"></script>
    <script>
        var curFooter="#footer_user";
    </script>
</body>

</html>