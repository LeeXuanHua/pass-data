package lab2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Rfc865UdpClient {
	static String MY_IP = "172.21.144.65";
	static String HOST_NAME = "swlab2-c.scse.ntu.edu.sg";
	
	//	Name: Lee Xuan Hua
	//	Group: FDDP2
	//	IP Address: 172.21.144.65

	public static void main(String[] args) {
		
		System.out.println("Starting client...");
		
		//
		// 1. Open UDP socket
		//
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
		InetAddress host_address = null;
		try {
			host_address = InetAddress.getByName(HOST_NAME);
			System.out.println(host_address);
			MY_IP = host_address.getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		
		try {
			//
			// 2. Send UDP request to server
			//
			String sentMsg = new String("Lee Xuan Hua, FDDP2, " + MY_IP);
			byte[] buffer = sentMsg.getBytes();
			DatagramPacket request = new DatagramPacket(buffer, buffer.length, host_address, 17);
			socket.send(request);
			
			// Note that to get the string, we also need the byte size of request
			System.out.println("Sent request: " + new String(request.getData(), 0, request.getLength()));
			
			
			//
			// 3. Receive UDP reply from server
			//
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            
            // Note that to get the string, we also need the byte size of reply
            System.out.println("Received reply: " + new String(reply.getData(), 0, reply.getLength()));
            
            String receivedMsg = new String(reply.getData(), 0, reply.getLength());
            
            socket.close();
            System.out.println("Closed socket...");
            
            // Output as per lab requirements
            System.out.println("Message send: " + sentMsg);
            System.out.println("Message received: " + receivedMsg);
            System.out.println("Your PC IP address: " + MY_IP);
            System.out.println("Quote of Day server IP address: " + reply.getAddress());
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
