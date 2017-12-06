import java.util.Date;
import java.util.Scanner;

class WordTest2 {


    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        WordGen.initialise(input);
        System.out.print("Enter the number of words you wish to generate initially: ");
        int n = input.nextInt();
        WordStore words = new WordStoreImp(n);
        for(int i=0; i<n; i++)
            words.add(WordGen.make());
        System.out.print("Enter number of words you wish to test: ");
        n = input.nextInt();
        String[] testWords = new String[n];
        for(int i=0; i<n; i++)
            testWords[i]=WordGen.make();
        long time1,time2;
        int count;
        time1 = new Date().getTime();
        for(int i=0; i<n; i++)
            count = words.count(testWords[i]);
        time2 = new Date().getTime();
        System.out.print("Time taken to test membership of "+n+" words ");
        System.out.println("is "+(time2-time1)+"ms");
    }
}