$.fn.tabPaging=function(o){
	var self = $(this);
	var dfop = {
        format: "< ncnn >",
        perpage: 10,
		lapping: 0,
		page: 1,
        onSelect: function(page) {
        	
        },
        onFormat: function(type) {
            switch (type) {
                case 'block':
                        if (!this.active)
                                return '<span class="disabled">' + this.value + '</span>';
                        else if (this.value != this.page)
                                return '<em><a href="#' + this.value + '">' + this.value + '</a></em>';
                        return '<span class="current">' + this.value + '</span>';
                case 'next':
                        if (this.active)
                                return '<a href="#' + this.value + '" class="next">下一页</a>';
                        return '<span class="disabled">Next</span>';
                case 'prev':
                        if (this.active)
                                return '<a href="#' + this.value + '" class="prev">上一页</a>';
                        return '<span class="disabled">Prev</span>';
                case 'first':
                        if (this.active)
                                return '<a href="#' + this.value + '" class="first">首页<</a>';
                        return '<span class="disabled">|<</span>';
                case 'last':
                        if (this.active)
                                return '<a href="#' + this.value + '" class="last">>尾页</a>';
                        return '<span class="disabled">>|</span>';
                case "leap":
                        if (this.active)
                                return "...";
                        return "";
                case 'fill':
                        if (this.active)
                                return "...";
                        return "";
        	}
        }
	}
    $.extend(dfop,o);
    return self.paging(dfop.total,dfop);
}