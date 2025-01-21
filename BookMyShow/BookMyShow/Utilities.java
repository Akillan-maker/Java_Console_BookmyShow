package BookMyShow;


import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {

        public static HashMap<Character, ArrayList<String>> grids(long noofSeats, String grid )
        {
//            ArrayList<User> users = BookMyShow.getUsers();

            var gridss = grid.split("\\*");
            int sumofGrids = 0;
            for(String grids:gridss)
            {
                sumofGrids += Integer.parseInt(grids);
            }
            if(noofSeats %sumofGrids == 0)
            {
                HashMap<Character,ArrayList<String>> seatsPatt = new HashMap<>();
                char containsAlphabet = 'A';

                while (noofSeats >0)
                {
                    ArrayList<String> seats = new ArrayList<>();
                    for(int col = 0;col<gridss.length;col++)
                    {

                        int noOfelement = Integer.parseInt(gridss[col]);
                        for(int element = 0; element<noOfelement; element++)
                        {
                            seats.add("__");
                        }
                        if(col<gridss.length-1)
                        {
                            seats.add(" <SPACE> ");
                        }
                    }
                    seatsPatt.put(containsAlphabet,seats);
                    containsAlphabet++;
                    noofSeats = noofSeats - sumofGrids;
                }
                return seatsPatt;
            }

            System.out.println("No of Seats does not match");
            return null;
        }
    }

