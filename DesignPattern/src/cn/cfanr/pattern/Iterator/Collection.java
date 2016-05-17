package cn.cfanr.pattern.Iterator;

public interface Collection {
	public Iterator iterator();
	//取得集合元素
	public Object get(int i);
	//取得集合大小
	public int size();
}

