package BookMyShow;


import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {

        public static HashMap<Character, ArrayList<String>> grids(long noofSeats, String grid ) {   // Function to generate seats and grids

            var gridss = grid.split("\\*");   // Splits the grid and store in ArrayList
            int sumofGrids = 0;      // To store sum of grids
            for(String grids:gridss) {       // Loops over gridss ArrayList
                sumofGrids += Integer.parseInt(grids);    // Sums the total grids
            }
            if(noofSeats %sumofGrids == 0) {    // Checks if number of seats and grids matches

                HashMap<Character,ArrayList<String>> seatsPatt = new HashMap<>();    // Creating a new HashMap to store seats in pattern
                char alphabet = 'A';    // To store the start row name

                while (noofSeats >0) {   // Loops until the number of seats gets 0

                    ArrayList<String> seats = new ArrayList<>();     // Creating a new ArrayList to store seats
                    for(int column = 0; column <gridss.length; column++) {      // Loops until the grid size is reached

                        int nofelement = Integer.parseInt(gridss[column]);    // To store the number of seats
                        for(int e = 0; e < nofelement; e++) {       // Loops until e reaches number of seats
                            seats.add("___");        // Adds the seat in ArrayList
                        }
                        if(column <gridss.length-1) {    // Checks for the grid length
                            seats.add(" ||    || ");      // Adds a space to the ArrayList
                        }
                    }
                    seatsPatt.put(alphabet,seats);    // Passing the seat pattern into the HashMap
                    alphabet++;     // moves the next alphabet
                    noofSeats = noofSeats - sumofGrids;   // subracting the added seats
                }
                return seatsPatt;
            }
            System.out.println("No of Seats and Girds does not match");
            return null;
        }
    }

