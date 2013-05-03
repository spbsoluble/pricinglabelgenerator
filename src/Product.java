
public class Product {
        private String productName; //The product's name
        private String SKU;         //The product's SKU
        private String UPC;         //The product's UPC
        private float MSRP;         //MSRP Price of the product
        private float wholesale;    //Wholesale price of the product
        private float distributor;  //distributor price of the product
        
        
        /*
         * 
         */
        public Product(String SKU, String UPC, String productName, float MSRP, float wholesale, float distributor){
            this.productName = productName;
            this.SKU = SKU;
            this.UPC = UPC;
            this.MSRP = MSRP;
            this.wholesale = wholesale;
            this.distributor = distributor;
           
        }
        
        /*
         * 
         */
        public String getProductName(){
            return productName;
        }
        
        /*
         * 
         */
        public String getUPC (){
            return UPC;
        }
        
        /*
         * 
         */
        public float getMSRP() {
            return MSRP;
        }
        
        /*
         * 
         */
        public String getSKU(){
            return SKU;
        }
        
        /*
         * 
         */
        public float getWholesale(){
            return wholesale;
        }
        
        /*
         * 
         */
        public float getDistributor(){
            return distributor;
        }
}
    

