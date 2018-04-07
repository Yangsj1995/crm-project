function openCustomerAddDialog() {
    $("#dlg").dialog("open");
}

function closeCustomerDialog() {
    initFormData();
    $("#dlg").dialog("close");

}

function openCustomerModifyDialog() {
    var rows = $("#dg").datagrid("getSelections");

    if(rows.length ==0){
        $.messager.alert("crm","请先选中一条数据","info");
        return ;
    }

    if(rows.length>1){
        $.messager.alert("crm","不能同时修改多条记录","info");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","修改客户信息");
}

function initFormData() {
    $("#id").val("");
    $("#name").val("");
    $("#area").combobox("clear");
    $("#cusManager").combobox("clear");
    $("#level").combobox("clear");
    $("#myd").combobox("clear");
    $("#xyd").combobox("clear");
    $("#postCode").val("");
    $("#phone").val("");
    $("#fax").val("");
    $("#webSite").val("");
    $("#address").val("");
    $("#yyzzzch").val("");
    $("#fr").val("");
    $("#zczj").val("");
    $("#nyye").val("");
    $("#khyh").val("");
    $("#khzh").val("");
    $("#dsdjh").val("");
    $("#gsdjh").val("");
}



function saveOrUpdateCustomer() {
    var id = $("#id").val();
    var save_update_url = ctx + "/customer/update";
    if(isEmpty(id)){
        save_update_url = ctx + "/customer/insert";
    }

    $("#fm").form("submit",{
        url:save_update_url,
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            $.messager.alert("crm",data.msg,"info");
            if(data.code==200){
                $("#dlg").dialog("close");
                initFormData();
                searchCustomer();
            }
        }
    })
}

function searchCustomer() {
    $("#dg").datagrid("load",{
        khno:$("#s_khno").val(),
        customerName:$("#s_name").val()
    })
}

function deleteCustomer() {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length ==0){
        $.messager.alert("crm","请先选中要删除的数据","info");
        return ;
    }

    var mydata = "ids=";
    // ids=1&ids=2
    for(var i=0;i<rows.length;i++){

        if(i<rows.length-1){
            mydata = mydata + rows[i].id +"&ids=" ;
        }else{
            mydata = mydata + rows[i].id
        }

    }

    $.messager.confirm("crm","确定要删除所选中的数据么？",function (r) {
        if(r){
            $.ajax({
                type:"post",
                url:ctx + "/customer/delete",
                data:mydata,
                dataType:"json",
                success:function (data) {
                    $.messager.alert("crm",data.msg,"info");
                    if(data.code==200){
                        searchCustomer();
                    }
                }
            })
        }
    })
}

function openCustomerOtherInfo(title,type) {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length ==0){
        $.messager.alert("crm","请先选中一个客户","info");
        return ;
    }

    if(rows.length>1){
        $.messager.alert("crm","不能同时操作多个客户吧","info");
        return;
    }

    window.parent.openTab(title,ctx+"/customer/openCustomerOtherInfo/"+type + "/" + rows[0].id);
}