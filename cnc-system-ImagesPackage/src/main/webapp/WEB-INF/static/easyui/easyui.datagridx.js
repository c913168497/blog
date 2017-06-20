/**
 * 给DataGrid和TreeGrid表头添加右键 显示/隐藏 列 的功能
 *
 * 注意：如果有些字段本身需要隐藏，同时又不想让客户通过该功能来 显示出来，请将不要设置该列的title(推荐)，或者设置成以'hidden_' 开头
 *
 * 例如：
 *
 *      {field:'id',hidden:true,sortable:false},    //没有设置title
 *      {field:'id',hidden:true,title:'hidden_ID',sortable:false},    //设置title以'hidden_'开头
 *
 */
$(function(){
    $.extend($.fn.datagrid.methods,{
        columnMoving:function(jq){
            return jq.each(function(){
                var grid = this;
                var directionDiv = $("<div></div>");
                directionDiv.hide();
                $("body").append(directionDiv);
                var fields =  $(grid).datagrid("getPanel").find(".datagrid-header td[field]:not(td[field='ck'])");
                fields.draggable({
                    revert:true,
                    cursor:"move",
                    deltaX:10,
                    deltaY:10,
                    edge:10,
                    proxy:function(source){
                        var proxyEl = $("<div></div>");
                        proxyEl.addClass("dg-proxy dg-proxy-error");
                        proxyEl.text($(source).text());
                        proxyEl.appendTo($("body"));
                        return proxyEl;
                    }
                }).droppable({
                    accept:".datagrid-header td[field]",
                    onDragOver:function(e,source){
                        $(source).draggable("proxy").removeClass("dg-proxy-error").addClass("dg-proxy-right");
                        $(".dg-hide-div").hide();
                        var thisIndex = $(this).index();
                        var sourceIndex = $(source).index();
                        var className = null;
                        var height = null;
                        var thisOffset = null;
                        var left = null;
                        var top = null;
                        height = $(this).height();
                        if(sourceIndex > thisIndex){
                            className = "dg-move-prev";
                            thisOffset = $(this).offset();
                            left = thisOffset.left;
                            top = thisOffset.top;
                        }else{
                            className = "dg-move-next";
                            if(thisIndex == $(this).parent().children(":last").index()){
                                thisOffset = $(this).offset();
                                left = thisOffset.left + $(this).width() - directionDiv.width();
                                top = thisOffset.top;
                            }else{
                                thisOffset = $(this).next().offset();
                                left = thisOffset.left - directionDiv.width();
                                top = thisOffset.top;
                            }
                        }
                        directionDiv.removeClass().addClass(className);
                        directionDiv.css({height:height, left:left, top:top});
                        directionDiv.show();
                    },
                    onDragLeave:function(e,source){
                        $(source).draggable("proxy").removeClass("dg-proxy-right").addClass("dg-proxy-error");
                        directionDiv.hide();
                    },
                    onDrop:function(e,source){
                        directionDiv.remove();
                        var thisIndex = $(this).index();
                        var sourceIndex = $(source).index();
                        var sourceCol = new Array();

                        $(source).remove();
                        $.each($(grid).datagrid("getPanel").find(".datagrid-body tr"),function(index,obj){
                            var sourceTd = $(obj).children("td:eq(" + sourceIndex + ")");
                            sourceCol.push(sourceTd);
                            sourceTd.remove();
                        });

                        var prev = sourceIndex > thisIndex;
                        thisIndex = $(this).index();
                        if(prev){
                            $(this).before($(source));
                        }else{
                            $(this).after($(source));
                        }

                        $.each($(grid).datagrid("getPanel").find(".datagrid-body tr"),function(index,obj){
                            var thisTd = $(obj).children("td:eq(" + thisIndex + ")");
                            if(prev){
                                thisTd.before(sourceCol[index]);
                            }else{
                                thisTd.after(sourceCol[index]);
                            }
                        });
                        $(grid).datagrid("columnMoving");
                    }
                });
            });
        }
    });

    var createGridHeaderContextMenu = function(e, field) {
        e.preventDefault();
        var grid = $(this);/* grid本身 */
        var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
        if (!headerContextMenu) {
            var tmenu = $('<div style="width:120px;"></div>').appendTo('body');
            var asc = $('<div iconCls="icon icon-asc" field="asc">升序</div>').appendTo(tmenu);
            var desc = $('<div iconCls="icon  icon-desc" field="desc">降序</div>').appendTo(tmenu);
            var filedHTML = $('<div iconCls="icon  icon-columns"></div>');
            var span = $('<span>显示/隐藏列</span>');
            var spdiv = $('<div></div>');
            var fields = grid.datagrid('getColumnFields');
            for ( var i = 0; i < fields.length; i++) {
                var fildOption = grid.datagrid('getColumnOption', fields[i]);
                //设置显示和隐藏过滤信息(规定不想让客户发现的字段，title以hidden_开头,或者不写title属性)
                if(fildOption.title && !fildOption.title.startsWith('hidden_')){
                    if (!fildOption.hidden) {
                        $('<div iconCls="button icon-checked" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(spdiv);
                    } else {
                        $('<div iconCls="button icon-unchecked" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(spdiv);
                    }
                }
            }
            span.appendTo(filedHTML);
            spdiv.appendTo(filedHTML);
            filedHTML.appendTo(tmenu);
            headerContextMenu = this.headerContextMenu = tmenu.menu({
                onClick : function(item) {
                    e.preventDefault();
                    var f = $(this).attr('field')
                    var fieldProperty = $(item.target).attr('field');
                    var iconChecked = new RegExp("icon-checked");
                    if (iconChecked.test(item.iconCls)) {
                        grid.datagrid('hideColumn', fieldProperty);
                        $(this).menu('setIcon', {
                            target : item.target,
                            iconCls : 'button icon-unchecked'
                        });
                        return;
                    }
                    var iconUnChecked = new RegExp("icon-unchecked");
                    if (iconUnChecked.test(item.iconCls)) {
                        grid.datagrid('showColumn', fieldProperty);
                        $(this).menu('setIcon', {
                            target : item.target,
                            iconCls : 'button icon-checked'
                        });
                        return;
                    }
                    var iconAsc = new RegExp("icon-asc");
                    if (iconAsc.test(item.iconCls)) {
                        var filedOptions = grid.datagrid('getColumnOption',f);
                        if(filedOptions.remoteSortDisabled){
                            return;
                        }
                        var options = grid.datagrid('options');
                        options.sortName = f;
                        options.sortOrder =fieldProperty;
                        grid.datagrid('reload');
                        return;
                    }
                    var iconDesc = new RegExp("icon-desc");
                    if (iconDesc.test(item.iconCls)) {
                        var filedOptions = grid.datagrid('getColumnOption',f);
                        if(filedOptions.remoteSortDisabled){
                            return;
                        }
                        var options = grid.datagrid('options');
                        options.sortName = f;
                        options.sortOrder =fieldProperty;
                        grid.datagrid('reload');
                        return;
                    }
                }
            });
        }
        headerContextMenu.attr('field',field);
        headerContextMenu.menu('show', {
            left : e.pageX,
            top : e.pageY
        });
    };
    $.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
    $.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
});
