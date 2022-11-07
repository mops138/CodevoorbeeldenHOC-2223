import java.io.*;
import java.util.Scanner;

public class BinaireFileDemo {

    public void opslaanGetallen(String bestandsnaam){

        //ObjectOutputStream object aanmaken`
        ObjectOutputStream output = null;

        try {

            // objectOutputStream initaliseren met FileOutputStream object)
            output = new ObjectOutputStream(new FileOutputStream(bestandsnaam));

            //Getallen inlezen
            Scanner keyboard = new Scanner(System.in);
            System.out.println ("Geef een positief getal .");
            System.out.println ("Om te stoppen geef een negatief getal.");
            int getal;

            do
            {
                getal = keyboard.nextInt ();
                output.writeInt(getal);
                // int wegschrijven naar ObjectOutputStream

            }
            while (getal >= 0);

            output.close();
            // Stream sluiten



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void lezenGetallen(String bestandsnaam) {
        //aanmaken ObjectInputStream object
        //ObjectOutputStream object aanmaken`
        ObjectInputStream input = null;


        try {

            input = new ObjectInputStream(new FileInputStream(bestandsnaam));
            int getal = input.readInt();

            while (getal > 0) {
                System.out.println(getal);
                getal = input.readInt();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void bewarenStudenten(String bestandsnaam){
        Student student1 = new Student("Boels","Karel","45678");
        Student student2 = new Student("De Smet","Dirk","67567");

        //aanmaken ObjectOutputStream

        //ObjectOutputStream object aanmaken`
        ObjectOutputStream output = null;

        try {
            output = new ObjectOutputStream(new FileOutputStream(bestandsnaam));
            output.writeObject(student1);
            output.writeObject(student2);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        //wegschrijven van studenten + stoppen bij fout

        // sluiten stream
    }

    public void lezenStudenten(String bestandsnaam){
        //aanmaken ObjectInpuStream

        //lezen van objecten en stoppen bij EOFException

        ObjectInputStream input = null;

        try {
            try{
                input = new ObjectInputStream(new FileInputStream(bestandsnaam));
                while (true) {
                    Student student = (Student) input.readObject();
                    System.out.println(student.toString());
                }
            } catch (EOFException e) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void bewarenStudenten2(String bestandsnaam, Student[] studenten){
        //ObjectOutputStream object aanmaken`
        ObjectOutputStream output = null;

        try {
            output = new ObjectOutputStream(new FileOutputStream(bestandsnaam));
            output.writeObject(studenten);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        //wegschrijven van studenten + stoppen bij fout

        // sluiten stream
    }

    public Student[] lezenStudenten2(String bestandsnaam){
        //aanmaken ObjectInpuStream

        //lezen van objecten en stoppen bij EOFException

        ObjectInputStream input = null;
        Student[] students = null;

        try {
            input = new ObjectInputStream(new FileInputStream(bestandsnaam));
            students = (Student[]) input.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void main(String[] args) {
        //DEMO 1
        //String bestandsnaam1 = "numbers.dat";
        //BinaireFileDemo demo1 = new BinaireFileDemo();
        //demo1.opslaanGetallen(bestandsnaam1);
        //demo1.lezenGetallen(bestandsnaam1);

        //Demo 2
        //String bestandsnaam2 = "student.dat";
        //BinaireFileDemo demo2 = new BinaireFileDemo();
        //demo2.bewarenStudenten(bestandsnaam2);
        //demo2.lezenStudenten(bestandsnaam2);

        //Demo 3
        Student student1 = new Student("Boels","Karel","45678");
        Student student2 = new Student("De Smet","Dirk","67567");

        Student[] studenten = new Student[2];
        studenten[0] = student1;
        studenten[1] = student2;

        String bestandsnaam3 = "studentenArray.dat";
        BinaireFileDemo demo3 = new BinaireFileDemo();
        demo3.bewarenStudenten2(bestandsnaam3,studenten);
        Student[] studenten2 = demo3.lezenStudenten2(bestandsnaam3);

    }
}
