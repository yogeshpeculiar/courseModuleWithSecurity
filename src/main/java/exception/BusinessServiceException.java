package exception;

public class BusinessServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	public BusinessServiceException(String businessServiceException) {
		super(businessServiceException);
	}

}
