#!/bin/bash

echo "Useage: ./_.sh <dest> <texture>"
# first is your file name that we put slab_ in front of
# second is filename of existing texture for example 'black_terracotta.png' exists
# for example, to make stairs_andesite block, use _.sh terracotta_black black_terracotta

dest=$1
texture=$2

newblock="slab_${dest}"
original="coarse_dirt"
seed="slab_${original}"

modid="absentbydesign"
folder="src/main/resources/assets/${modid}"

folderdata="src/main/resources/data/${modid}"
#
# create the files
#
cp "${folderdata}"/loot_tables/blocks/${seed}.json "${folderdata}"/loot_tables/blocks/"${newblock}".json
cp "${folderdata}"/recipes/${seed}.json "${folderdata}"/recipes/"${newblock}".json
cp "${folderdata}"/recipes/${seed}_stonecutting.json "${folderdata}"/recipes/"${newblock}_sc".json

cp "${folder}"/blockstates/"${seed}".json "${folder}"/blockstates/"${newblock}".json

cp "${folder}"/models/block/"${seed}".json "${folder}"/models/block/"${newblock}".json
cp "${folder}"/models/block/"${seed}"_upper.json "${folder}"/models/block/"${newblock}"_upper.json

cp "${folder}"/models/item/"${seed}".json "${folder}"/models/item/"${newblock}".json
rm -rf "${folder}"/models/item/"${newblock}"_double.json

#
# string replace 
#

sed -i -e "s/${seed}/${newblock}/g" "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${original}/${texture}/g" "${folderdata}"/recipes/"${newblock}".json
sed -i -e "s/${original}/${texture}/g" "${folderdata}_sc"/recipes/"${newblock}_sc".json

sed -i -e "s/${seed}/${newblock}/g" "${folderdata}"/loot_tables/blocks/"${newblock}".json

 
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}".json
sed -i -e "s/${original}/${texture}/g" "${folder}"/models/block/"${newblock}"_upper.json
 

sed -i -e "s/${seed}/${newblock}/g" "${folder}"/models/item/"${newblock}".json
 
sed -i -e "s/${original}/${dest}/g" "${folder}"/blockstates/"${newblock}".json
    
echo "  \"block.absentbydesign.${newblock}\":\"${newblock} Slab\",   "  
echo "  \"absentbydesign:${newblock}\",   "
echo "  to the blocks/slabs.json data tag"  

echo "Files written"
