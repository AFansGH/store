package com.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.channels.SeekableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.store.domain.Order;
import com.store.domain.Orderitem;
import com.store.domain.Product;
import com.store.domain.User;
import com.store.service.LoginService;
import com.store.service.OrderService;
import com.store.service.RegisterService;
import com.store.service.UserService;
import com.store.utils.MailUtils;

public class UserCentreServlet extends BaseServlet {
	
	//我的订单
	public void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//要查看我的订单应该先登陆，所以先看看session中有没有user数据
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(null == user){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
	
		//进入我的订单页面首先需要准备数据首先拿所有的订单，由数据库中获取
		OrderService orderService = new OrderService();
		String uid = user.getUid();
		List<Order> orders = orderService.findAllOrders(uid);
		if(null != orders){
			//拿到每个订单的数据，但是在页面需要每个订单项的数据，所以我们需要对每个订单项的数据再进行封装
			for(Order ord : orders){
				
				//拿着oid去数据库查每个订单项的数据再放到本订单中,这里面临的问题在于查询一个订单项的表数据并不能满足页面对于数据显示的要求
				//因为页面还需要的到订单项中产品的数据，所以我们这里引入多表的联结操作，一次查询两张表的数据，再返回一个map对数据进行封装
				
				//这里拿到了查询到的多表中的数据，使用键值对的形式储存，且所有行都被封装到list中
				List<Map<String,Object>> orderitemListInfo = orderService.findAllOrderItem(ord.getOid());
				//拿到每一行的数据，一行其实就是一个订单项数据
				for(Map<String,Object> orderitemInfo : orderitemListInfo){
					//封装订单项中需要的数据
					Orderitem orderitem = new Orderitem();
					try {
						BeanUtils.populate(orderitem, orderitemInfo);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					//封装订单项中产品需要的数据
					Product product = new Product();
					try {
						BeanUtils.populate(product, orderitemInfo);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					//把封装好的产品数据放到订单项中
					orderitem.setPro(product);
					//把订单项放到订单中，直到放完这个订单的所有订单项
					ord.getOrderitemList().add(orderitem);
					
				}
				
			}
			//把准备好的数据给request传到前台
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		}
		
	}
	
	
	
	

	//用户退出登陆的方法
	public void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//用户退出登录，我们把session中的数据删掉
		session.removeAttribute("user");
		//再把存到用户本地的cookie也拿掉，否则经过过滤器时会自动登陆
		Cookie username = new Cookie("username","");
		Cookie password = new Cookie("password","");
		username.setMaxAge(0);
		password.setMaxAge(0);
		username.setPath(request.getContextPath());
		password.setPath(request.getContextPath());
		response.addCookie(username);
		response.addCookie(password);
		
		response.sendRedirect(request.getContextPath()+"/productCentre?method=index");
	}

	// 用于用户激活的servlet
	public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		UserService userService = new UserService();
		boolean flag = userService.active(code);
		response.setContentType("text/html;Charset=UTF-8");
		if (flag) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			response.getWriter().write("激活失败！");
		}
	}

	// 检查重复名称的方法
	public void isExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserService userservice = new UserService();
		boolean falg = userservice.checkUser(username);
		String json = "{\"flag\":" + falg + "}";
		response.getWriter().write(json);
	}

	// 登陆的方法
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 先拿验证码与session中的验证码做对比，验证码不正确就直接转发回去并且加上提示信息
		String checkCode = request.getParameter("checkCode");
		HttpSession session = request.getSession();
	
		String sessionCheckCode = (String) session.getAttribute("checkcode_session"); // 存到域中的都为object
		// 如果验证码不为空且与存的验证码一致，则进行下一步判断（非空判断前台防君子不防小人）
		if (checkCode != null && checkCode.equals(sessionCheckCode)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			LoginService loginService = new LoginService();
			
			//这里应该让返回一个User对象
			User user = loginService.login(username, password);
			// 如果查到就重定向到header
			if (null != user) {
				session.setAttribute("user",user);
				//检查用户是不是点击了自动登陆
				String autoLogin = request.getParameter("autoLogin");
				//如果点击了就把用户信息放到cookie中
				if(autoLogin != null){
					//因为cookie不支持中文，所以将用户名编码在写到cookie里，在filter中对编过码的cookie再解码
					String username_code = URLEncoder.encode(username, "UTF-8");
					//创建Cookie
					Cookie username_cookie = new Cookie("username",username_code );
					Cookie password_cookie = new Cookie("password",password );
					//设置存在时间 参数为秒
					username_cookie.setMaxAge(100);
					password_cookie.setMaxAge(100);
					//设置路径
					username_cookie.setPath(request.getContextPath());
					password_cookie.setPath(request.getContextPath());
					//添加cookie
					response.addCookie(password_cookie);
					response.addCookie(username_cookie);
				}
				//检查用户是不是记住用户名
				String remberUsername = request.getParameter("remberUsername");
				if(remberUsername != null){
					String username_code = URLEncoder.encode(username, "UTF-8");
					Cookie username_cookie = new Cookie("username",username_code );
					username_cookie.setMaxAge(100);
					username_cookie.setPath(request.getContextPath());
					response.addCookie(username_cookie);
				}
				
				response.sendRedirect(request.getContextPath() + "/productCentre?method=index");
			} else {
				request.setAttribute("loginError", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		} else {
			request.setAttribute("checkCodeEror", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	// 用户注册的方法
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 拿到用户输入的验证码
		String checkCode = request.getParameter("checked");
		// 拿到我在session中存的验证码
		String sessionCheck = request.getSession().getAttribute("checkcode_session").toString();
		// 验证码相同才开始封装数据
		if (checkCode.equals(sessionCheck)) {

			// 拿到用户输入的数据，使用工具类Beanutil封装数据
			Map<String, String[]> properties = request.getParameterMap();
			User user = new User();
			try {
				// 将String类型数据转为date类型，这是一个转换器，需要写在populate的前面
				ConvertUtils.register(new Converter() {
					@Override
					public Object convert(Class arg0, Object arg1) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date param = null;
						try {
							param = sdf.parse(arg1.toString());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return param;
					}
				}, Date.class);

				BeanUtils.populate(user, properties);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			// 将提交的表单中没有的数据自己封装一下
			// uid
			user.setUid(UUID.randomUUID().toString());
			// telephone
			user.setTelephone("");
			// state 是否激活状态
			user.setState(0);
			// code 激活的码
			String code = UUID.randomUUID().toString();
			user.setCode(code);

			// 将用户传递给service层
			RegisterService registerService = new RegisterService();
			boolean flag = false;
			flag = registerService.regist(user);
			// 注册成功
			if (flag) {
				// 如果注册成功则使用邮箱进行激活操作
				String emailMsg = "恭喜您，注册成功！请点击下方链接激活账户<a href='Http://localhost:8080/Store/userCentre?method=active&code=" + code
						+ "'>点我</a>";
				try {
					MailUtils.sendMail(user.getEmail(), emailMsg);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath() + "/registSuccess.jsp");

			}
			// 注册失败
			else {
				request.getRequestDispatcher("/registError.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("checkCodeEror", "验证码错误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

}