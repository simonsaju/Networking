import java.net.*;
import java.io.*;
import java.util.*;
class timeserv
{
 DatagramSocket ds;
 DatagramPacket dp;
 byte[] recevPackt;
 byte[] sendPackt;
 BufferedReader in;
 InetAddress ip;
 int port;
 String str;
 public timeserv()
 {
 try
 {
 ds=new DatagramSocket(1456);
 in=new BufferedReader(new InputStreamReader(System.in));
 ip=InetAddress.getByName("localhost");
 Date d=new Date();
 while(true)
 {
 recevPackt=new byte[100];
 dp=new DatagramPacket(recevPackt,recevPackt.length);
 ds.receive(dp);
 String data=new String(dp.getData(),0,dp.getLength());
 System.out.println("Client Message:"+data);
 data=data.toLowerCase();
 if(data.equals("time"))
 str=d+"";
 else if(data.equals("exit"))
 str="Client Exited";
 else
 str="Invalid Request";
 port=dp.getPort();
 ip=dp.getAddress();
 sendPackt=new byte[100];
 System.out.println("Server:"+str);
 sendPackt=str.getBytes();
 dp=new DatagramPacket(sendPackt,sendPackt.length,ip,port);
 ds.send(dp);
 }
 }
 catch(Exception e){}
}
 public static void main(String args[])
 {
 timeserv s=new timeserv();
 }
}