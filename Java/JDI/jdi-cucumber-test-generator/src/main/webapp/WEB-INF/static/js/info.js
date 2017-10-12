function successInfoBlock(){
    $("#wrapper_info_block_success").animate({
    	right: '0',
    }, 800, function(){
        $(".icon_success_hide_corners").animate({
        	width: '20px',
        	height: '20px',
        	left: '15px',
        	top: '15px'
        }, 200, function(){
        	$(".icon_success_line_tip").animate({
        		top: '30px',
        		left: '8px',
        		width: '15px'
        	}, 200, function(){
        		$(".icon_success_line_long").animate({
        			top: '24px',
        			left: '14px',
        			width: '30px'
        		}, 200, function(){
        		    $("#wrapper_info_block_success").delay(1000).animate({
                    	right: '-100%',
                    }, 1000, function(){
                        $(".icon_success_hide_corners").css({
                            width: '60px',
                            height: '60px',
                            left: '-5px',
                            top: '-5px'
                        });
                        $(".icon_success_line_tip").css({
                        	top: '24px',
                        	left: '11px',
                        	width: '0px'
                        });
                        $(".icon_success_line_long").animate({
                        	top: '34px',
                        	left: '16px',
                        	width: '0px'
                        });
                    });
        		});
        	});
        });
    });
}

function errorInfoBlock(text){
    $("#element_info_block_error").text(text);
    $("#wrapper_info_block_error").animate({
    	right: '0',
    }, 800, function(){
        $(".icon_error_hide_ring").animate({
        	width: '20px',
        	height: '20px',
        	left: '15px',
        	top: '15px'
        }, 200, function(){
        	$(".icon_error_line").animate({
        		width: '35px',
        		top: '24px'
        	}, 200, function(){
        	    $("#wrapper_info_block_error").delay(1000).animate({
                	right: '-100%',
                }, 1000, function(){
                    $(".icon_error_hide_ring").css({
                        width: '60px',
                        height: '60px',
                        left: '-5px',
                        top: '-5px'
                    });
                    $(".icon_error_line").css({
                        width: '0px',
                        top: '10px'
                    });
                });
        	});
        });
    });
}