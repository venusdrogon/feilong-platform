//package com.feilong.spring.web.servlet.handler;
//
//import java.io.IOException;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
//
//import com.feilong.servlet.http.RequestUtil;
//
//public class ExceptionResolver extends SimpleMappingExceptionResolver{
//
//    /**
//     * business_exception_ 错误码的前缀
//     */
//    public static final String BUSINESS_EXCEPTION_PREFIX = "business_exception_";
//
//    @Resource
//    private ApplicationContext applicationContext;
//
//    @Override
//    protected ModelAndView getModelAndView(String viewName,Exception exception,HttpServletRequest request){
//        if (exception instanceof BusinessException){
//            BusinessException businessException = (BusinessException) exception;
//            exception = encodeBusinessException(businessException);
//        }
//        if (RequestUtil.isAjaxRequest(request)){
//            return getModelAndView("json", exception);
//        }else{
//            return null;
//        }
//        //return super.getModelAndView(viewName, exception, request);
//    }
//
//    @Override
//    protected ModelAndView doResolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
//
//        // Expose ModelAndView for chosen error view.
//        String viewName = determineViewName(ex, request);
//        if (viewName != null){
//            // Apply HTTP status code for error views, if specified.
//            // Only apply it if we're processing a top-level request.
//            Integer statusCode = determineStatusCode(request, viewName);
//            if (statusCode != null){
//                applyStatusCodeIfPossible(request, response, statusCode);
//            }
//            ModelAndView mView = getModelAndView(viewName, ex, request);
//            if (mView != null)
//                return mView;
//        }
//        try{
//            response.sendError(500);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private BusinessException encodeBusinessException(BusinessException businessException){
//        int errorCode = businessException.getErrorCode();
//        String key = BUSINESS_EXCEPTION_PREFIX + errorCode;
//
//        Object[] args = businessException.getArgs();
//        BusinessException linkedException = businessException.getLinkedException();
//
//        String message = applicationContext.getMessage(key, args, key, LocaleContextHolder.getLocale());
//
//        BusinessException result = new BusinessException(errorCode, message);
//        result.setArgs(args);
//
//        if (linkedException != null){
//            result.setLinkedException(encodeBusinessException(linkedException));
//        }
//        return result;
//    }
//}
