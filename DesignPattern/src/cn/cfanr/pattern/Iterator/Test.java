package cn.cfanr.pattern.Iterator;
/*
 * 迭代器模式就是顺序访问聚集中的对象
 */
public class Test {
	public static void main(String[] args) {
		Collection collection=new MyCollection();
		Iterator it=collection.iterator();
		
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
	}
}
