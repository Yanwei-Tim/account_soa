$.fn.extend({
	mailUtil:function(option){
		var self=$(this);
		var selfId=self.attr("id");
		var defaultOption={
			url:'',
			mailBoxUrl:'',
			subGridPostData:{},
			colModel:[],
			mailData:[],
			rowNum:15,
			datatype: "json",
			rowList:[10,15,20,30],
			viewrecords:true,
			multiselect: false,
			subGrid: true,
			jsonReader:{
				repeatitems:false,
				id:"0"
			},
			height: 'auto',
			width:'auto',
			sidx: 'id',
		    sord: "asc",
			pager: '#'+$(this).attr("id")+'Pager',
			page:1,
			showColModelButton:false,
			subGridRowExpanded: function(subgrid_id, row_id) {
			},
			subGridRowColapsed: function(subgrid_id, row_id) {
			},
			ondblClickRow : function(rowid){
				self.toggleSubGridRow(rowid);
			},
			caption:false,
			gridComplete:function(){},
			rowSelect:function(selectValue){alert(selectValue);},
			allSelect:function(selectValue){alert(selectValue);}
		};
		var selectValue='';
		$.extend(defaultOption, option);
		mailMain(defaultOption);
		self.height(defaultOption.height);
		function mailMainInit(defaultOption){
			$.ajax({
			   type: "GET",
			   url:defaultOption.url,
			   dataType:defaultOption.datatype,
			   data:{"page":defaultOption.page,"rows":defaultOption.rowNum,"sidx":defaultOption.sidx,"sord":defaultOption.sord},
			   success: function(data){
					defaultOption.mailData=data;
					initMailRow(defaultOption);
					clickMailRow(defaultOption);
					selectAllFun(defaultOption);
					rowSelect(selectValue);
					initPager(defaultOption);
					clickSelectPage(defaultOption);
			   }
			});
		};
		function mailMain(defaultOption){
			$.ajax({
			   type: "GET",
			   url:defaultOption.url,
			   dataType:defaultOption.datatype,
			   data:{"page":defaultOption.page,"rows":defaultOption.rowNum,"sidx":defaultOption.sidx,"sord":defaultOption.sord},
			   success: function(data){
					defaultOption.mailData=data;
					self.addClass("mailBox");
					initMailThead(defaultOption);
					initMailRow(defaultOption);
					clickMailRow(defaultOption);
					selectAllFun(defaultOption);
					rowSelect(selectValue);
					initPager(defaultOption);
					clickSelectPage(defaultOption);
			   }
			});
		};
		//构建Mail头部
		function initMailThead(defaultOption){
			var colTheadId="colThead";
			var rowContentId="rowContent";
			var colNumber=defaultOption.colModel.length;
			var colModelName=eval(defaultOption.colModel);
			var colWidthNum=0;
			var colContent='';
			var colWidth=0;
			var colThead=$('<div id="'+colTheadId+'"><strong class="checkboxItem"><input type="checkbox" class="form-checkbox" id="selectAll" /><input type="checkbox" class="form-checkbox" id="canelSelectAll" style="display:none;" /></strong></div><div class="clear"></div>');
			for(var i=0;i<colNumber;i++){
				if(!colModelName[i].hidden){
					colThead.append('<span class="rowItem" sidx="'+colModelName[i].name+'" id="'+selfId+colModelName[i].name+'" style="width:'+colModelName[i].width+'px;text-align:'+colModelName[i].align+';">'+colModelName[i].label+'</span>');
					if(colModelName[i].width){
						colWidthNum++;
						colWidth=colWidth+Number(colModelName[i].width);
					}
					
				}
			};
			self.append(colThead);
			sortMail(defaultOption);
			//头部
			for(var i=0;i<colNumber;i++){
				if(!colModelName[i].hidden){
					if(!colModelName[i].width){
						$("#"+selfId+colModelName[i].name).width((self.width()-colWidth-50)/($("#"+colTheadId).find(".rowItem").length-colWidthNum));
					};
				};
			};
		}
		//构建Mail主体
		function initMailRow(defaultOption){
			var colTheadId="colThead";
			var rowContentId="rowContent";
			var colNumber=defaultOption.colModel.length;
			var colModelName=eval(defaultOption.colModel);
			var colWidthNum=0;
			var colContent='';
			var colWidth=0;
			//列宽计算
			for(var i=0;i<colNumber;i++){
				if(!colModelName[i].hidden){
					if(colModelName[i].width){
						colWidthNum++;
						colWidth=colWidth+Number(colModelName[i].width);
					}
					
				}
			};
			// row
			var rowNumber=defaultOption.mailData.rows.length;
			var rowData=defaultOption.mailData.rows;
			var rowContent=$("<div />").attr("id",rowContentId).width(defaultOption.width).height(defaultOption.height-30).css("overflow","auto");
			for(var i=0;i<rowNumber;i++){
				var rowCon=$('<div class="rowCon" id="'+rowData[i].id+'" loading="false"><strong class="checkboxItem"><input class="form-checkbox" type="checkbox" rowid="'+rowData[i].id+'" /></strong></div>');
				for(var j=0;j<colNumber;j++){
					if(!colModelName[j].hidden){//是否显示
						rowCon.append('<span class="rowItem" id=rowItem'+rowData[i].id+'-'+j+' title="'+rowData[i][colModelName[j].name]+'" style="width:'+colModelName[j].width+'px;">'+rowData[i][colModelName[j].name]+'</span>');
					};
					if(colModelName[j].formatter){//格式化
						rowData[i][colModelName[j].name]=colModelName[j].formatter;
					};
				};
				rowContent.append(rowCon);
			};
			self.append(rowContent);
			//列表样式设置

			//列表
			for(var i=0;i<rowNumber;i++){
				for(var j=0;j<colNumber;j++){
					if(!colModelName[j].hidden){
						if(!colModelName[j].width){
							$('#rowItem'+rowData[i].id+'-'+j).width((self.width()-colWidth-60)/($("#"+colTheadId).find(".rowItem").length-colWidthNum));
						};
						if(colModelName[j].align){
							$('#rowItem'+rowData[i].id+'-'+j).css("text-align",colModelName[j].align);
						};
					};
				};
			};
			clickRowScrollTop();
		};
		function sortMail(defaultOption){
			$("#colThead .rowItem").toggle(function(){
				var sidx=$(this).attr("sidx");
				$(defaultOption.pager).empty();
				$("#rowContent").remove();
				defaultOption.sidx=sidx;
				defaultOption.sord="desc";
				mailMainInit(defaultOption);
			},
			function(){
				var sidx=$(this).attr("sidx");
				$(defaultOption.pager).empty();
				$("#rowContent").remove();
				defaultOption.sidx=sidx;
				defaultOption.sord="asc";
				mailMainInit(defaultOption);
			});
		}
		//构建底部页码
		function initPager(defaultOption){
			var pageNumber=defaultOption.mailData.records/defaultOption.rowNum;
			if(pageNumber<=0){
				$(defaultOption.pager).append('');
				$(".pagePrev").bind("click",function(){
					reload(defaultOption);
				})
				return;
			};
			if(Math.floor(pageNumber)==pageNumber){
				pageNumber=Math.floor(pageNumber);
			}else{
				pageNumber=Math.floor(pageNumber)+1;
			}
			var firstPage='<input type="button" class="firstPage defaultButton" value="第一页" />';
			var lastPage='<input type="button" class="lastPage defaultButton" value="最后一页" />';
			var prevPage='<input type="button" class="prevPage defaultButton" value="上一页" />';
			var nextPage='<input type="button" class="nextPage defaultButton" value="下一页" />';
			var rowPage='<select id="rowSelect">';
			for(var i=0;i<defaultOption.rowList.length;i++){
				rowPage=rowPage+'<option value="'+defaultOption.rowList[i]+'">'+defaultOption.rowList[i]+'</option>';
			};
			rowPage=rowPage+'</select>';
			var pageInfo='<div class="pageInfo">共'+defaultOption.mailData.records+'条数据&nbsp;&nbsp;当前第'+defaultOption.page+'/'+pageNumber+'页</div>'
			var pageList='';
			$(defaultOption.pager).append(firstPage+prevPage+nextPage+lastPage+pageInfo+rowPage);
			$("#rowSelect").val(defaultOption.rowNum);
			$(".firstPage").bind("click",function(){
				if(defaultOption.page!=1){
					toPager(defaultOption,1);
					defaultOption.page=1;
				}
			});
			$(".lastPage").bind("click",function(){
				if(defaultOption.page != pageNumber){
					toPager(defaultOption,pageNumber);
					defaultOption.page=pageNumber;
				}
			});
			$(".prevPage").bind("click",function(){
				if(defaultOption.page>1){
					defaultOption.page=defaultOption.page-1;
					toPager(defaultOption,defaultOption.page);
				}
			});
			$(".nextPage").bind("click",function(){
				if(defaultOption.page!=pageNumber){
					defaultOption.page=defaultOption.page+1;
					toPager(defaultOption,defaultOption.page);
				}
			});
		};
		//复选框 点击事件
		function rowSelect(selectValue){
			$(".rowCon .form-checkbox").bind('click',function(){
				selectValue="";
				self.find(".rowCon .form-checkbox").each(function(){
					if($(this).attr("checked")==true || $(this).attr("checked")=="checked"){
						selectValue=selectValue+$(this).parent().parent().attr("id")+",";
					}
				});
				defaultOption.rowSelect(selectValue);
			})
		}
		//列表项点击展开事件
		function clickMailRow(defaultOption){
			self.find(".rowCon span").bind('click',function() {
				var selfRow=$(this).parent();
				var selfRowId=selfRow.attr("id");
				if(selfRow.attr("selected")=="true"){
					selfRow.addClass("selectRow");
					$("#mailBoxContent"+selfRowId).hide();
					selfRow.removeClass("borderTop").attr("selected","false");
				}else{
					$(".mailBoxContent").hide();
					$('.rowCon').removeClass("borderTop").removeClass("selectRow").attr("selected","false");
					selfRow.addClass("borderTop");
					if(selfRow.attr("loading")=="false"){
						selfRow.after("<div class='mailBoxContent' id=mailBoxContent"+selfRowId+"></div>").addClass("borderTop");
						$("#mailBoxContent"+selfRowId).attr("selecked","true").show();
						$("#mailBoxContent"+selfRowId).load(defaultOption.mailBoxUrl+selfRowId, function(){
							selfRow.attr("loading","true");
						});
					}
					else{
						$("#mailBoxContent"+selfRowId).show();
					}
					selfRow.attr("selected","true");
				}
			}
			);
		};
		function clickRowScrollTop(){
			//点击自动滚到当前屏
			self.find(".rowCon").bind('click',function() {
				var thisId=$(this).attr("id");
				var scrHeight=0;
				$("#rowContent").find(".rowCon").each(function(i,n){
					if($(this).attr("id")==thisId){
						return false;
					}
					scrHeight=scrHeight+$(this).height();
				});
				$("#rowContent").scrollTop(scrHeight);
			});
		}
		function clickSelectPage(defaultOption){
			$("#rowSelect").bind("change",function(){
				defaultOption.rowNum=$(this).attr("value");
				$("#rowContent").remove();
				$(defaultOption.pager).empty();
				mailMainInit(defaultOption);
			})
		};
		function selectAllFun(defaultOption){
			$("#selectAll").click(function(){
				self.find(".rowCon .form-checkbox").attr("checked",true);
				$("#canelSelectAll").attr("checked",true);
				$(this).attr("checked",true);
				$(this).hide();
				$("#canelSelectAll").show();
				selectValue='';
				self.find(".rowCon .form-checkbox").each(function(){
					selectValue=selectValue+$(this).parent().parent().attr("id")+',';
				});
				defaultOption.allSelect(selectValue);
			}
			);
			$("#canelSelectAll").click(function(){
				self.find(".rowCon .form-checkbox").attr("checked",false);
				$("#selectAll").attr("checked",false);
				$(this).attr("checked",false);
				$(this).hide();
				$("#selectAll").show();
				selectValue='';
			})
		};
		function toPager(defaultOption,pager){
			$(defaultOption.pager).empty();
			$("#rowContent").remove();
			defaultOption.page=pager;
			mailMainInit(defaultOption);
		}
	}
})