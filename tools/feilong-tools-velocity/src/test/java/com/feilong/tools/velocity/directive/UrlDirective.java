/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * The Class UrlDirective.
 */
@SuppressWarnings("all")
public class UrlDirective extends AbstractDirective1{

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.velocity.directive.AbstractDirective1#doRender(org.apache.velocity.context.InternalContextAdapter,
     * org.apache.velocity.tools.view.ViewToolContext, java.io.Writer, org.apache.velocity.runtime.parser.node.Node)
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.velocity.runtime.directive.Directive#getName()
     */
    @Override
    public String getName(){
        return "url";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.velocity.runtime.directive.Directive#getType()
     */
    @Override
    public int getType(){
        return LINE;
    }

}