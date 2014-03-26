package com.sands.sys.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;







public class FileUtil {
	
	 /**
     * print the informations of the file input
     * @param f File
     */
   public static void fileInfo(String name) {
       fileInfo(new File(name));
   }

   public static void fileInfo(File f) {
	   
       System.out.println("--------- Attributes of the files ----------");
       if (f == null) {
           System.out.println("a null reference!!");
       }
       else if (!f.exists()) {
           System.out.println(f.toString() + " file Not Found!");
       }
       else if (f.isFile()) {
           StringBuffer length = new StringBuffer(String.valueOf(f.length()));
           int i = length.length() - 3;
           while (i > 0) {
               length.insert(i, ",");
               i -= 3;
           }
           System.out.println("\"" + f.toString() + "\" is a File.");
           System.out.println("Name: \t\t" + f.getName());
           System.out.println("Readable: \t" + f.canRead());
           System.out.println("Writable: \t" + f.canWrite());
           System.out.println("AbsolutePath:\t" + f.getAbsolutePath());
           System.out.println("Parent:\t\t" + f.getAbsoluteFile().getParent());
           System.out.println("Length:\t\t" + length + " bytes");
       }
       else {
           System.out.println("\"" + f.toString() + "\" is a Directory");
           System.out.println("Name: \t\t" + f.getName());
           System.out.println("Readable: \t" + f.canRead());
           System.out.println("Writable: \t" + f.canWrite());
           System.out.println("AbsolutePath:\t" + f.getAbsolutePath());
           System.out.println("Parent:\t\t" + f.getAbsoluteFile().getParent());
           System.out.println("Subfiles:\t" + f.list().length);
       }
       System.out.println("-------------- fileInfo END ---------------");
   }

   /**
     * compare file byte by byte<br>
     * can compare any file(binary file,text file...)
     * @param file1 File
     * @param file2 File
     * @return boolean
     */
   public static boolean compareFile(String file1, String file2) {
       return compareFile(new File(file1), new File(file2));
   }

   public static boolean compareFile(File file1, File file2) {
       BufferedInputStream in1 = null, in2 = null;
       try {
           in1 = new BufferedInputStream(new FileInputStream(
                   file1));
           in2 = new BufferedInputStream(new FileInputStream(
                   file2));
           int i;
           while ( (i = in1.read()) != -1) {
               if (i != in2.read()) {
                   return false;
               }
           }
           if (in2.read() != -1) {
               return false;
           }
           return true;
       }
       catch (FileNotFoundException ex) {
           System.out.println("File not found!");
       }
       catch (IOException ex) {
           System.out.println("IOException!");
       }
       finally {
           try {
               in1.close();
               in2.close();
           }
           catch (IOException ex1) {
               System.out.println("IOException when closing!");
           }
       }
       return false;
   }

   /**
     * the java.io.File.renameTo(File,String newname)
     * actually move the file to newname¡®s path and rename it which is inconvenient.<br>
     * this method just rename the file and keep where it is,ignore the move<br>
     * and return the new File¡®s reference<p>
     * eg. file = renameFile(file,"NewName.xxx");
     * @param file File
     * @param name String the newName
     * @return File the new File¡®s reference
     */
   public static File renameFile(String file, String name) {
       return renameFile(new File(file), name);
   }

   public static File renameFile(File file, String name) {
       File newname;
       if (file == null || !file.exists()) {
           System.out.println("File not found!");
           return null;
       }
       if (file.getParent() == null) {
           newname = new File(name);
           file.renameTo(newname);
       }
       else {
           newname = new File(file.getParentFile(), name);
           file.renameTo(newname);
       }
       System.out.println("rename is done: " + file + " -> " + newname);
       return newname;
   }

   /**
     * use java.io.File.renameTo(File,String newname) to move file<p>
     * parameters must be a file and a directory<br>
     * return a reference point to the new file
     * @param scr String
     * @param dir String
     * @return File a reference point to the new file
     */
   public static File moveFile(String scr, String dir) {
       return moveFile(new File(scr), new File(dir));
   }

   public static File moveFile(File scr, File dir) {
       if (scr == null || dir == null) {
           System.out.println("a null reference!");
           return null;
       }
       if (!scr.exists() || !dir.exists() || scr.isDirectory() || dir.isFile()) {
           System.out.println("not file or directory or not exist!");
           return null;
       }
       File f = new File(dir, scr.getName());
       if (f.exists()) {
           System.out.println("target file has existed!");
       }
       scr.renameTo(f);
       System.out.println("move file done: " + scr + " -> " + f);
       return f;
   }

   /**
     * turn file to String<p>
     * maybe you can use it to access file randomly through the string<br>
     * but it maybe fault when trun a big file to string
     * @param file String
     * @return String
     */
   public static String fileToString(String file) {
       String lineStr = "", string = "";
       int i;
       BufferedReader in = null;
       try {
           in = new BufferedReader(new FileReader(file));
           while ( (i = in.read()) != -1) {
               string += (char) i;
           }
           string = string.trim();
           return string;
       }
       catch (FileNotFoundException ex) {
           System.out.println("File Not Found!");
       }
       catch (IOException ex) {
           System.out.println("IO exception!");
       }
       finally {
           try {
               in.close();
           }
           catch (IOException ex1) {
               System.out.println("IOException when closing!");
           }
       }
       return null;
   }

   /**
     * write the string to file<br>
     * if fail return false else return true
     * @param src String
     * @param file String
     * @return boolean
     */
   public static boolean stringToFile(String src, String file) {
       BufferedWriter out = null;
       try {
           out = new BufferedWriter(new FileWriter(file));
           out.write(src);
//           out.write( src.g );
           return true;
       }
       catch (Exception ex) {
           System.out.println("IO exception!");
       }
       finally {
           try {
               out.close();
           }
           catch (IOException ex) {
               System.out.println("IOException when closing!");
           }
       }
       return false;
   }

   /**
     * only used to copy character files<br>
     * local char -> int -> unicode -> int -> local char
     * @param src String
     * @param dest String
     */
   static public void copyFileByChar(String src, String dest) {
       String lineStr;
       BufferedReader in = null;
       BufferedWriter out = null;
       try {
           in = new BufferedReader(new FileReader(src));
           out = new BufferedWriter(new FileWriter(dest));
           while ( (lineStr = in.readLine()) != null) {
               out.write(lineStr);
               out.newLine();
           }
           System.out.println("copy is done !");
       }
       catch (FileNotFoundException ex) {
           System.out.println("File Not Found!");
       }
       catch (IOException ex) {
           System.out.println("IO exception!");
       }
       finally {
           try {
               in.close();
               out.close();
           }
           catch (IOException ex1) {
               System.out.println("IOException when closing!");
           }
       }

   }

   /**
     * copy file by byte<br>
     * can copy any file,because any file is made of bytes<br>
     * bytes -> int -> bytes
     * @param src String
     * @param dest String
     */
   public static void copyFile(String src, String dest) {
       copyFile(new File(src), new File(dest));
   }

   public static void copyFile(File src, File dest) {
       int b;
       BufferedInputStream in = null;
       BufferedOutputStream out = null;
       try {
           in = new BufferedInputStream(new FileInputStream(src));
           out = new BufferedOutputStream(new FileOutputStream(
                   dest));
           while ( (b = in.read()) != -1) {
               out.write(b);
           }
           System.out.println("CopyFile is done: " + src + " -> " + dest);
       }
       catch (FileNotFoundException ex) {
           System.out.println("File Not Found!");
       }
       catch (IOException ex) {
           System.out.println("IO exception!");
       }
       finally {
           try {
               in.close();
               out.close();
           }
           catch (IOException ex1) {
               System.out.println("IOException when closing!");
           }
       }
   }
	
}
