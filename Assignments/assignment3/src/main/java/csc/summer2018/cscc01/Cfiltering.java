package csc.summer2018.cscc01;

import java.text.DecimalFormat;

public class Cfiltering {
    // this is a 2d matrix i.e. user*movie
    private int userMovieMatrix[][];
    // this is a 2d matrix i.e. user*movie
    private float userUserMatrix[][];
    // create a decimal format instance
    // to get all the values of the userUserMatrix in 4 decimal places
    private DecimalFormat df4 = new DecimalFormat("0.0000");
    
    /**
     * Default Constructor.
     */
    public Cfiltering() {
        // this is 2d matrix of size 1*1
        userMovieMatrix = new int[1][1];
        // this is 2d matrix of size 1*1
        userUserMatrix = new float[1][1];
    }
    
    /*
     * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
     * userUserMatrix WITH CORRECT DIMENSIONS.
     */
    /**
     * Constructs an object which contains two 2d matrices, one of size
     * users*movies which will store integer movie ratings and one of size
     * users*users which will store float similarity scores between pairs of
     * users.
     *
     * @param numberOfUsers Determines size of matrix variables.
     * @param numberOfMovies Determines size of matrix variables.
     */
    public Cfiltering(int numberOfUsers, int numberOfMovies) {
        // this is a 2d matrix of size numberOfUsers*numberOfMovies
        userMovieMatrix = new int[numberOfUsers][numberOfMovies];
        // this is a 2d matrix of size numberOfUsers*numberOfUsers
        userUserMatrix = new float[numberOfUsers][numberOfUsers];
    }
    
    /**
     * The purpose of this method is to populate the UserMovieMatrix. As input
     * parameters it takes in a rowNumber, columnNumber and a rating value. The
     * rating value is then inserted in the UserMovieMatrix at the specified
     * rowNumber and the columnNumber.
     *
     * @param rowNumber The row number of the userMovieMatrix.
     * @param columnNumber The column number of the userMovieMatrix.
     * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
     */
    public void populateUserMovieMatrix(int rowNumber, int columnNumber,
                                        int ratingValue) {
        userMovieMatrix[rowNumber][columnNumber] = ratingValue;
    }
    
    /*
     * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
     * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
     *
     * @param AND
     *
     * @return as required below.
     */
    /**
     * Determines how similar each pair of users is based on their ratings. This
     * similarity value is represented with with a float value between 0 and 1,
     * where 1 is perfect/identical similarity. Stores these values in the
     * userUserMatrix.
     *
     * @param COMPLETE THIS IF NEEDED
     * @param COMPLETE THIS IF NEEDED
     * @return COMPLETE THIS IF NEEDED
     */
    public void calculateSimilarityScore() {
        // store the number of users
        int user_number = userMovieMatrix.length;
        // initiate row_counter1 to loop through all the users
        // in userMovieMatrix
        for (int user_counter1 = 0; user_counter1 < user_number; user_counter1++) {
            // initiate row_counter2 which is the user to be compared with
            for (int user_counter2 =
                 0; user_counter2 < user_number; user_counter2++) {
                // create a variable to store
                // the calculated square of the difference between ratings
                double difference_square = 0;
                // store the number of movies in a variable
                int movie_number = userMovieMatrix[user_counter1].length;
                // loop through all the rating values to get them
                for (int movie_rating =
                     0; movie_rating < movie_number; movie_rating++) {
                    // if the user counts add up to less than the user_number
                    if (user_counter1 + user_counter2 < user_number) {
                        // get the rating from the two users being compared
                        int rating1 = userMovieMatrix[user_counter1][movie_rating];
                        int rating2 =
                        userMovieMatrix[user_counter1 + user_counter2][movie_rating];
                        // add up to the difference_square
                        difference_square += Math.pow(rating1 - rating2, 2);
                        // calculate the distance between the ratings of the users
                        double distance = Math.sqrt(difference_square);
                        // calculate the similarity score between them
                        double similarity_score = 1 / (1 + distance);
                        // cast the similarity_score to a float
                        float float_similarity_score = (float) similarity_score;
                        // store the similarity score on the userUserMatrix
                        userUserMatrix[user_counter1][user_counter1 + user_counter2] =
                        float_similarity_score;
                        userUserMatrix[user_counter1 + user_counter2][user_counter1] =
                        float_similarity_score;
                    }
                }
            }
        }
    }
    
    /*
     * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
     * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
     */
    /**
     * Prints out the similarity scores of the userUserMatrix, with each row and
     * column representing each/single user and the cell position (i,j)
     * representing the similarity score between user i and user j.
     *
     * @param COMPLETE THIS IF NEEDED
     * @param COMPLETE THIS IF NEEDED
     * @return COMPLETE THIS IF NEEDED
     */
    
    public void printUserUserMatrix() {
        // loop through all the rows
        for (int row = 0; row < userUserMatrix.length; row++) {
            // print out the opening square bracket
            System.out.print('[');
            // loop through all the columns
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                // print out the similarity_score in 4 decimal places
                System.out.print(df4.format(userUserMatrix[row][col]));
                // print out commas after each value up until the last value
                if (col != userUserMatrix[row].length - 1) {
                    System.out.print(", ");
                }
            }
            // print out the closing square bracket
            System.out.print("]" + "\n");
        }
    }
    
    /*
     * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
     * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
     */
    /**
     * This function finds and prints the most similar pair of users in the
     * userUserMatrix.
     *
     * @param COMPLETE THIS IF NEEDED
     * @param COMPLETE THIS IF NEEDED
     * @return COMPLETE THIS IF NEEDED
     */
    
    public void findAndprintMostSimilarPairOfUsers() {
        // set the highest similarity score to 0 since 0 is the minimum score
        float highest_sim_score = 0;
        // initiate two variables to store the first two most similar users
        int similar_user1 = 0;
        int similar_user2 = 0;
        // loop through the userUserMatrix to access all the similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                // when the users are not the same
                if (row != col) {
                    // if the similarity_score is higher than the previous highest
                    // similarity score
                    if (userUserMatrix[row][col] > highest_sim_score) {
                        // set the similarity_score to the highest similarity score
                        highest_sim_score = userUserMatrix[row][col];
                        // save the first two most similar users
                        similar_user1 = row;
                        similar_user2 = col;
                    }
                }
            }
        }
        // print the first two users with the highest similarity score
        System.out.println(
                           "The most similar pairs of users from above userUserMatrix are: ");
        System.out.print(
                         "User" + (similar_user1 + 1) + " and " + "User" + (similar_user2 + 1));
        // loop through the userUserMatrix to check which other users have the
        // highest similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                if (userUserMatrix[row][col] == highest_sim_score) {
                    if (row != col) {
                        // print the users in the upper triangle of the matrix
                        // and when they are not the first two most similar users
                        if ((col > row) && (row != similar_user1 || col != similar_user2)) {
                            // print a comma
                            System.out.print(",");
                            // print a newline
                            System.out.print("\n");
                            // print the other most similar pair of users
                            System.out
                            .print("User" + (row + 1) + " and " + "User" + (col + 1));
                        }
                    }
                }
            }
        }
        // print the highest similarity score
        System.out.println(
                           "\n" + "with similarity score of " + df4.format(highest_sim_score));
    }
    
    /*
     * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
     * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
     */
    /**
     * This function finds and prints the most dissimilar pair of users in the
     * userUserMatrix.
     *
     * @param COMPLETE THIS IF NEEDED
     * @param COMPLETE THIS IF NEEDED
     * @return COMPLETE THIS IF NEEDED
     */
    public void findAndprintMostDissimilarPairOfUsers() {
        // set the lowest similarity score to 2 since 2 > 1
        float lowest_sim_score = 2;
        // initiate two variables to store the first two most dissimilar users
        int dissimilar_user1 = 0;
        int dissimilar_user2 = 0;
        // loop through the userUserMatrix to access all the similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                // when the users are not the same
                if (row != col) {
                    // if the similarity_score is lower than the previous lowest
                    // similarity score
                    if (userUserMatrix[row][col] < lowest_sim_score) {
                        // set the similarity_score to the lowest similarity score
                        lowest_sim_score = userUserMatrix[row][col];
                        // save the first two most similar users
                        dissimilar_user1 = row;
                        dissimilar_user2 = col;
                    }
                }
            }
        }
        // print the first two users with the lowest similarity score
        System.out.println(
                           "The most dissimilar pairs of users from above userUserMatrix are: ");
        System.out.print("User" + (dissimilar_user1 + 1) + " and " + "User"
                         + (dissimilar_user2 + 1));
        // loop through the userUserMatrix to check which other users have the
        // lowest similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                if (userUserMatrix[row][col] == lowest_sim_score) {
                    if (row != col) {
                        // print the users in the upper triangle of the matrix
                        // and when they are not the first two most dissimilar users
                        if ((col > row)
                            && (row != dissimilar_user1 || col != dissimilar_user2)) {
                            // print a comma
                            System.out.print(",");
                            // print a newline
                            System.out.print("\n");
                            // print the other most dissimilar pair of users
                            System.out
                            .print("User" + (row + 1) + " and " + "User" + (col + 1));
                        }
                    }
                }
            }
        }
        // print the lowest similarity score
        System.out.println(
                           "\n" + "with similarity score of " + df4.format(lowest_sim_score));
    }
}
