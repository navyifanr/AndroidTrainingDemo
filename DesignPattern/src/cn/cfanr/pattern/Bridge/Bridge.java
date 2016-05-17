package cn.cfanr.pattern.Bridge;

/*
 * ����һ���ţ�����Sourceable��һ��ʵ��
 */
public abstract class Bridge {
	private Sourceable source;
	
	public void method(){
		source.method();
	}
	
	public Sourceable getSource(){
		return source;
	}
	
	public void setSource(Sourceable source){
		this.source=source;
	}
}
