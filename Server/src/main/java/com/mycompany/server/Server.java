/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.server;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenner
 */
public class Server {
    
    
    
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Cache c = new Cache();
        MultithreadServer serv = new MultithreadServer(5555,c);
        MultithreadServer serv2 = new MultithreadServer(5556,c);
        serv.start();
        serv2.start();
        
        
        
    }
}
