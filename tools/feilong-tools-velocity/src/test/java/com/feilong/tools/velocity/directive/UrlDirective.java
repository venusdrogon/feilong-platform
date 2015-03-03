package com.feilong.tools.velocity.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.view.ViewToolContext;

//import org.tuckey.web.filters.urlrewrite.UrlRewriteWrappedResponse;

@SuppressWarnings("all")
public class UrlDirective extends AbstractDirective1{

	@Override
	protected boolean doRender(InternalContextAdapter internalContext,ViewToolContext context,Writer writer,Node node) throws IOException,
					ResourceNotFoundException,ParseErrorException,MethodInvocationException{
		// get url
		SimpleNode sn = (SimpleNode) node.jjtGetChild(0);
		String url = (String) sn.value(internalContext);

		// get context path
		String contextPath = null;
		//= context.getRequest().getContextPath();
		if ("/".equals(contextPath)){
			contextPath = "";
		}

		// if (context.getResponse() instanceof UrlRewriteWrappedResponse){
		// url = context.getResponse().encodeURL(url);
		// }else{
		// url = context + url;
		// }

		url = context + url;

		writer.write(url);

		return true;
	}

	@Override
	public String getName(){
		return "url";
	}

	@Override
	public int getType(){
		return LINE;
	}

}