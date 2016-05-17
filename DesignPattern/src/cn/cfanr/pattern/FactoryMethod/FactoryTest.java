package cn.cfanr.pattern.FactoryMethod;

public class FactoryTest {
	public static void main(String[] args) {
		/*
		 * 工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
		 * 在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相对于第二种，不需要实
		 * 例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
		 *
		 * 但是，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则，这就要用到抽象工厂方法模式
		 */
		//普通工厂方法模式
		SendFactory1 factory=new SendFactory1();
		Sender sender=factory.produce("sms");
		sender.send();

		//多个工厂方法模式
		SendFactory factory2=new SendFactory();
		Sender sender2=factory2.produceMail();
		sender2.send();

		//静态工厂方法模式（多数选用）
		Sender sender3=SendFactory3.produceSms();
		sender3.send();
	}
}

