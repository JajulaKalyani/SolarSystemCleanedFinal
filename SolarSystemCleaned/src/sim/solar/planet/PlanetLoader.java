package sim.solar.planet;

import java.util.ArrayList;
import java.io.FileReader;  
import java.util.List; 
import com.opencsv.CSVReader; 
import java.util.Arrays;
import java.io.IOException;

public class PlanetLoader {

    private static final String planetConfigFile = "solarconfig.csv";
        
    //TODO: use the row value to select just one row of the solarconfig.csv to be used for each display 
    
    public List<PlanetInterface> Produce(int row) {
    
       List<PlanetInterface> planetList = new ArrayList<>();
       List< List<String> > configData = ReadPlanetConfig(planetConfigFile);
              
       int numplanets = Integer.parseInt(configData.get(row).get(0));   
       int orbit      = Integer.parseInt(configData.get(row).get(1));  
       int increment  = Integer.parseInt(configData.get(row).get(2));    
       int angleinc   = Integer.parseInt(configData.get(row).get(3));    
       int planetsize = Integer.parseInt(configData.get(row).get(4));   
       int redbase    = Integer.parseInt(configData.get(row).get(5));  
       int greenbase  = Integer.parseInt(configData.get(row).get(6));  
       int bluebase   = Integer.parseInt(configData.get(row).get(7));   
       int redinc     = Integer.parseInt(configData.get(row).get(8));   
       int greeninc   = Integer.parseInt(configData.get(row).get(9));   
       int blueinc    = Integer.parseInt(configData.get(row).get(10));    
        
 
        int angle     = 0;
        int red ;
        int green;
        int blue; 
        
        for (int i=0; i<numplanets; i++) {
            angle    = angle + angleinc;   // controls offset between planets
            red      = redbase   + redinc*i;   // planet color 
            green    = greenbase + greeninc*i;   // planet color
            blue     = bluebase  + blueinc*i;   // planet color
 
            red = checkColorRange(red);
            green = checkColorRange(green);
            blue = checkColorRange(blue);
          
            planetList.add(new Planet (angle, orbit, increment, planetsize, red, green, blue));
         }
       return planetList; 
   }
   
         
   //TODO: upgrade this method as necessary to return the data in a form that can be used by Produce method
   
   private  List< List<String> > ReadPlanetConfig(String fileName)  {  
      CSVReader reader = null; 
      List< List<String> > configData = new ArrayList<>();

      try {  
         reader = new CSVReader(new FileReader(fileName));
         String [] nextLine;  
         nextLine = reader.readNext();  // read the header line 
        //TODO: take each token and copy them into some type of collection storage, ArrayList, HashMap, etc 
         while ((nextLine = reader.readNext()) != null) {  
            configData.add(Arrays.asList(nextLine));
           }
          reader.close(); 
      }  
      catch (IOException e) {  
         e.printStackTrace();  
      }  
      return configData;
   } 
   
  private Integer checkColorRange(int color){
   int MINIMUM_VALUE = 0;
   int MAXIMUM_VALUE = 255;
   if (color < MINIMUM_VALUE) color=0;
   if (color > MAXIMUM_VALUE) color=255;
   return color;
  } 

 }

