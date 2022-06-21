let selectView = $('#selectView');

function loadGetListBysearch(){
    let keyword  = $('#keyword').val();
    console.log("검색어 들어오나유?",keyword);
    $.getJSON("/rest/getListBySearch/"+keyword,function (data){
        console.log(data)
        let str = "";
        $(data).each(function (){
            console.log(this.pImage)
            // str +=	'<tr><td ><a href="/shop/read?pno='+this.pno+'"><img class="search_img" src=/display?fileName='+this.pimage+'></a></td>';
            // str +=	'<td style="vertical-align : bottom; font-size:15px;"><a  href="/shop/read?pno='+this.pno+'">'+this.pname+'</a>';
            // str +=   '<p style="font-size:7px">'+this.pprice+'원</p></td>';
            str += '<div class="single-cart clearfix">'
            str +='<div class="cart-image">'
            str +=	'<a style="width: 245px" href="/shop/read?pno='+this.pno+'">' +
                '<img   class="search_img" src="/display?fileName='+this.pimage+'" style="width: 350px; height:90;float: left;display: inline"></a>';
            str +=	'<span style="float: left"><a style="width: 200px;font-size: 15px;text-align: center;margin-left: 20px" class="search_Name"  href="/shop/read?pno='+this.pno+'">'+this.pname+'</a></span>';
            str += '</div>'
            str +='</div>'
        })
        selectView.html(str);
    })

}
$("input[name='keyword']").keyup(function (){
    loadGetListBysearch();
})
$("#findBtn").click(function (e){
    e.preventDefault()
    let form = $(".searchForm")
    let keyword=$("input[name='keyword']").val()
    form.attr("action","/shop/list?pCateCode=''&tier=''&page=1&numPerPage=8&sort=''&keyword="+ keyword)
    form.attr("method","get")
    form.submit()
})