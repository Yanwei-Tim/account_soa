<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:iterator value="moduelClicks" var="tab">
		<div class="portlet_box portlet_boxPop" id="portlet_box">
			<div class="portlet_box_person">
				<a href="${tab.url}" rel="${tab.rel}"><img src="${tab.imgUrl}"/></a>
			</div>
			<p class="portlet_box_title"><a href="${tab.url}" rel="${tab.rel}">${tab.permission.cname}</a></p>
		</div>
	</s:iterator>
<script type="text/javascript">
$(function() {
	$("#portlet_box a").click(function(){
		var rel=$(this).attr("rel");
		showPageByTopMenu(rel);
	})
});
</script>