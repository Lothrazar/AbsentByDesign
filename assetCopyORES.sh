#!/bin/bash

echo "Useage: ./_.sh <destination_filename> <new_texture>"

dest=$1
texture=$2
newblock="stairs_${dest}"
seed="stairs_granite"
texture_old="stone_granite"
modid="absentbydesign"
folder="src/main/resources/assets/${modid}"

# create the files

cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json



cp "${folder}"/models/block/"${seed}".json "${folder}"/models/block/"${newblock}".json
cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json
cp "${folder}"/recipes/"${seed}".json "${folder}"/recipes/"${newblock}".json


# replace blockids

sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json

sed -i -e "s/${texture_old}/${texture}/g" "${folder}"/models/block/"${newblock}".json



echo "Four files written"
