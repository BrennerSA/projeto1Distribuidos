/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.cliente1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenner
 */
public class Cliente1 {

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            int size=0;
            byte []b=new byte[100000000];
            
            System.out.println("Digite o nome do cliente");
            String client=s.nextLine();
            System.out.println("Digite o endereço do server");
            String server_host=s.nextLine();
            System.out.println("Digite a porta");
            String porta=s.nextLine();
            System.out.println("Digite o nome do arquivo solicitado");
            String arquivo=s.nextLine();
            System.out.println("deseja receber a lista de arquivos na cache? y/n");
            String check=s.nextLine();
            
            String msg=client.concat(";").concat(server_host).concat(";").concat(porta).concat(";").concat(arquivo).concat(";").concat(check);
            
            Socket socket = new Socket("localhost",Integer.parseInt(porta));
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            FileOutputStream file = new FileOutputStream("/home/brenner/Documentos/receives/"+arquivo);
            
            
            output.writeUTF(msg);
            output.flush();
            if(check.equals("y")==true){
                String lista=input.readUTF();
                System.out.println("Lista da cache "+lista);
                output.flush();
                
            }
            
            size=input.read(b);
            if(size==-1){
                System.out.println("file not found");
                System.exit(0);
            }
            file.write(b, 0, size);
            System.out.println("arquivo "+ arquivo +" salvo");
            
            input.close();
            output.close();
            socket.close();
           
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
