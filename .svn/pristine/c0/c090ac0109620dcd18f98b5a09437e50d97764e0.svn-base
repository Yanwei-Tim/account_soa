<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<input type="hidden" id="contextId" value='<s:property value="@java.util.UUID@randomUUID().toString()"/>'></input>
<div class="dlgBox">
	<div class="dlgLeft">
		<div class="dlgHendBox">
				<jsp:include page="/common/header.jsp"/>
		</div>
		
		<ul class="dlgLeftMenu">
		</ul>
	</div>
	<div class="dlgRightAdd" >
		<div class="ui-widget-overlay dlgUrlBoxLoading">
			<div class="loadingCtt"><img src="/resource/images/dlgloading1.gif" /><div>正在加载中，请稍候…</div></div>
		</div>
		<div class="dlgUrlBox" style="height:480px;overflow-y:auto; overflow-x:hidden;">
			
		</div>
		<div class="clear"></div>
		<div class="dlgBtnList"></div>
		<div class="clear"></div>
		
	    <div class="pane clearfix">
	      <img src="/resource/js/ui/default/menu.gif" alt="Loulou form Sos Chats Geneva" />
	      <form action="">
	      <div class="upTreatedAvatar"><input id="upTreatedAvatar" type="submit" value="确定上传"></div>
	      <table class="coords">
	        <tr><td>crop x</td><td><input type="text"  /></td></tr>
	        <tr><td>crop y</td><td><input type="text" /></td></tr>
	        <tr><td>crop width</td><td><input type="text" /></td></tr>
	        <tr><td>crop height</td><td><input type="text" /></td></tr>
	        <tr><td>image width</td><td><input type="text" /></td></tr>
	        <tr><td>image height</td><td><input type="text" /></td></tr>
	        <tr><td>lock proportion</td><td><input type="checkbox" checked="checked" /></td></tr>
	      </table>
	      </form>
	    </div>
	    <!-- End pane -->
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$(".dlgUrlBox").scroll(function(){
		$(".tip-error").remove();
		$(".tip-yellowsimple").remove();
		$("*[createMsg='true']").attr("createMsg","false");
	});
});
</script>
