package kara.diamond.billing.service.base;

import java.net.*;

public class ExceptionHelper {
	public static Exception getActualException(Exception ex) {
		if (ex == null) return null;
		Throwable t = ex.getCause();
		if (t == null) return ex;
		else return getActualException((Exception) t);
	}
	
	public static BaseError getError(Exception ex) {
		BaseError error = new BaseError();
		if (ex instanceof java.sql.SQLException) {
			//	java.sql
			error.setType("Database");
		} else if (ex instanceof ConnectException
				|| ex instanceof NoRouteToHostException
				|| ex instanceof SocketException
				|| ex instanceof SocketTimeoutException
				|| ex instanceof UnknownHostException
				|| ex instanceof UnknownServiceException) {
			//	java.net
			error.setType("Network");
		} else {
			error.setType("Normal");
		}
		error.setMessage(ex.getMessage());
		return error;
	}
}