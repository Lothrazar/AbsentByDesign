#!/bin/bash




cd src/main/resources/data/absentbydesign/loot_tables/blocks/

seed="slab_basalt"
dest=$1

filename="slab_${dest}.json"

echo $filename

 
sed -i -e "s/${seed}/slab_${dest}/g" "${filename}"

 
