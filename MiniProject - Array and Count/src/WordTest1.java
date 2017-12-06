import java.util.Scanner;

class WordTest1 {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        WordGen.initialise(input);
        System.out.print("Enter the number of words you wish to generate: ");
        int n = input.nextInt();
        WordStore words = new WordStoreImp(n);
        for(int i=0; i<n; i++)
            words.add(WordGen.make());
        String line = input.nextLine();
        System.out.println("Enter words to test, empty line to exit");
        line = input.nextLine();
        while(!line.equals(""))
        {
            String[] wordlist = line.split(" ");
            for(int i=0; i<wordlist.length; i++)
            {
                int count = words.count(wordlist[i]);
                System.out.print("\""+wordlist[i]+"\" ");
                if(count==0)
                    System.out.println("NOT generated");
                else if(count==1)
                    System.out.println("generated once");
                else
                    System.out.println("generated "+count+" times ");
            }
            line = input.nextLine();
        }
    }
}
