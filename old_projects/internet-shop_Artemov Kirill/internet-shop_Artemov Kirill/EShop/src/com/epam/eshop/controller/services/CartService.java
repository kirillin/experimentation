package com.epam.eshop.controller.services;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.epam.eshop.dao.daos.ProductDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;
import com.epam.eshop.lib.CartLib;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.model.entities.Cart;
import com.epam.eshop.model.entities.Product;

/**
 * Logic for processing cart
 * 
 * @author Kirill Artemov
 * 
 */
public class CartService extends Service {

	private final String ENCODING = "UTF-8";

	public CartService() {
	}

	/**
	 * @param daoFactory
	 * @param nameService
	 */
	public CartService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	public HashMap<Product, Integer> getCart(
			HashMap<Integer, Integer> mapIdCount) {
		HashMap<Product, Integer> fullCart = new HashMap<Product, Integer>();
		ProductDAO productDAO = null;
		try {
			productDAO = dao.getProductDAO();
			Iterator<Entry<Integer, Integer>> it = mapIdCount.entrySet()
					.iterator();
			while (it.hasNext()) {
				Map.Entry me = it.next();
				int id = (int) me.getKey();
				Product product = productDAO.getProductById(id);
				Integer count = (Integer) me.getValue();
				fullCart.put(product, count);
			}
			MySQLDAOFactory.releaseConnection(productDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fullCart;
	}

	public Cookie makeCookie(Cart cart) throws IOException {
		String serCart = CartLib.toString(cart);
		Cookie cookie = new Cookie(AttributesEnum.CART.toString(),
				URLEncoder.encode(serCart, ENCODING));
		// Cookie cookie = new Cookie("cart", serCart);
		cookie.setMaxAge(3600 * 24 * 365);
		return cookie;
	}

	public Cart getCart(HttpSession session, Cookie cookie)
			throws ClassNotFoundException, IOException {
		Cart cart = Cart.getInstance(session);
		String serCart = URLDecoder.decode(cookie.getValue(), ENCODING);
		cart = (Cart) CartLib.fromString(serCart);
		return cart;
	}

	public Cookie getCookie(Cookie[] cookies, String cookieName) {
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					cookie = cookies[i];
					break;
				}
			}
		}
		return cookie;
	}

}
