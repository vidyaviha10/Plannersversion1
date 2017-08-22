import java.net.InetAddress;
import java.net.UnknownHostException;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	System.out.println(InetAddress.getLocalHost().getHostName());
} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

}
