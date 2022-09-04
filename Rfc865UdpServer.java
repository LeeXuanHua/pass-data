package Lab2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {

	public static void main(String[] args) {
		// Initialize variables
		InetAddress address = null;
		int port = 0;
		byte[] buffer = new byte[1024];
		int buffer_length = buffer.length;
		
		System.out.println("Starting server...");
		
		//
		// 1. Open UDP socket at well-known port
		//
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(17);
		} catch (SocketException e) {}
		
		
		while (true) {
			try {
				//
				// 2. Listen for UDP request from client
				//
				DatagramPacket request = new DatagramPacket(buffer, buffer_length);
				socket.receive(request);
				
				// Note that to get the string, we also need the byte size of request
				System.out.println("Received request: " + new String(request.getData(), 0, request.getLength()));
				
				address = request.getAddress();
				port = request.getPort();
				
				
				//
				// 3. Send UDP reply to client
				//
				buffer = "Sample Quote of the Day".getBytes();
				buffer_length = buffer.length;
				DatagramPacket reply = new DatagramPacket(buffer, buffer_length, address, port);
				socket.send(reply);
				
				// Note that to get the string, we also need the byte size of reply
				System.out.println("Sent reply: " + new String(reply.getData(), 0, reply.getLength()));
				 
			} catch (IOException e) {
				socket.close();
				System.out.println("Closed socket");
			}
		}
	}
}

//Online Tutorials for Realtime UDP via scanner
//public class Rfc865UdpServer {
//	
//	private DatagramSocket datagramSocket;
//	private byte[] buffer = new byte[256];
//	
//	public Rfc865UdpServer(DatagramSocket datagramSocket) {
//		this.datagramSocket = datagramSocket;
//	}
//	
//	public void receiveThenSend() {
//		while (true) {
//			System.out.println("Awaiting data...");
//			try {
//				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
//				datagramSocket.receive(datagramPacket);
//				InetAddress inetAddress = datagramPacket.getAddress();
//				int port = datagramPacket.getPort();
//				String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
//				System.out.println("Message from client: " + messageFromClient);
//				datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
//				datagramSocket.send(datagramPacket);
//			} catch (IOException e) {
//				e.printStackTrace();
//				break;
//			}
//		}
//	}
//	
//	
//	public static void main(String[] argv) {
//		DatagramSocket datagramSocket = null;
//		try {
//			datagramSocket = new DatagramSocket(1234);
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
//		Rfc865UdpServer server = new Rfc865UdpServer(datagramSocket);
//		server.receiveThenSend();
//	 }
//}
