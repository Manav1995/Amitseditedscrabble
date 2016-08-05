package Tasks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Scrabble {

	  /*public static boolean validCheck(String s) throws IOException
	    {
	        BufferedReader br=null;
	        
	        try 
	        {
	            br = new BufferedReader( new FileReader("C:\\Users\\satyadav\\Downloads\\sowpods.txt"));
	            String line;
	            
	            while((line =br.readLine()) != null)
	            {
	            	if(line.equals(s))
	            		return true;
	            }
	            	            
	        }
	        catch (FileNotFoundException e) 
	        {
	            e.printStackTrace();
		}catch (IOException e) 
	        {
	            e.printStackTrace();
		}
	        finally
	       {
	           if(br!=null)
	               br.close();
	           
	       }
			return false;
	    }*/
	
	public static Map<String,String> readFile(String path){
		 Map<String,String> words_count = new HashMap<String,String>();
			
			try {
				FileInputStream fstream = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;
				while ((strLine = br.readLine()) != null)   {
				  
					String sorted=sortLetters(strLine);
				     
					     if(words_count.keySet().contains(sorted))
					     {
					    	 
					         words_count.put(sorted, words_count.get(sorted)+"  "+strLine);
					     }
					     else
					         words_count.put(sorted,strLine);

					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return words_count;
		
	}
	  
	
		private static String sortLetters(String strLine) {
			// TODO Auto-generated method stub
			char[] chars = strLine.toCharArray();
	       Arrays.sort(chars);
	       String sorted = new String(chars).toLowerCase();
			return sorted;
		}
	


	public static int getValue(String scrabble, HashMap<Character, Integer> values) {
		
		char[] chars = scrabble.toCharArray();
		int value = 0;
		for(int i = 0; i < scrabble.length(); i++) {
			value += values.get(chars[i]);
		}
		
		return value;
	}
	
	
	public static void main(String[] args) {
		
		HashMap<Character, Integer> values = new HashMap<Character, Integer>();
		
		Scanner scanner = new Scanner(System.in);
		String scrabble = scanner.nextLine();
		ArrayList<String> sub = new ArrayList<String>();
		int length;
		length = scrabble.length();

		sub = subString(scrabble, length);

		sub = sort(sub);//anagrams of all subsets
		//anagrams of all subsets
		Map<String,String> anagram=readFile("C:\\Users\\magulati\\Downloads\\sowpods.txt");
		ArrayList<String> check=checkformatch(anagram,sub);

		values.put('a', 1);
		values.put('e', 1);
		values.put('i', 1);
		values.put('o', 1);
		values.put('u', 1);
		values.put('l', 1);
		values.put('n', 1);
		values.put('r', 1);
		values.put('s', 1);
		values.put('t', 1);
		
		values.put('d', 2);
		values.put('g', 2);
		
		values.put('b', 3);
		values.put('c', 3);
		values.put('m', 3);
		values.put('p', 3);
		
		values.put('f', 4);
		values.put('h', 4);
		values.put('v', 4);
		values.put('w', 4);
		values.put('y', 4);
		
		values.put('k', 5);
		
		values.put('j', 8);
		values.put('x', 8);
		
		values.put('q', 10);
		values.put('z', 10);
		int maxValue=0;
		int x;
		String word="";
		for(String s:check)
		{	ArrayList<String> nospace = new ArrayList();
			x=getValue(s,values);
			if(x>=maxValue)
			{maxValue = x;
			 word=s;
			}
			}
		
		System.out.println(word+" "+maxValue);
		
	}	


		private static ArrayList<String> checkformatch(Map<String, String> anagram, ArrayList<String> sub) {
			// TODO Auto-generated method stub
			ArrayList<String> match=new ArrayList<>();
			ArrayList<String> f=new ArrayList<>();
			for(String s : sub){
			if (anagram.containsKey(s)){
				match.add(anagram.get(s));}
			}
			ArrayList<String> splitted = new ArrayList<>();
			for (String s:match)
			{
				if(s.contains(" "))
				{
					String[] temp = s.split("\\s+");
					for(String t : temp)
						splitted.add(t.toLowerCase());
				}else
					splitted.add(s.toLowerCase());
			}
			return splitted;
		}
	


	private static ArrayList<String> sort(ArrayList<String> sub) {
		ArrayList<String>sortedlist=new ArrayList<String>();
		for(String s : sub) {
			char[] c = s.toLowerCase().toCharArray();
			Arrays.sort(c);
		 sortedlist.add(String.valueOf(c));
	}
	return sortedlist;	
	}


	private static ArrayList<String> subString(String scrabble, int length) {
		
			ArrayList<String> sub = new ArrayList<String>();
			// TODO Auto-generated method stub
			for (int c = 0; c < length; c++) {
				for (int i = 1; i <= length - c; i++) {
					sub.add(scrabble.substring(c, c + i));
				}
			}
			return sub;
	}

}
