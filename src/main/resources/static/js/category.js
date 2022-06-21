(function (){

    $.getJSON("/rest/getCategoryList/",function (data){
        let str ="";
        let shopMainCatecory=$(".mainCatecory_ul")
        $(data).each(function (index,tier1){
            if(tier1.tier==1){
                str +='<li><a href="/shop/list?pCateCode='+tier1.pcateCode+'&tier=1&page=1&numPerPage=8&sort=\'\'">'
                str +=  tier1.pcateName +'</a>'
                str +=  '<ul class="sub-menu shopCategory">'
                $(data).each(function (index,tier2){
                    if(tier2.cateParent == tier1.pcateCode ){
                        str +='<div class="shopCategory_div">'
                        str += '<li><a class="tier2_a" style="font-size: 15px" ' +
                            'href="/shop/list?pCateCode='+tier2.pcateCode+'&tier=2&page=1&numPerPage=8&sort=\'\'">' +
                            '<strong>'+tier2.pcateName+'</strong></a>'
                        str += '<ul>';
                        $(data).each(function (index,tier3){
                            if(tier3.cateParent==tier2.pcateCode){
                                str +='<li><a class="tier3_a" style="font-size: 13px" ' +
                                    'href="/shop/list?pCateCode='+tier3.pcateCode+'&tier=3&page=1&numPerPage=8&sort=\'\'">'+
                                    tier3.pcateName +'</a></li>'
                            }
                        })
                        str += '</ul>'
                        str +='</li>';
                        str +='</div>'
                    }
                })
                 str +='</ul>'
                 str +='</li>'
            }
         })
        shopMainCatecory.prepend(str)
    })
})(jQuery);
