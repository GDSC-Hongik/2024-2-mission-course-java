package com.gdsc.game.action;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class ActionRepository {

    private List<Action> actions = new ArrayList<>();

    public void saveActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> findAll() {
        return new ArrayList<>(actions);
    }
    public Action findByName(String name) {
        return actions.stream()
                .filter(action -> action.getClass().getSimpleName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    public Action findByIdx(int idx) {
        return actions.get(idx - 1); // idx - 1 : 입력 받는 인덱스는 1~5이므로 0~4 범위로 변경하는 과정
    }
}
