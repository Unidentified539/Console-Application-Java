/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
package org.con.idea;

import org.jasypt.util.text.BasicTextEncryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        application();
    }

    public static void application() throws IOException {
        Scanner sc = new Scanner(System.in);

        String menu;
        menu = "    Application Menu \n" +
                "1. Type one to decrypt a file.\n" +
                "2. Type two to encrypt a file.\n" +
                "3. Type three to encrypt text from terminal\n" +
                "4. Type four to decrypt text from terminal.\n" +
                "5. Type five to exit.\n" +
                "*  Disclaimer if you pick one or two the path of the file you enter will be rewritten as the encrypted version.\n" +
                "                Pick an option.";






        System.out.println(menu);
        String choice1 = sc.nextLine();
        String one = "1";
        String two = "2";
        String three = "3";
        String four = "4";
        String five = "5";
        if (!one.equals(choice1) && !two.equals(choice1) && !three.equals(choice1) && !four.equals(choice1) && !five.equals(choice1)) {
            System.out.println("ERROR: You have to enter 1, 2, 3, 4, or 5 for this to work. Make sure there are no spaces and that you only one of these numbers.");
            application();
        }


        switch (choice1) {
            case "1":
                System.out.println("Enter the path of the file you want to decrypt");
                String filepath = sc.nextLine();
                String file = readFile(filepath);
                System.out.println("Enter your key.");
                String key = sc.nextLine();
                String decrypted = decryptThis(file, key);
                writeToFile(filepath, decrypted);
                System.out.println("Your file has been decrypted to this filepath (" + filepath + ")" );
                application();

            case "2":
                System.out.println("Enter the path of the file you want to encrypt");
                String filepath0 = sc.nextLine();
                String file0 = readFile(filepath0);
                System.out.println("A key to encrypt your file with");
                String key0 = sc.nextLine();
                String encrypted = encrypt(file0, key0);
                writeToFile(filepath0, encrypted);
                System.out.println("Your file has been encrypted to this filepath (" + filepath0 + ")" );
                application();

            case "3":
                System.out.println("Enter the text you want to encrypt.");
                String text = sc.nextLine();
                System.out.println("Enter a key to encrypt your text with");
                String key1 = sc.nextLine();
                String encrypted0 = encrypt(text, key1);
                System.out.println("Encrypted text: " + encrypted0);
                application();

            case "4":
                System.out.println("Enter the text you want to encrypt.");
                String text0 = sc.nextLine();
                System.out.println("Enter a key to encrypt your text with");
                String key2 = sc.nextLine();
                String encrypted2 = decryptThis(text0, key2);
                System.out.println("Decrypted text: " + encrypted2);
                application();

            case "5":
                System.out.println("Goodbye!");
        }

    }

    public static String decryptThis(Object decryptThis, Object key) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(String.valueOf(key));
        String plainText = textEncryptor.decrypt((String) decryptThis);
        return plainText;
    }

    public static String encrypt(String encryptThis, String yourKey) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(yourKey);
        String myEncryptedText = textEncryptor.encrypt(encryptThis);
        return myEncryptedText;
    }

    public static String readFile(String filePath) throws FileNotFoundException, FileNotFoundException {

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            return data;
        }
        myReader.close();
        return filePath;
    }

    public static void writeToFile(String filepath, Object writeThis) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(String.valueOf(writeThis));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void newFile(String fileName) {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
            } else {
                System.out.println("Error");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}