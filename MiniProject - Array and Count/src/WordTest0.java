import java.util.Scanner;

class WordTest0 {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        WordGen.initialise(input);
        System.out.print("Enter the number of words you wish to generate: ");
        int n = input.nextInt();
        String[] words = new String[n];
        for(int i=0; i<n; i++)
            words[i]=WordGen.make();
        System.out.println("The words generated are:");
        for(int i=0; i<n; i++)
            System.out.println(words[i]);
    }
}