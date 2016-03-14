/*
 * An Exception class which is used to handle exceptions based on illegal input argument  
 */
public class IllegalArgumentsException extends RuntimeException {


	private static final long serialVersionUID = 4020449055675733923L;
	
	public IllegalArgumentsException(String msg){
		super(msg);
		System.exit(1);
	}
	
	

}
