#!/bin/bash

echo "Useage: ./_.sh <filename> <texture> <fullblockmodel>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite

# I just happened to make granite first
dest=$1
texture=$2
fullblockmodel=$3
newblock="slab_${dest}"
original="granite"
seed="slab_${original}"
texture_old="stone_${original}"
modid="absentbydesign"
folder="src/main/resources/assets/${modid}"

# create the files

cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json
cp "${folder}"/blockstates/"${seed}"_double.json "${folder}"/blockstates/"${newblock}"_double.json

cp "${folder}"/models/block/"${seed}".json "${folder}"/models/block/"${newblock}".json
cp "${folder}"/models/block/"${seed}"_upper.json "${folder}"/models/block/"${newblock}"_upper.json

cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json
rm -rf "${folder}"/models/item/"${newblock}"_double.json

cp "${folder}"/recipes/"${seed}".json "${folder}"/recipes/"${newblock}".json

# string replace for texture and models 
 
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}".json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_upper.json
 

sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json
 
sed -i -e "s/${original}/${dest}/g" "${folder}"/blockstates/"${newblock}".json
sed -i -e "s/${original}/${fullblockmodel}/g" "${folder}"/blockstates/"${newblock}"_double.json
    

echo "Files written"
