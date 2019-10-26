#!/bin/bash

echo "Useage: ./_.sh <dest> <texture>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite

# I just happened to make granite first
dest=$1
texture=$2
newblock="fence_${dest}"
original="quartz"
seed="fence_${original}"
texture_old="${original}_block_side"
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

cp "${folder}"/models/block/"${seed}"_inventory.json "${folder}"/models/block/"${newblock}"_inventory.json
cp "${folder}"/models/block/"${seed}"_post.json "${folder}"/models/block/"${newblock}"_post.json
cp "${folder}"/models/block/"${seed}"_side.json "${folder}"/models/block/"${newblock}"_side.json


sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_inventory.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_post.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_side.json

echo "\"block.absentbydesign.${newblock}\":\"lang\"    

echo "Files written"
