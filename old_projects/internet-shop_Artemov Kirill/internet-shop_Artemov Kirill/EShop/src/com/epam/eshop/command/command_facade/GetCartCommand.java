package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.CartService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.Cart;
import com.epam.eshop.model.entities.Product;

public class GetCartCommand implements Command {

	private static Logger logger = Logger.getLogger(GetCartCommand.class);
	private final String DEL = "del";
	private final String PRICE = "price";

	/**
	 * Default constructor
	 */
	public GetCartCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		if ((session.getAttribute(AttributesEnum.IS_IN_BLACKLIST.toString()) != null)
				&& ((Boolean) session
						.getAttribute(AttributesEnum.IS_IN_BLACKLIST.toString()) == false)) {
			Cart cart = Cart.getInstance(session);
			if (request.getParameter(AttributesEnum.CLEAR.toString()) != null) {
				// clear Cart and remove Cookies
				cart.clearCart();
				Cookie cookie = new Cookie(AttributesEnum.CART.toString(), "");
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
			}
			if (request.getParameter(DEL) != null) {
				if (request.getParameter(PRICE) != null) {
					// For remove item from cart necessary deduct price
					// it item from total price
					String idForDel = request.getParameter(DEL);
					String priceOfId = request.getParameter(PRICE);
					float price = 0F;
					if (Validator.isNumber(priceOfId)) {
						price = Float.valueOf(priceOfId);
					}
					int id = Integer.parseInt(idForDel);
					cart.delItem(id, price);
					session.setAttribute(AttributesEnum.CART.toString(), cart);
				}
			}
			// Get from cart HashMap of pairs id-count
			HashMap<Integer, Integer> mapIdCount = cart.getHashMap();
			CartService getCartService = (CartService) ServiceLocator
					.getService(AttributesEnum.CART_SERVICE.toString());
			// Via HshMap id-count doing HashMap product-count
			HashMap<Product, Integer> mapProdutCount = getCartService
					.getCart(mapIdCount);
			if (mapProdutCount != null) {
				request.setAttribute(AttributesEnum.CART_MAP.toString(),
						mapProdutCount);
				page = ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.CART_PAGE);
			} else {
				logger.trace(MessageManager.getInstance().getProperty(
						MessageManager.CART_ERROR));
				request.setAttribute(
						AttributesEnum.ERROR_MESSAGE.toString(),
						MessageManager.getInstance().getProperty(
								MessageManager.CART_ERROR));
				page = ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ERROR_PAGE);
			}
		} else {
			logger.trace(MessageManager.getInstance().getProperty(
					MessageManager.BLACK_USER));
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.BLACK_USER));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}

}
