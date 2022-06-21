(function($) {
    "use strict";

/*--
    Menu Sticky
-----------------------------------*/
var $window = $(window);
$window.on('scroll', function() {
	var scroll = $window.scrollTop();
	if (scroll < 300) {
		$(".sticker").removeClass("stick");
	}else{
		$(".sticker").addClass("stick");
	}
});

/*--
    Header Option Button
-----------------------------------*/

$(".search-toggle").on('click', function(){
    $(".header-search-form").slideToggle(500);
  });

$(".account-toggle").on('click', function(){
    $(".account-menu").slideToggle(500);
  });

$(".cart-toggle").on('click', function(){
    $(".mini-cart-brief").slideToggle(500);
  });


/*--
    Mobile Menu
-----------------------------------*/

$('.main-menu').meanmenu({
	meanScreenWidth: '991',
	meanMenuContainer: '.mobile-menu',
	meanMenuClose: '<i class="pe-7s-close-circle"></i>',
	meanMenuOpen: '<i class="pe-7s-menu"></i>',
	meanRevealPosition: 'right',
	meanMenuCloseSize: '30px',
});

/*--
    Nivo Slider
-----------------------------------*/
$('#home-slider').nivoSlider({
    directionNav: true,
    animSpeed: 1000,
    effect: 'random',
    slices: 18,
    pauseTime: 88885000,
    pauseOnHover: false,
    controlNav: false,
    prevText: '<i class="fa fa-long-arrow-left"></i>',
    nextText: '<i class="fa fa-long-arrow-right"></i>'
});

/*--
	Home Slick Slider
-----------------------------------*/
/*-- Image Slider --*/
$('.home-slick-image-slider').slick({
    asNavFor: '.home-slick-text-slider',
    slidesToShow: 1,
    autoplay: true,
    prevArrow: '<button type="button" class="arrow-prev"><i class="fa fa-long-arrow-left"></i></button>',
    nextArrow: '<button type="button" class="arrow-next"><i class="fa fa-long-arrow-right"></i></button>',
    responsive: [
        {
            breakpoint: 767,
            settings: {
                arrows: false,
                autoplay: true,
                autoplaySpeed: 5000,
            }
        },
    ]
});
/*-- Text Slider --*/
$('.home-slick-text-slider').slick({
    arrows: false,
    autoplay: true,
    asNavFor: '.home-slick-image-slider',
    slidesToShow: 1,
});

/*--
	Isotop with ImagesLoaded
-----------------------------------*/
var productFilter = $('.isotope-product-filter');
var productGrid = $('.isotope-grid');
/*-- Images Loaded --*/
productGrid.imagesLoaded( function() {
    /*-- Filter List --*/
    productFilter.on( 'click', 'button', function() {
        productFilter.find('button').removeClass('active');
        $(this).addClass('active');
        var filterValue = $(this).attr('data-filter');
        productGrid.isotope({ filter: filterValue });
    });
    /*-- Filter Grid --*/
    productGrid.isotope({
      itemSelector: '.isotope-item',
      masonry: {
        columnWidth: '.isotope-item',
      }
    });
});

/*--
    Price Range
-----------------------------------*/
$('#price-range').slider({
   range: true,
   min: 0,
   max: 700,
   values: [ 70, 500 ],
   slide: function( event, ui ) {
    
	$('.price-amount').val( '€' + ui.values[ 0 ] + ' - €' + ui.values[ 1 ] );
    
   }
});
$('.price-amount').val( '€' + $('#price-range').slider( 'values', 0 ) +
   ' - €' + $('#price-range').slider('values', 1 ) );

/*--
    Product Quantity
-----------------------------------*/
$('.product-quantity').append('<span class="dec qtybtn"><i class="fa fa-angle-left"></i></span><span class="inc qtybtn"><i class="fa fa-angle-right"></i></span>');
$('.qtybtn').on('click', function() {
    var $button = $(this);
    var oldValue = $button.parent().find('input').val();
    if ($button.hasClass('inc')) {
        var newVal = parseFloat(oldValue) + 1;
    } else {
        // Don't allow decrementing below zero
        if (oldValue > 0) {
            var newVal = parseFloat(oldValue) - 1;
        } else {
            newVal = 0;
        }
    }
    $button.parent().find('input').val(newVal);
});

/*--
    Product Slider 4 Item
-----------------------------------*/
$('.product-slider-4').slick({
    speed: 700,
    slidesToShow: 4,
    slidesToScroll: 1,
    prevArrow: '<button type="button" class="arrow-prev"><i class="fa fa-angle-left"></i></button>',
    nextArrow: '<button type="button" class="arrow-next"><i class="fa fa-angle-right"></i></button>',
    responsive: [
        {
            breakpoint: 1169,
            settings: {
                slidesToShow: 3,
            }
        },
        {
            breakpoint: 991,
            settings: {
                slidesToShow: 2,
            }
        },
        {
            breakpoint: 767,
            settings: {
                slidesToShow: 1,
            }
        },
    ]
});

    //커뮤니티 슬라이더 JH 추가
    $('.community-slider-4').slick({
        speed: 2000,
        slidesToShow: 2,
        slidesToScroll: 1,
        autoplay : true,
        prevArrow: '<button type="button" class="arrow-prev"><i class="fa fa-angle-left"></i></button>',
        nextArrow: '<button type="button" class="arrow-next"><i class="fa fa-angle-right"></i></button>',
        responsive: [
            {
                breakpoint: 1169,
                settings: {
                    slidesToShow: 3,
                }
            },
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 2,
                }
            },
            {
                breakpoint: 767,
                settings: {
                    slidesToShow: 1,
                }
            },
        ]
    });
/*--
    Product Details Thumbnail Slider
-----------------------------------*/
$('.pro-thumb-img-slider').slick({
    speed: 700,
    slidesToShow: 3,
    slidesToScroll: 1,
    prevArrow: '<button type="button" class="arrow-prev"><i class="fa fa-angle-left"></i></button>',
    nextArrow: '<button type="button" class="arrow-next"><i class="fa fa-angle-right"></i></button>',
    responsive: [
        {
            breakpoint: 991,
            settings: {
                slidesToShow: 3,
            }
        },
        {
            breakpoint: 767,
            settings: {
                slidesToShow: 3,
            }
        },
    ]
})

$('.modal').on('shown.bs.modal', function (e) {
    $('.pro-thumb-img-slider').slick('setPosition');
    $('.modal-body').addClass('open');
});


/*--
    Checkout Form Collapse on Checkbox
-----------------------------------*/
$('.checkout-form input[type="checkbox"]').on('click', function(){
    var $collapse = $(this).data('target');
    if( $(this).is(':checked') ){
        $('.collapse[data-collapse="'+$collapse+'"]').slideDown();
    }else {
        $('.collapse[data-collapse="'+$collapse+'"]').slideUp();
    }
})

/*--
    Product Filter Toggle
-----------------------------------*/
$('.product-filter-toggle').on('click', function(){
    $('.product-filter-wrapper').slideToggle();
})

/*-- 
    ScrollUp
-----------------------------------*/
$.scrollUp({
    scrollText: '<i class="fa fa-angle-up"></i>',
    easingType: 'linear',
    scrollSpeed: 900,
    animation: 'fade'
});

/*-- 
    WOW
-----------------------------------*/
new WOW().init();




})(jQuery);



