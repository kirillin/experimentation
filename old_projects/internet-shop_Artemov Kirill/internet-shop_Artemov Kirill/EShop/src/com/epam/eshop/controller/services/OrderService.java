package com.epam.eshop.controller.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.eshop.controller.exception.OrderException;
import com.epam.eshop.dao.daos.CityDAO;
import com.epam.eshop.dao.daos.OrderDAO;
import com.epam.eshop.dao.daos.OrdersProductsDAO;
import com.epam.eshop.dao.daos.PaymentMethodDAO;
import com.epam.eshop.dao.daos.ShippingDAO;
import com.epam.eshop.dao.daos.ShippingMethodDAO;
import com.epam.eshop.dao.daos.StatusDAO;
import com.epam.eshop.dao.daos.UserDAO;
import com.epam.eshop.dao.factory.MySQLDAOFactory;
import com.epam.eshop.manager.MessageManager;
import com.epam.eshop.model.entities.City;
import com.epam.eshop.model.entities.Order;
import com.epam.eshop.model.entities.PaymentMethod;
import com.epam.eshop.model.entities.Product;
import com.epam.eshop.model.entities.Shipping;
import com.epam.eshop.model.entities.ShippingMethod;
import com.epam.eshop.model.entities.Status;
import com.epam.eshop.view.views.OrdersView;

public class OrderService extends Service {

	private static Logger logger = Logger.getLogger(OrderService.class);

	public OrderService() {
		super();
	}

	public OrderService(int daoFactory, String nameService) {
		super(daoFactory, nameService);
	}

	@Override
	public String getNameService() {
		return super.getNameService();
	}

	public List<Status> getStatuses() {
		StatusDAO statusDAO = null;
		List<Status> statuses = null;
		try {
			statusDAO = dao.getStatusDAO();
			statuses = statusDAO.getStatuses();
			MySQLDAOFactory.releaseConnection(statusDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	public List<City> getCities() {
		CityDAO cityDAO = null;
		List<City> cities = null;
		try {
			cityDAO = dao.getCityDAO();
			cities = cityDAO.getCities();
			MySQLDAOFactory.releaseConnection(cityDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cities;
	}

	public List<ShippingMethod> getShippingMethods() {
		ShippingMethodDAO shippingMethodDAO = null;
		List<ShippingMethod> methods = null;
		try {
			shippingMethodDAO = dao.getShippingMethodDAO();
			methods = shippingMethodDAO.getShippingMethods();
			MySQLDAOFactory.releaseConnection(shippingMethodDAO
					.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return methods;
	}

	public List<PaymentMethod> getPaymentMethods() {
		PaymentMethodDAO paymentMethodDAO = null;
		List<PaymentMethod> methods = null;
		try {
			paymentMethodDAO = dao.getPaymentMethodDAO();
			methods = paymentMethodDAO.getPaymentMethods();
			MySQLDAOFactory.releaseConnection(paymentMethodDAO
					.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return methods;
	}

	public int addOrder(int shippingMethodId, int cityId, String adress,
			String zip, String phone, int userId, int paymentId,
			long order_date, float totalPrice, int statusId,
			HashMap<Integer, Integer> pairsIdCount) {
		OrderDAO orderDAO = null;
		ShippingDAO shippingDAO = null;
		OrdersProductsDAO ordersProductsDAO = null;
		int orderId = -1;
		try {
			shippingDAO = dao.getShippingDAO();
			// returned ID if all right else returned -1
			int shippingId = shippingDAO.addShipping(shippingMethodId, cityId,
					adress, zip, phone);
			MySQLDAOFactory.releaseConnection(shippingDAO.releaseConnection());
			if (shippingId != -1) {
				orderDAO = dao.getOrderDAO();
				orderId = orderDAO.addOrder(userId, paymentId, shippingId,
						statusId, order_date, totalPrice);
				MySQLDAOFactory.releaseConnection(orderDAO.releaseConnection());
				ordersProductsDAO = dao.getOrdersProductsDAO();
				ordersProductsDAO.addNodeOrdersProducts(orderId, pairsIdCount);
				MySQLDAOFactory.releaseConnection(ordersProductsDAO
						.releaseConnection());
			} else {
				throw new OrderException("Have any problem into orderDAO");
			}
		} catch (SQLException e) {
			logger.error(MessageManager.getInstance().getProperty(
					MessageManager.SQL_EXCEPTION_ERROR_MESSAGE)
					+ e.getClass());
		} catch (OrderException e) {
			logger.error(MessageManager.getInstance().getProperty(
					MessageManager.ORDER_EXCEPTION_ERROR_MESSAGE)
					+ e.getClass() + e.getMessage());
		} catch (Exception e) {
			logger.error(MessageManager.getInstance().getProperty(
					MessageManager.EXCEPTION_ERROR_MESSAGE)
					+ e.getClass());
		}
		return orderId;
	}

	public List<OrdersView> getOrders(String userLogin) {
		OrderDAO orderDAO = null;
		UserDAO userDAO = null;
		PaymentMethodDAO paymentMethodDAO = null;
		ShippingDAO shippingDAO = null;
		ShippingMethodDAO shippingMethodDAO = null;
		OrdersProductsDAO ordersProductsDAO = null;
		CityDAO cityDAO = null;
		StatusDAO statusDAO = null;
		List<Order> orders = null;
		List<OrdersView> ordersView = null;
		try {
			orderDAO = dao.getOrderDAO();
			orders = orderDAO.getOrders(userLogin);
			MySQLDAOFactory.releaseConnection(orderDAO.releaseConnection());
			if (orders != null) {
				ordersView = new LinkedList<OrdersView>();
				for (Iterator<Order> iterator = orders.iterator(); iterator
						.hasNext();) {
					Order order = (Order) iterator.next();
					userDAO = dao.getUserDAO();
					String login = userDAO.getUser(order.getUserId())
							.getLogin();
					String email = userDAO.getUser(order.getUserId())
							.getEmail();
					MySQLDAOFactory.releaseConnection(userDAO
							.releaseConnection());
					paymentMethodDAO = dao.getPaymentMethodDAO();
					String paymentMethod = paymentMethodDAO
							.getPaymentMethodById(order.getPaymentMethodId())
							.getPaymentMethod();
					MySQLDAOFactory.releaseConnection(paymentMethodDAO
							.releaseConnection());
					shippingDAO = dao.getShippingDAO();
					Shipping shipping = shippingDAO.getShippingById(order
							.getShippingId());
					MySQLDAOFactory.releaseConnection(shippingDAO
							.releaseConnection());
					shippingMethodDAO = dao.getShippingMethodDAO();
					String shippingMethod = shippingMethodDAO
							.getShippingMethodById(
									shipping.getShippingnethodId())
							.getShippingMethod();
					// float shippCost =
					// shippingMethodDAO.getShippingMethodById(shipping.getShippingnethodId()).getCost();
					// int length =
					// shippingMethodDAO.getShippingMethodById(shipping.getShippingnethodId()).getLength();
					MySQLDAOFactory.releaseConnection(shippingMethodDAO
							.releaseConnection());
					cityDAO = dao.getCityDAO();
					String city = cityDAO.getCityById(shipping.getCityId())
							.getCity();
					MySQLDAOFactory.releaseConnection(cityDAO
							.releaseConnection());
					statusDAO = dao.getStatusDAO();
					String status = statusDAO
							.getStatusById(order.getStatusId()).getStatus();
					MySQLDAOFactory.releaseConnection(statusDAO
							.releaseConnection());
					String adress = shipping.getAdress();
					String zip = shipping.getZip();
					String phone = shipping.getPhone();
					
					ordersProductsDAO = dao.getOrdersProductsDAO();
					
					HashMap<Product, Integer> pairsProductQuantity = ordersProductsDAO
							.getNodeOrdersProducts(order.getId());
					MySQLDAOFactory.releaseConnection(ordersProductsDAO.releaseConnection());
					
					OrdersView orderView = new OrdersView();
					orderView.setId(order.getId());
					orderView.setLogin(login);
					orderView.setEmail(email);
					orderView.setPaymentMethod(paymentMethod);
					orderView.setShippingMethod(shippingMethod);
					orderView.setCity(city);
					orderView.setAdress(adress);
					orderView.setAdress(zip);
					orderView.setPhone(phone);
					orderView.setStatus(status);
					orderView.setOrderDate(order.getOrderDate());
					orderView.setTotalPrice(order.getTotalPrice());
					orderView.setPairsProductQuantity(pairsProductQuantity);
					ordersView.add(orderView);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ordersView != null) {
			logger.trace("Orders was returned!");
		}
		return ordersView;
	}

	public List<OrdersView> getOrders() {
		OrderDAO orderDAO = null;
		UserDAO userDAO = null;
		PaymentMethodDAO paymentMethodDAO = null;
		ShippingDAO shippingDAO = null;
		ShippingMethodDAO shippingMethodDAO = null;
		OrdersProductsDAO ordersProductsDAO = null;
		CityDAO cityDAO = null;
		StatusDAO statusDAO = null;
		List<Order> orders = null;
		List<OrdersView> ordersView = null;
		try {
			orderDAO = dao.getOrderDAO();
			orders = orderDAO.getOrders();
			MySQLDAOFactory.releaseConnection(orderDAO.releaseConnection());
			if (orders != null) {
				ordersView = new LinkedList<OrdersView>();
				for (Iterator<Order> iterator = orders.iterator(); iterator
						.hasNext();) {
					Order order = (Order) iterator.next();
					userDAO = dao.getUserDAO();
					String login = userDAO.getUser(order.getUserId())
							.getLogin();
					String email = userDAO.getUser(order.getUserId())
							.getEmail();
					MySQLDAOFactory.releaseConnection(userDAO
							.releaseConnection());
					paymentMethodDAO = dao.getPaymentMethodDAO();
					String paymentMethod = paymentMethodDAO
							.getPaymentMethodById(order.getPaymentMethodId())
							.getPaymentMethod();
					MySQLDAOFactory.releaseConnection(paymentMethodDAO
							.releaseConnection());
					shippingDAO = dao.getShippingDAO();
					Shipping shipping = shippingDAO.getShippingById(order
							.getShippingId());
					MySQLDAOFactory.releaseConnection(shippingDAO
							.releaseConnection());
					shippingMethodDAO = dao.getShippingMethodDAO();
					String shippingMethod = shippingMethodDAO
							.getShippingMethodById(
									shipping.getShippingnethodId())
							.getShippingMethod();
					// float shippCost =
					// shippingMethodDAO.getShippingMethodById(shipping.getShippingnethodId()).getCost();
					// int length =
					// shippingMethodDAO.getShippingMethodById(shipping.getShippingnethodId()).getLength();
					MySQLDAOFactory.releaseConnection(shippingMethodDAO
							.releaseConnection());
					cityDAO = dao.getCityDAO();
					String city = cityDAO.getCityById(shipping.getCityId())
							.getCity();
					MySQLDAOFactory.releaseConnection(cityDAO
							.releaseConnection());
					statusDAO = dao.getStatusDAO();
					String status = statusDAO
							.getStatusById(order.getStatusId()).getStatus();
					MySQLDAOFactory.releaseConnection(statusDAO
							.releaseConnection());
					String adress = shipping.getAdress();
					String zip = shipping.getZip();
					String phone = shipping.getPhone();
					
					// product_ids which user bought					
					ordersProductsDAO = dao.getOrdersProductsDAO();
					HashMap<Product, Integer> pairsProductQty = ordersProductsDAO
								.getNodeOrdersProducts(order.getId());
					MySQLDAOFactory.releaseConnection(ordersProductsDAO
								.releaseConnection());		
					
					OrdersView orderView = new OrdersView();
					orderView.setId(order.getId());
					orderView.setLogin(login);
					orderView.setEmail(email);
					orderView.setPaymentMethod(paymentMethod);
					orderView.setShippingMethod(shippingMethod);
					orderView.setCity(city);
					orderView.setAdress(adress);
					orderView.setAdress(zip);
					orderView.setPhone(phone);
					orderView.setStatus(status);
					orderView.setOrderDate(order.getOrderDate());
					orderView.setTotalPrice(order.getTotalPrice());
					orderView.setPairsProductQuantity(pairsProductQty);
					ordersView.add(orderView);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ordersView != null) {
			logger.trace("Orders was returned!");
		}
		return ordersView;
	}

	public boolean updateOrder(int orderId, int statusId) {
		OrderDAO orderDAO = null;
		boolean success = false;
		try {
			orderDAO = dao.getOrderDAO();
			success = orderDAO.updateOrder(orderId, statusId);
			MySQLDAOFactory.releaseConnection(orderDAO.releaseConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
}
