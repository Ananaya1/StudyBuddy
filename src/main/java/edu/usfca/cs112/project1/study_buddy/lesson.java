package edu.usfca.cs112.project1.study_buddy;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
public class lesson implements Serializable{
private static final long serialVersionUID = 1L;
private transient Model mymodel;
private  String topic;
private  String response;
private  int score;
private transient Course course;

public lesson (String topic)
{
	this.mymodel=new Model();
	this.topic=topic;
}
public String getTopic() {
    return topic;
}
public int getScore()
{
	return score;
}
public void setScore(int newScore) {
    this.score = newScore;  // Update score
}
public void reinitialize() {
	this.mymodel = new Model();
}
public void doLesson()
        
{    mymodel.generate("We are helping the user prepare for a test on java programming language and the basic plan if for the topic we will generate two questions for the understanding of the user then in points summarise the topic and in the end generate a quiz and maintain a score for that quiz which will be revealed later now do as directed");
	Scanner sc= new Scanner(System.in);
	System.out.println("WELCOME TO "+topic);
	//Scanner sc= new Scanner(System.in);
	String question1 =  mymodel.generate("ask a question about "+topic);
	System.out.println(question1);
	String useranswer1=sc.nextLine();
	String review = mymodel.generate(question1+" also review users answer to it which is"+useranswer1);
	System.out.println(review);
	String question2 =  mymodel.generate("ask a different question about "+topic);
	System.out.println(question2);
	String useranswer2=sc.nextLine();
	String revieew = mymodel.generate(question2+" also review users answer to it which is"+useranswer2);
	System.out.println(revieew);
	System.out.println("lets now review:");
	String last=mymodel.generate("Summarise in points everything they need to know about "+topic);
	System.out.println(last);
	System.out.println("Now Lets take a quiz");
	String quiz1 = mymodel.generate("ask a quiz question about " + topic);
    String quizanswer1 = answer(sc,quiz1);;
    
    
    

    String quiz2 = mymodel.generate("ask a quiz question about " + topic + " something other than " + topic + " " + quiz1);
    String quizanswer2 =answer(sc,quiz2);
   

    String quiz3 = mymodel.generate("ask a quiz question about " + topic + " something other than " + quiz1 + " and " + quiz2);
    String quizanswer3 =answer(sc,quiz2);;
   

 	String scoree = mymodel.generate("return ONLY a number between 0 and 100 as the score for the cumalative score that the user gets in the quiz, grade the user out of 100 for the answers "+ quizanswer1+" to the question "+quiz1+" and "+quiz2+" for "+ quizanswer2+"and "+quiz3+"for "+quizanswer3);
 	
 	System.out.println("Raw AI Score Response: " + scoree);

 	try {
 	    this.setScore(Integer.parseInt(scoree.trim())); 
 	   System.out.println("Updated Score: " + this.getScore());
 	} catch (NumberFormatException e) {
 	    System.out.println("Error parsing score: " + scoree);
 	    score = 0; 
 	}
 
     
     
	System.out.println("Thank for using");
	
           
       
    }
private  String answer(Scanner sc, String question) {
    String answer;
    while (true) {
        System.out.println(question);
        answer = sc.nextLine();
        if (answer.equalsIgnoreCase("skip")) {
            System.out.println("Question skipped. Generating a new question...");
            question = mymodel.generate("ask a different question about " + topic);  // Generate new question
        } else {
            break;  
        }
    }
    return answer;
}
}
	


//public lesson gettopic()
//public static void main(String [] args)
//{
// lesson lesson = new lesson("Initial Topic");
 //lesson.doLesson(); 
//Model model = new Model();
//response= model.generate("what is the capital of india");	
//System.out.print(response);

