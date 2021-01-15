package com.snapmine.SnapMineApi.middleware;

import com.google.gson.Gson;
import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.exception.ApiRequestException;
import com.snapmine.SnapMineApi.model.dtos.response.AuthResponse;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.service.SecurityService;
import com.snapmine.SnapMineApi.util.PathParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Component
public class SecuredFilterHandlerInterceptor
 implements HandlerInterceptor {

	final SecurityService securityService;
	Gson gson = new Gson();

	@Autowired
	public SecuredFilterHandlerInterceptor(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod) {
			Secured filter = ((HandlerMethod) (handler)).getMethod().getAnnotation(Secured.class);
			if (filter == null)
				return true;
//				String hashedToken = request.getHeader("token");
//				response.setContentType("application/json");
//				response.setCharacterEncoding("UTF-8");
//				if(hashedToken==null) {
//					response.setStatus(401);
//					response.getWriter().write("{ \"message\" : \"Api requires a token.\"}");
//					return false;

			List<String> roles = Arrays.asList(filter.roles());
			String hashedToken = request.getHeader("token");
			if (hashedToken == null)
				throw new ApiRequestException("This request requires a token.", 401);

			SessionToken token = securityService.validateToken(hashedToken);

			String uri = request.getRequestURI();
			int[] scope = PathParser.findNSlash(uri, filter.idPos());
			System.out.println(uri);
			System.out.println(Arrays.toString(scope));
			System.out.println(uri.substring(scope[0]+1,scope[1]));
//			if(token.getClientID() == id)
//				return true;

			boolean hasRoles =
					(token.getRoles().stream().anyMatch(r -> (roles).contains(r.getName())));
			if (hasRoles)
				return true;
			if(filter.idPos() == -1)
				throw new ApiRequestException("No authorization.",401);



		}
		return true;
	}

}

