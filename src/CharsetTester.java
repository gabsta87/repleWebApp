import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CharsetTester {

	public static void main(String[] args) throws IOException {
		Path p = Paths.get("C:\\Users\\Formation\\.reple\\saved_capitals_list.txt");
		List<String> s = Files.readAllLines(p,StandardCharsets.UTF_8);
		s.forEach(System.out::println);
	}

}
