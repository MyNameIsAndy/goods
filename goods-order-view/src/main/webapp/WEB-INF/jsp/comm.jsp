<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<meta name="renderer" content="ie-stand">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" /> -->
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/icon.css">
<link rel="stylesheet" href="<%=contextPath%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=contextPath%>/css/easyui2.css">
<link rel="stylesheet" href="<%=contextPath%>/css/icon2.css">
<link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=contextPath%>/css/superBlue.css" id="themeCss">
<link rel="stylesheet" href="<%=contextPath%>/css/myStyle.css">

<script src="<%=contextPath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/sweetalert.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery-easyui-1.4.1/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/WdatePicker.js"></script>
<!-- easyUI拓展动态合并datagrid单元格-->
<script type="text/javascript" src="<%=contextPath%>/js/autoMergeCellsExtend.js"></script>
<script src="<%=contextPath%>/js/super.js"></script>
<script type="text/javascript" charset="UTF-8">
    //双击combobox显示下拉框
    $(function() {
        showPanel();
    })

    function showPanel() {
        $(".combo").click(function(){
            $(this).prev().combobox("showPanel");
        })
    }
    function closePanel() {
        $(".combo").click(function(){
            $(this).prev().combobox("hidePanel");
        })
    }
    //work with jQuery Compact 3.x
    jQuery.prototype.serializeObject = function () {
        var a, o, h, i, e;
        a = this.serializeArray();
        o = {};
        h = o.hasOwnProperty;
        for (i = 0; i < a.length; i++) {
            e = a[i];
            if (!h.call(o, e.name)) {
                o[e.name] = e.value;
            }
        }
        return o;
    };

    //序列化grid
    function GetGridDataCheck(gridname, datafailed) {
        var jsondata = new Object();
        var grid = $("#" + gridname);
        var List = grid.datagrid("getChecked");
        $.each(List, function (i, o) {
            o.InfoGroupName = gridname;
            for (var p in o) {
                jsondata[datafailed + "[" + i + "]." + p] = List[i][p];
            }
        });
        return jsondata;
    }

    //序列化grid
    function GetGridData(gridname, datafailed) {
        var jsondata = new Object();
        var grid = $("#" + gridname);
        var List = grid.datagrid("getRows");
        $.each(List, function (i, o) {
            o.InfoGroupName = gridname;
            for (var p in o) {
                jsondata[datafailed + "[" + i + "]." + p] = List[i][p];
            }
        });
        return jsondata;
    }

    //序列化form
    function GetFormData(formId, dataFailed) {
        var jsonData = new Object();
        var array = $("#" + formId).serializeArray();
        $(array).each(function (i, o) {
            var n = o.name, v = o.value;
            jsonData[dataFailed + "." + n] = v;
        });
        return jsonData;
    }

    //获取多选框中的属性值
    function GetGridAttr(gridname) {
        var ids = [];
        var grid = $("#" + gridname);
        var rows = grid.datagrid("getChecked");
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i].id);
        }
        return {ids: ids.join(',')};
    }


    function readOnly() {
        $('#detailForm .easyui-textbox').textbox('disableValidation');//添加有校验函数的
        $('#detailForm .easyui-textbox').textbox("readonly", true);
        $('#detailForm .easyui-combobox').combobox("readonly", true);closePanel();//主要是查看的时候不能打开下拉
        $('#detailForm .easyui-numberbox').textbox("readonly", true);
    }

    function cancelReadOnly() {
        $('#detailForm .easyui-textbox').textbox("readonly", false);
        $('#detailForm .easyui-combobox').combobox("readonly", false);showPanel();
        $('#detailForm .easyui-numberbox').textbox("readonly", false);
    }

    function getNowDate() {
        var date = new Date();
        var sign1 = "-";
        var sign2 = ":";
        var year = date.getFullYear() // 年
        var month = date.getMonth() + 1; // 月
        var day = date.getDate(); // 日
        var hour = date.getHours(); // 时
        var minutes = date.getMinutes(); // 分
        var seconds = date.getSeconds() //秒
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (day >= 0 && day <= 9) {
            day = "0" + day;
        }
        if (hour >= 0 && hour <= 9) {
            hour = "0" + hour;
        }
        if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
        }
        var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds;
        return currentdate;
    }

    //查询重置
    function cleanFun() {
        $('#searchForm').form('reset');
    }

    //点击enter，用于触发查询按钮
    $(window).keydown(function (e) {
        if (e.which == 13) {
            searchFun();
            if(typeof searchFun1 === "function") {
                searchFun1();
            }
            if(typeof searchFun2 === "function") {
                searchFun2();
            }
            }
    });
    //提交查询
    function searchFun() {
        datagrid.datagrid('load', $('#searchForm').serializeObject());
    }


    $.extend($.fn.validatebox.defaults.rules, {

        /*通用长度无空格*/        lengths: {

            validator: function (value, param) {
                var rules = $.fn.validatebox.defaults.rules;
                if (/\s/g.test(value)) {
                    rules.lengths.message = '不允许输入空格！';
                    return false;
                }
                eval("var re = /^[\\S]{0," + param[0] + "}$/;");
                rules.lengths.message = '请输入正确格式，长度在' + param[0] + '以内！';
                return re.test(value);
            },
            message: ''
        },
        /*验证参数是否存在以及长度无空格*/
        remotes: {
            validator: function (value, param) {
                var rules = $.fn.validatebox.defaults.rules;
                var name = param[2];
                eval("var re = " + param[3] + ";");
                if (/\s/g.test(value)) {
                    rules.remotes.message = '不允许输入空格！';
                    return false;
                }
                if (!re.test(value)) {
                    rules.remotes.message = '请输入正确格式，且长度在' + param[4] + '！';
                    return false;
                }
                var newary = param.slice(0, 2);
                if (!rules.remote.validator(value, newary)) {
                    rules.remotes.message = name + '已存在';
                    return false;
                }
                return true;
            },
            message: ''
        }
    });

    //修改日历框的显示格式
    function formatter(date){
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        month = month < 10 ? '0' + month : month;
        day = day < 10 ? '0' + day : day;
        hour = hour < 10 ? '0' + hour : hour;
        return year  + month  + day;
    }
    function parser(s){
        var t = Date.parse(s);
        if (!isNaN(t)){
            return new Date(t);
        }
    }
//校验@字符
    $(function(){
        $.extend($.fn.validatebox.defaults.rules, {
            specialSymbol: {//校验@符号
                validator: function (value) {
                    if(!RegExp(/@/).test(value))
                    return true;
                },
                message: '不能输入@符号'
            }
        })
    });
</script>
<style type="text/css">
    .datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber {
        text-overflow: ellipsis;
    }
</style>