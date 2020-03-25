package shopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 登录验证的拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("user")!=null)
			//返回true，应用程序将继续执行下一个拦截器或handler
			return true;
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+"/admin/");
			//返回false,应用程序将停止本次请求的执行
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在目标handler执行后,渲染视图之前运行，适用于对视图做修改	
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 在渲染视图之后执行，适用于释放资源	
	}

}
