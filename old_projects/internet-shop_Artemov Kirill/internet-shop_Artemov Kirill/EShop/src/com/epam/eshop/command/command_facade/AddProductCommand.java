package com.epam.eshop.command.command_facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.service_locator.ServiceLocator;
import com.epam.eshop.controller.services.ProductService;
import com.epam.eshop.lib.Validator;
import com.epam.eshop.manager.AttributesEnum;
import com.epam.eshop.manager.ConfigurationManager;
import com.epam.eshop.manager.MessageManager;

/**
 * Class makes it possible add products to DB from form in JSP
 * 
 * @author Kirill Artemov
 * 
 */
public class AddProductCommand implements Command {

	private static Logger logger = Logger.getLogger(AddProductCommand.class);
	/**
	 * Names of fields from form
	 */
	private final String PARAM_NAME_CATEGOTY = "categoryId";
	private final String PARAM_NAME_PRODUCT_NAME = "productName";
	private final String PARAM_NAME_PRODUCT_PRICE = "productPrice";
	private final String PARAM_NAME_UNITS_QUANTITY = "quantity";
	private final String PARAM_NAME_DESCRIPTION = "description";

	/**
	 * Default constructor
	 */
	public AddProductCommand() {
	}

	/**
	 * The method receives parameters from form for adding product. From all
	 * parameters strips different harmful symbols and call to
	 * productService.addProduct(... parameters ...) return page for forwarding
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		int categoryId = -1;
		float productPrice = -1;
		int quantity = -1;
		String categoryIdValues = Validator.stripHarmful(request.getParameter(
				PARAM_NAME_CATEGOTY).trim());
		if (categoryIdValues != null) {
			if (Validator.isNumber(categoryIdValues)) {
				categoryId = Integer.parseInt(categoryIdValues);
			}
		}
		String productName = Validator.stripHarmful(request.getParameter(
				PARAM_NAME_PRODUCT_NAME).trim());
		String productPriceValue = Validator.stripHarmful(request.getParameter(
				PARAM_NAME_PRODUCT_PRICE).trim());
		if (productPriceValue != null) {
			if (Validator.isNumber(productPriceValue)) {
				productPrice = Float.parseFloat(productPriceValue);
			}
		}
		String quantityValue = Validator.stripHarmful(request.getParameter(
				PARAM_NAME_UNITS_QUANTITY).trim());
		if (quantityValue != null) {
			if (Validator.isNumber(quantityValue)) {
				quantity = Integer.parseInt(quantityValue);
			}
		}
		String description = Validator.stripHarmful(request.getParameter(
				PARAM_NAME_DESCRIPTION).trim());
		if ((categoryId != -1) && (productName != null) && (productPrice != -1)
				&& (quantity != -1)) {
			ProductService productService = (ProductService) ServiceLocator
					.getService(AttributesEnum.PRODUCT_SIRVECE.toString());
			if (productService.addProduct(categoryId, productName,
					productPrice, quantity, description)) {
				page = ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ADD_PRODUCT_PAGE);
			} else {
				// sets in logs error message
				logger.fatal(MessageManager.getInstance().getProperty(
						MessageManager.ERR_ADD_PRODUCT));
				// sets in request error message
				request.setAttribute(AttributesEnum.ERROR_MESSAGE.toString(),
						MessageManager.getInstance().getProperty(
								MessageManager.ERR_ADD_PRODUCT));
				// sets in page variable error page
				page = ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.ERROR_PAGE);
			}
		} else {
			// sets in logs error message
			logger.fatal(MessageManager.getInstance().getProperty(
					MessageManager.ERR_ADD_PRODUCT));
			// sets in request error message
			request.setAttribute(AttributesEnum.ERROR_MESSAGE.toString(), 
					MessageManager.getInstance()
					.getProperty(MessageManager.ERR_ADD_PRODUCT));
			// sets in page variable error page
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE);
		}
		return page;
	}
}
