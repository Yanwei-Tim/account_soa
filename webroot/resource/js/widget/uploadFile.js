(function ($) {
	$.fn.uploadFile=function(option){
		function   GetCookie(sName){     
		    var   aCookie   =   document.cookie.split("; "); 
		    for(var i=0;i<aCookie.length;i++) {      
		        var aCrumb=aCookie[i].split("=");
		        if(sName==aCrumb[0])     
		            return unescape(aCrumb[1]);   
		    }       
		    return null;  
		}
		
		var self = $(this);
		var thisTitle=document.title;
		var rename=false;
		var fileExtBol=false;
		var flashMessage=$.flashVersion();
		var sid=GetCookie("sid");
		var returnBoo=true;
		var filenameExt='*.jpg;*.gif;*.png;*.zip;*.jpeg;*.pdf;*.pptx;*.rtf;*.tar;*.vsd;*.xlsx;*.tif;*.rar;*.bmp;*.xls;*.docx;*.doc;*.txt';
		var defaultOption={
			queueID        : "",
			sizeLimit      : 2097152,
			selectInputId  : "",
			containId      : false,
			fileDataName   : 'uploadFile',  
			method 		   : "GET",
			scriptData	   : {sid:sid,isFlash:true},
			uploader       : PATH+'/resource/js/uploadify/uploadify.swf',
			script         : PATH+'/enclosure/enclosureUpload.action',
			cancelImg      : RESOURCE_PATH+'/resource/js/uploadify/cancel.png',
			buttonImg	   : RESOURCE_PATH+'/resource/js/uploadify/uploadButton.jpg',
			folder         : '/',
			multi          : true,
			auto           : true,
			queueSizeLimit : 3,
			maxFileUpload : 10,
			onQueueFull    :function(){
					//alert("单次文件上传，最多选择"+defaultOption.queueSizeLimit+"个文件");
					$.messageBox({message:"单次文件上传，最多选择"+defaultOption.queueSizeLimit+"个文件",level: "error"});
					return false;
				},
			removeCompleted: false,
			width : 60,
			height: 20,
			fileExt        : filenameExt,
			fileDesc       : '文档及图片',
			onSelect:function(event,ID,fileObj){
				document.title=thisTitle;
				returnBoo=true;
				$("#"+defaultOption.selectInputId+" option").each(function(){
					if($(this).attr("value")==fileObj.name){
						alert("文件不允许重名。请修改文件名后再上传");
						$(this).remove();
						returnBoo=false;
					};
				});
				var fileExtNumber=4;
				if(fileObj.name.substring(fileObj.name.length-4,fileObj.name.length-3)!='.'){
					fileExtNumber=5;
				}
				var flieExtName=fileObj.name.substring(fileObj.name.length-fileExtNumber,fileObj.name.length);
				var flieExtNameArr=filenameExt.split(";");
				for(var i=0;i<flieExtNameArr.length;i++){
					if(flieExtNameArr[i]==('*'+flieExtName.toLowerCase())){
						fileExtBol=true;
					};
				};
				if(!fileExtBol){
					alert("该类型文件不允许上传");
					$(this).remove();
					return false;
				};
				if($("#"+defaultOption.queueID+" .uploadifyQueueItem").size()>=defaultOption.maxFileUpload){
					alert("最多允许上传"+defaultOption.maxFileUpload+"个文件");
					return false;
				};
				if(fileObj.size<=0){
					alert("不允许上传空文件");
					returnBoo=false;
				}
				if(!returnBoo){
					return returnBoo;
				}
			}
        };
		$.extend(defaultOption,option);
		if(flashMessage){
			$("#custom-queue").html(flashMessage);
			$("#custom_file_uploadUploader").remove();
		}
		var events={
		  	onAllComplete  : function(event,data) {
			  	var size=$("#"+defaultOption.queueID).attr("totalSize");
			  	if(size==undefined){
			  		$("#"+defaultOption.queueID).attr("totalSize",data.allBytesLoaded);
			  	}else{
			  		$("#"+defaultOption.queueID).attr("totalSize",(parseInt(size)+data.allBytesLoaded));
			  	}
			  	if(option.onAllComplete){
			  		option.onAllComplete.call(null,event,data);
			  	}
			},
			onComplete	: function(e,queueId,fileObj,response,data){
				$("a","#"+self.attr("id")+queueId).click(function(){
					$.ajax({
		        		url:defaultOption.removeAction,
						type:"post",
						data:{fileName: fileObj.name},
		        		success:function(){
							if (defaultOption.containId){
								$("option[value=',"+fileObj.name+"']",$("#"+defaultOption.selectInputId)).remove();
							}else{
								$("option[value='"+fileObj.name+"']",$("#"+defaultOption.selectInputId)).remove();
							}
		        		}
		        	});
				});
				if(returnBoo && $("#"+defaultOption.selectInputId+" option").size()<=defaultOption.maxFileUpload){
					if (defaultOption.containId){
						$("#"+defaultOption.selectInputId).append("<option value=',"+fileObj.name+"' selected></option>");
					}else{
						$("#"+defaultOption.selectInputId).append("<option value='"+fileObj.name+"' selected></option>");
					}
				}
				if(option.onComplete){
			  		option.onComplete.call(null,e,queueId,fileObj,response,data);
			  	}
				var queueID = $("#"+defaultOption.queueID);
				queueID.scrollTop=queueID.scrollHeight-queueID.offsetHeight;
			}
		};
		$.extend(defaultOption,events);
		self.uploadify(defaultOption); 
		var settings=jQuery(this).data('settings');
		if(settings==null){
			settings = defaultOption;
		}
		self.unbind("uploadifySelectOnce").bind("uploadifySelectOnce", {'action': settings.onSelectOnce}, function(event, data) {
			if($("#"+defaultOption.selectInputId+" option").size()>=defaultOption.maxFileUpload){
				return false;
			}
			event.data.action(event, data);
			if (settings.auto) {
				if (settings.checkScript) { 
					jQuery(this).uploadifyUpload(null, false);
				} else {
					jQuery(this).uploadifyUpload(null, true);
				}
			}
		});
		document.title=thisTitle;
		$("#custom_file_uploadUploader").attr("title","仅支持JPG、GIF、PNG、ZIP、RAR、BMP、XLS、DOCX、DOC、TXT格式的文件");
	};
	
	$.fn.addUploadFileValue = function(option){
		var self = $(this);
		function removeItem(id){
			$("#item"+self.attr("id")+id).remove();
		};
		var defaultOption={
			filename:"",
			filesize:"",
			id:"",
			link:"javascript:void(0)",
			showCloseButton:true,
			onRemove:function(id){}
		};
		$.extend(defaultOption,option);
		if(option.onRemove){
			defaultOption.onRemove=function(){
				option.onRemove.call(null,defaultOption.id);
			};
		};
		var fileSizeMsg="";
		if(defaultOption.filesize!=""&&defaultOption.filesize!=0){
			fileSizeMsg = '('+defaultOption.filesize+')';
		};
		if(defaultOption.showCloseButton){
			var canelHtml='<div class="cancel"><a href="javascript:void(0)" id="'+$(this).attr("id")+defaultOption.id+'"><img src="'+RESOURCE_PATH+'/resource/js/uploadify/delete.jpg" border="0"></a></div>';
		}
		else{
			var canelHtml='';
			$("#custom_file_uploadUploader").remove();
		};
		var itemHtml = '<div id="item'+self.attr("id")+defaultOption.id+'" class="uploadifyQueueItem completed">'+canelHtml+'<a href="'+defaultOption.link+'" target="_blank"><span class="fileName">'+defaultOption.filename+' '+fileSizeMsg+'</span></a></div>';
		$(this).append($(itemHtml));
		if(defaultOption.showCloseButton){
			$("#"+$(this).attr("id")+defaultOption.id).click(function(){
				defaultOption.onRemove(defaultOption.id);
				removeItem(defaultOption.id);
			})
		};
	}
	
	$.fn.getTotalUploadFiles = function(){
		
	}
	
	$.fn.getTotalUploadSize = function(){
		return $(this).attr("totalSize");
	}
	$.flashVersion=function(){
		var up=false;
		var p = navigator.plugins;
        if (p && p.length) {
            for (var i = 0; i < p.length; i++) {
                if (p[i].name.indexOf('Shockwave Flash') > -1) {
                    if(p[i].description.split('Shockwave ')[1]<="Flash 6.0 r154"){
                    	//up=false;
                    }
                    break;
                }
            }
        } else if (window.ActiveXObject) {
        	var f=5;
            for (var j=9;j>=6;j--) {
                   try {   
                    var fl=eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash."+j+"');");
                    if (fl) {
                        f=j;
                        break;
                    }
                   }
                   catch(e) {}
            }
            if(f<=6){
            	up=true;
            }
        }
        if(up){
        	return '<div id="flashMessage">您当前的flash版本过低，导致上传功能暂时无法使用，请<a href="http://get.adobe.com/cn/flashplayer/" target="_blank">点击这里</a>进行升级。</div>';
        }
        else{
        	return false;
        }
	}
})(jQuery);