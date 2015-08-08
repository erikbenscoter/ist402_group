package edu.psu.ist402.touchtournament;

/**
 * Created by erikbenscoter on 8/7/15.
 */
public class TournamentGenerator {


    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Calculate 2^input
    ///////////////////////////////////////////////////////////////////////////
    public int myExp(int p_input){

        if( p_input == 0){
            return 1;
        }
        else{
            return 2*myExp(p_input - 1);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Calculate Byes
    ///////////////////////////////////////////////////////////////////////////
    public int ByeCalculator(int numberOfTeams){

        // the number of byes required is the difference between the number of teams and the next-highest power of two
        // -wikepedia
        int powerOfTwoItt = 0;
        int currentAnswerItt = myExp(0);

        //keep going up by power of twos until you get one bigger than the number of teams
        while( currentAnswerItt < numberOfTeams ){

            powerOfTwoItt ++;
            currentAnswerItt = myExp(powerOfTwoItt);

        }

        int numberOfByes = currentAnswerItt - numberOfTeams;

        return numberOfByes;
    }

}
