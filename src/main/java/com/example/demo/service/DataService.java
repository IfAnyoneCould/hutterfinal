package com.example.demo.service;

import com.example.demo.model.ActionData;
import com.example.demo.model.MainData;
import com.example.demo.model.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class DataService {

    private final Map<Integer, MainData> groupMainData = new HashMap<>();
    private final Random random = new Random();

    private List<List<ActionData>> actionData = new ArrayList<>();

    public DataService() {

        for (int i = 1; i <= 6; i++) {
            groupMainData.put(i, new MainData(
                    "Initial Group " + i + " Data",
                    100 + i * 10,
                    "This is the initial data for group " + i + "."
            ));
        }

        actionData.add(makeActionData(new String[]{"A: test", "B", "C"}, new String[]{"Perform alpha operation.", "Initiate beta sequence.", "Execute gamma protocol."}));
    }

    public List<ActionData> makeActionData(String[] title, String[] description) {
        List<ActionData> actionData = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            actionData.add(new ActionData(title[i], description[i]));
        }

        return actionData;
    }

    public ApiResponse getInitialDataForGroup(int groupNumber) {
        MainData main = groupMainData.get(groupNumber);
        if (main == null) {
            return null;
        }
        List<ActionData> actions = actionData.get(random.nextInt(actionData.size()));
        return new ApiResponse(main, actions);
    }

    public ApiResponse triggerActionAndUpdate(String mainObjectId, String actionType) {
        MainData main = groupMainData.values().stream()
                .filter(md -> md.getId().equals(mainObjectId))
                .findFirst()
                .orElse(null);

        if (main == null) {
            return null;
        }

        main.performAction(actionType);

        List<ActionData> newActions = getRandomActionData();

        return new ApiResponse(main, newActions);
    }

    private List<ActionData> getRandomActionData() {
        return actionData.get(random.nextInt(actionData.size()));
    }
}