package shopping.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码类
 * 
 * @author Jayce
 * @version V1.0
 *
 */
public class ValidateCode {

	/**
	 * 构造方法
	 * @param response 响应对象
	 */
	public ValidateCode(HttpServletRequest request, HttpServletResponse response,int height) {
		this.response = response;
		this.request=request;
		this.random = new Random();
		this.width = 80;
		this.height = height;
	}

	/**
	 * 图片的宽度
	 */
	private int width;

	/**
	 * 图片高度
	 */
	private int height;

	/**
	 * 响应对象
	 */
	private HttpServletResponse response;
	
	private HttpServletRequest request;

	/**
	 * 产生随机数对象
	 */
	private Random random;
	
	/**
	 * 设置响应的缓存方式
	 */
	private void setCache() {
		response.setContentType("text/html;charset=utf-8");		
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	/**
	 * 生成验证码图像
	 * 
	 * @return 验证码
	 */
	public void build() {
		
		//设置图片不缓存
		this.setCache();

		// 创建图像
		BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		// 获取画笔
		Graphics g = image.getGraphics();

		// 设置背景色
		g.setColor(this.getRandColor(200, 250));
		g.fillRect(0, 0, this.width, this.height);

		// 设置边框
		g.setColor(this.getRandColor(160, 200));
		g.drawRect(0, 0, this.width - 1, this.height - 1);

		// 产生验证码
		String code = this.generateCheckCode();
		for (int i = 0; i < code.length(); i++) {
			String rand = String.valueOf(code.charAt(i));
			// 设定字体
			g.setFont(new Font("宋体", Font.BOLD, 20));
			// 设置字符颜色
			g.setColor(new Color(20 + this.random.nextInt(110), 20 + this.random.nextInt(110), 20 + this.random.nextInt(110)));
			g.drawString(rand, i * 18 + 8, this.height-this.height/4);
		}

		// 销毁画笔
		g.dispose();
		
		this.request.getSession().setAttribute("validate_code", code);

		// 保存图像到输出流
		ImageIO.setUseCache(true);
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			ImageIO.write(image, "JPEG", sos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				sos.flush();
				sos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取随机颜色（X,Y的值越大颜色越浅，X与Y的差值越大差生颜色色差越大）
	 * @param x 随机颜色的下限
	 * @param y 随机颜色的上限
	 * @return 颜色
	 */
	private Color getRandColor(int x, int y) {
		if (x > 255)
			x = 255;
		if (y > 255)
			y = 255;
		int r = x + this.random.nextInt(y - x);
		int g = x + this.random.nextInt(y - x);
		int b = x + this.random.nextInt(y - x);
		return new Color(r, g, b);
	}

	/**
	 * 生成验证码
	 * @return 验证码
	 */
	private String generateCheckCode() {
		//产生验证码的可选字符（可以自定义修改）
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String rands = "";
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * 36);
			rands += chars.charAt(rand);
		}
		return rands;
	}
}
