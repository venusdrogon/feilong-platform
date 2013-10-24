package com.feilong.taglib.common.middleware;

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.middleware.email.EmailUtil;

/**
 * 邮箱显示其名称及登录路径
 * 
 * @author 金鑫 2010-3-25 下午02:42:49
 */
@Deprecated
public class EmailTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 邮箱
	 */
	private String				email;

	/**
	 * 显示内容 仅支持
	 * 
	 * <pre>
	 * 
	 * chineseName :中文名
	 * loginHref :登录路径 
	 * all	:所有
	 * </pre>
	 * 
	 * 默认all
	 */
	private String				showContent			= "all";

	@Override
	public String writeContent(){
		// if ("all".equals(showContent)){// 所有
		// return EmailUtil.getEmailLoginHrefByEmail(email);
		// }else if ("chineseName".equals(showContent)){// 中文名
		// return EmailUtil.getEmailChineseName(email);
		// }else if ("loginHref".equals(showContent)){// 登录路径
		// return EmailUtil.getEmailLoginHrefByEmail(email);
		// }
		return "";
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * @param showContent
	 *            the showContent to set
	 */
	public void setShowContent(String showContent){
		this.showContent = showContent;
	}
}
