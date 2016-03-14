import java.io.FileNotFoundException;
import java.io.IOException;

public class InvokeCreateGraph {

	/*
	 *  The Entry point of the program
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateGraph create=new CreateGraph();
		try{
		
			create.handleFileInput();
		}
		catch(FileNotFoundException fn){
			fn.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

	}

}
