package com.cnc.common.persistence.exception;

import com.cnc.common.lang.exception.CommonException;

/**
 * @描述: 业务异常基类，所有业务异常都必须继承于此异常
 * @作者: haw
 * @创建时间: 2016/12/15
 * @版本: 1.0
 */
public class BizException extends CommonException {

	private static final long serialVersionUID = -5875371379845226068L;
	/**
	 * 异常信息
	 */
	protected String msg;
	/**
	 * 具体异常码
	 */
	protected int code;

	public BizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BizException newInstance(String msgFormat, Object... args) {
		return new BizException(this.code, msgFormat, args);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public BizException(BizExceptionType type){
		this.code = type.getCode();
		this.msg = type.getMsg();
	}
}
