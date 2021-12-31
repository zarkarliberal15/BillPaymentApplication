package com.project.bill.payment.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bill.payment.application.entity.Order;
import com.project.bill.payment.application.entity.OrderProduct;
import com.project.bill.payment.application.entity.Product;
import com.project.bill.payment.application.exception.OrderProductNotFoundException;
import com.project.bill.payment.application.exception.ProductNotFoundException;
import com.project.bill.payment.application.repository.OrderProductRepository;
import com.project.bill.payment.application.repository.OrderRepository;
import com.project.bill.payment.application.repository.ProductRepository;
import com.project.bill.payment.application.service.OrderProductService;

@Service
public class OrderProductServiceImpl implements OrderProductService {
	@Autowired
	private OrderProductRepository repo;
	
	@Autowired
	private OrderServiceImpl impl;
	
	@Autowired
	private ProductRepository prepo;
	
	@Override
	public List<OrderProduct> getAllOrderProduct() {
		return (List<OrderProduct>) repo.findAll();
	}

	@Override
	public String addOrderProduct(OrderProduct orderProduct) {
		double price = orderProduct.getProduct().getPrice();
		int quantity = orderProduct.getQuantity();
		double tax = orderProduct.getProduct().getTax();
		double discount = orderProduct.getProduct().getDiscount();
		
		double tax_amount = calculateTaxAmount(price,quantity,tax);
		double discount_amount = calculateDiscountAmount(price,quantity,discount);
		double amount = calculateAmount(price,quantity,tax_amount,discount_amount);
		
		orderProduct.setD_price(discount_amount);
		orderProduct.setT_price(tax_amount);
		orderProduct.setAmount(amount);
		
		if(isProductAvailable(orderProduct.getProduct().getId(), orderProduct.getQuantity())) {
			OrderProduct op = repo.save(orderProduct);
			if(op != null) {
				impl.editOrder(op.getOrder());
				return "Added to cart";
			}else {
				return "Not added to cart";
			}	
		}else {
			return "Out of stock!";
		}
		
		
	}

	@Override
	public String deleteOrderProduct(long id) {
		OrderProduct op = repo.findById(id).get();
		repo.deleteById(id);
		impl.editOrder(op.getOrder());
		return "Deleted from cart!";
	}

	@Override
	public String editOrderProduct(OrderProduct orderProduct) {
		double price = orderProduct.getProduct().getPrice();
		int quantity = orderProduct.getQuantity();
		double tax = orderProduct.getProduct().getTax();
		double discount = orderProduct.getProduct().getDiscount();
		
		double tax_amount = calculateTaxAmount(price,quantity,tax);
		double discount_amount = calculateDiscountAmount(price,quantity,discount);
		double amount = calculateAmount(price,quantity,tax_amount,discount_amount);
		
		orderProduct.setD_price(discount_amount);
		orderProduct.setT_price(tax_amount);
		orderProduct.setAmount(amount);
		
		if(isProductAvailable(orderProduct.getProduct().getId(), orderProduct.getQuantity())) {
			OrderProduct op = repo.save(orderProduct);
			if(op != null) {
				impl.editOrder(op.getOrder());
				return "Updated";
			}else {
				return "Not updated!";
			}
		}else {
			return "Out of stock!";
		}
		
	}

	@Override
	public OrderProduct getOrderProduct(long id) {
		OrderProduct op = repo.findById(id).get();
		if(op == null) {
			try {
				throw new OrderProductNotFoundException("Not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return op;
	}


	@Override
	public double calculateAmount(double price, int quantity, double taxAmount, double discountAmount) {
		double amount = price * quantity +  taxAmount - discountAmount;
		return amount;
	}

	@Override
	public double calculateTaxAmount(double price, int quantity, double tax) {
		double tax_amount = 0;
		if(price >0 && tax >0)
			tax_amount = price * quantity * (tax/100);
		return tax_amount;
	}

	@Override
	public double calculateDiscountAmount(double price, int quantity, double discount) {
		double discount_amount = 0;
		if(price >0 && discount >0)
			 discount_amount = price * quantity * (discount/100);
		return discount_amount;
	}

	@Override
	public List<OrderProduct> findByOrder(Order order) {
		return repo.findByOrder(order);
	}

	@Override
	public boolean isProductAvailable(long id,int quantity) {
		boolean flag = false;
		Product product = prepo.findById(id).get();
		if(product == null) {
			try {
				throw new ProductNotFoundException("Product not found!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(quantity <= product.getQuantity()) {
			flag = true;
		}
		return flag;
		
	}

}
