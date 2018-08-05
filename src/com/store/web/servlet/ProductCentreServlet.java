package com.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.store.domain.Cart;
import com.store.domain.CartProduct;
import com.store.domain.Category;
import com.store.domain.Order;
import com.store.domain.Orderitem;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.domain.User;
import com.store.service.CategoryService;
import com.store.service.OrderService;
import com.store.service.ProductListByIdService;
import com.store.service.ProductListService;
import com.store.service.ProductService;

public class ProductCentreServlet extends BaseServlet {


	//用户确认订单的方法
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pd_FrpId = request.getParameter("pd_FrpId");
		String uid = request.getParameter("uid");
		System.out.println(uid+":::"+pd_FrpId);
	}
	
	
	
	
	// 页面点击提交订单后在servlet中封装数据到order中，而后传递到dao写入数据库
	// 这里用到了三个表，用户表，订单项表，订单表
	public void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 拿到session中的订单数据
		HttpSession session = request.getSession();
		if (null != session) {

			// 对用户的登陆状态检查，如果没有登陆就什么都不做
			User user = (User) session.getAttribute("user");
			if (null != user) {

				Cart cart = (Cart) session.getAttribute("cart");
				// 拿到session中的购物车对象判断购物车里有没有对象
				if (null != cart) {
					Order order = new Order();

					// 继续对订单的数据进行封装
					/*
					 * private String oid; //订单id private Date ordertime; //创建时间
					 * private double total; //订单金额 private int state; //付款的状态
					 * 
					 * private String address; //收货人地址 private String name;
					 * //收货人名称 private String otelephoneid; //联系电话
					 * 
					 * private User uid; //订单所属用户
					 */

					// 订单id
					order.setOid(UUID.randomUUID().toString());
					// 创建时间
					order.setOrdertime(new Date());
					// 先对总金额封装
					order.setTotal(cart.getTotal());
					// 付款的状态
					order.setState(0);

					// 收货人地址
					order.setAddress("");
					// 收货人名称
					order.setName(user.getName());
					// 联系电话
					order.setOtelephoneid(user.getTelephone());
					// 订单所属用户
					order.setUser(user);

					// 在这里封装数据,拿到购物车中的商品信息，封装为一个订单项，而后将订单项封装到订单对象中
					for (Map.Entry<String, CartProduct> entry : cart.getCartmap().entrySet()) {
						Orderitem orderitem = new Orderitem();
						CartProduct cartProduct = entry.getValue();
						// 单个订单项的id
						orderitem.setItemid(UUID.randomUUID().toString());
						// 购买数量
						orderitem.setCount(cartProduct.getBuyNum());
						// 小计金额
						orderitem.setSubtotal(cartProduct.getSubtotal());
						// 订单产品
						orderitem.setPro(cartProduct.getPro());
						// 所属订单
						orderitem.setOrder(order);

						// 将封装好的订单项加入到订单的list集合中
						order.getOrderitemList().add(orderitem);

					}
					
					//传递数据到servie层
					OrderService orderService = new OrderService();
					//添加订单
					boolean flag =  orderService.addOrder(order);
					
					if(flag){
						//将订单的数据放到session中，方便订单页面的使用
						session.setAttribute("order", order);
						response.sendRedirect(request.getContextPath()+"/order_info.jsp");
					}

				}

			}
			else{
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}

		}
	}

	// 清空购物车

	public void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("cart");
		response.sendRedirect(request.getContextPath() + "/cart.jsp");

	}

	// 删除购物车某个商品的方法
	public void deleteCartProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		// 拿到单个商品的栏对象
		CartProduct cp = cart.getCartmap().get(pid);
		// 栏产品数量减1
		cp.setBuyNum(cp.getBuyNum() - 1);
		;
		// 小计金额减掉一份
		cp.setSubtotal(cp.getSubtotal() - cp.getPro().getShop_price());
		// 把产品栏放回去
		cart.getCartmap().put(pid, cp);
		// 总的金额减掉一份
		cart.setTotal(cart.getTotal() - cp.getPro().getShop_price());
		// 如果产品的数量为0了，直接把这个产品栏拿掉
		if (cp.getBuyNum() == 0) {
			cart.getCartmap().remove(pid);
		}
		// 更新session中的数据
		session.setAttribute("cart", cart);
		// 回去购物车
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 购物车的方法
	public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 拿到用户需要购买的商品id和数量
		String buyNum = request.getParameter("buyNum");
		String pid = request.getParameter("pid");
		// 应该封装到一个购物项对象中，这个对象需要的是产品对象，数量还有小计金额
		CartProduct cartProduct = new CartProduct();
		ProductService pService = new ProductService();
		if (null != buyNum && null != pid) {
			// 对buyNum封装
			int num = Integer.parseInt(buyNum);
			cartProduct.setBuyNum(num);
			Product pro = null;
			try {
				pro = pService.findProduct(pid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cartProduct.setPro(pro);
			double subtotal = num * pro.getShop_price();
			cartProduct.setSubtotal(subtotal);
		}
		// 封装完CartProduct，则去session中查看有没有购物车对象，没有就创建，有就将新加的CartProduct放到购物车里
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (null == cart) {
			cart = new Cart();
		}
		// 如果map里已经存了我加入购物车的产品，那么需要把存的拿出来，加上我这次继续添加的同类产品数量，和更改小计金额
		if (cart.getCartmap().containsKey(pid)) {
			cartProduct.setBuyNum(cart.getCartmap().get(pid).getBuyNum() + cartProduct.getBuyNum());
			cartProduct.setSubtotal(cart.getCartmap().get(pid).getSubtotal() + cartProduct.getSubtotal());
		}
		// 对商品总价格的封装，每访问一次就把上次存的总金额拿出来加上这次小计
		cart.setTotal(cart.getTotal() + cartProduct.getSubtotal());
		cart.getCartmap().put(pid, cartProduct);
		session.setAttribute("cart", cart);
		// response.sendRedirect(request.getContextPath()+"/cart.jsp");

		// 试试看加入购物车后回去刚刚的页面然后提示添加成功，这里遇到的问题在于当我转发出现了反射的异常。因为转发地址栏是不变的，那么参数就不对
		// 所以使用重定向来做
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		response.sendRedirect(request.getContextPath() + "/productCentre?method=productInfo&pid=" + pid + "&cid=" + cid
				+ "&currentPage=" + currentPage + "&addFlag=1");

	}

	// 首页的产品的数据
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 用于在数据库查询最新商品与热门商品，在返回给页面
		ProductListService pService = new ProductListService();
		// 查询热门商品
		List<Product> hotProductList = pService.findHotProductList();
		// 查询最新商品
		List<Product> newProductList = pService.findNewProductList();

		request.setAttribute("newProductList", newProductList);
		request.setAttribute("hotProductList", hotProductList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	// 点击导航栏后产品的分页
	public void productListById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 点击导航栏后应该显示的数据为分页后的产品数据假设当前页为第一页
		int currentPage = 1;
		String current = request.getParameter("currentPage");
		if (null == current) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(current);
		}
		// 一页显示的数据规定为12个产品
		int onePageCount = 12;
		String cid = request.getParameter("cid");
		ProductListByIdService productListByIdService = new ProductListByIdService();
		PageBean<Product> pageBean = productListByIdService.findProductListById(cid, currentPage, onePageCount);

		// 返回上一页走的也是这里，那么我们在这里对cookie中的数据进行解析查询到产品在传递给页面
		Cookie[] cookies = request.getCookies();
		String[] pidArray = null;
		List<Product> HeStoryProduct = new ArrayList<Product>();
		ProductService pService = new ProductService();
		String pids = "";
		if (null != cookies) {
			for (Cookie coo : cookies) {
				if ("pids".equals(coo.getName())) {
					pids = coo.getValue();
					pidArray = pids.split("!");
				}
			}
		}
		if (null != pidArray) {
			for (int i = 0; i < pidArray.length && i < 7; i++) {
				Product pro = null;
				try {
					pro = pService.findProduct(pidArray[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				HeStoryProduct.add(pro);
			}
		}
		request.setAttribute("HeStoryProducts", HeStoryProduct);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid); // 这里的cid是给分页按钮用的，因为上下页需要再查询，如果不给cid就没办法分类查询了
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	// 点击某个产品后具体的产品信息
	public void productInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService pService = new ProductService();
		Product product = null;
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");

		// 用于添加购物车后，访问该商品信息页面，表示添加成功的标记
		String addFlag = request.getParameter("addFlag");
		if (null != addFlag) {
			request.setAttribute("addFlag", "加入购物车成功！");
		}

		try {
			product = pService.findProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 先对Cookie进行判断，如果是不是第一次访问就拼接，如果是第一次访问就把pid放到cookie中
		Cookie[] cookies = request.getCookies();
		String pids = pid + "!";
		if (cookies != null) {
			for (Cookie coo : cookies) {
				// 拿到cookie中的pids
				if ("pids".equals(coo.getName())) {
					pids = coo.getValue();
					// 包含就用空字符替代再放到头部
					if (pids.contains(pid + "!"))
						pids = pids.replace(pid + "!", "");
					pids = pid + "!" + pids;
				}
			}
		}
		// 如果是第一次访问就在Cookie中加上没有被重新赋值的pids
		Cookie cookie = new Cookie("pids", pids);
		response.addCookie(cookie);

		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);

	}

	// 导航栏每个分类信息的获取，在header.jsp中用到了。使用的是ajax技术
	public void categoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryService categoryService = new CategoryService();
		List<Category> categoryList = categoryService.findAllCategory();
		// 使用json工具类gson对查到的数据封装，在响应给ajax
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		// 考虑到用数据库中读取的数据为中文，所以响应体应设置中文UTF-8
		response.setContentType("text/html;Charset=UTF-8");
		response.getWriter().write(json);
	}

}
