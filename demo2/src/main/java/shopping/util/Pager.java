	package shopping.util;

import java.util.List;

/**
 * 分页类
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class Pager<T> {

	/**
	 * 带参数的构造方法
	 * 
	 * @param datas     当前显示的数据
	 * @param size      总记录数
	 * @param page      当前页码
	 * @param page_size 页容量
	 */
	public Pager(List<T> datas, int size, int page,int page_size) {
		this.datas = datas;
		this.size = size;
		this.page = page;
		this.page_size = page_size;
		
		//设置本类的属性的值
		this.setPropertites();

	}

	/**
	 * 当前页显示的数据
	 */
	private List<T> datas;

	/**
	 * 总记录数
	 */
	private int size;

	/**
	 * 页码
	 */
	private int page;

	/**
	 * 页容量
	 */
	public  int page_size;

	/**
	 * 总页数
	 */
	private int pages;

	/**
	 * 上一页
	 */
	private int pre;

	/**
	 * 下一页
	 */
	private int next;

	/**
	 * 首页
	 */
	private int first;

	/**
	 * 末页
	 */
	private int last;

	/**
	 * 设置本类属性的值
	 */
	private void setPropertites() {

		this.pages = this.size % this.page_size == 0 ? this.size / this.page_size : this.size / this.page_size + 1;

		if (this.page > 1)
			this.pre = this.page - 1;
		else
			this.pre = 1;

		if (this.page < this.pages)
			this.next = this.page + 1;
		else
			this.next = this.pages;

		this.first = 1;
		this.last = this.pages;

	}

	/**
	 * 获取当前页显示的数据
	 * 
	 * @return 数据集合
	 */
	public List<T> getDatas() {
		return datas;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 获取页容量
	 * 
	 * @return 页容量
	 */
	public int getPage_size() {
		return this.page_size;
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * 获取上一页
	 * 
	 * @return 上一页
	 */
	public int getPre() {
		return pre;
	}

	/**
	 * 获取下一页
	 * 
	 * @return 下一页
	 */
	public int getNext() {
		return next;
	}

	/**
	 * 获取首页
	 * 
	 * @return 首页
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * 获取末页
	 * 
	 * @return 末页
	 */
	public int getLast() {
		return last;
	}

}
