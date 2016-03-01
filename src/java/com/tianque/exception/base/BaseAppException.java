package com.tianque.exception.base;

/**
 * 系统非运行时异常基类,系统中所有自定义的非运行时异常必须继承该类<br/>
 * e.g.&nbsp;<br/>
 * <br/>
 * 
 * <code>@Component<br/>@Exceptional(errorCode = "EE100-01") <br/>
 * public ExampleAppException extends BaseAppException{ }<br/><br/>
 * 其子类如果使用<code>@Exceptional注解，而isLogging和handler没有指定，则默认为isLogging=true,handler=DefaultExceptionHandler.class
 * 
 * @author yulei
 * @date 2014-9-2上午10:09:41
 * @version 1.0.0
 * 
 */
public class BaseAppException extends Exception {

	private static final long serialVersionUID = 8343048459443313229L;
	private String title;

	public BaseAppException() {
		super();
	}

	public BaseAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseAppException(String title, String message, Throwable cause) {
		super(message, cause);
		this.title = title;
	}

	public BaseAppException(String message) {
		super(message);
	}

	public BaseAppException(Throwable cause) {
		super(cause);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
