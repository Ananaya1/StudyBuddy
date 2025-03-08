package edu.usfca.cs112.project1.study_buddy;
import java.util.*;
import java.io.*;
public class Course {
	private  ArrayList<lesson> lessons;
	private ArrayList<String> topics;
	public Course(String topicsFile, String lessonsFile) throws IOException, ClassNotFoundException
	{
		lessons = new ArrayList<>(); 
		topics = new ArrayList<>(); 
		loadLessonsFromFile(lessonsFile);  
		loadTopicsFromFile(topicsFile);	
	}

	public void loadLessonsFromFile(String lessonsFile) throws IOException, ClassNotFoundException {
	    File file = new File(lessonsFile);

	    if (!file.exists() || file.length() == 0) {
	        System.out.println("No lessons to load.");
	        return;
	    }

	    try (FileInputStream fis = new FileInputStream(file);
	         ObjectInputStream ois = new ObjectInputStream(fis)) {

	       
	        while (true) {
	            try {
	                lesson l = (lesson) ois.readObject();  
	                lessons.add(l);
	                //System.out.println("Loaded lesson: " + l.getTopic());  // Debug output
	            } catch (EOFException e) {
	                System.out.println("Reached end of file.");
	                break;
	            }
	        }
	    } catch (ClassNotFoundException | IOException e) {
	        e.printStackTrace();
	    }
	}




	
		private void loadTopicsFromFile(String topicsFile) throws FileNotFoundException 
		{
			File file=new File(topicsFile);
			Scanner sc= new Scanner(file);
			while(sc.hasNext())
			{
				topics.add(sc.nextLine());
				
			}
		}
		public void SavetoFile() {
		    if (lessons.isEmpty()) {
		        System.out.println("No lessons to save!");
		        return;
		    }

		    try (FileOutputStream fos = new FileOutputStream("src/main/resources/lessons.txt");
		         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

		        System.out.println("Saving lessons:");
		        for (lesson l : lessons) {
		            System.out.println("Saving lesson: " + l.getTopic());
		            oos.writeObject(l);
		        }

		        System.out.println("Lessons saved successfully.");

		    } catch (IOException e) {
		        System.out.println("Error saving lessons: " + e.getMessage());
		    }
		}




		
         public void completed(lesson l)
         {
        	 lessons.add(l);
         }
		public lesson takeLessonOutOfOrder(String topic) throws IOException {
		    if (!topics.contains(topic)) {
		        System.out.println("Invalid topic!");
		        return null;
		    }
		    
		   lesson newLesson = new lesson(topic);
		   
		    return newLesson;
		}
		public lesson getNextLesson() throws IOException {  
		    for (String topic : topics) {
		        boolean alreadyCovered = false;

		        // Check if this topic is already covered
		        for (lesson l : lessons) {
		            if (l.getTopic().equals(topic)) {
		                alreadyCovered = true;
		                break; // No need to check further if it's found
		            }
		        }

		       
		        if (!alreadyCovered) {
		            
		            return new lesson(topic);
		        }
		    }

		   
		    System.out.println("All lessons have been completed!");
		    return null; 
		}
		public lesson evaluatelessons() {
		    if (lessons.isEmpty()) {
		        System.out.println("No lessons available for evaluation.");
		        return null;
		    }

		    lesson bestLesson = null;
		    lesson worstLesson = null;
		    int maxScore = -1;
		    int minScore = Integer.MAX_VALUE;
		    int totalScore = 0;
		    int lessonCount = lessons.size();

		    for (lesson l : lessons) {
		        System.out.println("Checking Lesson: " + l.getTopic() + " | Score: " + l.getScore());
		        
		        
		        if (l.getScore() > maxScore) {
		            maxScore = l.getScore();
		            bestLesson = l;
		        }
		        
		        if (l.getScore() < minScore) {
		            minScore = l.getScore();
		            worstLesson = l;
		        }
		        
		        totalScore += l.getScore();  
		    }

		   
		    double averageScore = (double) totalScore / lessonCount;

		    System.out.println("Highest Scoring Lesson: " + (bestLesson != null ? bestLesson.getTopic() : "None") + " | Score: " + maxScore);
		    System.out.println("Lowest Scoring Lesson: " + (worstLesson != null ? worstLesson.getTopic() : "None") + " | Score: " + minScore);
		    System.out.println("Average Score: " + averageScore);
		    System.out.println("Total Score: " + totalScore);
		    System.out.println("Total Lessons Evaluated: " + lessonCount);

		    return bestLesson;
		}


	    // Main method for testing
//	    public static void main(String[] args) throws ClassNotFoundException, IOException {
//	        // Create the course and load files
//	        Course c = new Course("src/main/resources/topics.txt", "src/main/resources/lessons.txt");
//	       
//	        for (String topic : c.topics)
//	        {
//	        	lesson l= new lesson(topic);
//	        	c.completed(l);
//	        }
//	        
//	        c.SavetoFile();
//	        c.loadLessonsFromFile("\"src/main/resources/lessons.txt\"");
//	    }
//
//	  
}

	   