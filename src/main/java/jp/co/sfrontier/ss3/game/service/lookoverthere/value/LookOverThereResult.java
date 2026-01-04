package jp.co.sfrontier.ss3.game.service.lookoverthere.value;

import jp.co.sfrontier.ss3.game.common.Direction;
import jp.co.sfrontier.ss3.game.common.ResultCode;

public class LookOverThereResult {

    private final ResultCode resultCode;
    private final Direction defenderDirection;

    public LookOverThereResult(ResultCode resultCode, Direction defenderDirection) {
        this.resultCode = resultCode;
        this.defenderDirection = defenderDirection;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public Direction getDefenderDirection() {
        return defenderDirection;
    }
}
