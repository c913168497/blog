/**
 * Created by Administrator on 2017-05-09.
 */
var Index = function(){
    var Index = this;
    $("#menumanage").click(function(){
        addTab($("#menumanage").text(),'https://www.baidu.com/')
    })
    tabCloseEven()
}
$(function(){
     new Index();
});

//function addTab(title){
//    if($('#tabs').tabs('exists', title)){
//        $('#tabs').tabs('select', title);
//    } else {
//        var content = '<iframe scrolling="auto" frameborder="0" src="https://www.baidu.com/"  style="width:100%;height:600px;"></iframe>';
//        $('#tabs').tabs('add', {
//                    title:title,
//                    content:content,
//                    closable:true
//        });
//    }
//}
function addTab(subtitle,url,icon){
    if(!$('#tabs').tabs('exists',subtitle)){
        $('#tabs').tabs('add',{
            title:subtitle,
            content:createFrame(url),
            closable:true
        });
    }else{
        $('#tabs').tabs('select',subtitle);
    }
    tabClose();
}

function createFrame(url)
{
    var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    return s;
}
function tabClose()
{
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close',subtitle);
    });
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu',function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle =$(this).children(".tabs-closable").text();

        $('#mm').data("currtab",subtitle);
        $('#tabs').tabs('select',subtitle);
        return false;
    });
}
//绑定右键菜单事件
function tabCloseEven()
{
    //刷新
    $('#mm-tabupdate').click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        if(url != null && url != ''){
            $('#tabs').tabs('update',{
                tab:currTab,
                options:{
                    content:createFrame(url)
                }
            })
        }
    });
    //关闭当前
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close',currtab_title);
    });
    //全部关闭
    $('#mm-tabcloseall').click(function(){
        $('.tabs-inner span').not($('.tabs-inner span:first')).each(function(i,n){
            var t = $(n).text();
            $('#tabs').tabs('close',t);
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function(){
        $('#mm-tabcloseright').click();
        $('#mm-tabcloseleft').click();
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function(){
        var nextall = $('.tabs-selected').nextAll();
        if(nextall.length==0){
            return false;
        }
        nextall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#tabs').tabs('close',t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        if(prevall.length==1){
            return false;
        }
        prevall.not(prevall.last()).each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#tabs').tabs('close',t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function(){
        $('#mm').menu('hide');
    })
}
/**
 * 树结构转化
 * @param sNodes
 * @param setting
 * @returns {Array}
 */
function transformTozTreeFormat(sNodes,setting) {

    if(!setting){
        setting={idKey:'id',parentKey:'pid',childKey:'children'};
    }
    var i,l,
        key = setting.idKey,
        parentKey = setting.parentKey,
        childKey = setting.childKey;
    if (!key || key=="" || !sNodes) return [];

    var r = [];
    var tmpMap = {};
    for (i=0, l=sNodes.length; i<l; i++) {
        tmpMap[sNodes[i][key]] = sNodes[i];
    }
    for (i=0, l=sNodes.length; i<l; i++) {
        if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
            if (!tmpMap[sNodes[i][parentKey]][childKey])
                tmpMap[sNodes[i][parentKey]][childKey] = [];
            tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
        } else {
            r.push(sNodes[i]);
        }
    }
    return r;
}

