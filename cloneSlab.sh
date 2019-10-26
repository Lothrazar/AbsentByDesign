#!/bin/bash

echo "Useage: ./_.sh <filename> <texture>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite

# I just happened to make granite first
dest=$1
texture=$1

newblock="slab_${dest}"
original="coarse_dirt"
seed="slab_${original}"

modid="absentbydesign"
folder="src/main/resources/assets/${modid}"

folderdata="src/main/resources/data/${modid}"

# create the files


cp "${folderdata}"/loot_tables/blocks/${seed}.json "${folderdata}"/loot_tables/blocks/"${newblock}".json
cp "${folderdata}"/recipes/${seed}.json "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${seed}/${newblock}/g" "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${seed}/${newblock}/g" "${folderdata}"/loot_tables/blocks/"${newblock}".json

cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json

cp "${folder}"/models/block/"${seed}".json "${folder}"/models/block/"${newblock}".json
cp "${folder}"/models/block/"${seed}"_upper.json "${folder}"/models/block/"${newblock}"_upper.json

cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json
rm -rf "${folder}"/models/item/"${newblock}"_double.json


# string replace for texture and models 
 
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}".json
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}"_upper.json
 

sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json
 
sed -i -e "s/${original}/${dest}/g" "${folder}"/blockstates/"${newblock}".json
    

echo "Files written"
