
import java.util.Scanner;

public class PracticalTest01 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int[] array = new int[100];
        int index;
        for(int i =0;i<100;i++){
            array[i]=(int)(Math.random() * 100 +1);
        }
        try{
        System.out.print("Enter the index of array :");
        index=input.nextInt();
        System.out.println("Value at index " + index + " : " + array[index]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
    }
    }
}
    
    
        

