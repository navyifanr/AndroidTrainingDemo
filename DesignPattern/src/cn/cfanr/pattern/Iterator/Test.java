package cn.cfanr.pattern.Iterator;
/*
 * ������ģʽ����˳����ʾۼ��еĶ���
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
