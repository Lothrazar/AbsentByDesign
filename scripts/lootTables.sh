#!/bin/bash




cd src/main/resources/data/absentbydesign/recipes/


for filename in *.json
do
    echo "${filename}"

    stuff=${filename::-5}
    echo "${stuff}"

    rm -f ../loot_tables/blocks/"${filename}"
    cp "../loot_tables/blocks/seed.json" ../loot_tables/blocks/"${filename}"
    sed -i -e "s/minecraft:seed/absentbydesign:${stuff}/g" "../loot_tables/blocks/${filename}"


done
