
import au.com.bytecode.opencsv.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author Sean
 */
public class PricingList {
    private List<String[]> products;    //List of all the products represented as a list of string arrays containing each field's data
    private String inventoryListFilePath;   //file path for the inventory loaded
    private HashMap upcLookup;  //table that maps UPC => product data
    private HashMap skuLookup;  //table that maps SKU => product data
    private String [] fileHeader;   //header line of the csv file loaded
    
    public PricingList(){
        this.inventoryListFilePath = "default.csv";
        this.loadProducts();
        System.out.println("Loaded product inventory successfully");
    }
    
    public PricingList(String filePath){
        this.inventoryListFilePath = filePath;
        this.loadProducts();
        
    }
    
    private void loadProducts(){
        //read csv
        try{
            FileReader fReader = new FileReader(inventoryListFilePath); //File reader for reading the csv file
            CSVReader reader = new CSVReader(fReader, ';'); //file is assumed to be semicolon delimited
            products = reader.readAll();    //read all lines into products list
        } catch (FileNotFoundException fnfe){
            System.err.println("File not found please verify the file exists");
        } catch (IOException ioe){
            System.err.println("Error reading the file");
        }
        
        //generate hashmap lookup tables for SKU => list<String[]> and UPC => list<String[]>
        this.generateHashMaps();
    }
    
    public void generateHashMaps(){
        upcLookup = new HashMap();  //reinitialize upcLookup table
        skuLookup = new HashMap();  //reinitialize skuLookup table
        
        Iterator<String[]> i = products.iterator(); //set up iterator for the product list
        
        if(i.hasNext()){    //so long as something was read go ahead and start populating tables
            
            String[] header = i.next(); //file is assumed to have a header at the top line
            this.fileHeader = header;   //assign header data to the global header
            
            while(i.hasNext()){ //anything after the first line is considered to be data
                String [] line = i.next();  //current fields for the line
                
                HashMap lineHashMap = new HashMap();    //temp hash map of all the values of the string array; uses header array
                
                for(int j = 0; j < line.length; j++){   //iterate over the line data fields and assign to line hashmap
                    String key = header[j];             //key gotten from the header array parsed at the beginning
                    String value = line[j];             //value is current field iteration data
                    lineHashMap.put(key,value);         //hashmap entry is formed as headerfield => line value
                }
                
                //check for duplicate UPCs and inform
                if(upcLookup.containsKey(lineHashMap.get("UPC"))){
                    System.err.println("Duplicate UPC for product: " + lineHashMap.get("Product Name"));
                    System.err.println("UPC is assigned to " + ((HashMap)upcLookup.get("UPC")).get("Product Name"));
                }
                
                //check for duplicate skus on products and inform
                 if(skuLookup.containsKey(lineHashMap.get("SKU"))){
                    System.err.println("Duplicate SKU for product: " + lineHashMap.get("Product Name"));
                    System.err.println("SKU is assigned to " + ((HashMap)upcLookup.get("SKU")).get("Product Name"));
                }
                 
                 //So long as the UPC isn't blank then add it to the table
                 if(!(lineHashMap.get("UPC").toString() == null || lineHashMap.get("UPC").toString().equals(""))){
                    upcLookup.put(lineHashMap.get("UPC").toString(), lineHashMap);  //add entry to the UPC lookup table 
                 }
                 
                 //So long as the sku isn't empty att it to the table
                 if(!(lineHashMap.get("SKU").toString() == null || lineHashMap.get("SKU").toString().equals(""))){
                    skuLookup.put(lineHashMap.get("SKU").toString(), lineHashMap);  //add entry to the SKU lookup table
                 }
            }
        }
    }
    
    public List<String[]> getProdcutList(){
        return products;
    }
    
    public String[] getFileHeader(){
        return this.fileHeader;
    }
    
    private Product createProduct(HashMap productData){
        String SKU = (String)productData.get("SKU");
        String UPC = (String)productData.get("UPC");
        String productName = (String)productData.get("Product Name");
        float MSRP = Float.parseFloat(productData.get("MSRP").toString());
        float wholesale = Float.parseFloat(productData.get("Wholesale").toString());
        float distributor = Float.parseFloat(productData.get("Distributor").toString());
        return new Product(SKU,UPC, productName, MSRP, wholesale, distributor );
    }
    
    public Product findProduct(String sku_upc){
        if(upcLookup.containsKey(sku_upc)){
            HashMap productData = (HashMap)upcLookup.get(sku_upc);
            return this.createProduct(productData);
        }
        else if(skuLookup.containsKey(sku_upc)){
            HashMap productData = (HashMap)skuLookup.get(sku_upc);
            return this.createProduct(productData);
        } else {
            return null;
        }
            
    }
    
    public boolean updateInventory(){
        return true;
    }
}
