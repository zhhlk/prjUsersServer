package com.server;
import com.biz.*;
import com.bean.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class UsersServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private UsersBiz ubiz=new UsersBizImp();

    public UsersServer(){
        try {
            serverSocket=new ServerSocket(8899);//创建服务器socket对象绑定端口号
            System.out.println("服务器已经启动，请勿关闭！");

            while(true){
                socket=serverSocket.accept();
                in=socket.getInputStream();
                objectInputStream=new ObjectInputStream(in);//通过低级输入流构建高级对象输入流
                Users us= (Users) objectInputStream.readObject();//将对象输入流中传递的用户对象转为Users对象,反序列化
                boolean flag=ubiz.check(us);//验证
                //验证完成后，向客户端发送结果码
                out=socket.getOutputStream();
                if(flag){
                    out.write(1);
                }else {
                    out.write(0);
                }
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
