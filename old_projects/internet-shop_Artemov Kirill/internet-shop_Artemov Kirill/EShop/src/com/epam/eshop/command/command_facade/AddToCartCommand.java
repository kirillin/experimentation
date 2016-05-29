package com.epam.eshop.command.command_facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.model.entities.Cart;

public class AddToCartCommand implements Command {

	private static Logger logger = Logger.getLogger(CheckoutCommand.class);
	private final String PRODUCT_ID_PARAM = "product_id";
	private final String PRICE_PARAM = "price";
	private final String CART_ATTRIBUTE = "cart";
	
	public AddToCartCommand() {
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		Cart cart = Cart.getInstance(session);
		cart.addItem(request.getParameter(PRODUCT_ID_PARAM));
		Float totalPrice = cart.getTotalPrice(); 
		Float total = Float.valueOf(request.getParameter(PRICE_PARAM));
		totalPrice = totalPrice + total;
		cart.setTotalPrice(totalPrice);
		session.setAttribute(CART_ATTRIBUTE, cart);
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.GET_CATALOG);
		return page;
	}

	
}
