/**
 * Created by 韩明泽 on 2017/7/12.
 */
var setting={
 data:{
     key:{
         url: "xUrl"
     },simpleData: {
         enable: true,
         idKey: "id",
         pIdKey: "pid"
     }
 }
};
var zTreeObj;
$(function() {
    $.ajax({
        type : "post",
        url : "/zTree",
        success : function(data) {
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting,data);
        }
    });
});

