<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wychuan.dbtools.common.ModelViewUtils"%>
<%@ page import="com.wychuan.dbtools.model.CodeResult"%>
<%@ page import="com.wychuan.dbtools.model.CodeFileItem" %>
<%@ page import="com.wychuan.dbtools.model.TableViewModel" %>
<%
	System.out.println("aaaaaa");
	TableViewModel viewmodel = ModelViewUtils.getData(request);
	CodeResult model = (CodeResult) viewmodel.getCodeResult();
	String preClass = "brush:csharp";
	if(!CodeResult.empty.equals(model)){
		switch (model.getLanguage()) {
		case CSharp:
			preClass = "brush:csharp";
			break;
		case Java:
			preClass = "brush:java";
			break;
		default:
			preClass = "brush:csharp";
			break;
		}
	}
	System.out.println(preClass);
%>

<%if(!CodeResult.empty.equals(model)){%>
	<div class="tabbable tabs-left">
		<ul class="nav nav-tabs">
			<%if(model.getCodes()!=null){
				int index = 1;
				for(CodeFileItem code : model.getCodes()){%>
					<li <%=index++==1?"class=active":""%>>
						<a href="#<%=code.getId() %>" data-toggle="tab"><%=code.getTabText() %></a>
					</li>
				<%}
			} %>
		</ul>
		<div class="tab-content">
			<%if(model.getCodes()!=null){
				int tabindex = 1;
				for(CodeFileItem code : model.getCodes()){
					if("mybatis".equals(code.getId())){
						preClass = "brush:xml";
					}
					String strClass = tabindex++==1 ? "active": "";
					%>
					<div class="tab-pane <%=strClass%>" id="<%=code.getId() %>">
						<pre name="<%=code.getId() %>" class="<%=preClass %>">
							<%=code.getCode() %>
						</pre>
					</div>
				<%}
			}%>
		</div>
	</div>
<%}%>