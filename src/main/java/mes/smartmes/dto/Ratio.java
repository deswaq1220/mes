package mes.smartmes.dto;

public class Ratio {

    final static public double orderInput = 666;                                    //수주량(단위: box)
    final static public double waterOrderInputQty = orderInput * 30;                //양배추즙, 흑마늘즙 수주량(단위: ea)
    final static public double jellyOrderInputQty = orderInput * 25;                //젤리류 수주량(단위: ea)

    //양배추즙
    final static public double cabbageInputQty = waterOrderInputQty / 20;           //양배추 투입량(단위: kg)
    final static public double cabbageWaterInput = waterOrderInputQty / 20;         //양배추즙 만들 때 필요한 정제수량(단위: kg)
    final static public double cabbageWater = cabbageInputQty + cabbageWaterInput;  //양배추즙 추출 시 투입량(단위: kg)
    final static public double cabbageWaterOutput = cabbageWater * 0.8;             //양배추추출액(단위: kg)

    //마늘즙
    final static public double garlicInputQty = waterOrderInputQty / 120;           //마늘 투입량(단위: kg)
    final static public double garlicWaterInput = (waterOrderInputQty / 120) * 3;   //마늘즙 만들 때 필요한 정제수량(단위: kg)
    final static public double blackGarlicWater = garlicInputQty + garlicWaterInput; //흑마늘즙 추출 시 투입량(단위: kg)
    final static public double blackGarlicOutput = blackGarlicWater * 0.6;           //흑마늘추출액(단위: kg)

    //젤리
    final static public double jellyInputQty = jellyOrderInputQty * 15;             //젤리류 투입량(단위: ml) (농축액 5 + 콜라겐 2 + 정제수 8)

    //양배추즙, 마늘즙 충진 시 필요한


}