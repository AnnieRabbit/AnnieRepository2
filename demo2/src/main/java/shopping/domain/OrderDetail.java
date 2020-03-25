package shopping.domain;

import java.util.List;

public class OrderDetail {

	private int id;
	
	private String addtime;
	
	private Goods goods;
	
	private int goodsid;
	
	private String ordercode;
	
	private List<View_GoodsSpec> view_GoodsSpecs;
	
	private double price;
	
	private int count;
	
	private double subtotal;

	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<View_GoodsSpec> getView_GoodsSpecs() {
		return view_GoodsSpecs;
	}

	public void setView_GoodsSpecs(List<View_GoodsSpec> view_GoodsSpecs) {
		this.view_GoodsSpecs = view_GoodsSpecs;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
