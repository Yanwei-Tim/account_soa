$.raphael=function(options){
		var self=this;
		var dfop={
			id:'',
			width:'100%',
			height:400,
			baseHeight:100,
			baseWidth:100,
			url:'',
			/*data:[
				{id:1,name:'网格办公室',orgLevelInternalId:0,relation:'上报',to:2,states:['加急','红牌督办','黄牌督办','普通督办','蓝牌督办']},
				{id:2,name:'村办公室',orgLevelInternalId:10,relation:'上报',to:3,states:['加急','红牌督办']},
				{id:3,name:'镇、街道办公室1',orgLevelInternalId:20,relation:'上报',to:4,states:['加急','红牌督办']},
				{id:4,name:'区办公室1',orgLevelInternalId:30,relation:'上报',to:5,states:['加急','红牌督办']},
				{id:5,name:'市委办公室1',orgLevelInternalId:40,relation:'交办',to:7,states:['加急','红牌督办']},
				{id:7,name:'市委办公室2',orgLevelInternalId:40,relation:'交办',to:8,states:['加急','红牌督办']},
				{id:8,name:'市委办公室3',orgLevelInternalId:40,relation:'交办',to:9,states:['加急','红牌督办']},
				{id:9,name:'区办公室2',orgLevelInternalId:30,relation:'回退',to:10,states:['加急','红牌督办']},
				{id:10,name:'市委办公室3',orgLevelInternalId:40,relation:'回退',to:11,states:['加急','红牌督办']},
				{id:11,name:'区办公室2',orgLevelInternalId:30,relation:'办结',states:['加急','红牌督办']}
			],*/
			boxHeight:35,//节点高
			boxWidth:100,//节点宽
			boxIntervalHeight:100,//节点上下边距
			boxIntervalWidth:150,//节点左右边距
			nodeClick:function(data){
				//alert("node");
			},
			relationClick:function(data){
				//alert("relation");
			},
			nodeMouseover:function(data){
				
			}
		}
		$.extend(dfop,options);
		var that=new Raphael(dfop.id, dfop.width,dfop.height);
		var nodeSet=that.set();
		var imageSet=that.set();
		var relationSet=that.set();
		var textSet=that.set();
		var color = Raphael.getColor();
		that.connections=[];
		that.popups=[];
		that.dragger = function () {
			this.ox = this.type == "rect" ? this.attr("x") : this.attr("cx");
			this.oy = this.type == "rect" ? this.attr("y") : this.attr("cy");
			this.animate({"fill-opacity": .2}, 500);
			
		};
		that.move = function (dx, dy) {
			var att = this.type == "rect" ? {x: this.ox + dx, y: this.oy + dy} : {cx: this.ox + dx, cy: this.oy + dy};
			var thatAttrs=this.attrs;
			var linkText=that.getById("text"+this.id);
			var motionText=that.getById("motion"+this.id);
			this.attr(att);
			if(linkText){
				var thisAtt={
					x:att.x+thatAttrs.width/2,
					y:att.y+thatAttrs.height/2
				};
				$.extend(true,att,thisAtt);
				linkText.attr(att);
			}
			for (var i = that.connections.length; i--;) {
				that.connection(that.connections[i]);
			}
			that.safari();
		};
		that.up = function () {
			this.animate({"fill-opacity": 0}, 500);
		};
		that.PopData=[];
		that.popup=function(options){
			var dfop={
				top:0,
				left:0,
				url:''
			};
			$.extend(dfop,options);
			var build=function(){
				var $dom;
				var buildFlag=true;
				if($("#holderBoxPop")[0]){
					$dom=$("#holderBoxPop");
				}else{
					$dom=$('<div class="raphaelTips" id="holderBoxPop"><a href="javascript:;" class="tip-close">关闭</a><div class="raphaelTips-text"></div><div class="raphaelTips-angle diamond"></div></div>');
					$dom.find(".tip-close").click(function(){
						$("#holderBoxPop").fadeOut(300);
					})
					$dom.appendTo("body");
				}
				var $popContent=$("#holderBoxPop").find(".raphaelTips-text").empty();;
				var thisData;
				var opts = {
				  lines: 10,
				  length: 10,
				  width: 5,
				  radius: 5,
				  corners: 1, 
				  rotate: 0, 
				  direction: 1,
				  color: '#999',
				  speed: 1,
				  trail: 100,
				  shadow: false,
				  hwaccel: false,
				  className: 'spinner',
				  zIndex: 2e9,
				  top: 'auto',
				  left: 'auto' 
				};
				$("#holderBoxPop").show().animate({
					top:$(dfop.thatDom[0]).offset().top-dfop.thatDom.attrs.height-160,
					left:$(dfop.thatDom[0]).offset().left+dfop.thatDom.attrs.width/2-132
				},50);
				for(var i=0;i<that.PopData.length;i++){
					if(that.PopData[i].id==dfop.thatDom.id || that.PopData[i].id=="text"+dfop.thatDom.id){
						thisData=that.PopData[i];
						buildFlag=false;
					}
				}
				if(dfop.url!='' && buildFlag==true){
					var spinner = new Spinner(opts).spin($popContent[0]);
					$.ajax({
						url:dfop.url,
						success:function(data){
							$popContent.html(data);
							thisData={id:dfop.thatDom.id,data:''}
							thisData.data=data;
							that.PopData.push(thisData);
						}
					})
				}else{
					$popContent.html(thisData.data);
				}
				$(".issueRight").scroll(function(){
					$("#holderBoxPop").hide();
				})
			}()
		}
		that.connection=function (obj1, obj2, line, bg,thisRelation,triangle) {
			if (obj1.line && obj1.from && obj1.to) {
				line = obj1;
				obj1 = line.from;
				obj2 = line.to;
			}
			var bb1 = obj1.getBBox(),
				bb2 = obj2.getBBox(),
				p = [{x: bb1.x + bb1.width / 2, y: bb1.y - 1},
				{x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1},
				{x: bb1.x - 1, y: bb1.y + bb1.height / 2},
				{x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2},
				{x: bb2.x + bb2.width / 2, y: bb2.y - 1},
				{x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1},
				{x: bb2.x - 1, y: bb2.y + bb2.height / 2},
				{x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2}],
				d = {}, dis = [];
			for (var i = 0; i < 4; i++) {
				for (var j = 4; j < 8; j++) {
					var dx = Math.abs(p[i].x - p[j].x),
						dy = Math.abs(p[i].y - p[j].y);
					if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
						dis.push(dx + dy);
						d[dis[dis.length - 1]] = [i, j];
					}
				}
			}
			if (dis.length == 0) {
				var res = [0, 4];
			} else {
				res = d[Math.min.apply(Math, dis)];
			}
			var x1 = p[res[0]].x,
				y1 = p[res[0]].y,
				x4 = p[res[1]].x,
				y4 = p[res[1]].y;
			dx = Math.max(Math.abs(x1 - x4) / 2, 10);
			dy = Math.max(Math.abs(y1 - y4) / 2, 10);
			var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
				y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
				x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
				y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
			var arrow=function (l, k, d) {
	            var g = Math.atan2(l.y - k.y, k.x - l.x) * (180 / Math.PI);
	            var h = k.x - d * Math.cos(g * (Math.PI / 180));
	            var f = k.y + d * Math.sin(g * (Math.PI / 180));
	            var e = h + d * Math.cos((g + 120) * (Math.PI / 180));
	            var j = f - d * Math.sin((g + 120) * (Math.PI / 180));
	            var c = h + d * Math.cos((g + 240) * (Math.PI / 180));
	            var i = f - d * Math.sin((g + 240) * (Math.PI / 180));
	            return "M" + k.x + " " + k.y + "L" + e + " " + j + "L" + c + " " + i + "z";
	        }
	        var path = ["M", x1.toFixed(3), y1.toFixed(3), "L", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
			if (line && line.line) {
				line.bg && line.bg.attr({path: path});
				line.line.attr({path: path});
				if(line.relation){//add motion
					line.relation.attr({
						x:(x1+x4)/2,
						y:(y1+y4)/2
					})
				}
			} else {
				var arrayPath=arrow({x:x3,y:y3},{x:x4,y:y4},5);
				var connection={
					line:this.path(path).attr({'stroke-width':1,fill:'none',stroke: line}),
					from: obj1,
					to: obj2,
					array:this.path(arrayPath).attr({stroke:line,fill: line})
				}
				if(thisRelation){//init motion
					connection.relation=thisRelation.attr({
						x:(x1+x4)/2,
						y:(y1+y4)/2
					})
				}
				if(that.connections != undefined){
					that.connections[that.connections.length]=connection;
				}else{
					that.connections[0]=connection;
				}
				return connection;
			}
		};//连线
		var createNode=function(id,x, y, width, height,r){
			var thatRect=that.rect(x, y, width, height,r).attr({fill: '90-#FFE084:10-#FFF4D2', "stroke-width":1,stroke:'#FFD65F', cursor: "hand"}).mouseover(function(){//绑定指向事件
				if(this.glows!=undefined){
					this.glows.show();
				}else{
					this.glows=this.glow({color:'#FFD65F',width:7});
				}
			}).mouseout(function(){//绑定鼠标移出事件
				this.glows.hide();
			});
			thatRect.id=id;
			nodeSet.push(thatRect);
			return thatRect;
		};
		var createText=function(id,x,y,name){
			var thatText=that.text(x, y, name).attr({font: 'bold 14px "microsoft yahei", Arial',fill: '#B22D00',cursor:'hand'}).mouseover(function(){//绑定指向事件
				var thisDate={
					id:this.id,
					type:this.nodeType
				}
				if(that.getById(this.nodeId).type=='rect'){
					if(this.glows!=undefined){
						this.glows.show();
					}else{
						this.glows=that.getById(this.nodeId).glow({color:'#FFD65F',width:7});
					}
				}
				clearTimeout(window._holderPopTimerOut);
			}).mouseout(function(){//绑定鼠标移出事件
				var thisDate={
					id:this.id,
					type:this.nodeType
				}
				if(that.getById(this.nodeId).type=='rect'){
					this.glows.hide();
				}
			});
			thatText.id="text"+id;
			thatText.nodeId=id;
			textSet.push(thatText);
			return thatText;
		};
		var createRelation=function(id,x,y,name){
			var thatRelation=that.text(x, y, name).attr({font:'normal 14px "microsoft yahei"', fill: "#333","stroke-linejoin": "round",cursor:'hand'})
			//thatMotion.id="motion"+id;
			relationSet.push(thatRelation);
			return thatRelation;
		};
		var createImage=function(src,x,y,w,h){
			var thatImage=that.image(src,x, y,w,h);
			
			//thatMotion.id="motion"+id;
			imageSet.push(thatImage);
			return thatImage;
		};
		var calculateIndex=function(data){
			var index=1;
			var maxIndex=1;
			function conculationJiaoBan(data,i){
				if(data[i-1].orgLevelInternalId==data[i].orgLevelInternalId){
					return data[i-1].index+1;
				}
				if(data[i-1].orgLevelInternalId>data[i].orgLevelInternalId){
					if(!data[i-2]||data[i-2].orgLevelInternalId>=data[i-1].orgLevelInternalId){
						return data[i-1].index;
					}else {
						return data[i-1].index+1;
					}
				}
			}
			function conculationHuitui(data,i){
				return data[i-1].index+1;	
			}
			function conculationShangbao(data,i){
				if(data[i-2]&&data[i-2].orgLevelInternalId>data[i-1].orgLevelInternalId){
					return data[i-1].index+1;
				}else{
					return data[i-1].index;
				}
			}
			for(var i=0;i<data.length;i++){
				if(i==0){
					data[i].index=index;
				}
				if(i!=0){
					if(data[i-1].relation=='上报'){
						index=conculationShangbao(data,i)
						data[i].index=index;
					}
					if(data[i-1].relation=='交办'){
						index=conculationJiaoBan(data,i);
						data[i].index=index;
					}
					if(data[i-1].relation=='回退'){
						index=conculationHuitui(data,i);
						data[i].index=index;
					}
				}
			}
			return data;
		}
		var createNodes=function(holder,thisData,maxOrgLevel){
			var x=(thisData.index-1)*dfop.boxIntervalWidth+10;
			var y=(maxOrgLevel/10-(thisData.orgLevelInternalId/10))*dfop.boxIntervalHeight+10;
			var box=createNode(thisData.id,x, y, dfop.boxWidth, dfop.boxHeight, 3).click(function(){
				dfop.nodeClick(thisData);
			}).mouseover(function(){
				var that=this;
				holder.popup({thatDom:that,url:"/issues/issueManage/viewIssueDealLog.action?issueMap.id="+thisData.id+"&issueMap.orgId="+thisData.orgId});
				$("#holderBoxPop").unbind("mouseover").mouseover(function(){
					clearTimeout(window._holderPopTimerOut);
				}).unbind("mouseout").mouseout(function(){
					window._holderPopTimerOut=setTimeout(function(){
						$("#holderBoxPop").hide();
					},500);
				})
			}).mouseout(function(){
				window._holderPopTimerOut=setTimeout(function(){
					$("#holderBoxPop").hide();
				},300);
			})
			var thisName=createText(thisData.id,x+dfop.boxWidth/2,y+dfop.boxHeight/2,thisData.name).click(function(){
				dfop.nodeClick(thisData);
			})
			$.extend(true,box,{//遍历box对象
				text:thisData.text,
				orgLevelInternalId:thisData.orgLevelInternalId,
				index:thisData.index
			})
			return box;
		}
		var createState=function(holder,thisData){
			var thisBox=holder.getById(thisData.id);
			var toBox=holder.getById(thisData.to);
			var x=thisBox.attr("x");
			var y=thisBox.attr("y");
			
			var boxWidth=thisBox.attrs.width;
			var boxHeight=thisBox.attrs.height;
			var boxInterval=thisBox.attrs.interval;
			var buildState=function(id,x,y,name){
				var iconLength=0;
				for(var i=0;i<thisData.states.length;i++){//构建状态
					var iconW=14;
					var iconH=16;
					var thisX=x+boxWidth/2-iconW*(iconLength+1)-5*iconLength;
					var thisY=y+boxHeight/2-iconH/2;
					switch(thisData.states[i]){
						case '加急':
							createImage('/resource/system/images/issue/icon_Emerignce.png',thisX,thisY,12,12);
							iconLength++;
							break;
						case '普通督办':
							createImage('/resource/system/images/issue/icon_bHandle.png',thisX,thisY,iconW,iconH);
							iconLength++;
							break;
						case '黄牌督办':
							createImage('/resource/system/images/issue/icon_yHandle.png',thisX,thisY,iconW,iconH);
							iconLength++;
							break;
						case '红牌督办':
							createImage('/resource/system/images/issue/icon_rHandle.png',thisX,thisY,iconW,iconH);
							iconLength++;
							break;
						case '蓝牌督办':
							createImage('/resource/system/images/issue/icon_rHandle.png',thisX,thisY,iconW,iconH);
							iconLength++;
							break;
					}
				}
			}
			var buildMotion=function(id,x,y,motion){
				var thisRelation=createRelation(id,x,y,motion);
				return thisRelation;
			}
			
			buildState(thisData.id,x+boxWidth/2,y+boxHeight/2,thisData.name)
			if(thisData.id && thisData.to){//连线
				var toBox=holder.getById(thisData.to);
				var toX=toBox.attr("x");
				var toY=toBox.attr("y");
				var centerX=(x+toX)/2+boxWidth/2;
				var centerY=(y+toY)/2+boxHeight/2;
				var thisRelation=buildMotion(thisData.id,centerX,centerY,thisData.relation).click(function(){
					dfop.relationClick(thisData);
				});
				holder.connection(thisBox,toBox,"#888",'',thisRelation,true);
			}
		}
		function buildNodes(data){
			var maxOrgLevel=0;
			var minOrgLevel=60;
			var maxIndex=1;
			for(var i=0;i<data.length;i++){
				if(maxOrgLevel<data[i].orgLevelInternalId){
					maxOrgLevel=data[i].orgLevelInternalId;
				}
				if(minOrgLevel>data[i].orgLevelInternalId){
					minOrgLevel=data[i].orgLevelInternalId;
				}
			}
			for(var i=0;i<data.length;i++){
				createNodes(that,data[i],maxOrgLevel);
				if(maxIndex<data[i].index){
					maxIndex=data[i].index;
				}
			}
			$(that.canvas).width(maxIndex*(dfop.boxWidth+dfop.boxIntervalWidth/2)).height((maxOrgLevel-minOrgLevel)/10*dfop.boxIntervalHeight+dfop.boxHeight*2);//设定画布宽高
			for(var i=0;i<data.length;i++){
				createState(that,data[i]);
				
			}
		}
		if(dfop.url!=''){
			$.ajax({
				type:'POST',
				url:dfop.url,
				async:false,
				success:function(data){
					data=calculateIndex(data);
					buildNodes(data);
				}
			})
		}else{
			data=calculateIndex(dfop.data);
			buildNodes(data);
		}
		return that;
	}