import curdexample.dto.User;
import curdexample.entity.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReadExample {

    public static void main(String[] args) throws IOException {
        processCsvLazy();
    }

    private static void processCsvLazy() throws IOException {
        //Files.lines() is lazy, so it does not load the complete file into memory:
        try(Stream<String> lines = Files.lines(Paths.get(","))){
            lines.skip(1)  //skips header row
                    .map(line -> line.split(","))
                    .map(data -> new User(
                                    Long.valueOf(data[0]),
                                    data[1],
                                    data[2]
                            )
                    )
                    .distinct()
                    .toList();
        }
    }

    private static void processCsvBatches() throws IOException{

        List<User> batch = new ArrayList<>(1000);

        //BufferedReader reads the file incrementally. It maintains a small internal buffer and gives me one line at a time.
        try(BufferedReader reader =
                Files.newBufferedReader(Paths.get(","))){

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null){
                User user = parseUser(line);
                batch.add(user);

                if(batch.size() == 1000){
                    saveBatch(batch);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                saveBatch(batch);
            }
        }
    }
}
