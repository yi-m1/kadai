package jp.co.sfrontier.ss3.game.service.janken;

import jp.co.sfrontier.ss3.game.common.Hand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JankenResult {
    private int result;
    private Hand cpuHand;
}
