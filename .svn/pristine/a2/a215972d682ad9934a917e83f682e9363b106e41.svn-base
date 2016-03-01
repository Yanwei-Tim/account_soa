<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="column" id="" >
		<div >
			<ul>
				<li>智慧社会管理综合信息系统
					<ul>
					<s:iterator value="permissions" var="p">
						<li>${p.cname}
							<ul>
							<s:iterator value="childMap.get(#p.ename)" var="child">
								<li>&nbsp;&nbsp;
								<a href="/module.jsp#${p.ename}-${child.ename}" id="${child.ename}" >
									<span>${child.cname}</span>
								</a>
									<ul>
										<s:iterator value="grandsonMap.get(#child.ename)" var="grandson">
											<li>&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="/module.jsp#${p.ename}-${grandson.ename}" id="${grandson.ename}" >
												<span style="color:red;">${grandson.cname}</span>
												</a>
											</li>
										</s:iterator>
									</ul>
								</li>
							</s:iterator>
							</ul>
						</li>
					</s:iterator>
					</ul>
				</li>
			</ul>
		</div>
</div>
<script>
</script>