:: useage 
:: $ ./assetCopyORES.bat silver
 
set dest=%1
set seed=stairs_granite

cp src/main/resources/assets/absentbydesign/blockstates/stairs_granite.json src/main/resources/assets/absentbydesign/blockstates/stairs_%dest%.json

cp src/main/resources/assets/absentbydesign/models/block/stairs_granite.json src/main/resources/assets/absentbydesign/models/block/stairs_%dest%.json
cp src/main/resources/assets/absentbydesign/models/item/stairs_granite.json src/main/resources/assets/absentbydesign/models/item/stairs_%dest%.json
                              
