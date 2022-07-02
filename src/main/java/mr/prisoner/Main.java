package mr.prisoner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Integer> rand = new ArrayList<>(), speci = new ArrayList<>();
    public static void main(String[] args) {
        new Idk() {
            @Override
            void onComplete(boolean randomPick, boolean specificFlow, int numberOfRandomSelectionQualify, int numberOfFlowNumberQualify) {
                System.out.println("randomPick Qualification: " + randomPick + "\n" +
                                "specific Qualification: "+specificFlow +
                        "\n Number of Random Selection Qualify: " + numberOfRandomSelectionQualify + "/100"+
                        "\n Number of Specific Selection Qualify: " + numberOfFlowNumberQualify + "/100"
                        );
               /* if(randomPick){
                    rand.add(0);
                }
                if(specificFlow){
                    speci.add(0);
                }*/
            }
        }.start();
    }
}