package cn.cfanr.pattern.Prototype;
/*
 * 该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象.
 * 一个原型类，只需要实现Cloneable接口，覆写clone方法，此处clone方法可以改成任意的名称，
 * 因为Cloneable接口是个空接口，你可以任意定义实现类的方法名
 */
public class Prototype implements Cloneable {
	public Object clone() throws CloneNotSupportedException {
		Prototype proto = (Prototype) super.clone(); //此处调用的是Object的方法
		return proto;
	}
}
