import java.net.*;
import java.io.*;
import java.util.*;
class timecli
{
 DatagramSocket ds;
 DatagramPacket dp;
 byte[] sendPackt;
 byte[] recevPackt;
 BufferedReader in;
 InetAddress ip;
 int port;
 String str;
 public timecli()
 {
 try
 {
 port=1456;
 in=new BufferedReader(new InputStreamReader(System.in));
 ip=InetAddress.getByName("localhost");
 ds=new DatagramSocket();
 while(true)
 {
 sendPackt=new byte[100];
 System.out.print("Client:");
 str=in.readLine();
 sendPackt=str.getBytes();
 dp=new DatagramPacket(sendPackt,sendPackt.length,ip,port);
 ds.send(dp);
 if(str.equals("exit"))
 System.exit(0);
 recevPackt=new byte[100];
 dp=new DatagramPacket(recevPackt,recevPackt.length);
 ds.receive(dp);
 String data=new String(dp.getData(),0,dp.getLength());
 System.out.println("Server Message:"+data);
 if(data.equals("exit"))
 System.exit(0);
 port=dp.getPort();
 ip=dp.getAddress();
 }
 }
 catch(Exception e)
 {
 System.out.println(e);
 }
 }
 public static void main(String args[])
 {
 timecli s=new timecli();
 }
}