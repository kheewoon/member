package report.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class testtt {

    @Test
    void dndndndn(){
      log.info("result = {}", solution(1990));
    }

    public int solution(int N) {
        int calcN = digitCalc(N);
        int K = N + 1;
        while(calcN != digitCalc(K)){
            K++;
        }

        return K;
    }

    public int digitCalc(int num){
        int calcNum = 0;

        while(num > 0){
            calcNum += num % 10;
            num = num / 10;
        }
      return calcNum;
    }
}
