import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("all")
public class ConcatFilter implements Filter{

	public void destroy(){}

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String getQueryString = req.getQueryString();

		String type = null;
		List<String> resList = new ArrayList<String>();
		if (req.getRequestURI().contains("fonts")){
			chain.doFilter(request, response);
			return;
		}
		if (getQueryString != null && getQueryString.startsWith("?")){
			getQueryString = getQueryString.substring(1);
			String[] reses = getQueryString.split(",");
			for (String res : reses){
				resList.add(req.getRequestURI() + res.split("\\?")[0]);
			}
			for (String res : resList){
				String t = context.getMimeType(res);
				if (t != null){
					if (type == null)
						type = t;
					else if (!type.equals(t)){
						resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
						return;
					}
				}
			}

			if (type != null){
				resp.setContentType(type);
			}
			for (String res : resList){
				RequestDispatcher dispatcher = context.getRequestDispatcher(res);
				if (dispatcher != null){
					dispatcher.include(req, resp);
				}
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException{
		context = filterConfig.getServletContext();
	}

	private ServletContext	context	= null;

}
