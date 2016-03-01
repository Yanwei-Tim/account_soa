(function( $ ) {
	$.widget( "ui.combobox", {
		options: {
			afterChange:false,
			isHavePlease:false
		},
		_create: function() {
			var self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text().split(",")[0] : "";
				
			var input = $( "<input class='form-txt' >" );
			input.insertAfter( select )
			.val( value )
			.autocomplete({
				delay: 0,
				minLength: 0,
				source: function( request, response ) {
					var inputTerm = request.term.toLowerCase();
					response( select.children( "option" ).map( function(n) {
						if(self.options.isHavePlease && n==0 && inputTerm == ""){
							return {
								label: "--选择全部--",
								value: "",
								option: this
							};
						}
						var text = $( this ).text();
						var textArray = text.split(",");
						if ( this.value  && (textArray[0].indexOf(inputTerm)==0||textArray[1].indexOf(inputTerm)==0||textArray[2].indexOf(inputTerm)==0) ){
							return {
								label: textArray[0],
								value: textArray[0],
								option: this
							};
						}
					}));
				},
				open: function(ui,event) {
					$(".ui-autocomplete").find("iframe").remove();
					$(".ui-autocomplete").css({"overflow-y":"auto"});
				},
				close: function(ui,event) {
					$(".ui-autocomplete").css({"overflow-y":"hidden"});
				},
				select: function( event, ui ) {
					$(ui.item.option).attr("selected" , true);
					if(self.options.afterChange){
						self.options.afterChange.call(null,$(ui.item.option).val());
					}
					self._trigger( "selected", event, {
						item: ui.item.option
					});
						
				},
				change: function( event, ui ) {
					if(""==input.val()){
						select.val( "" );
						return false;
					}
					if(ui.item==null){
						var inputTerm = $(this).val(),
							valid = false;
						select.children( "option" ).each(function(n) {
							if(n!=0){
								var text = $( this ).text();
								var textArray = text.split(",");
								if ($(this).attr("selected")){
									input.val(textArray[0]);
									valid=true;
								}
							}
						});
						if ( !valid ) {
							$(this).val( "" );
							select.val( "" );
							return false;
						}
					}
				}
			});
			
			var buttonObj = $('<button type="button" '+
					'class="ui-autocompleteButton ui-button ui-widget ui-state-default ui-button-icon-only ui-button-icon"'+
					' tabindex="-1" title="显示全部" role="button" aria-disabled="false" style="width:22px;top:5px;_top:6px;position:absolute;left:'+(input.width()-14)+'px;">'+
					'<span class="ui-button-icon-primary ui-icon ui-icon-triangle-1-s" style="width:12px"></span>'+
					'<span class="ui-button-text">&nbsp;</span></button>');
			input.parent().css("position","relative");
			buttonObj.insertAfter( input )
			buttonObj.click(function() {
				if (input.autocomplete( "widget" ).is( ":visible" ) ) {
					input.autocomplete( "close" );
					return;
				}

				input.autocomplete("search", "" );
				input.focus();
			});
		}
	});
})( jQuery );