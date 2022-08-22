package TestNG0013ListenerClasses;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class ListenerIAnnotationTransformer implements IAnnotationTransformer{
	
	int counter = 2;

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		if (testMethod.getName().equals("enterEmail")) {
			System.out.println("Changing invocation for the following method: " + testMethod.getName());
			annotation.setInvocationCount(counter);
		}
	}
}