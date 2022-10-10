import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Wordladder {
    private static ArrayList<String> dictionary = new ArrayList<>();

    public static void main(String args[]){
        Scanner file = null;

        try { file = new Scanner(new File("C:\\Users\\aadiu\\Desktop\\Programming Files\\School Labs\\WordLadder Lab\\Wordladder Lab\\src\\dictionary.txt")); }
        catch (FileNotFoundException e) {  }

        while(file.hasNext())
            dictionary.add(file.next());
        
        String start =    "fears".toUpperCase();
        String target =   "shear".toUpperCase();

        Stack<String> ladder = new Stack<>();
        ladder.add(start.toUpperCase());

        Stack<ArrayList<String>> pool = new Stack<>();
        ArrayList<String> topLayer = new ArrayList<>();
        topLayer.add(start);
        pool.push(topLayer);

        search(pool, ladder,  start.toUpperCase(), start.toUpperCase(), target.toUpperCase());

        ladder = refine(ladder, target);

        System.out.println(ladder);
    }

    private static Stack<String> refine(Stack<String> ladder, String target){

        Stack<String> refined = new Stack<>();
        
        for(int i = 0; i < ladder.size(); i++){
            if(ladder.get(i).equals(target)){
                refined.add(ladder.get(i));
                break;
            }
            refined.add(ladder.get(i));
        }

        return refined;
    }

    private static boolean available(Stack<ArrayList<String>> usedPool, String s){
        for(ArrayList<String> list : usedPool){
            if(list.contains(s))
                return false;
        }
        return true;
    }

    private static Stack<String> search(Stack<ArrayList<String>> usedPool, Stack<String> ladder, String start, String current, String target){
        // System.out.println(ladder.size() + "\n\n");
        
        ArrayList<String> available = new ArrayList<>();

        for(String s: dictionary){
            if(target.length() == s.length() && valid(s, current) && available(usedPool, s)){
                if(s.equals(target)){
                    ladder.push(s);
                    return ladder;
                }
                available.add(s);
            }
        }
        usedPool.push(available);
        
        // System.out.println(usedPool + "\n\n");

        if(available.size() == 0){
            usedPool.pop();
            // if(!ladder.peek().equals(start))
                ladder.pop();
            return ladder;
        }

        for(String s : available){
            ladder.push(s);
            Stack<String> tempLadder = search(usedPool, ladder, start, s, target);
            if(!tempLadder.empty() && tempLadder.contains(target)){
                ladder.push(s);
                return ladder;
            } else {//if(!ladder.peek().equals(start)){
                // System.out.println(ladder + "\n\n");
                // System.out.println(ladder.pop());
                ladder.pop();
            }
        }
        
        usedPool.pop();
        // if(!ladder.peek().equals(start))
            ladder.pop();

        return ladder;
    }

    /**
     * check if 1 letter difference
     *   -> yes -> true
     *   -> no -> false
     */
    private static boolean valid(String s, String comp){

        int c = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != comp.charAt(i))
                c++;
        }
        return c <= 1 && c != 0;
    }
}
