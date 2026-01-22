package common.layer.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class LogEntry {
    /**
     * Represents an entry from a single log line. Log lines look like this in the file:
     *
     * 34400.409 SXY288 210E ENTRY
     *
     * Where:
     * * 34400.409 is the timestamp in seconds since the software was started.
     * * SXY288 is the license plate of the vehicle passing through the toll booth.
     * * 210E is the location and traffic direction of the toll booth. Here, the toll
     *     booth is at 210 kilometers from the start of the tollway, and the E indicates
     *     that the toll booth was on the east-bound traffic side. Tollbooths are placed
     *     every ten kilometers.
     * * ENTRY indicates which type of toll booth the vehicle went through. This is one of
     *     "ENTRY", "EXIT", or "MAINROAD".
     **/

    private final String timestamp;
    private final String licensePlate;
    private final String boothType;
    private final int location;
    private final String direction;

    public LogEntry(String logLine) {
        String[] tokens = logLine.split(" ");
        this.timestamp = tokens[0];
        this.licensePlate = tokens[1];
        this.boothType = tokens[3];
        this.location =
                Integer.parseInt(tokens[2].substring(0, tokens[2].length() - 1));
        String directionLetter = tokens[2].substring(tokens[2].length() - 1);
        if (directionLetter.equals("E")) {
            this.direction = "EAST";
        } else if (directionLetter.equals("W")) {
            this.direction = "WEST";
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBoothType() {
        return boothType;
    }

    public int getLocation() {
        return location;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return String.format(
                "<LogEntry timestamp: %f  license: %s  location: %d  direction: %s  booth type: %s>",
                timestamp,
                licensePlate,
                location,
                direction,
                boothType
        );
    }

}

public class CarLogEntry {
    public static void main(String[] args){
        List<LogEntry> logEntryList = new ArrayList<>();

        logEntryList.add(new LogEntry("90750.191 JOX304 250E ENTRY"));
        logEntryList.add(new LogEntry("91081.684 JOX304 260E MAINROAD"));
        logEntryList.add(new LogEntry("91082.101 THX138 110E ENTRY"));
        logEntryList.add(new LogEntry("91483.251 JOX304 270E MAINROAD"));
        logEntryList.add(new LogEntry("91873.920 THX138 120E MAINROAD"));
        logEntryList.add(new LogEntry("91874.493 JOX304 280E EXIT"));

        //Grouping entries by LicensePlate
        Map<String,List<LogEntry>> map1 =  logEntryList.stream().filter(x -> !x.getBoothType().equals("MAINROAD")).collect(Collectors.groupingBy(LogEntry::getLicensePlate));

        //Grouping entries by LicensePlate and then value as count
        Map<String,Long> map =  logEntryList.stream().filter(x -> !x.getBoothType().equals("MAINROAD")).collect(Collectors.groupingBy(LogEntry::getLicensePlate,Collectors.counting()));

        //fetching all the complete entries - LogEntry for car with Entry and Exit BoothType
        List<String> journeyList =  logEntryList.stream().filter(x -> !x.getBoothType().equals("MAINROAD")).collect(Collectors.groupingBy(LogEntry::getLicensePlate,Collectors.counting())).entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).collect(Collectors.toList());
    }
}