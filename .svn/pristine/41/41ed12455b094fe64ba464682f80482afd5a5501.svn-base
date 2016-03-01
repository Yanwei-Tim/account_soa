	function provinceSelect(config){
		var $s1=$("#"+config.province);
		var v1=config.provinceValue?config.provinceValue:null;
		appendOptionTo($s1,"","","","");
		$.each(threeSelectData,function(k,v){
			appendOptionTo($s1,k,v.val,v1);
		});
		function appendOptionTo($o,k,v,d){
			var $opt=$("<option>").text(k).val(k);
			if(k==d){$opt.attr("selected", "selected")}
			$opt.appendTo($o);
		}
	}

		function threeSelect(config){
			var $s1=$("#"+config.province);
			var $s2=$("#"+config.city);
			var $s3=$("#"+config.district);
			var v1=config.provinceValue?config.provinceValue:null;
			var v2=config.cityValue?config.cityValue:null;
			var v3=config.districtValue?config.districtValue:null;
			if($s1.children()[0]){
				clearOptions($s1);
				$s1.html("");
			}
			appendOptionTo($s1,"","","","");
			$.each(threeSelectData,function(k,v){
				appendOptionTo($s1,k,v.val,v1);
			});
			$s1.change(function(){
				clearOptions($s2);
				if(this.selectedIndex==-1 || this.options[this.selectedIndex].value==""){
					$s2.change();
					return;
				} 
				var s1_curr_val = this.options[this.selectedIndex].value;
				$.each(threeSelectData,function(k,v){
					if(s1_curr_val==k){
						if(v.items){
							$.each(v.items,function(k,v){
								appendOptionTo($s2,k,v.val,v2);
							});
						}
					}
				});
				$s2.change();
			}).change();
			$s2.change(function(){
				clearOptions($s3);
				var s1_curr_val = $s1[0].options[$s1[0].selectedIndex].value;
				if(this.selectedIndex==-1 || this.options[this.selectedIndex].value=="") return;
				var s2_curr_val = this.options[this.selectedIndex].value;
				$.each(threeSelectData,function(k,v){
					
					if(s1_curr_val==k){
						if(v.items){
							$.each(v.items,function(k,v){
								if(s2_curr_val==k){
									$.each(v.items,function(k,v){
										appendOptionTo($s3,k,v,v3);
									});
								}
							});
						}
					}
				});
			}).change();
			function appendOptionTo($o,k,v,d){
				var $opt=$("<option>").text(k).val(k);
				if(k==d){$opt.attr("selected", "selected")}
				$opt.appendTo($o);
			}
			
			function clearOptions($o){
				$o.html("");
				appendOptionTo($o,"","","","");
			}
		}
