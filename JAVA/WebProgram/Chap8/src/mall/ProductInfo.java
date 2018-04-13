package mall;
public class ProductInfo {
     private String code; // 제품코드
     private String name; // 제품명
     private int price; // 가격
     public void setCode(String code) {
          this.code = code;
     }
     public void setName(String name) {
          this.name = name;
     }
     public void setPrice(int price) {
          this.price = price;
     }
     public String getCode() {
          return code;
     }
     public String getName() {
         return name;
     }
     public int getPrice() {
          return price;
     }
}
