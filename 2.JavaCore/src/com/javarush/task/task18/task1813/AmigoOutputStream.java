package com.javarush.task.task18.task1813;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    FileOutputStream original;

    public static String fileName = "C:/tmp/result.txt";

    public AmigoOutputStream (FileOutputStream file) throws FileNotFoundException{
        super(fileName);
        this.original = file;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
        //String s = "s";
        //System.out.println(s.getBytes());
    }

    @Override
    public void write(int b) throws IOException {
        original.write(b);
    }

    @Override
    public void close() throws IOException {
        original.flush();
        write("JavaRush © All rights reserved.".getBytes());
        original.close();
    }

    @Override
    public void flush() throws IOException {
        original.flush();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        original.write(b, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        original.write(b);
    }
}
