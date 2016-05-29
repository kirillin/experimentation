package com.epam.eshop.command.command_facade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.BlacklistService;
import com.epam.eshop.controller.services.CartService;
import com.epam.eshop.controller.services.ProductService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.Cart;
import com.epam.eshop.model.entities.Category;
import com.epam.eshop.view.views.ProductsView;

public class GetProductsCommand implements Command {

	private static Logger logger = Logger.getLogger(GetProductsCommand.class);
	private final String PRODUCT_ID = "productId";
	private final String UPDATE_ACTUAL = "updateActual";

	/**
	 * Default constructor
	 */
	public GetProductsCommand() {
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(true);
		List<ProductsView> products = null;
		List<Category> categories = null;
		ProductService productService = (ProductService) ServiceLocator
				.getService(AttributesEnum.PRODUCT_SIRVECE.toString());
		BlacklistService blacklistService = (BlacklistService) ServiceLocator
				.getService(AttributesEnum.BLACKLIST_SERVICE.toString());
		// Process cookies for saving user's cart
		Cart cart = null;
		Cookie cookie = null;
		CartService cartService = (CartService) ServiceLocator
				.getService(AttributesEnum.CART_SERVICE.toString());

		// from cart to cookie
		if (session.getAttribute(AttributesEnum.CART.toString()) != null) {
			cart = (Cart) session.getAttribute(AttributesEnum.CART.toString());
			if (cart != null) {
				cookie = cartService.makeCookie(cart);
				response.addCookie(cookie);
			}
		}

		String productIdValue = Validator.stripHarmful(request
				.getParameter(PRODUCT_ID));
		String updateActualValue = Validator.stripHarmful(request
				.getParameter(UPDATE_ACTUAL));
		session.removeAttribute(AttributesEnum.ACTUAL_UPDATED.toString());
		if ((productIdValue != null) && (updateActualValue != null)) {
			int productId = -1;
			Boolean actual = null;
			if (Validator.isNumber(productIdValue)) {
				productId = Integer.parseInt(productIdValue);
			}
			if (updateActualValue.equals("true")) {
				actual = true;
			} else if (updateActualValue.equals("false")) {
				actual = false;
			}
			if (productService.updateProduct(productId, actual)) {
				session.setAttribute(AttributesEnum.ACTUAL_UPDATED.toString(), true);
			} else {
				session.setAttribute(AttributesEnum.ACTUAL_UPDATED.toString(),
						false);
			}
		}
		products = productService.getProducts();
		categories = productService.getCategories();
		if (products != null) {
			session.setAttribute(AttributesEnum.PRODUCTS.toString(), products);
			if (request.getParameter(AttributesEnum.COMMAND.toString()).equals(
					AttributesEnum.GET_CATALOG.toString())) {
				page = ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.SHOP_PAGE);
			}
			if (blacklistService.isInBlacklist(((String) session
					.getAttribute(AttributesEnum.USER_NAME.toString())))) {
				session.setAttribute(AttributesEnum.IS_IN_BLACKLIST.toString(),
						true);
			} else {
				session.setAttribute(AttributesEnum.IS_IN_BLACKLIST.toString(),
						false);
			}
		} else {
			logger.fatal(MessageManager.getInstance().getProperty(
					MessageManager.PRODUCTS_NULL));
			request.setAttribute(
					AttributesEnum.ERROR_MESSAGE.toString(),
					MessageManager.getInstance().getProperty(
							MessageManager.PRODUCTS_NULL));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		if (request.getParameter(AttributesEnum.COMMAND.toString()).equals(
				AttributesEnum.GET_PRODUCTS.toString())) {
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.CATALOG_PAGE);
			session.setAttribute(AttributesEnum.CATEGORIES.toString(), categories);
		}
		return page;
	}

}
