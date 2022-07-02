package mr.prisoner;

import dev.ssdd.zot.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class Idk {
    public boolean overallQualified1 = true,overallQualified2 = true;
    private final List<Integer> numbers = new ArrayList<>();
    private final List<JSONObject> x = new ArrayList<>(), xx = new ArrayList<>();
    private final LinkedHashMap<Integer, Integer> boxes = new LinkedHashMap<>();
    private int holder = 0,holder1 = 0, prisonerSent =0;
    void start(){
        initList();
        init();
    }

    private void initList() {
        for (int i = 1; i < 101; i++) {
            numbers.add(i);
        }
    }

    private void init() {
        for (int i = 1; i < 101; i++) {
            int random = (int) (Math.random() * (numbers.size()));
            boxes.put(i,numbers.get(random));
            numbers.remove(random);
        }
        initList();
        sendPrisner();
    }

    private void sendPrisner() {
        prisonerSent++;
        if(prisonerSent < 101){
            selectRandomly(prisonerSent);
        }else {
            int flow=0, rand=0;
            for (int i = 0; i < 100; i++) {
                JSONObject j = x.get(i), jj = xx.get(i);
                if(!j.getBoolean("qualified")){
                    overallQualified1 = false;
                }else {
                    rand++;
                }
                if(!jj.getBoolean("qualified")){
                    overallQualified2 = false;
                }else {
                    flow++;
                }
            }
            onComplete(overallQualified1, overallQualified2, rand, flow);
        }
    }

    abstract void onComplete(boolean randomPick, boolean specificFlow, int numberOfRandomSelectionQualify, int numberOfFlowNumberQualify);

    private synchronized void flow(int prisonerNumber) {
        if(holder1<51){
            int selectedNo = boxes.get(prisonerNumber);
            if(prisonerNumber == selectedNo){
                holder1 = 0;
                JSONObject data = new JSONObject()
//                        .put("list", selectedNumberList)
                        .put("prisonerNumber", prisonerNumber).put("qualified", true);
                xx.add(data);
                sendPrisner();
            }else {
                holder1++;
                flow(selectedNo);
            }
        }else {
            holder1 = 0;
            JSONObject data = new JSONObject()
//                        .put("list", selectedNumberList)
                    .put("prisonerNumber", prisonerNumber).put("qualified", false);
            xx.add(data);
            sendPrisner();
        }
    }

    private void selectRandomly(int prisonerNumber) {
        if(holder < 51){
            int random = (int) (Math.random() * 100)+1;
            int selectedNo = boxes.get(random);
            if(selectedNo == prisonerNumber){
                holder = 0;
                JSONObject data = new JSONObject()
//                        .put("list", selectedNumberList)
                        .put("prisonerNumber", prisonerNumber).put("qualified", true);
                x.add(data);
                flow(prisonerNumber);
            }else {
                holder++;
                selectRandomly(prisonerNumber);
            }
        }else {
            holder = 0;
            JSONObject data = new JSONObject()
//                    .put("list", selectedNumberList)
                    .put("prisonerNumber", prisonerNumber).put("qualified", false);
            x.add(data);
            flow(prisonerNumber);
        }
    }
}
