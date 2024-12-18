package com.gdsc.game.manager;

import com.gdsc.game.action.ActionService;
import com.gdsc.game.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class TurnController {

    private final PlayerService playerService;
    private final TurnService turnService;
    private final ActionService actionService;

    @GetMapping("/status") // 두 플레이어 상태 확인
    public ResponseEntity<String> getStatus() {
        String response = playerService.printPlayerState();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/status/{playerName}") // 특정 플레이어 상태 확인 + 현재 턴 수 3/5턴
    public ResponseEntity<String> getStatus(@PathVariable String playerName) {
        String response = playerService.printPlayerState(playerName);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{playerName}/action") // 특정 플레이어의 액션 리스트 뽑아냄.
    public ResponseEntity<String> getActionList(@PathVariable String playerName) {
        String response = actionService.printActions(playerName);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{playerName}/{actionIdx}") // 특정 플레이어가 특정 액션을 요청 바디에 넣음(게임의 중추)
    public ResponseEntity<String> chooseAction(@PathVariable String playerName, @PathVariable int actionIdx) {
        String response = turnService.startTurn(playerName, actionIdx);
        return ResponseEntity.ok().body(response);
    }


}
