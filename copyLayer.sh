#!/bin/bash

echo "Useage: ./_.sh <destination> <new_texture>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite

# I just happened to make granite first
dest=$1
texture=$2
newblock="layer_${dest}"
original="clay"
seed="layer_${original}"
texture_old="${original}"
modid="absentbydesign"
folder="src/main/resources/assets/${modid}"

# create the files

cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json

cp "${folder}"/models/block/"${seed}"_height2.json  "${folder}"/models/block/"${newblock}"_height2.json
cp "${folder}"/models/block/"${seed}"_height4.json  "${folder}"/models/block/"${newblock}"_height4.json
cp "${folder}"/models/block/"${seed}"_height6.json  "${folder}"/models/block/"${newblock}"_height6.json
cp "${folder}"/models/block/"${seed}"_height8.json  "${folder}"/models/block/"${newblock}"_height8.json
cp "${folder}"/models/block/"${seed}"_height10.json "${folder}"/models/block/"${newblock}"_height10.json
cp "${folder}"/models/block/"${seed}"_height12.json "${folder}"/models/block/"${newblock}"_height12.json
cp "${folder}"/models/block/"${seed}"_height14.json "${folder}"/models/block/"${newblock}"_height14.json

cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json


# replace blockids

sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json

sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height2.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height4.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height6.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height8.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height10.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height12.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_height14.json

sed -i -e "s/${original}/${dest}/g" "${folder}"/blockstates/"${newblock}".json
    

echo "Files written"
