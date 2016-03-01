<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<style type="text/css">
.imageC pre img{width:200px;height:200px;}

</style>
<table class="tablelist">
	<tr>
		<td class="title"><label>区 域</label></td>
		<td colspan="3" class="content">${publicNotice.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>标 题</label></td>
		<td colspan="3" class="content" >
			<span id="">${publicNotice.publicNoticeTitle}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>编辑者</label></td>
		<td class="content"><span>${publicNotice.noticeEditor }</span></td>
		<td class="title"><label>编辑时间</label></td>
		<td class="content"><span><s:date
					name="publicNotice.editorDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
		<td class="title">内 容</td>
		<td  colspan="3" class="content" style="padding-top: 8px; padding-bottom: 5px;">
			<span id="" class="imageC"><pre>${publicNotice.publicNoticeMatter}</pre></span>
		</td>
	</tr>
	<s:if test='publicNotice.isAttachment'>
	<tr>
	 <td class="title">附件</td>
	 <td class="content" colspan="3">
		 <s:iterator value="publicNotice.noticeFiles" var="obj">
			<a href="/workBench/noticeManage/downLoadActualNoticeFile.action?fileId=${obj.id}" target="_blank">
				<img  src="/resource/images/fujian.jpg"/>${obj.fileName}
			</a>
		</s:iterator>
	 </td>
	</tr>
	</s:if>
	<tr>
		<td class="title">截止日期</td>
		<s:if test='publicNotice.overdueDate == null'>
			<td class="content" colspan="3">不限</td>
		</s:if>
		<s:else>
			<td class="content" colspan="3">
				<s:date	name="publicNotice.overdueDate" format="yyyy-MM-dd" />
			</td>
		</s:else>
	</tr>
</table>

