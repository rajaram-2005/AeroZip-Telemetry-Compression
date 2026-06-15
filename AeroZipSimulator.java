package lifechanging;

import java.util.Random;

public class AeroZipSimulator {

   
    private static final double TEMP_THRESHOLD_CELSIUS = 80.0;
    private static final double VIBRATION_THRESHOLD_MMS = 5.0;

    public static void main(String[] args) {
        int totalReadings = 10000;
        Random random = new Random(42); 

        long totalBytesBaseline = 0;
        long totalBytesAeroZip = 0;
        int anomalyCount = 0;
        int normalCount = 0;

        System.out.println("=========================================================");
        System.out.println("Starting AeroZip Telemetry Compression Simulation...");
        System.out.println("=========================================================\n");

        for (int i = 0; i < totalReadings; i++) {
            
            long timestamp = System.currentTimeMillis() + (i * 1000L);
            String turbineId = "WTG-001";
            
     
            double temperature = 55.0 + (random.nextDouble() * 15.0); 
            double vibration = 1.2 + (random.nextDouble() * 2.0);     
            double powerOutput = 1200.0 + (random.nextDouble() * 300.0); 

       
            if (random.nextDouble() < 0.03) {
                temperature += 25.0; 
                vibration += 4.5;  
                powerOutput *= 0.5; 
            }

        
            String baselinePacket = String.format(
                "{\"ts\":%d,\"id\":\"%s\",\"temp\":%.2f,\"vibe\":%.2f,\"pwr\":%.2f}",
                timestamp, turbineId, temperature, vibration, powerOutput
            );
            totalBytesBaseline += baselinePacket.getBytes().length;

            
            boolean isAnomaly = (temperature >= TEMP_THRESHOLD_CELSIUS) || (vibration >= VIBRATION_THRESHOLD_MMS);
            String aeroZipPacket;

            if (isAnomaly) {
               
                aeroZipPacket = baselinePacket; 
                anomalyCount++;
            } else {
              
                aeroZipPacket = String.format("{\"ts\":%d,\"status\":\"OK\"}", timestamp);
                normalCount++;
            }
            totalBytesAeroZip += aeroZipPacket.getBytes().length;
        }

       
        double baselineKB = totalBytesBaseline / 1024.0;
        double aeroZipKB = totalBytesAeroZip / 1024.0;
        double dataReductionPercentage = ((totalBytesBaseline - totalBytesAeroZip) / (double) totalBytesBaseline) * 100.0;

  
        System.out.println("---------------------------------------------------------");
        System.out.println("SIMULATION METRICS & RESULTS");
        System.out.println("---------------------------------------------------------");
        System.out.printf("Total SCADA Readings Processed : %d\n", totalReadings);
        System.out.printf("Normal State Packets (Summary) : %d\n", normalCount);
        System.out.printf("Anomaly State Packets (Raw)     : %d\n", anomalyCount);
        System.out.println("---------------------------------------------------------");
        System.out.printf("Baseline Network Payload       : %.2f KB\n", baselineKB);
        System.out.printf("AeroZip Network Payload        : %.2f KB\n", aeroZipKB);
        
        System.out.printf("TOTAL NETWORK BANDWIDTH SAVED  : %.2f%%\n", dataReductionPercentage);
        System.out.println("=========================================================");
        
        System.out.println("\n[PATENT CLAIMS DATA SHIELD]");
        System.out.printf(
            "The technical effect demonstrates a localized, edge-computed volume reduction of time-series data from an uncompressed baseline of %.2f KB down to an optimized footprint of %.2f KB, thereby directly reducing cloud infrastructure ingestion bandwidth by a factor of %.1fx without loss of anomaly fidelity.\n", 
            baselineKB, aeroZipKB, (baselineKB / aeroZipKB)
        );
    }
}
