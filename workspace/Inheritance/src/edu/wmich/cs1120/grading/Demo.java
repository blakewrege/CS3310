package edu.wmich.cs1120.grading;


import java.util.Scanner;
/**
 *  This program demonstrates the Exam class which
 *  implements the Relatable interface and other classes defined above
 */
public class Demo
{
   public static void main(String[] args)
   {
      // Exam #1 had 100 questions and the student
      // missed 20 questions.
      Exam exam1 = new Exam(100, 20);

      // Exam #2 had 100 questions and the student
      // missed 30 questions.
      Exam exam2 = new Exam(100, 30);

      // Display the exam scores.
      System.out.println("Exam 1: " + exam1.getScore());
      System.out.println("Exam 2: " + exam2.getScore());

      // Compare the exam scores.
      if (exam1.equals(exam2))
         System.out.println("The exam scores are equal.");

      if (exam1.isGreater(exam2))
         System.out.println("The Exam 1 score is the highest.");

      if (exam1.isLess(exam2))
         System.out.println("The Exam 1 score is the lowest.");
      

/* test PassFailExam */ 
      int questions,     // Number of questions
          missed;        // Number of questions missed
      double minPassing; // Minimum passing score
      
      // Create a Scanner object for keyboard input.
      Scanner keyboard = new Scanner(System.in);

      // Get the number of questions on the exam.
      System.out.print("How many questions are " +
                       "on the exam? ");
      questions = keyboard.nextInt();

      // Get the number of questions the student missed.
      System.out.print("How many questions did the " +
                       "student miss? ");
      missed = keyboard.nextInt();

      // Get the minimum passing score.
      System.out.print("What is the minimum " +
                       "passing score? ");
      minPassing = keyboard.nextInt();

      // Create a PassFailExam object.
      PassFailExam exam = 
         new PassFailExam(questions, missed, minPassing);

      // Display the test results.
      System.out.println("Each question counts " +
                         exam.getPointsEach() +
                         " points.");
      System.out.println("The exam score is " +
                         exam.getScore());
      System.out.println("The exam grade is " +
                         exam.getGrade());
      System.out.println("compare" + exam1.getScore() + " "+ exam.getScore());
      


   }
}


 

