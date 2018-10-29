#!/bin/bash

echo "Useage: ./_.sh <destination> <new_texture>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite

# I just happened to make granite first
dest=$1
texture=$2
newblock="stairs_${dest}"
original="granite"
seed="stairs_${original}"
texture_old="stone_${original}"
modid="absentbydesign"
folder="src/main/resources/assets/${modid}"

# create the files

cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json

cp "${folder}"/models/block/"${seed}".json "${folder}"/models/block/"${newblock}".json
cp "${folder}"/models/block/"${seed}"_outer.json "${folder}"/models/block/"${newblock}"_outer.json
cp "${folder}"/models/block/"${seed}"_inner.json "${folder}"/models/block/"${newblock}"_inner.json


cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json
cp "${folder}"/recipes/"${seed}".json "${folder}"/recipes/"${newblock}".json


# replace blockids

sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json

sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}".json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_outer.json
sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}"_inner.json

sed -i -e "s/${original}/${dest}/g" "${folder}"/blockstates/"${newblock}".json
    

echo "Four files written"
