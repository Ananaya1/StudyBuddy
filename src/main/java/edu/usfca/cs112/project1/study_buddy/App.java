package edu.usfca.cs112.project1.study_buddy;

import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//import java.io.FileNotFoundException;
public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	try { 
    	     Scanner scan = new Scanner(System.in);      
    	     System.out.println("Welcome to the Study Buddy."); 
    	     
    	     Course course = new Course("src/main/resources/topics.txt","src/main/resources/lessons.txt"); 
    	     while(true) { 
    	      System.out.println("What would you like to do?  Please enter a number: "); 
    	          System.out.println("     1. Do a lesson in the sequence."); 
    	          System.out.println("     2. Choose a lesson out of sequence."); 
    	          System.out.println("     3. Evaluate progress."); 
    	          System.out.println("     4. Save and Quit the program."); 
    	          String choice = scan.nextLine(); 
    	          if(choice.equals("1")) { 
    	           System.out.println("Please be patient while I load your Lesson."); 
    	           lesson l = course.getNextLesson(); 
    	           l.doLesson(); 
    	           course.completed(l);
    	           
    	          }else if (choice.equals("2")) { 
    	           lesson l = selectLesson(scan, course); 
    	           l.doLesson();
    	           course.completed(l);
    	          
    	         }else if (choice.equals("3")) { 
    	             lesson l=  course.evaluatelessons(); 
    	             
    	             
    	        	 
    	          }else if (choice.equals("4")){ 
    	          
    	        	  
    	        	  
    	        	  
    	        	 course.SavetoFile();
    	        	  
    	        	  

    	         System.out.println("Thanks for using the Study Buddy, goodbye!"); 
    	           break;  
    	          }else { 
    	           System.out.println("Please only enter 1, 2, 3, or 4."); 
    	          } 
    	     } 
    	     }catch(FileNotFoundException e) { 
    	         System.out.println("You did not load a valid file into the program."); 
    	     }
    }
    	public static lesson selectLesson(Scanner scan, Course course) throws IOException {
            System.out.println("Enter the lesson topic: ");
            String topic = scan.nextLine();
            return course.takeLessonOutOfOrder(topic);
        }}
		

