:: useage 
:: $ ./assetCopyORES.bat silver
 
set dest=%1
set seed=stairs_granite

cp src/main/resources/assets/absentbydesign/blockstates/%seed%.json src/main/resources/assets/absentbydesign/blockstates/stairs_%dest%.json

cp src/main/resources/assets/absentbydesign/models/block/%seed%.json src/main/resources/assets/absentbydesign/models/block/stairs_%dest%.json

cp src/main/resources/assets/absentbydesign/models/item/%seed%.json src/main/resources/assets/absentbydesign/models/item/stairs_%dest%.json

cp src/main/resources/assets/absentbydesign/models/recipes/%seed%.json src/main/resources/assets/absentbydesign/models/recipes/stairs_%dest%.json
                              
