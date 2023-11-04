/**
 * My project Diet Planner has the purpose of giving you a detailed planner on what you should be eating based
 * on personal factors or preferences. Questions  include factors such as gender, weight, amount of exercise,
 * type of exercise,  and more. Based on this information, the program gives you the amount of macronutrients you
 * should be consuming, as well as exercise plans on this information.
 *
 * @author: Noah Babel
 * @version: 05/12/22
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static User user;
    public static BinaryTree tree;
    private static BinaryTreeNode root;
    private static BinaryTreeNode currentNode;

    //creates user and tree objects
    public Main(User user, BinaryTree tree){
        this.user = user;
        this.tree = tree;
    }

    public static void main(String[] args){
        //calories, totalCalories and exerciseList to be used for curating the workout plan section later in code
        int calories = 0;
        int totalCalories = 0;
        String exerciseList = "";
        User user = new User();
        /*
        Sets up the binary tree of all the different questions that will be asked to the user, and the corresponding
        diet plan
        */
        setUpTree();
        currentNode = root;
        Scanner scanner = new Scanner(System.in);
        //creates a stack of exercises to choose from with the user
        Stack<String> exercises = new Stack<String>();
        exercises.push("Walking");
        exercises.push("Hiking");
        exercises.push("Dance");
        exercises.push("Skiing");
        exercises.push("Ice Skating");
        exercises.push("Soccer");
        exercises.push("Running");
        exercises.push("Volleyball");
        exercises.push("Basketball");
        exercises.push("Football");

        //gives the user a name, and sends it back to the user object
        System.out.println("What is your name?");
        String input = scanner.nextLine();
        user.setUsername(input);

        //introductions
        System.out.println();
        System.out.println("Nice to meet you, " + user.getUsername() + ". Today we are going to be curating a diet " +
                "plan for you based on your lifestyle.");
        System.out.println("For the following questions, type \"1\" to select the first option, or type \"2\" to select" +
                " the second option.");
        System.out.println();

        /*
        curates a diet plan via a binary tree

        Run loop where the user can navigate the tree. If the left node is null, that means that the next right node
        is the leaf, meaning that the while loop should stop since it has reached the end of the binary tree.
        */
        while(currentNode.getLeft() != null){
            //prints out the current question
            System.out.println(currentNode.getVal());
            input = scanner.nextLine();
            //two options for the user to decide, each with corresponds with the next "level" on the binary tree
            if(input.equals("1")){
                currentNode = currentNode.getLeft();
            }
            else if(input.equals("2")){
                currentNode = currentNode.getRight();
            }
            //not either a 1 or a 2
            else{
                System.out.println("That was not a valid answer. Please try again");
            }
            System.out.println();

        }

        //moves onto curating a workout plan via the stack
        System.out.println();
        System.out.println("Let's now plan some exercise. You should aim to burn 400-500 calories a day.");
        System.out.println("Here are some options: " + exercises);
        System.out.println("Each of those exercises are 30 minutes. ");
        System.out.println("What exercise would you like to do?");
        System.out.println();

        //iterates through the exercises stack
        while(true) {
            input = scanner.nextLine();
            /*
            calculating the calories has to do with the order each exercise is in the stack. The formula is:

            corresponding calories = 200 calories + (order in the stack) * 10

            By searching the element that the user inputs, we can find the order in the stack that the element is, and
            therefore calculate the corresponding calorie count.
            */
            calories = exercises.search(input);
            if(input.equals("Done") || input.equals("done")){
                break;
            }
            //check for valid input by seeing if searching the input gave a value of -1.
            else if(calories == -1) {
                System.out.println("That is not a valid input. Please try again.");
            }
            else{
                //does the calorie calculation
                calories = ((10-calories)*10) + 200;
                totalCalories += calories;
                /*
                adds the added exercise to the exercise list string, by using the getAt method to find the value
                of the index at that point within the stack (index given by searching the stack)
                * */
                exerciseList = exerciseList + getAt(exercises,exercises.search(input));

                //repeating info to the user
                System.out.println("You've choosen 30 minutes of " + exerciseList + ", and burned a total of " + totalCalories + " calories.");
                System.out.println();
                System.out.println("Would you like to pick any more? Say \"Done\" to continue.");
                exerciseList = exerciseList + ", ";
            }
        }
        System.out.println(currentNode.getVal());
        System.out.println();
        System.out.println("Total Calories burned: " + totalCalories + ".");
        //removes the comma at the end of the exercise list and replaces it with a period
        System.out.println("Activities done: " + exerciseList.substring(0, exerciseList.length() - 2) + ".");
    }

    //method finds the value of the index givin within the stack
    public static <T> T getAt(Stack<T> stack, int index) {
        if (index == 1) {
            return stack.peek();
        }

        T x = stack.pop();
        try {
            return getAt(stack, index - 1);
        } finally {
            stack.push(x);
        }
    }
    /*
    set up tree sets up all the nodes of the binary tree (questions for the user) and the leafs (corresponding diet
    plans). All the leafs are right nodes, which makes it really easy to tell when the next element of the binary tree
    is a leaf, as you can just see if the binary node has a left element
     */
        public static void setUpTree(){
            root = new BinaryTreeNode("Are you a male(1) or female (2)?");

            root.setLeft(new BinaryTreeNode("Are you below 5'9\"(1) or above(2)?"));
            root.setRight(new BinaryTreeNode(("Are you below 5'6\"(1) or above(2)?")));

            root.getLeft().setLeft(new BinaryTreeNode("Are you below 175 pounds(1) or above(2)?"));
            root.getLeft().setRight(new BinaryTreeNode("Are you below 200 pounds(1) or above(2)?"));
            root.getRight().setLeft(new BinaryTreeNode("Are you below 170 pounds(1) or above(2)?"));
            root.getRight().setRight(new BinaryTreeNode("Are you below 180 pounds(1) or above(2)?"));

            root.getLeft().getLeft().setLeft(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getLeft().getLeft().setRight(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getLeft().getRight().setLeft(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getLeft().getRight().setRight(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getRight().getLeft().setLeft(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getRight().getLeft().setRight(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getRight().getRight().setLeft(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));
            root.getRight().getRight().setRight(new BinaryTreeNode("Are you trying to lose weight(1) or gain weight(2)?"));

            root.getLeft().getLeft().getLeft().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getLeft().getLeft().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getLeft().getRight().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getLeft().getRight().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getRight().getLeft().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getRight().getLeft().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getRight().getRight().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getLeft().getRight().getRight().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getLeft().getLeft().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getLeft().getLeft().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getLeft().getRight().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getLeft().getRight().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getRight().getLeft().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getRight().getLeft().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getRight().getRight().setLeft(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));
            root.getRight().getRight().getRight().setRight(new BinaryTreeNode("Do you do primary lighter exercise(1) or heavier exercise(2)"));

            root.getLeft().getLeft().getLeft().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 106 grams/day\n" +
                    "Carbs: 233 grams/day\n" +
                    "Fat: 50 grams/day\n" +
                    "Sugar: <47 grams/day\n" +
                    "Saturated Fat: <20 grams/day\n" +
                    "Calories: 1,745 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getLeft().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 128 grams/day\n" +
                    "Carbs: 280 grams/day\n" +
                    "Fat: 60 grams/day\n" +
                    "Sugar: <56 grams/day\n" +
                    "Saturated Fat: <24 grams/day\n" +
                    "Calories: 2,101 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getLeft().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 167 grams/day\n" +
                    "Carbs: 366 grams/day\n" +
                    "Fat: 78 grams/day\n" +
                    "Sugar: <73 grams/day\n" +
                    "Saturated Fat: <31 grams/day\n" +
                    "Calories: 2,745 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getLeft().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 185 grams/day\n" +
                    "Carbs: 404 grams/day\n" +
                    "Fat: 86 grams/day\n" +
                    "Sugar: <81 grams/day\n" +
                    "Saturated Fat: <34 grams/day\n" +
                    "Calories: 3,031 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getRight().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 114 grams/day\n" +
                    "Carbs: 249 grams/day\n" +
                    "Fat: 53 grams/day\n" +
                    "Sugar: <50 grams/day\n" +
                    "Saturated Fat: <21 grams/day\n" +
                    "Calories: 1,870 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getRight().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 132 grams/day\n" +
                    "Carbs: 290 grams/day\n" +
                    "Fat: 62 grams/day\n" +
                    "Sugar: <58 grams/day\n" +
                    "Saturated Fat: <25 grams/day\n" +
                    "Calories: 2,172 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getRight().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 175 grams/day\n" +
                    "Carbs: 383 grams/day\n" +
                    "Fat: 82 grams/day\n" +
                    "Sugar: <77 grams/day\n" +
                    "Saturated Fat: <33 grams/day\n" +
                    "Calories: 2,870 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getLeft().getRight().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 193 grams/day\n" +
                    "Carbs: 423 grams/day\n" +
                    "Fat: 90 grams/day\n" +
                    "Sugar: <85 grams/day\n" +
                    "Saturated Fat: <36 grams/day\n" +
                    "Calories: 3,172 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getLeft().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 119 grams/day\n" +
                    "Carbs: 259 grams/day\n" +
                    "Fat: 55 grams/day\n" +
                    "Sugar: <52 grams/day\n" +
                    "Saturated Fat: <22 grams/day\n" +
                    "Calories: 1,945 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getLeft().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 138 grams/day\n" +
                    "Carbs: 301 grams/day\n" +
                    "Fat: 64 grams/day\n" +
                    "Sugar: <60 grams/day\n" +
                    "Saturated Fat: <26 grams/day\n" +
                    "Calories: 2,256 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getLeft().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 180 grams/day\n" +
                    "Carbs: 393 grams/day\n" +
                    "Fat: 84 grams/day\n" +
                    "Sugar: <79 grams/day\n" +
                    "Saturated Fat: <33 grams/day\n" +
                    "Calories: 2,845 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getLeft().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 199 grams/day\n" +
                    "Carbs: 434 grams/day\n" +
                    "Fat: 93 grams/day\n" +
                    "Sugar: <87 grams/day\n" +
                    "Saturated Fat: <37 grams/day\n" +
                    "Calories: 3,256 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getRight().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 126 grams/day\n" +
                    "Carbs: 276 grams/day\n" +
                    "Fat: 59 grams/day\n" +
                    "Sugar: <55 grams/day\n" +
                    "Saturated Fat: <24 grams/day\n" +
                    "Calories: 2,070 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getRight().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 146 grams/day\n" +
                    "Carbs: 320 grams/day\n" +
                    "Fat: 68 grams/day\n" +
                    "Sugar: <64 grams/day\n" +
                    "Saturated Fat: <27 grams/day\n" +
                    "Calories: 2,397 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getRight().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 187 grams/day\n" +
                    "Carbs: 409 grams/day\n" +
                    "Fat: 87 grams/day\n" +
                    "Sugar: <82 grams/day\n" +
                    "Saturated Fat: <35 grams/day\n" +
                    "Calories: 3,070 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getLeft().getRight().getRight().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 207 grams/day\n" +
                    "Carbs: 453 grams/day\n" +
                    "Fat: 97 grams/day\n" +
                    "Sugar: <91 grams/day\n" +
                    "Saturated Fat: <39 grams/day\n" +
                    "Calories: 3,397 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getLeft().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 87 grams/day\n" +
                    "Carbs: 189 grams/day\n" +
                    "Fat: 40 grams/day\n" +
                    "Sugar: <38 grams/day\n" +
                    "Saturated Fat: <16 grams/day\n" +
                    "Calories: 1,420 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getLeft().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 102 grams/day\n" +
                    "Carbs: 222 grams/day\n" +
                    "Fat: 47 grams/day\n" +
                    "Sugar: <44 grams/day\n" +
                    "Saturated Fat: <19 grams/day\n" +
                    "Calories: 1,665 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getLeft().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 148 grams/day\n" +
                    "Carbs: 323 grams/day\n" +
                    "Fat: 69 grams/day\n" +
                    "Sugar: <65 grams/day\n" +
                    "Saturated Fat: <28 grams/day\n" +
                    "Calories: 2,420 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getLeft().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 163 grams/day\n" +
                    "Carbs: 355 grams/day\n" +
                    "Fat: 76 grams/day\n" +
                    "Sugar: <71 grams/day\n" +
                    "Saturated Fat: <30 grams/day\n" +
                    "Calories: 2,665 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getRight().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 94 grams/day\n" +
                    "Carbs: 206 grams/day\n" +
                    "Fat: 44 grams/day\n" +
                    "Sugar: <41 grams/day\n" +
                    "Saturated Fat: <18 grams/day\n" +
                    "Calories: 1,545 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getRight().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 110 grams/day\n" +
                    "Carbs: 241 grams/day\n" +
                    "Fat: 51 grams/day\n" +
                    "Sugar: <48 grams/day\n" +
                    "Saturated Fat: <21 grams/day\n" +
                    "Calories: 1,805 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getRight().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 155 grams/day\n" +
                    "Carbs: 339 grams/day\n" +
                    "Fat: 72 grams/day\n" +
                    "Sugar: <68 grams/day\n" +
                    "Saturated Fat: <29 grams/day\n" +
                    "Calories: 2,545 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getLeft().getRight().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 171 grams/day\n" +
                    "Carbs: 374 grams/day\n" +
                    "Fat: 80 grams/day\n" +
                    "Sugar: <75 grams/day\n" +
                    "Saturated Fat: <32 grams/day\n" +
                    "Calories: 2,805 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getRight().getLeft().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 93 grams/day\n" +
                    "Carbs: 203 grams/day\n" +
                    "Fat: 43 grams/day\n" +
                    "Sugar: <41 grams/day\n" +
                    "Saturated Fat: <17 grams/day\n" +
                    "Calories: 1,526 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getRight().getLeft().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 109 grams/day\n" +
                    "Carbs: 238 grams/day\n" +
                    "Fat: 51 grams/day\n" +
                    "Sugar: <48 grams/day\n" +
                    "Saturated Fat: <20 grams/day\n" +
                    "Calories: 1,784 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getRight().getLeft().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 154 grams/day\n" +
                    "Carbs: 337 grams/day\n" +
                    "Fat: 72 grams/day\n" +
                    "Sugar: <67 grams/day\n" +
                    "Saturated Fat: <29 grams/day\n" +
                    "Calories: 2,526 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getRight().getLeft().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 170 grams/day\n" +
                    "Carbs: 371 grams/day\n" +
                    "Fat: 79 grams/day\n" +
                    "Sugar: <74 grams/day\n" +
                    "Saturated Fat: <32 grams/day\n" +
                    "Calories: 2,784 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getRight().getRight().getLeft().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 101 grams/day\n" +
                    "Carbs: 220 grams/day\n" +
                    "Fat: 47 grams/day\n" +
                    "Sugar: <44 grams/day\n" +
                    "Saturated Fat: <19 grams/day\n" +
                    "Calories: 1,651 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getRight().getRight().getLeft().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 117 grams/day\n" +
                    "Carbs: 257 grams/day\n" +
                    "Fat: 55 grams/day\n" +
                    "Sugar: <51 grams/day\n" +
                    "Saturated Fat: <22 grams/day\n" +
                    "Calories: 1,925 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));
            root.getRight().getRight().getRight().getRight().setLeft(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 162 grams/day\n" +
                    "Carbs: 353 grams/day\n" +
                    "Fat: 75 grams/day\n" +
                    "Sugar: <71 grams/day\n" +
                    "Saturated Fat: <30 grams/day\n" +
                    "Calories: 2,651 Calories/day\n" +
                    "Exercise: 15-30 minutes of elevated heart rate activity"));
            root.getRight().getRight().getRight().getRight().setRight(new BinaryTreeNode("Thanks for your input. Here are the some recommendations for somebody of your size and activity:\n" +
                    "Protein: 195 grams/day\n" +
                    "Carbs: 427 grams/day\n" +
                    "Fat: 91 grams/day\n" +
                    "Sugar: <85 grams/day\n" +
                    "Saturated Fat: <36 grams/day\n" +
                    "Calories: 3,199 Calories/day\n" +
                    "Exercise: 45-120 minutes of elevated heart rate activity"));


            BinaryTreeNode currentNode = root;

            BinaryTree tree = new BinaryTree(root);
        }


}
