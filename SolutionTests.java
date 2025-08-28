import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTests {

  @Test
  void examples() {
    assertEquals(List.of(1,2), Kata.filterList(List.of(1,2,"a","b")), formatInput(List.of(1,2,"a","b")));
    assertEquals(List.of(1,0,15), Kata.filterList(List.of(1,"a","b",0,15)), formatInput(List.of(1,"a","b",0,15)));
    assertEquals(List.of(1,2,123), Kata.filterList(List.of(1,2,"aasf","1","123",123)), formatInput(List.of(1,2,"aasf","1","123",123)));
  }
  
  @Test
  void extras() {
    assertEquals(List.of(), Kata.filterList(List.of("a","b","1")), formatInput(List.of("a","b","1")));
    assertEquals(List.of(1,2), Kata.filterList(List.of(1,2,"a","b")), formatInput(List.of(1,2,"a","b")));
  }
  
  @Test
  void randomTest(){
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    for(int i = 0; i < 20; i++) {
      int size = rand.nextInt(20);
      List<Object> input = new ArrayList<>();
      List<Integer> expected = new ArrayList<>();
      for(int j = 0; j < size; ++j) {
        if(rand.nextBoolean()) {
          int n = rand.nextInt(0, 1000);
          input.add(n);
          expected.add(n);
        } else {
          if(rand.nextBoolean())
            input.add(rand.ints(rand.nextInt(6), 48, 123).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
          else input.add(Integer.toString(rand.nextInt(1000)));
        }
      }
      
      List<Object> result = Kata.filterList(List.copyOf(input));
      assertEquals(expected, result, formatInput(input));
    }
  }
  private String formatInput(List<Object> input) {
      String partialInput = input.stream().map(x -> x instanceof String ? "\"" + x.toString() + "\"" : x.toString()).collect(Collectors.joining(", "));
      return String.format("For input: [%s]", partialInput);
  }
}