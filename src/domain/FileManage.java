/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Jaziel
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileManage {

    public boolean existFile(String address) {
        File file = new File(address);
        return file.exists();
    }

    public void addManager(String username, String password, String keyWord) throws IOException, ListException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("managersFile.txt", true));//no sobreescibe
        try {
            bw.write(username + "," + password + "," + keyWord);
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean readManagers(String fileDir, String username, String password) throws IOException {//Lee todos los managers ingresados

        BufferedReader br = new BufferedReader(new FileReader(fileDir));

        String linea = br.readLine();
        if (linea != null) {
            while (linea != null) {
                try {
                    String managers[] = linea.split(",");//Divide la linea en un array de Strings

                    String decyptedKey = dencrypt(managers[2], "DR54");

                    String dencryptedPassword = dencrypt(managers[1], decyptedKey);

                    if (managers[0].equalsIgnoreCase(username) && password.equalsIgnoreCase(dencryptedPassword)) {//Si encuentra un manager resgistrado
                        return true;
                    }
                    linea = br.readLine();
                } //Lee toda una linea
                catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(FileManage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//Fin linea+
        br.close();
        return false;
    }

    public void addCareer(Career career) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("careersFile.txt", true));//no sobreescibe
        try {
            bw.write(career.getId() + "," + career.getDescription());
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyCareer(DoublyLinkedList list) throws ListException, IOException {

        String result = "";
        for (int i = 1; i <= list.size(); i++) {
            Career career = (Career) list.getNode(i).data;
            result += i + "," + career.getDescription() + "\n";//No trabajar con el id sino con e i
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("careersFile.txt"));//sobreescibe
        try {
            String careers[] = result.split("\n");
            for (int i = 0; i < careers.length; i++) {
                bw.write(careers[i]);
                bw.newLine();
                bw.flush();
            }
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCourses(Course course) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("coursesFile.txt", true));//no sobreescibe
        try {
            bw.write(course.getId() + "," + course.getName() + "," + course.getCredits() + "," + course.getCarrerID());
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void modifyCourses(CircularDoublyLinkedList list) throws ListException, IOException {

        String result = "";
        for (int i = 1; i <= list.size(); i++) {
            Course course = (Course) list.getNode(i).data;
            result += i + "," + course.getId() + "," + course.getName() + "," 
                    + course.getCredits()+ "," + course.getCarrerID() + "\n";//No trabajar con el id sino con e i
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("coursesFile.txt"));//sobreescibe
        try {
            String course[] = result.split("\n");
            for (int i = 0; i < course.length; i++) {
                bw.write(course[i]);
                bw.newLine();
                bw.flush();
            }
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public DoublyLinkedList loadListCareer(String fileDir, DoublyLinkedList list) throws FileNotFoundException, IOException, ListException {

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();
        //DoublyLinkedList aux = new DoublyLinkedList();
        if (linea != null) {
            while (linea != null) {
                String careers[] = linea.split(",");//Divide la linea en un array de String
                list.add(new Career(careers[1]));
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return list;
    }

    public CircularDoublyLinkedList loadListCourse(String fileDir, CircularDoublyLinkedList list) throws FileNotFoundException, IOException, ListException {

        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        String linea = br.readLine();
        //DoublyLinkedList aux = new DoublyLinkedList();
        if (linea != null) {
            while (linea != null) {
                String course[] = linea.split(",");//Divide la linea en un array de String
                list.add(new Course(course[0], course[1], Integer.parseInt(course[2]), Integer.parseInt(course[3])));
                linea = br.readLine();
            }
        }//Fin linea+
        br.close();
        return list;
    }

    /**
     * Crea la clave de encriptacion usada internamente
     *
     * @param clave Clave que se usara para encriptar
     * @return Clave de encriptacion
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private SecretKeySpec createKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] keyEncrypt = key.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        keyEncrypt = sha.digest(keyEncrypt);
        keyEncrypt = Arrays.copyOf(keyEncrypt, 16);

        //Construye una clave secreta apartir de una matriz de bytes dada
        SecretKeySpec secretKey = new SecretKeySpec(keyEncrypt, "AES");

        return secretKey;
    }

    /**
     * Aplica la encriptacion AES a la cadena de texto usando la clave indicada
     *
     * @param data Cadena a encriptar
     * @param secretK Clave para encriptar
     * @return Información encriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String encrypt(String data, String secretK) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = this.createKey(secretK);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] dataEncrypt = data.getBytes("UTF-8");
        byte[] bytesEncrypt = cipher.doFinal(dataEncrypt);
        String encrypt = Base64.getEncoder().encodeToString(bytesEncrypt);

        return encrypt;
    }

    /**
     * Desencripta la cadena de texto indicada usando la clave de encriptacion
     *
     * @param dataEncrypt Datos encriptados
     * @param secretK Clave de encriptacion
     * @return Informacion desencriptada
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String dencrypt(String dataEncrypt, String secretK) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKey = this.createKey(secretK);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncrypt = Base64.getDecoder().decode(dataEncrypt);
        byte[] dataDencrypt = cipher.doFinal(bytesEncrypt);
        String data = new String(dataDencrypt);

        return data;
    }
}

//finClass

