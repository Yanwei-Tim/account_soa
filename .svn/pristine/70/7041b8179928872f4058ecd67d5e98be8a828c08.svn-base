package com.tianque.exception.base;

/**
 * 系统运行时异常基类 <br/>
 * e.g.&nbsp;<br/>
 * <br/>
 * 
 * <code>@Component<br/>@Exceptional(errorCode = "EE100-01") <br/>
 * public ExampleAppRuntimeException extends BaseAppRuntimeException{ }<br/><br/>
 * 其子类如果使用<code>@Exceptional注解，而isLogging和handler没有指定，则默认为isLogging=true,handler=DefaultExceptionHandler.class
 * 
 * @author yulei
 * @date 2014-9-2上午10:08:26
 * @version 1.0.0
 * 
 */
public class BaseAppRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6021077900819863433L;
	private String title;

	public BaseAppRuntimeException() {
		super();
	}

	public BaseAppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseAppRuntimeException(String title, String message, Throwable cause) {
		super(message, cause);
		this.title = title;
	}

	public BaseAppRuntimeException(String message) {
		super(message);
	}

	public BaseAppRuntimeException(Throwable cause) {
		super(cause);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
