(function($){
    //首先备份下jquery的ajax方法
    var _ajax=$.ajax;

    //重写jquery的ajax方法
    $.ajax=function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){}
        }
        if(opt.error){
            fn.error=opt.error;
        }
        if(opt.success){
            fn.success=opt.success;
        }

        //扩展增强处理
        var_opt = $.extend(opt,{
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //错误方法增强处理
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },
            success:function(data, textStatus){
                if(data.status==101){
                    layer.open({
                        content: '登陆己过期，请重新登陆',
                        scrollbar: false,
                        end: function(){
                            window.top.location.href="/login";
                        }
                    });
                    return;
                }
                if(data.status==401){
                    layer.open({
                        content: '此次求未授权',
                        scrollbar: false
                    });
                    return;
                }
                //成功回调方法增强处理
                fn.success(data, textStatus);
            },
            beforeSend:function(XHR){
                //提交前回调方法
               // $('body').append("<div id='ajaxInfo' style=''>正在加载,请稍后...</div>");
            },
            complete:function(XHR, TS){
                //请求完成后回调函数 (请求成功或失败之后均调用)。
               // $("#ajaxInfo").remove();;
            }
        });
        return _ajax(opt);
    };
})(jQuery);
