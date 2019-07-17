#!/bin/bash

echo "Useage: ./_.sh <newblock> "
# for example, to make fence_log_birch loot, use ./sh fence_log_birch

# I just happened to make granite first
newblock=$1
itemdrop=$2

modid="absentbydesign"

folder="src/main/resources/data/${modid}/loot_tables/blocks"

# create the files

seed=fence_quartz

cp "${folder}"/"${seed}".json "${folder}"/"${newblock}".json

sed -i -e "s/minecraft:quartz/absentbydesign:${newblock}/g" "${folder}"/"${newblock}".json

echo "Files written"
