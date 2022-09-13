/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author brenner
 */
public class Cache {
    public Nodo primeiro=null;
    public Nodo ultimo=null;
    public int size=0;
    
    public void Adicionar (String nome) throws FileNotFoundException, IOException{
        try{
        FileInputStream file = new FileInputStream("/home/brenner/Documentos/"+nome);
        byte[] b = new byte[3145728];
        int i=file.read(b, 0, b.length);
        String s = new String(b, 0, i);
        
        Nodo aux = new Nodo();
        byte[] dado=s.getBytes();
        aux.dado=dado;
        aux.nome=nome;
        
        if (primeiro==null){
            primeiro=aux;
            ultimo=aux;
            size=size+aux.dado.length;
        }else if(size>=45){//67108864
            size=size-primeiro.dado.length;
            primeiro=primeiro.next;
        }else{
            ultimo.next=aux;
            aux.ant=ultimo;
            ultimo=aux;
            size=size+aux.dado.length;
        }
        }catch(FileNotFoundException e){
            System.out.println("File not Found");
        }
        
    }
    
    public String search(String nome) throws FileNotFoundException, IOException{
        
        
        if(primeiro==null){
            Adicionar(nome);
        }else{
            int hit=0;
            Nodo aux=primeiro;
            while(aux!=null){
                if(aux.nome.equals(nome)==true){
                    String dado=new String(aux.dado,0,aux.dado.length);
                    System.out.println("Arquivo encontrado na cache");
                    return dado;
                    //retornar arquivo
                }
                aux=aux.next;
            }
            if (hit==0){
                FileInputStream file = new FileInputStream("/home/brenner/Documentos/"+nome);
                byte[] b = new byte[3145728];
                int i=file.read(b, 0, b.length);
                String s = new String(b, 0, i);
                Adicionar(nome);
                System.out.println("O arquivo não esta na cache, arquivo enviado para a cache");
                return s;
            }
        }
        try{
        FileInputStream file = new FileInputStream("/home/brenner/Documentos/"+nome);
        byte[] b = new byte[3145728];
        int i=file.read(b, 0, b.length);
        String s = new String(b, 0, i);
        return s;
        }catch(FileNotFoundException e){
            System.out.println("File not Found");
            
        }
        return null;
        
        
    }
    
    public String listaCache(){
        String lista ="";
        Nodo aux = new Nodo();
        aux=primeiro;
        while(aux!=null){
            lista = lista.concat(aux.nome).concat(";");
            aux=aux.next;
        }
        return lista;
    }
    
    
}
