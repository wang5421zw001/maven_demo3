/**
 * Created by 韩明泽 on 2017/7/10.
 */

function f1(){
    $("#user_add_modal").modal({
        backdrop:"static"
    });
}
function save() {
    $("#addForm").submit();
    $("#user_add_modal").modal('hide');
}
function update() {
    $("#updateForm").submit();
    $("#user_update_modal").modal('hide');
}
function findUserByName_add () {
    var userName=$("#userName").val();
    $.ajax({
        url:"/findUserByName",
        data:{"userName":userName},
        type:"POST",
        dataType:"json",
        success:function(data){
            if(data=='1') {
                $("#un").html("用户名可以使用");
                $("#btSave").removeAttr("disabled");
            } else {
                $("#un").html("用户名已使用");
                $("#userName").focus();
                $("#btSave").attr("disabled","disabled");
            }
        }
    });
}
function findUserByName_update () {
    var set_userName=$("#set_userName").val();
    $.ajax({
        url:"/findUserByName",
        data:{"userName":set_userName},
        type:"POST",
        dataType:"json",
        success:function(data){
            if(data=='1') {
                $("#name").html("用户名可以使用");
                $("#btUpdate").removeAttr("disabled");
            } else {
                $("#name").html("用户名已使用");
                $("#set_username").focus();
                $("#btUpdate").attr("disabled","disabled");
            }
        }
    });
}
function deleteUser(a){
    var userId=a.value;
    $.ajax({
        url:"/deleteUserById",
        data:{"userId":userId},
        type:"POST",
        dataType:"json"
    });
}
function findUserById(o){
    var userId=o.value;
    $.ajax({
        url:"/findUserById",
        data:{"userId":userId},
        type:"POST",
        dataType:"json",
        success:function(data){
            $("#set_userId").val(data.userId);
            $("#set_userName").val(data.userName);
            $("#set_pwd").val(data.password);
            $("#set_userEmail").val(data.email);
            $("#set_userMoney").val(data.userMoney);
            var gender=data.gender;
            if(gender=="男"){
                $("#set_genderRadio1").attr("checked","checked");
            }
            else{
                $("#set_genderRadio2").attr("checked","checked");
            }
        }
    });

}
function chickAll(){
   var chickAll=document.getElementById("chickAll");
    var boxes=document.getElementsByName("blankCheckbox");
    for(var i=0;i<boxes.length;i++){
          if(chickAll.checked==true){
              boxes[i].checked=true;
          }
          else{
              boxes[i].checked=false;
          }
    }
}
function exportAll() {

    var boxes=document.getElementsByName("blankCheckbox");

    var ids="";
    for(var i=0;i<boxes.length;i++){
       if(boxes[i].checked){
           if(ids==""){
              ids+=boxes[i];
           }
           else{
               ids+=","+boxes[i];
           }
       }
    }
    if(ids==""){
        alert("请选择一条记录");
        return false;
    }
    var yOrN=confirm("确定导出这几条记录吗");
    if(yOrN){
        $.ajax({
            url: "/exportMsg",
            data: {"ids": ids},
            type: "POST",
            dataType: "json"
        });
    }
    return false;
}