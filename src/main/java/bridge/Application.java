package bridge;

import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();

        BridgeMaker user = new BridgeMaker(bridgeNumberGenerator);
        int size = InputView.readBridgeSize();
        List<String> userBridge = user.makeBridge(size);
        BridgeGame Game = new BridgeGame(userBridge);
        System.out.println(userBridge);
        playGame(size, Game);
    }

    public static void playGame(int size, BridgeGame Game) {
        int index = 0;
        String nowResult = "";
        int matchNum = 1;
        while (index != size) {
            String userDirection = InputView.readMoving();
            nowResult = String.valueOf(Game.move(userDirection,index));
            OutputView.printMap(nowResult);
            index += 1;
            if (nowResult.contains("X")) {
                index = 0;
                String userRetry = InputView.readGameCommand();
                if (userRetry.equals("Q")) {
                    break;
                }
                matchNum += 1;
                Game.retry();
            }
        }
        if (index == size) {
            endGame(nowResult,true,matchNum);
        }
        if (index != size) {
            endGame(nowResult,false,matchNum);
        }
    }

    public static void endGame(String finalResult, boolean matchResult, int matchNum) {
        OutputView.printResult(finalResult, matchResult,matchNum);

    }
}
