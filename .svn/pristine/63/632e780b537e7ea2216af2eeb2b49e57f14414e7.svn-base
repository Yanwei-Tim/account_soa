<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<a href="javascript:;" class="portlet_roll_left"></a>
	<a href="javascript:;" class="portlet_roll_right"></a>
	<div class="portlet_box_list clearfix">
		<s:iterator value="myVisits" var="p">
			<div class="portlet_box" id="${p.eName}" >
			<div class="portlet_box_person">
				<a href="javascript:;"><img src="/resource/workBench/images/icon/${p.eName}.png"/></a>
			</div>
			<div class="portlet_box_personData">
				<a href="javascript:;" class="number">${p.amount}</a>
				<div class="downSorrow"></div>
			</div>
			<p class="portlet_box_title">
				<a href="javascript:;">
					${p.cName}
				</a>
			</p>
			</div>
		</s:iterator>
	</div>
	<div class="portlet_object">
	</div>