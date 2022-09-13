/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenner
 */
public class MultithreadServer extends Thread{
    
    public MultithreadServer(int porta, Cache c){
     this.porta=porta;   
     this.c=c;
     
    }
    
    private static Lock lock= new ReentrantLock();
    ServerSocket serverSocket;
    Cache c = null;
    int porta;
    
    void createServerSocket(int porta) throws IOException{
        
        serverSocket= new ServerSocket(porta);
    }
    
    Socket waitConnection() throws IOException{
        
        
        Socket socket = serverSocket.accept();
        return socket;
    }
    
    public void treatConnection(Socket socket) throws IOException{
        
        try {
            ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            
            
            String msg = input.readUTF();
            String[] array = new String[5];
            array=msg.split(";");
            System.out.println("O cliente "+array[0]+"solicita o arquivo "+array[3]);
            if(array[4].equals("y")==true){
                String lista=c.listaCache();
                output.writeUTF(lista);
                output.flush();
            }
            String s=c.search(array[3]);
            if(s!=null){
                byte[] b=s.getBytes();
                output.write(b, 0, b.length);
            }else{

            output.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeSocket(socket);
        }
    }
    
    public void closeSocket(Socket socket) throws IOException{
        socket.close();
    }
    
    @Override
    public void run(){
        
        try{
            
            MultithreadServer server = new MultithreadServer(porta,c);
            server.createServerSocket(this.porta);
            while(true){
            System.out.println("Esperando conexão na porta "+porta+". Diretorio de arquivos: /home/brenner/Documentos/");
            
            Socket socket=server.waitConnection();
            System.out.println("Conexão estabelecida");
            lock.lock();
            server.treatConnection(socket);
            lock.unlock();
            }
        }catch(IOException e){
            
        }
    }
    
    
}
