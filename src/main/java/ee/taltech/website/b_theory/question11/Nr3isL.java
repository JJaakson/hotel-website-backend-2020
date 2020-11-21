package ee.taltech.website.b_theory.question11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nr3isL {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does L stand for in SOLID? Explain the principle.
    //Anwser: L stands for Liskov substitution principle
    //Principle states that the objects of a superclass can be replaced with the objects
    //of its subclasses, without causing errors. But this requires that the subclass behaves
    //similar. To use the same input parameters as the superclass and apply the same rules
    //for output parameters.

}

//todo B Give an example. Write actual or pseudo code.
//Creating an interface for spaceships to fly
interface Spaceship {

    String fly(int speed);

}

//has only 1 engine and small crew, starts flying fast
class Fighter implements Spaceship {

    @Override
    public String fly(int speed) {
        engineStart();
        //makes spaceship move
        //start one engine fast and start moving
        return "Started moving with the speed of: " + speed;
    }

    private void engineStart() {
    }
}

//has 9 engines and a very large crew, alerting and notifying everyone takes quite long
class ColonyShip implements Spaceship {

    private final List<Integer> crew = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    @Override
    public String fly(int speed) {
        alertEnginesCrew();
        List<Integer> crewsReady = new ArrayList<>();
        while (crewsReady.size() != 9) {
            for (Integer crewLeader: crew) {
                if (!crewsReady.contains(crewLeader)) {
                    boolean status = checkOnCrewStatus(crewLeader);
                    if (status) {
                        crewsReady.add(crewLeader);
                    }
                }
            }
        }
        engineStart();
        return "Started moving with the speed of: " + speed;
    }

    private void alertEnginesCrew() {
        for (Integer crewLeader: crew) {
            alert(crewLeader);
        }
    }

    private void alert(int crew) {
        //alerts the specific crew that the ship needs to start moving
    }

    private boolean checkOnCrewStatus(int id) {
        //checks on the crew, if the engines are ready to be started
        return true;
    }

    private void engineStart() {
        //starts all engines, if all engine crews are ready to start them
    }
}
