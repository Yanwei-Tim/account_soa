function getTwinIntStr(v){
	if(v>=10){
		return ""+v;
	}else
		return "0"+v;
}

Date.prototype.toString = function(dateOnly){
	var s = this.getFullYear()
		+ "-" + getTwinIntStr(this.getMonth()+1)
		+ "-" + getTwinIntStr(this.getDate());
	if(dateOnly==true)
		return s;

	s = s + " " + getTwinIntStr(this.getHours())
		+ ":" + getTwinIntStr(this.getMinutes())
		+ ":" + getTwinIntStr(this.getSeconds());
	return s;
}


String.prototype.trim = function () {
	var reg = /(^\s*)|(\s*$)/g;
	return this.replace(reg, "");
}

String.prototype.endWith = function(s){
	var p = this.lastIndexOf(s);
	if(p+s.length==this.length){
		return true;
	}else
		return false;
}

String.prototype.startWith = function(s){
	if(s==null)
		return false;
	var p = this.indexOf(s);
	if(p==0)
		return true;
	else return false;
}

String.prototype.asciiLen = function(){
    var intLength=0
    for (var i=0;i<this.length;i++){
        if ((this.charCodeAt(i) < 0) || (this.charCodeAt(i) > 255))
            intLength=intLength+2
        else
            intLength=intLength+1
    }
    return intLength

}

Array.prototype.indexOf=function(o){
	for(var i=0;i<this.length;i++){
		if(this[i]==o)
			return i;
	}
	return-1;
};

Array.prototype.lastIndexOf=function(o){
	for(var i=this.length-1;i>=0;i--){
		if(this[i]==o)
			return i;
	}
	return-1;
};

Array.prototype.contains=function(o){
	return this.indexOf(o)!= -1;
};

Array.prototype.addAll=function(newArray){
	for(var i=0;i<newArray.length;i++){
		this.push(newArray[i]);
	}
}

Array.prototype.copy=function(o){
	return this.concat();
};

Array.prototype.insertAt=function(o,i){
	this.splice(i,0,o);
};

Array.prototype.insertBefore=function(o,o2){
	var i=this.indexOf(o2);
	if(i== -1)
		this.push(o);
	else
		this.splice(i,0,o);
};

Array.prototype.insertAfter = function(o1,o2){
	var i = this.indexOf(o2);
	this.splice(i,0,o1);
}

Array.prototype.removeAt=function(i){
	this.splice(i,1);
};

Array.prototype.remove=function(o){
	var i=this.indexOf(o);
	if(i!= -1)
		this.splice(i,1);
};



Number.prototype.strValue = function(){
	var b = this.format("#.0000000000");
	var i = b.length-1;
	for(;i>0;i--){
		if(b.charAt(i)!="0"){
			break;
		}
	}
	b = b.substring(0,i);
	if(b.endWith("."))
		b = b.substring(0,b.length-1);
	return b;
}
Number.prototype.format = function(f){
	var n = this;
	f = f.trim();
	if(f==null)	f = "#.00";
	var isPercent = false;
	if(f.endWith("%")){
		isPercent = true;
		n = n*100;
		f = f.substring(0,f.length-1);
	}
	var v ;
	if(f=="#"){
		v = String(Math.round(n));
	}else{
		var c = 0;
		if(f.endWith("#")){
			c = f.length - 3;
			v  = ""+n;
		}else{
			c = f.length - 2;
			var p = Math.pow(10,c);
			var t = p * n;
			t = Math.round(t);
			v = String(t / p);
		}
		if(c>0){
			if(v.indexOf(".")===-1){
				v = v+".0";
			}
			var p = v.indexOf(".")+1;
			if(v.length-p<c){
				var l = c-(v.length-p);
				for(var i = 0;i<l;i++){
					v = v+"0";
				}
			}
		}
	}
	if(isPercent===true){
		v = v+"%";
	}
	return v;
}

Function.prototype.getName = function(){
	var r = ""+this;
	var p = r.indexOf("{");
	r = r.substring(0,p);
	return r;
}

