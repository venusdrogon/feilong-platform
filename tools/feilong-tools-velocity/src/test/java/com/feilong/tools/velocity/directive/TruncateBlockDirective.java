package com.feilong.tools.velocity.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

@SuppressWarnings("all")
public class TruncateBlockDirective extends Directive{

    private Log     log;

    private int     maxLength;

    private String  suffix;

    private Boolean truncateAtWord;

    @Override
    public String getName(){
        return "truncateBlock";
    }

    @Override
    public int getType(){
        return BLOCK;
    }

    @Override
    public void init(RuntimeServices rs,InternalContextAdapter context,Node node) throws TemplateInitException{
        super.init(rs, context, node);
        log = rs.getLog();

        // read dafault values from config
        maxLength = rs.getInt("userdirective.truncateBlock.maxLength", 10);
        suffix = rs.getString("userdirective.truncateBlock.suffix", "...");
        truncateAtWord = rs.getBoolean("userdirective.truncateBlock.truncateAtWord", false);

    }

    @Override
    public boolean render(InternalContextAdapter context,Writer writer,Node node) throws IOException,ResourceNotFoundException,
                    ParseErrorException,MethodInvocationException{

        log.debug("truncateBlock directive render() call");

        String truncateMe = null;

        // default settings
        int maxLength = this.maxLength;
        String suffix = this.suffix;
        Boolean truncateAtWord = this.truncateAtWord;

        // loop through all "params"
        for (int i = 0; i < node.jjtGetNumChildren(); i++){
            if (node.jjtGetChild(i) != null){
                if (!(node.jjtGetChild(i) instanceof ASTBlock)){
                    // reading and casting inline parameters
                    if (i == 0){
                        maxLength = (Integer) node.jjtGetChild(i).value(context);
                    }else if (i == 1){
                        suffix = String.valueOf(node.jjtGetChild(i).value(context));
                    }else if (i == 2){
                        truncateAtWord = (Boolean) node.jjtGetChild(i).value(context);
                    }else{
                        break;
                    }
                }else{
                    // reading block content and rendering it
                    StringWriter blockContent = new StringWriter();
                    node.jjtGetChild(i).render(context, blockContent);

                    truncateMe = blockContent.toString();
                    break;
                }
            }
        }

        // truncate and write result to writer
        try{
            writer.write(truncate(truncateMe, maxLength, suffix, truncateAtWord));
        }catch (Exception e){
            String msg = "Truncate failed";
            log.error(msg, e);
            throw new RuntimeException(msg, e);

        }
        return true;

    }

    // does actual truncating (taken directly from DisplayTools)
    public String truncate(String truncateMe,int maxLength,String suffix,boolean truncateAtWord){
        if (truncateMe == null || maxLength <= 0){
            return null;
        }

        if (truncateMe.length() <= maxLength){
            return truncateMe;
        }
        if (suffix == null || maxLength - suffix.length() <= 0){
            // either no need or no room for suffix
            return truncateMe.substring(0, maxLength);
        }
        if (truncateAtWord){
            // find the latest space within maxLength
            int lastSpace = truncateMe.substring(0, maxLength - suffix.length() + 1).lastIndexOf(" ");
            if (lastSpace > suffix.length()){
                return truncateMe.substring(0, lastSpace) + suffix;
            }
        }
        // truncate to exact character and append suffix
        return truncateMe.substring(0, maxLength - suffix.length()) + suffix;

    }

}
