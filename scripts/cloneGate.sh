#!/bin/bash

echo "Useage: ./_.sh <dest> <texture>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite


dest=$1
texture=$2
newblock="gate_${dest}"
original="nether_bricks"
seed="gate_${original}"

modid="absentbydesign"
folder="src/main/resources/assets/${modid}"
folderdata="src/main/resources/data/${modid}"

# create the files


cp "${folderdata}"/loot_tables/blocks/${seed}.json "${folderdata}"/loot_tables/blocks/"${newblock}".json
cp "${folderdata}"/recipes/${seed}.json "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${seed}/${newblock}/g" "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${seed}/${newblock}/g" "${folderdata}"/loot_tables/blocks/"${newblock}".json


cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json
sed -i -e "s/${original}/${dest}/g" "${folder}"/blockstates/"${newblock}".json

cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json
sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json

cp "${folder}"/models/block/"${seed}".json "${folder}"/models/block/"${newblock}".json
cp "${folder}"/models/block/"${seed}"_open.json "${folder}"/models/block/"${newblock}"_open.json
cp "${folder}"/models/block/"${seed}"_wall.json "${folder}"/models/block/"${newblock}"_wall.json
cp "${folder}"/models/block/"${seed}"_wall_open.json "${folder}"/models/block/"${newblock}"_wall_open.json

sed -i -e "s/${original}/${texture}/g" "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}".json
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}"_open.json
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}"_wall.json
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}"_wall_open.json

echo "  \"block.absentbydesign.${newblock}\":\"lang Gate\",   " 
echo "  \"absentbydesign:${newblock}\",     to the blocks/fence_gates.json data tag"  

echo "Files written"
