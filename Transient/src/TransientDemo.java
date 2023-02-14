import java.io.*;

public class TransientDemo implements Serializable {
    int i = 10, j = 20;
    transient  int k = 30;
    transient static int l = 40;
    transient final int m = 50;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        TransientDemo td = new TransientDemo();
        FileOutputStream fos = new FileOutputStream("test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(td);

        FileInputStream fis = new FileInputStream("test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TransientDemo tdInput = (TransientDemo) ois.readObject();

        System.out.println("\n Non Transient Variables: \n i: " + tdInput.i + ", j: " + tdInput.j);
        System.out.println("\n Transient Variable: \n k: " + tdInput.k);
        System.out.println("\n Transient has no effect on static variables: \n l: " + tdInput.l);
        System.out.println("\n Transient has no effect on final variables: \n m:  " + tdInput.m);

        // Sending transient variables over the network
        TransientServer.runServer();
    }
}
