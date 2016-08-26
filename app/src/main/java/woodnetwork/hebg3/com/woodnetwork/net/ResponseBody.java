package woodnetwork.hebg3.com.woodnetwork.net;

import java.util.List;

/** json数据的内容数�? */
public class ResponseBody {
	/**
	 * 
	 */
	public Base base;
	/**
	 * 需要把json转化成的对象，data中的第一层对象
	 */
	public Object obj;
	/**
	 * 需要把json转化成的集合，如果返回的json为集合形式使用
	 */
	@SuppressWarnings("rawtypes")
	public List list;// 对象数组
	@Override
	public String toString() {
		return "ResponseBody [base=" + base + ", obj=" + obj + ", list=" + list
				+ "]";
	}

	
}